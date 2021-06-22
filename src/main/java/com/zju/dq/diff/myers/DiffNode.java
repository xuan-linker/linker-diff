package com.zju.dq.diff.myers;

/**
 * @author Linker
 * @date 6/19/2021 10:33 AM
 * @description：
 */
public class DiffNode extends PathNode {
    /**
     * Concatenates a new path node with an existing diffpath.
     *
     * @param i    The position in the original sequence for the new node.
     * @param j    The position in the revised sequence for the new node.
     * @param prev The previous node in the path.
     */
    protected DiffNode(int i, int j, PathNode prev) {
        super(i, j, (prev == null?null:prev.previousSnake()));
    }

    @Override
    public boolean isSnake() {
        return false;
    }
}
