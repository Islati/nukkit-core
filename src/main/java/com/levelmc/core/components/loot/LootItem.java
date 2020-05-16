package com.levelmc.core.components.loot;

import com.levelmc.core.api.Chanceable;
import com.levelmc.core.api.yml.Path;
import com.levelmc.core.api.yml.YamlConfig;
import lombok.Getter;

public class LootItem extends YamlConfig implements Chanceable {

    @Path("item-id")
    @Getter
    private int itemId;

    @Path("rarity")
    private String itemRarity = "common";

    public LootItem(int itemId, Rarity rarity) {
        this.itemId = itemId;
        this.itemRarity = rarity.name().toLowerCase();
    }

    public Rarity getRarity() {
        return Rarity.valueOf(itemRarity.toUpperCase());
    }

    @Override
    public int getChance() {
        return getRarity().getChance();
    }

    @Override
    public int getMeasure() {
        return getRarity().getMeasure();
    }
}
