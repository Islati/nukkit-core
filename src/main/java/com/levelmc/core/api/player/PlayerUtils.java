package com.levelmc.core.api.player;

import cn.nukkit.Player;
import cn.nukkit.item.Item;

public class PlayerUtils {

    public static Item getItemInHand(Player player, HandSlot slot) {
        Item item = null;

        if (slot == HandSlot.OFF_HAND) {
            item = player.getOffhandInventory().getItem(0);
        } else {
            item = player.getInventory().getItemInHand();
        }

        return item;
    }

    public static boolean handIsEmpty(Player player, HandSlot slot) {
        Item item = getItemInHand(player,slot);
        return item != null && item.getId() != Item.AIR;
    }
}
