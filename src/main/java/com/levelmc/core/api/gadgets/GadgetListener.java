package com.levelmc.core.api.gadgets;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerDropItemEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.item.Item;
import cn.nukkit.plugin.PluginBase;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.levelmc.core.api.chat.Chat;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class GadgetListener implements Listener {

    private PluginBase parent;
    private GadgetManager manager;

    private Cache<UUID, Long> gadgetCooldown = CacheBuilder.newBuilder().expireAfterWrite(500, TimeUnit.MILLISECONDS).build();

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

        if (gadgetCooldown.asMap().containsKey(player.getUniqueId())) {
            Chat.actionMsg(player, "&7... S l o w   d o w n ...");
            return;
        }

        Gadget gadget = manager.getGadget(item);
        gadget.onInteractEvent(e);
    }

    @EventHandler
    public void onPlayerDropItemEvent(PlayerDropItemEvent e) {
        Item item = e.getItem();
        Player player = e.getPlayer();

        if (!item.hasCompoundTag()) {
            return;
        }

        if (!manager.isGadget(item)) {
            return;
        }

        Gadget gadget = manager.getGadget(item);
        gadget.onPlayerDropItemEvent(e);
    }
}
