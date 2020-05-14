package com.levelmc.core.api.gadgets;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.item.Item;
import cn.nukkit.plugin.PluginBase;

public class GadgetListener implements Listener {

    private PluginBase parent;
    private GadgetManager manager;

    public GadgetListener(PluginBase parent, GadgetManager manager) {
        this.parent = parent;
        this.manager = manager;
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerInteractEvent(PlayerInteractEvent e) {
        Item item = e.getItem();

        if (!item.hasCompoundTag()) {
            return;
        }

        Player player = e.getPlayer();
        PlayerInteractEvent.Action action = e.getAction();
        Block block = e.getBlock();

        if (!manager.isGadget(item)) {
            return;
        }

        Gadget gadget = manager.getGadget(item);
        gadget.onInteractEvent(e);

        parent.getLogger().info("Interaction Handled for Gadget ID " + gadget.id() + " from " + parent.getName());
    }
}
