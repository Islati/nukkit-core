package com.levelmc.core.api.debug;

import com.levelmc.core.Core;
import lombok.Getter;

import java.util.TreeMap;

/**
 * Handles all debug actions.
 */
public class Debugger {
    private static Debugger instance = null;

    public static Debugger getInstance() {
        if (instance == null) {
            instance = new Debugger();
        }

        return instance;
    }

    @Getter
    private TreeMap<String, DebugAction> debugActions = new TreeMap<>();

    protected Debugger() {

    }

    public void registerDebugActions(DebugAction... actions) {
        for (DebugAction action : actions) {
            debugActions.put(action.getId(), action);
            action.onEnable();
            Core.getInstance().getLogger().info(String.format("Registered debug action %s", action.getId()));
        }
    }
}
