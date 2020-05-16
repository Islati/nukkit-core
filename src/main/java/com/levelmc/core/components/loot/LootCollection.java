package com.levelmc.core.components.loot;

import com.levelmc.core.api.utils.ListUtils;
import com.levelmc.core.api.yml.Path;
import com.levelmc.core.api.yml.YamlConfig;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class LootCollection extends YamlConfig {

    @Path("items")
    @Getter
    private List<LootItem> items = new ArrayList<>();

    public LootCollection() {

    }

    public LootCollection add(int itemId, Rarity rarity) {
        items.add(new LootItem(itemId, rarity));
        return this;
    }

    public LootItem getRandomElement() {
        return ListUtils.getRandom(items);
    }

    public LootItem selectItemByChance() {
        LootItem item = getRandomElement();

        while (!item.chanceCheck()) {
            item = getRandomElement();
        }

        return item;
    }

}
