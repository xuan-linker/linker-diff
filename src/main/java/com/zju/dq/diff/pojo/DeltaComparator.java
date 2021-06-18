package com.zju.dq.diff.pojo;

import java.io.Serializable;
import java.util.Comparator;

/**
 * @author Linker
 * @date 6/18/2021 8:52 PM
 * @description：
 */
public class DeltaComparator implements Comparator<Delta>, Serializable {

    private static final long serialVersionUID = 1L;
    public static final Comparator<Delta> INSTANCE = new DeltaComparator();

    public DeltaComparator() {
    }

    @Override
    public int compare(final Delta a, final Delta b) {
        final int posA = a.getOriginal().getPosition();
        final int posB = b.getOriginal().getPosition();

        if (posA > posB) {
            return 1;
        } else if (posA < posB) {
            return -1;
        }
        return 0;
    }
}
