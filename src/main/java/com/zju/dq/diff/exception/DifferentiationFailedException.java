package com.zju.dq.diff.exception;

import com.zju.dq.diff.myers.DiffException;

/**
 * @author Linker
 * @date 6/18/2021 9:34 PM
 * @description：
 */
public class DifferentiationFailedException extends DiffException {
    private static final long serialVersionUID = 1L;

    public DifferentiationFailedException() {
    }

    public DifferentiationFailedException(String msg) {
        super(msg);
    }
}
