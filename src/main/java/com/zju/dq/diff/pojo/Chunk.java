package com.zju.dq.diff.pojo;

import com.zju.dq.diff.exception.PatchFailedException;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * @author Linker
 * @date 6/18/2021 7:53 PM
 * @description：
 */
@Data
public class Chunk {
    private final int position;
    private List<?> lines;

    public Chunk(int position, List<?> lines) {
        this.position = position;
        this.lines = lines;
    }

    public Chunk(int position, Object[] lines) {
        this.position = position;
        this.lines = Arrays.asList(lines);
    }

    public void verify(List<?> target) throws PatchFailedException {
        if (last() > target.size()) {
            throw new PatchFailedException("Incorrect Chunk: the position of chunk > target size");
        }
        for (int i = 0; i < target.size(); i++) {
            if (!target.get(position + i).equals(lines.get(i))) {
                throw new PatchFailedException("Incorrect Chunk: the chunk content doesn't match the target");
            }

        }
    }

    /**
     * @return index of the last line of the chunk.
     */
    public int last() {
        return this.getPosition() + this.getLines().size() - 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        Chunk other = (Chunk) o;
        if (lines == null) {
            if (other.lines != null)
                return false;
        } else if (!lines.equals(other.lines))
            return false;
        if (position != other.position)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((lines == null) ? 0 : lines.hashCode());
        result = prime * result + position;
        result = prime * result + this.lines.size();
        return result;
    }

    @Override
    public String toString() {
        return "Chunk{" +
                "position=" + position +
                ", size: " + lines.size() +
                ", lines=" + lines +
                '}';
    }
}
