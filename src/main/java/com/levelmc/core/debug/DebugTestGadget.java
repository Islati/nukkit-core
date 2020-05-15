package com.levelmc.core.debug;

import cn.nukkit.Player;
import cn.nukkit.utils.TextFormat;
import com.levelmc.core.api.debug.DebugAction;

public class DebugTestGadget extends DebugAction {
    public DebugTestGadget() {
        super("test-gadget");
    }

    @Override
    public void onDebug(Player player, String[] args) {
        player.getInventory().addItem(TestGadget.getInstance().getItem());

        player.sendActionBar(TextFormat.colorize("&7Received Gadget: &b" + TestGadget.getInstance().id()));
    }
}
