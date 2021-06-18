package com.zju.dq.diff.myers;

/**
 * @author Linker
 * @date 6/18/2021 9:23 PM
 * @description：
 */
public abstract class PathNode {
    public final int i;
    public final int j;
    public final PathNode prev;

    /**
     * Concatenates a new path node with an existing diffpath
     *
     * @param i    The position in the original sequence for the new node.
     * @param j    The position in the revised sequence for the new node.
     * @param prev The previous node in the path.
     */
    protected PathNode(int i, int j, PathNode prev) {
        this.i = i;
        this.j = j;
        this.prev = prev;
    }

    public abstract boolean isSnake();

    /**
     * Is this a bootstrap node?
     *
     * @return
     */
    public boolean isBootstrap() {
        return i < 0 || j < 0;
    }
}
