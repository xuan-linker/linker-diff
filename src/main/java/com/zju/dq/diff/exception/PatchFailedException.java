package com.zju.dq.diff.exception;

/**
 * @author Linker
 * @date 6/18/2021 8:01 PM
 * @description：
 */
public class PatchFailedException extends DiffException {


    private static final long serialVersionUID = 1L;

    public PatchFailedException() {
    }

    public PatchFailedException(String message) {
        super(message);
    }
}
