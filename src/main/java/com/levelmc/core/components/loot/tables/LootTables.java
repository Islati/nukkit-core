package com.levelmc.core.components.loot.tables;

import cn.nukkit.item.ItemID;
import com.levelmc.core.components.loot.LootTable;
import com.levelmc.core.components.loot.NameSlot;
import com.levelmc.core.components.loot.Rarity;
import lombok.Getter;

public class LootTables {

    private static LootTables instance = null;

    public static LootTables getInstance() {
        if (instance == null) {
            instance = new LootTables();
        }

        return instance;
    }

    @Getter
    private LootTable defaultLootTable = new LootTable();

    protected LootTables() {

        defaultLootTable.getLoot()
                .add(ItemID.STICK, Rarity.COMMON);

        defaultLootTable.getNameSettings()
                .add(NameSlot.BASE,"Wand",100,100)
                .add(NameSlot.PREFIX,"",95,100)
                .add(NameSlot.PREFIX,"",95,100)
                .add(NameSlot.PREFIX,"",95,100)
                .add(NameSlot.PREFIX,"",95,100)
                .add(NameSlot.PREFIX,"",95,100)
                .add(NameSlot.PREFIX,"",95,100)
                .add(NameSlot.PREFIX,"",95,100)
                .add(NameSlot.PREFIX,"",95,100)
                .add(NameSlot.PREFIX,"Weak",Rarity.COMMON)
                .add(NameSlot.PREFIX,"Beginners",Rarity.COMMON)
                .add(NameSlot.PREFIX,"New",Rarity.COMMON)
                .add(NameSlot.PREFIX,"Basic",Rarity.COMMON)
                .add(NameSlot.PREFIX,"Improved",Rarity.UNCOMMON)
                .add(NameSlot.PREFIX,"Enhanced",Rarity.UNCOMMON)
                .add(NameSlot.SUFFIX,"of Mages",Rarity.UNCOMMON)
                .add(NameSlot.SUFFIX,"of Spell Casting",Rarity.UNCOMMON)
                .add(NameSlot.SUFFIX,"of the Wizard",Rarity.UNCOMMON)
                .add(NameSlot.SUFFIX,"of Storms",Rarity.RARE)
                .add(NameSlot.SUFFIX,"of Imps",Rarity.RARE);
    }


}
