package com.levelmc.core.api;

import com.levelmc.core.api.utils.NumberUtil;

public interface Chanceable {
    /**
     * To be used with measure to determine whether or not it's chosen.
     * @return
     */
    int getChance();

    /**
     * Eg. 1/1000 chances. Measure is the latter number.
     * @return
     */
    int getMeasure();

    default boolean chanceCheck() {
        return NumberUtil.getRandomInRange(1,getMeasure()) <= getChance();
    }
}
