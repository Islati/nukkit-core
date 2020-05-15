package com.levelmc.core.api.utils;

import cn.nukkit.Server;
import cn.nukkit.event.Listener;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginManager;

public class PluginUtils {

    public static void registerListeners(Plugin plugin, Listener... listeners) {
        PluginManager manager = Server.getInstance().getPluginManager();

        for (Listener l : listeners) {
            manager.registerEvents(l, plugin);
        }
    }
}
