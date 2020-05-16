package com.levelmc.core.components.loot;

import lombok.Getter;

public enum Rarity {
    COMMON("&f", 33, 100),
    UNCOMMON("&7", 20, 100),
    RARE("&b", 5, 100),
    EPIC("&d", 5, 250),
    LEGENDARY("&6", 1, 1000);

    @Getter
    private int chance;
    @Getter
    private int measure;

    @Getter
    private String colorPrefix;

    Rarity(String colorPrefix, int chance, int measure) {
        this.colorPrefix = colorPrefix;
        this.chance = chance;
        this.measure = measure;
    }

    public String attachPrefix(String text) {
        return String.format("%s%s", getColorPrefix(), text);
    }



}
