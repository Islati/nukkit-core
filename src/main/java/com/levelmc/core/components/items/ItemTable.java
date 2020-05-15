package com.levelmc.core.components.items;

import com.levelmc.core.api.utils.ListUtils;
import com.levelmc.core.api.yml.Path;
import com.levelmc.core.api.yml.YamlConfig;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ItemTable extends YamlConfig {

    @Path("items")
    @Getter
    private List<LootItem> items = new ArrayList<>();

    public ItemTable() {

    }

    public ItemTable add(int itemId, ItemRarity rarity) {
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
