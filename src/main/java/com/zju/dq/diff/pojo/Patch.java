package com.zju.dq.diff.pojo;

import com.zju.dq.diff.exception.PatchFailedException;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * @author Linker
 * @date 6/18/2021 7:52 PM
 * @description：Describes the patch holding all deltas between the original and revised texts.
 */
public class Patch {
    private List<Delta> deltas = new LinkedList<>();


    /**
     * Apply this patch to the given target.
     *
     * @param target
     * @return the patched text
     * @throws PatchFailedException if can't apply patch
     */
    public List<?> applyTo(List<?> target) throws PatchFailedException {
        List<Object> result = new LinkedList<Object>(target);
        ListIterator<Delta> it = getDeltas().listIterator(deltas.size());
        while (it.hasPrevious()) {
            Delta delta = it.previous();
            delta.applyTo(result);
        }
        return result;
    }

    /**
     * Restore the text to original. Opposite to applyTo() mathod.
     *
     * @param target the given target
     * @return the restored text
     */
    public List<?> restore(List<?> target) {
        List<Object> result = new LinkedList<Object>(target);
        ListIterator<Delta> it = getDeltas().listIterator(deltas.size());
        while (it.hasPrevious()) {
            Delta delta = it.previous();
            delta.restore(result);
        }
        return result;
    }


    /**
     * Add the given delta to this patch
     *
     * @param delta the given delta
     */
    public void addDelta(Delta delta) {
        deltas.add(delta);
    }

    /**
     * @return the list of computed deltas
     */
    public List<Delta> getDeltas() {
        Collections.sort(deltas, DeltaComparator.INSTANCE);
        return deltas;
    }


}
