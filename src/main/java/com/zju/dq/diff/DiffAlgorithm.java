package com.zju.dq.diff;


import com.zju.dq.diff.pojo.Patch;

import java.util.List;

/**
 * @author Linker
 * @date 6/18/2021 7:50 PM
 * @description：Interface for compare two texts
 */
public interface DiffAlgorithm {

    /**
     * Compare the difference between the original sequence and the revised sequence
     *
     * @param original text
     * @param revised  text
     * @return the Patch {@link Patch Patch} object
     */
    public Patch diff(Object[] original, Object[] revised);


    /**
     * Compare the difference between the original sequence and the revised sequence
     *
     * @param original text
     * @param revised  text
     * @return the patch {@link Patch Patch} object
     */
    public Patch diff(List<?> original, List<?> revised);
}
