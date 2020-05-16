package com.levelmc.core.components.loot;

import cn.nukkit.item.Item;
import com.levelmc.core.Core;
import com.levelmc.core.api.item.ItemBuilder;
import com.levelmc.core.components.loot.commands.ItemGenCommand;

/**
 * Manages the creation and in
 */
public class LootGenerator {

    private Core core;

    public LootGenerator(Core core) {
        this.core = core;
    }

    public void init() {
        core.getCommandHandler().registerCommands(
            new ItemGenCommand(this)
        );
    }

    public Item createWand(LootTable table, WandGenerationSettings settings) {
        LootItem selectedLoot = table.getLoot().selectItemByChance();

        String generatedName = table.getNameSettings().generateName();

        Item item = ItemBuilder.of(selectedLoot.getItemId()).name(selectedLoot.getRarity().attachPrefix(generatedName)).item();

        settings.applySettings(item);

        return item;
    }

}
