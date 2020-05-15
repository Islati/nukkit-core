package com.levelmc.core;

import cn.nukkit.plugin.PluginBase;
import com.levelmc.core.api.cmd.CommandHandler;
import com.levelmc.core.api.cmd.commands.DebuggerCommand;
import com.levelmc.core.api.debug.Debugger;
import com.levelmc.core.api.gadgets.GadgetManager;
import com.levelmc.core.api.utils.PluginUtils;
import com.levelmc.core.commands.TestCommand;
import com.levelmc.core.debug.DebugTestGadget;
import com.levelmc.core.debug.TestGadget;
import com.levelmc.core.users.UserManager;
import com.levelmc.core.wizarding.SpellManager;
import com.levelmc.core.wizarding.spells.FireISpell;
import lombok.Getter;

public class Core extends PluginBase {

    private static Core instance = null;

    public static Core getInstance() {
        return instance;
    }

    private CommandHandler commandHandler;

    @Getter
    private GadgetManager gadgetManager;

    @Getter
    private UserManager userManager;

    @Getter
    private SpellManager spellManager;

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
        gadgetManager.registerGadget(
                TestGadget.getInstance()
        );

        userManager = new UserManager();
        getLogger().info("Created UserManager");

        spellManager = new SpellManager(this);
        spellManager.registerSpells(
                new FireISpell()
        );

        /* Register the listeners */
        PluginUtils.registerListeners(this,
                gadgetManager.getGadgetListener(),
                userManager
        );

        getLogger().info("Listeners Registered: GadgetListener, UserManager (Listener)");

        commandHandler.registerCommands(
                new DebuggerCommand(),
                new TestCommand(this)
        );

        getLogger().info("Commands registered");

        Debugger.getInstance().registerDebugActions(
                new DebugTestGadget()
        );

        getServer().getLogger().info("Registered Debug Actions");
    }
}
