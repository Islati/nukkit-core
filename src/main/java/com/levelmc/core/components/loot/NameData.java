package com.levelmc.core.components.loot;

import com.levelmc.core.api.Chanceable;
import com.levelmc.core.api.yml.Path;
import com.levelmc.core.api.yml.YamlConfig;
import lombok.Getter;

public class NameData extends YamlConfig implements Chanceable {

    @Path(value = "value")
    @Getter
    private String value = "";

    @Path(value = "chance")
    @Getter
    private int chance;

    @Path(value = "measure")
    @Getter
    private int measure;

    public NameData(String value, int chance, int measure) {
        this.value = value;
        this.chance = chance;
        this.measure = measure;
    }

    @Override
    public int getChance() {
        return chance;
    }

    @Override
    public int getMeasure() {
        return measure;
    }
}
