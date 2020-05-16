package com.levelmc.core.components.loot;

import com.levelmc.core.api.utils.ListUtils;
import com.levelmc.core.api.yml.Path;
import com.levelmc.core.api.yml.YamlConfig;
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

    public NameTable add(String value, Rarity rarity) {
        return add(value, rarity.getChance(), rarity.getMeasure());
    }

    public NameData selectViaChance() {
        if (names.isEmpty()) {
            return null;
        }

        if (names.size() == 1) {
            return names.get(0);
        }

        NameData data = ListUtils.getRandom(names);

        while (!data.chanceCheck()) {
            data = ListUtils.getRandom(names);
        }

        return data;

    }
}
