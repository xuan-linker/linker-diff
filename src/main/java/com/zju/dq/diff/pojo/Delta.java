package com.zju.dq.diff.pojo;

import com.zju.dq.diff.exception.PatchFailedException;
import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 * @author Linker
 * @date 6/18/2021 7:53 PM
 * @description：
 */
@Data
public abstract class Delta {
    private Chunk original;
    private Chunk revised;

    public enum TYPE {
        CHANGE, DELETE, INSERT
    }

    public Delta(Chunk original, Chunk revised) {
        this.original = original;
        this.revised = revised;
    }

    public abstract void verify(List<?> target) throws PatchFailedException;

    public abstract void applyTo(List<Object> target) throws PatchFailedException;

    public abstract void restore(List<Object> target);

    public abstract TYPE getType();

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        Delta other = (Delta) o;
        if (original == null) {
            if (other.original != null)
                return false;
        } else if (!original.equals(other.original))
            return false;
        if (revised == null) {
            if (other.revised != null)
                return false;
        } else if (!revised.equals(other.revised))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((original == null) ? 0 : original.hashCode());
        result = prime * result + ((revised == null) ? 0 : revised.hashCode());
        return result;
    }
}
