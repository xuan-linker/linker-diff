package com.zju.dq.diff.myers;

import com.zju.dq.diff.ChangeDelta;
import com.zju.dq.diff.DeleteDelta;
import com.zju.dq.diff.DiffAlgorithm;
import com.zju.dq.diff.InsertDelta;
import com.zju.dq.diff.exception.DifferentiationFailedException;
import com.zju.dq.diff.pojo.Chunk;
import com.zju.dq.diff.pojo.Delta;
import com.zju.dq.diff.pojo.Patch;

import java.lang.reflect.Array;
import java.util.List;

/**
 * @author Linker
 * @date 6/18/2021 9:17 PM
 * @description：
 */
public class MyersDiff implements DiffAlgorithm {
    public MyersDiff() {
    }

    /**
     * @param original text
     * @param revised  text
     * @return empty diff if get the error while comparing
     */
    public Patch diff(List<?> original, List<?> revised) {
        return diff(original.toArray(), revised.toArray());
    }


    @Override
    public Patch diff(Object[] original, Object[] revised) {
      PathNode path;
        try {
            path = buildPath(original, revised);
            return buildRevision(path, original, revised);
        } catch (DifferentiationFailedException e) {
            e.printStackTrace();
        }
        return new Patch();
    }


    /**
     * The minimum diffPath for original and revised sequence.
     *
     * @param orig
     * @param rev
     * @return
     */
    public static PathNode buildPath(Object[] orig, Object[] rev) throws DifferentiationFailedException {
        if (orig == null)
            throw new IllegalArgumentException("original sequence is null");
        if (rev == null)
            throw new IllegalArgumentException("revised sequence is null");

        //these are local constants
        final int N = orig.length;
        final int M = rev.length;

        //orig's length + rev's length + 1
        final int MAX = N + M + 1;

        final int size = 1 + 2 * MAX;
        final int middle = size / 2;
        //diagonal 对角线
        final PathNode diagonal[] = new PathNode[size];
        //中心点作为原点
        diagonal[middle + 1] = new Snake(0, -1, null);
        for (int d = 0; d < MAX; d++) {
            for (int k = -d; k <= d; k += 2) {
                final int kmiddle = middle + k;
                final int kplus = kmiddle + 1;
                final int kminus = kmiddle - 1;
                PathNode prev = null;

                int i;
                if ((k == -d) || (k != d && diagonal[kminus].i < diagonal[kplus].i)) {
                    i = diagonal[kplus].i;
                    prev = diagonal[kplus];
                } else {
                    i = diagonal[kminus].i + 1;
                    prev = diagonal[kminus];
                }

                diagonal[kminus] = null; // no longer used

                int j = i - k;

                PathNode node = new DiffNode(i, j, prev);

                // orig and rev are zero-based
                // but the algorithm is one-based
                // that's why there's no +1 when indexing the sequences
                while (i < N && j < M && orig[i].equals(rev[j])) {
                    i++;
                    j++;
                }
                if (i > node.i)
                    node = new Snake(i, j, node);

                diagonal[kmiddle] = node;

                if (i >= N && j >= M) {
                    return diagonal[kmiddle];
                }
            }
            diagonal[middle + d - 1] = null;

        }
        // According to Myers, this cannot happen
        throw new DifferentiationFailedException("could not find a diff path");
    }

    /**
     * Constructs a {@link Patch} from a difference path.
     *
     * @param path The path.
     * @param orig The original sequence.
     * @param rev  The revised sequence.
     * @return A {@link Patch} script corresponding to the path.
     * @throws DifferentiationFailedException if a {@link Patch} could
     *                                        not be built from the given path.
     */
    public static Patch buildRevision(PathNode path, Object[] orig, Object[] rev) {
        if (path == null)
            throw new IllegalArgumentException("path is null");
        if (orig == null)
            throw new IllegalArgumentException("original sequence is null");
        if (rev == null)
            throw new IllegalArgumentException("revised sequence is null");

        Patch patch = new Patch();
        if (path.isSnake())
            path = path.prev;
        while (path != null && path.prev != null && path.prev.j >= 0) {
            if (path.isSnake())
                throw new IllegalStateException("bad diffpath: found snake when looking for diff");
            int i = path.i;
            int j = path.j;

            path = path.prev;
            int ianchor = path.i;
            int janchor = path.j;

            Chunk original = new Chunk(ianchor, copyOfRange(orig, ianchor, i));
            Chunk revised = new Chunk(janchor, copyOfRange(rev, janchor, j));
            Delta delta = null;
            if (original.getLines().size() == 0 && revised.getLines().size() != 0) {
                delta = new InsertDelta(original, revised);
            } else if (original.getLines().size() > 0 && revised.getLines().size() == 0) {
                delta = new DeleteDelta(original, revised);
            } else {
                delta = new ChangeDelta(original, revised);
            }

            patch.addDelta(delta);
            if (path.isSnake())
                path = path.prev;
        }
        return patch;
    }

    /**
     * Copied here from JDK 1.6
     */

    @SuppressWarnings("unchecked")
    public static <T> T[] copyOfRange(T[] original, int from, int to) {
        return copyOfRange(original, from, to, (Class<T[]>) original.getClass());
    }

    /**
     * Copied here from JDK 1.6
     */
    @SuppressWarnings("unchecked")
    public static <T, U> T[] copyOfRange(U[] original, int from, int to,
                                         Class<? extends T[]> newType) {
        int newLength = to - from;
        if (newLength < 0)
            throw new IllegalArgumentException(from + " > " + to);
        T[] copy = ((Object) newType == (Object) Object[].class) ? (T[]) new Object[newLength]
                : (T[]) Array.newInstance(newType.getComponentType(), newLength);
        System.arraycopy(original, from, copy, 0, Math.min(original.length - from, newLength));
        return copy;
    }

}
