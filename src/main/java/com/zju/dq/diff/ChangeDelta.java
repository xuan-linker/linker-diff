package com.zju.dq.diff;

import com.zju.dq.diff.exception.PatchFailedException;
import com.zju.dq.diff.pojo.Chunk;
import com.zju.dq.diff.pojo.Delta;

import java.util.List;

/**
 * @author Linker
 * @date 6/19/2021 11:57 AM
 * @description：
 */
public class ChangeDelta extends Delta {

    /**
     * {@inheritDoc}
     */
    public ChangeDelta(Chunk original, Chunk revised) {
        super(original, revised);
    }

    /**
     * {@inheritDoc}
     *
     * @throws PatchFailedException
     */
    @Override
    public void applyTo(List<Object> target) throws PatchFailedException {
        verify(target);
        int position = getOriginal().getPosition();
        int size = getOriginal().getLines().size();
        for (int i = 0; i < size; i++) {
            target.remove(position);
        }
        int i = 0;
        for (Object line : getRevised().getLines()) {
            target.add(position + i, line);
            i++;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void restore(List<Object> target) {
        int position = getRevised().getPosition();
        int size = getRevised().getLines().size();
        for (int i = 0; i < size; i++) {
            target.remove(position);
        }
        int i = 0;
        for (Object line : getOriginal().getLines()) {
            target.add(position + i, line);
            i++;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void verify(List<?> target) throws PatchFailedException {
        getOriginal().verify(target);
        if (getOriginal().getPosition() > target.size()) {
            throw new PatchFailedException("Incorrect patch for delta: "
                    + "delta original position > target size");
        }
    }

    @Override
    public String toString() {
        return "[ChangeDelta, position: " + getOriginal().getPosition() + ", lines: "
                + getOriginal().getLines() + " to " + getRevised().getLines() + "]";
    }

    @Override
    public TYPE getType() {
        return Delta.TYPE.CHANGE;
    }
}
