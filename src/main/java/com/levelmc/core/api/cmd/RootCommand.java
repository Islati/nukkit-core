package com.levelmc.core.api.cmd;

import cn.nukkit.command.PluginCommand;

import java.lang.reflect.Method;


public class RootCommand extends RegisteredCommand {
    private PluginCommand root;

    RootCommand(PluginCommand root, CommandHandler handler) {
        super(root.getLabel(), handler, null);
        this.root = root;
    }

    public PluginCommand getNukkitCommand() {
        return root;
    }

    @Override
    void set(Object methodInstance, Method method) {
        super.set(methodInstance, method);
        root.setDescription(getDescription());
        root.setUsage(getUsage());
    }
}
