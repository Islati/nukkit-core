package com.levelmc.core.components.items.name;

import com.levelmc.core.api.yml.Path;
import com.levelmc.core.api.yml.YamlConfig;
import com.levelmc.core.components.items.ItemRarity;
import lombok.Getter;

public class NameSettings extends YamlConfig {

    @Path("prefix")
    @Getter
    private NameTable prefixTable = new NameTable();

    @Path("base")
    @Getter
    private NameTable baseTable = new NameTable();

    @Path("suffix")
    @Getter
    private NameTable suffixTable = new NameTable();

    public NameSettings() {

    }

    public NameSettings add(NameSlot slot, String name, int chance, int measure) {

        NameTable table = null;

        switch (slot) {
            case PREFIX:
                table = prefixTable;
                break;
            case BASE:
                table = baseTable;
                break;
            case SUFFIX:
                table = suffixTable;
                break;
        }

        table.add(name, chance, measure);

        return this;
    }

    public NameSettings add(NameSlot slot, String name, ItemRarity rarity) {
        return add(slot, name, rarity.getChance(), rarity.getMeasure());
    }

    public String generateName() {
        //todo return generated name.

        return "";
    }
}
