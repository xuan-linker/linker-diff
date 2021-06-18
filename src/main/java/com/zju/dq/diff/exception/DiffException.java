package com.zju.dq.diff.exception;

/**
 * @author Linker
 * @date 6/18/2021 8:01 PM
 * @description：Base class for all exception emanating from this package.
 */
public class DiffException extends Exception {

    private static final long serialVersionUID = 1L;

    public DiffException() {
    }

    public DiffException(String message) {
        super(message);
    }
}
