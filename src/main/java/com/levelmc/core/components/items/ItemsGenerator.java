package com.levelmc.core.components.items;

import cn.nukkit.item.Item;
import com.levelmc.core.Core;
import com.levelmc.core.components.items.name.NameSettings;
import com.levelmc.core.components.items.name.NameTable;

/**
 * Manages the creation and in
 */
public class ItemsGenerator {

    private Core core;

    public ItemsGenerator(Core core) {
        this.core = core;
    }


    public Item createWand(ItemTable itemTable, NameSettings nameSettings, WandGenerationSettings settings) {
        LootItem selectedLoot = itemTable.selectItemByChance();




    }

}
