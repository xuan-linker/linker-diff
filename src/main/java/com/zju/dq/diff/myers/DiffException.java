package com.zju.dq.diff.myers;

/**
 * @author Linker
 * @date 6/18/2021 9:34 PM
 * @description：
 */
public class DiffException extends Exception {

    private static final long serialVersionUID = 1L;

    public DiffException() {
    }

    public DiffException(String msg) {
        super(msg);
    }
}
