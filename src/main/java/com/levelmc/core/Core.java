package com.levelmc.core;

import cn.nukkit.plugin.PluginBase;
import com.levelmc.core.api.cmd.CommandHandler;
import com.levelmc.core.api.cmd.commands.DebuggerCommand;
import com.levelmc.core.commands.TestCommand;

public class Core extends PluginBase {

    private static Core instance = null;

    public static Core getInstance() {
        return instance;
    }

    private CommandHandler commands;

    @Override
    public void onLoad() {
        super.onLoad();
        instance = this;

        commands = new CommandHandler(this);
        getLogger().info("Created Command Handler");

        commands.registerCommands(
                new DebuggerCommand(),
                new TestCommand(this)
        );
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }
}
