package com.levelmc.core;

import cn.nukkit.plugin.PluginBase;
import com.levelmc.core.api.cmd.CommandHandler;
import com.levelmc.core.api.cmd.commands.DebuggerCommand;
import com.levelmc.core.api.debug.Debugger;
import com.levelmc.core.api.gadgets.GadgetManager;
import com.levelmc.core.commands.TestCommand;
import com.levelmc.core.debug.DebugTestGadget;
import com.levelmc.core.debug.TestGadget;
import lombok.Getter;

public class Core extends PluginBase {

    private static Core instance = null;

    public static Core getInstance() {
        return instance;
    }

    private CommandHandler commandHandler;

    @Getter
    private GadgetManager gadgetManager;

    @Override
    public void onLoad() {
        super.onLoad();
        instance = this;
    }

    @Override
    public void onEnable() {
        super.onEnable();


        commandHandler = new CommandHandler(this);
        getLogger().info("Created Command Handler");

        gadgetManager = new GadgetManager(this);
        getServer().getPluginManager().registerEvents(gadgetManager.getGadgetListener(), this);
        gadgetManager.registerGadget(
                TestGadget.getInstance()
        );

        commandHandler.registerCommands(
                new DebuggerCommand(),
                new TestCommand(this)
        );

        Debugger.getInstance().registerDebugActions(
                new DebugTestGadget()
        );
        getServer().getLogger().info("Registered Debug Actions");
    }
}
