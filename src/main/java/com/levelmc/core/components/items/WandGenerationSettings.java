package com.levelmc.core.components.items;

import com.levelmc.core.api.yml.Path;
import com.levelmc.core.api.yml.YamlConfig;
import lombok.Getter;

public class WandGenerationSettings extends YamlConfig {

    @Path(value = "item-id")
    private int id;

    @Path(value = "wand-id")
    private String wandId;

    @Path(value = "weapon-type")
    private String weaponType = "wand";

    @Path(value = "range-bonus.min.base")
    @Getter
    private int rangeBonusBaseMin = 0;

    @Path(value = "range-bonus.max.base")
    @Getter
    private int rangeBonusBaseMax = 2;

    @Path(value = "range-bonus.min.increment-per-level")
    @Getter
    private int rangeBonusMinIncrementPerLevel = 1;

    @Path(value = "range-bonus.max.increment-per-level")
    @Getter
    private int rangeBonusMaxIncrementPerLevel = 1;

    public WandGenerationSettings() {

    }

}
