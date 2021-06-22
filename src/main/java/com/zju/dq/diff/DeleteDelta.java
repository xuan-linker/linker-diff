package com.zju.dq.diff;

import com.zju.dq.diff.exception.PatchFailedException;
import com.zju.dq.diff.pojo.Chunk;
import com.zju.dq.diff.pojo.Delta;

import java.util.List;

/**
 * @author Linker
 * @date 6/19/2021 11:56 AM
 * @description：
 */
public class DeleteDelta extends Delta {

    /**
     * {@inheritDoc}
     */
    public DeleteDelta(Chunk original, Chunk revised) {
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
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void restore(List<Object> target) {
        int position = this.getRevised().getPosition();
        List<?> lines = this.getOriginal().getLines();
        for (int i = 0; i < lines.size(); i++) {
            target.add(position + i, lines.get(i));
        }
    }

    @Override
    public TYPE getType() {
        return Delta.TYPE.DELETE;
    }

    @Override
    public void verify(List<?> target) throws PatchFailedException {
        getOriginal().verify(target);
    }

    @Override
    public String toString() {
        return "[DeleteDelta, position: " + getOriginal().getPosition() + ", lines: "
                + getOriginal().getLines() + "]";
    }
}
