package com.levelmc.core;

import cn.nukkit.plugin.PluginBase;
import com.levelmc.core.api.cmd.CommandHandler;
import com.levelmc.core.api.cmd.commands.DebuggerCommand;
import com.levelmc.core.api.debug.Debugger;
import com.levelmc.core.api.forms.FormListener;
import com.levelmc.core.api.gadgets.GadgetManager;
import com.levelmc.core.api.utils.PluginUtils;
import com.levelmc.core.api.yml.InvalidConfigurationException;
import com.levelmc.core.commands.TestCommand;
import com.levelmc.core.components.loot.LootGenerator;
import com.levelmc.core.components.wizarding.WizardingComponent;
import com.levelmc.core.config.CoreConfig;
import com.levelmc.core.debug.DebugTestGadget;
import com.levelmc.core.debug.TestGadget;
import com.levelmc.core.users.UserManager;
import lombok.Getter;

import java.io.File;

public class Core extends PluginBase {

    private static Core instance = null;

    public static Core getInstance() {
        return instance;
    }

    @Getter
    private CommandHandler commandHandler;

    @Getter
    private GadgetManager gadgetManager;

    @Getter
    private UserManager userManager;

    @Getter
    private WizardingComponent wizardingComponent = null;

    @Getter
    private LootGenerator lootGenerator = null;

    private CoreConfig config;

    private String pathCoreConfigLoc = "plugins/LevelCore/config.yml";

    @Override
    public void onLoad() {
        super.onLoad();
        instance = this;

        config = new CoreConfig(this);

        try {
            config.init(new File(pathCoreConfigLoc));
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
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

        wizardingComponent = new WizardingComponent(this);
        wizardingComponent.init();
        getLogger().info("Wizarding component initialized");

        lootGenerator = new LootGenerator(this);
        lootGenerator.init();
        getLogger().info("LootGenerator initialized");

        /* Register the listeners */
        PluginUtils.registerListeners(this,
                gadgetManager.getGadgetListener(),
                userManager,
                new FormListener(this)
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
