package com.levelmc.core.components.items.name;

import com.levelmc.core.api.yml.Path;
import com.levelmc.core.api.yml.YamlConfig;
import com.levelmc.core.components.items.ItemRarity;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class NameTable extends YamlConfig {

    @Path("names")
    @Getter
    private List<NameData> names = new ArrayList<>();

    public NameTable() {

    }

    public NameTable add(String value, int chance, int measure) {
        names.add(new NameData(value, chance, measure));
        return this;
    }

    public NameTable add(String value, ItemRarity rarity) {
        return add(value, rarity.getChance(), rarity.getMeasure());
    }
}
