package com.levelmc.core.components.wizarding;

import lombok.Getter;

public enum SpellSlot {
    RIGHT_CLICK("right_click"),
    CROUCH_RIGHT_CLICK("crouch_right_click"),
    LEFT_CLICK("left_click"),
    CROUCH_LEFT_CLICK("crouch_left_click");

    @Getter
    private String slotName;

    SpellSlot(String name) {
        this.slotName = name;
    }

}
