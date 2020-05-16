package com.levelmc.core.components.wizarding.commands;

import cn.nukkit.Player;
import com.levelmc.core.api.cmd.Command;
import com.levelmc.core.api.cmd.commands.CommandInfo;
import com.levelmc.core.components.wizarding.guis.MagicCategoryGUI;

@CommandInfo(name = "spells",aliases = {"sp"})
public class SpellsCommand {

    public SpellsCommand() {

    }

    @Command(identifier = "spells")
    public void onSpellsCommand(Player player) {
        player.showFormWindow(new MagicCategoryGUI());
    }
}
