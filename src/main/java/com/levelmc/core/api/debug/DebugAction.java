package com.levelmc.core.api.debug;

import cn.nukkit.Player;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class DebugAction {

    @Getter
    private final String id;

    public abstract void onDebug(Player player, String[] args);

    public void onEnable() {}

    public void onDisable() {}
}
