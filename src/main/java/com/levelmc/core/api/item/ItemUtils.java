package com.levelmc.core.api.item;

import cn.nukkit.block.BlockID;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemID;
import org.apache.commons.lang3.StringUtils;
import org.joor.Reflect;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ItemUtils {
    /* TODO: Check for custom entities inside of registry, along with custom items and forth when supported */

    private static final Map<String, Integer> nameToIdMap = new HashMap<>();
    private static final Map<Integer, String> idToNameMap = new HashMap<>();

    private static final Set<Integer> itemIds = new HashSet<>();
    private static final Set<Integer> blockIds = new HashSet<>();

    static {
        for (Map.Entry<String, Reflect> fieldEntry : Reflect.onClass(ItemID.class).fields().entrySet()) {
            String name = fieldEntry.getKey();
            int id = fieldEntry.getValue().get();

            nameToIdMap.put(name, id);
            idToNameMap.put(id, name);

            itemIds.add(id);
        }

        for (Map.Entry<String, Reflect> fieldEntry : Reflect.onClass(BlockID.class).fields().entrySet()) {
            String name = fieldEntry.getKey();
            int id = fieldEntry.getValue().get();

            nameToIdMap.put(name, id);
            idToNameMap.put(id, name);

            blockIds.add(id);
        }
    }

    /**
     * Create an item by its name or parsed integer id value
     * @param val name or id of the item
     * @return created item.
     */
    public static Item get(String val) {
        if (StringUtils.isNumeric(val)) {
            return Item.get(Integer.parseInt(val));
        }

        return Item.get(nameToIdMap.get(val));
    }

    public static Item get(int val) {
        return Item.get(val);
    }
}
