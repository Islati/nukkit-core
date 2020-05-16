package com.levelmc.core.components.loot.commands;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import com.levelmc.core.api.chat.Chat;
import com.levelmc.core.api.cmd.Command;
import com.levelmc.core.api.cmd.commands.CommandInfo;
import com.levelmc.core.components.loot.LootGenerator;
import com.levelmc.core.components.loot.WandGenerationSettings;
import com.levelmc.core.components.loot.tables.LootTables;

@CommandInfo(name="ig",aliases = {"itemgen"})
public class ItemGenCommand {

    private LootGenerator parent;
    public ItemGenCommand(LootGenerator parent) {
        this.parent = parent;
    }

    @Command(identifier = "ig",permissions = "levelmc.admin")
    public void onItemGenCommand(Player player) {
        Item wand = parent.createWand(LootTables.getInstance().getDefaultLootTable(),new WandGenerationSettings("basicWand"));
        player.getInventory().addItem(wand);

        Chat.actionMsg(player,wand.getCustomName());
    }

}
