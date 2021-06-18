package com.zju.dq.diff.myers;

import com.zju.dq.diff.DiffAlgorithm;
import com.zju.dq.diff.pojo.Patch;

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
        return null;
    }


}
