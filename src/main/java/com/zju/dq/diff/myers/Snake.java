package com.zju.dq.diff.myers;

/**
 * @author Linker
 * @date 6/19/2021 10:31 AM
 * @description：
 */
public class Snake extends PathNode {

    /**
     * Concatenates a new path node with an existing diffpath.
     *
     * @param i    The position in the original sequence for the new node.
     * @param j    The position in the revised sequence for the new node.
     * @param prev The previous node in the path.
     */
    protected Snake(int i, int j, PathNode prev) {
        super(i, j, prev);
    }

    @Override
    public boolean isSnake() {
        return true;
    }
}
