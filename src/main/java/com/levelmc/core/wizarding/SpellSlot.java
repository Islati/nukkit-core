package com.levelmc.core.wizarding;

import lombok.Getter;

public enum SpellSlot {
    RIGHT_CLICK("right_click"),
    SHIFT_RIGHT_CLICK("shift_right_click"),
    LEFT_CLICK("left_click"),
    SHIFT_LEFT_CLICK("shift_left_click");

    @Getter
    private String slotName;

    SpellSlot(String name) {
        this.slotName = name;
    }

}
