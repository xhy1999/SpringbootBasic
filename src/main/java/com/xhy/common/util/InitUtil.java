package com.xhy.common.util;

import java.util.Collection;

/**
 * @author xuehy
 * @since 2022/1/11
 */
public class InitUtil {

    public static final int getMapInitialCapacity(Collection<?> coll) {
        return getMapInitialCapacity(coll.size());
    }

    public static final int getMapInitialCapacity(int size) {
        return (int) (size / .75f) + 1;
    }

}
