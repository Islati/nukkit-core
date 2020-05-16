package com.levelmc.core.components.wizarding;

import lombok.Getter;

public enum MagicType {
    FIRE("&cFire"),
    ICE("&bIce"),
    DEATH("&0Death"),
    LIGHTNING("&eLightning"),
    AURA("&dAura");

    @Getter
    private String displayName = "";

    MagicType(String buttonName) {
        this.displayName = buttonName;
    }
}
