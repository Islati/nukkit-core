package com.levelmc.core.components.loot;

import com.levelmc.core.api.yml.Path;
import lombok.Getter;

public class LootTable {

    @Path("loot")
    @Getter
    private LootCollection loot = new LootCollection();

    @Path("names")
    @Getter
    private NameSettings nameSettings = new NameSettings();

    public LootTable() {

    }


}
