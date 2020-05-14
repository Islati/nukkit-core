package com.levelmc.core.api.gadgets;

import cn.nukkit.Player;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.item.Item;

/**
 * Design blueprint for the Item Gadgets
 */
public interface Gadget {

    /**
     * @return unique id for the gadget
     */
    String id();

    Item getItem();

    void onInteractEvent(PlayerInteractEvent e);

    void onEntityDamageByEntity(EntityDamageByEntityEvent e);

}
