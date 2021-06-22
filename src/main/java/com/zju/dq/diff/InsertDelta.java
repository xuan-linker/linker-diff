package com.zju.dq.diff;

import com.zju.dq.diff.exception.PatchFailedException;
import com.zju.dq.diff.pojo.Chunk;
import com.zju.dq.diff.pojo.Delta;

import java.util.List;

/**
 * @author Linker
 * @date 6/19/2021 11:54 AM
 * @description：
 */
public class InsertDelta extends Delta {

    /**
     * {@inheritDoc}
     */
    public InsertDelta(Chunk original, Chunk revised) {
        super(original, revised);
    }

    /**
     * {@inheritDoc}
     * @throws PatchFailedException
     */
    @Override
    public void applyTo(List<Object> target) throws PatchFailedException {
        verify(target);
        int position = this.getOriginal().getPosition();
        List<?> lines = this.getRevised().getLines();
        for (int i = 0; i < lines.size(); i++) {
            target.add(position + i, lines.get(i));
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
    }

    @Override
    public void verify(List<?> target) throws PatchFailedException {
        if (getOriginal().getPosition() > target.size()) {
            throw new PatchFailedException("Incorrect patch for delta: "
                    + "delta original position > target size");
        }

    }

    public TYPE getType() {
        return Delta.TYPE.INSERT;
    }

    @Override
    public String toString() {
        return "[InsertDelta, position: " + getOriginal().getPosition() + ", lines: "
                + getRevised().getLines() + "]";
    }
}
