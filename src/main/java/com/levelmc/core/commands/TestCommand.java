package com.levelmc.core.commands;

import cn.nukkit.Player;
import cn.nukkit.utils.TextFormat;
import com.levelmc.core.Core;
import com.levelmc.core.api.cmd.Arg;
import com.levelmc.core.api.cmd.Command;
import com.levelmc.core.api.cmd.commands.CommandInfo;

@CommandInfo(name = "test", description = "Test Command")
public class TestCommand {

    public TestCommand(Core core) {
        core.getLogger().info("TestCommand initialized");
    }

    @Command(identifier = "test")
    public void onTestCommand(Player player, @Arg(name = "arg") String arg) {
        player.sendActionBar(TextFormat.colorize(arg));
    }
}
