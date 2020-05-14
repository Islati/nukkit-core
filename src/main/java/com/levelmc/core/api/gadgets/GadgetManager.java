package com.levelmc.core.api.gadgets;

import cn.nukkit.item.Item;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.plugin.PluginBase;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Handles registration of gadgets and such
 */
public class GadgetManager {

    private Map<String, Gadget> gadgetIdsMap = new HashMap<>();

    private PluginBase base;

    @Getter
    private GadgetListener gadgetListener;

    public GadgetManager(PluginBase base) {
        this.base = base;
        this.gadgetListener = new GadgetListener(base, this);
    }

    public void registerGadget(Gadget... gadgets) {
        for (Gadget g : gadgets) {
            gadgetIdsMap.put(g.id(), g);
        }
    }

    public boolean isGadget(Item item) {
        if (!item.hasCompoundTag()) {
            return false;
        }

        CompoundTag tag = item.getNamedTag();
        if (!tag.contains("GadgetId")) {
            return false;
        }

        String id = tag.getString("GadgetId");
        return gadgetIdsMap.containsKey(id);
    }

    public Gadget getGadget(Item item) {
        if (!isGadget(item)) {
            return null;
        }

        CompoundTag tag = item.getNamedTag();
        if (!tag.contains("GadgetId")) {
            return null;
        }

        String id = tag.getString("GadgetId");
        return gadgetIdsMap.get(id);
    }
}
