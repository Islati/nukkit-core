package com.levelmc.core.api.gadgets;

import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.item.Item;
import cn.nukkit.nbt.tag.CompoundTag;

public class BaseGadget implements Gadget {
    private String id;
    private Item item;

    public BaseGadget(String id, Item item) {
        this.id = id;
        this.item = item.clone();

        updateNbtId();
    }

    protected void updateNbtId() {
        CompoundTag tag = item.getNamedTag();
        tag.putString("GadgetId", id);
        this.item.setCompoundTag(tag);
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public Item getItem() {
        return this.item;
    }

    @Override
    public void onInteractEvent(PlayerInteractEvent e) {

    }

    @Override
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {

    }
}
