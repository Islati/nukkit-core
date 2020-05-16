package com.levelmc.core.components.loot;

import cn.nukkit.item.Item;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.utils.TextFormat;
import com.levelmc.core.api.utils.NumberUtil;
import com.levelmc.core.api.yml.Path;
import com.levelmc.core.api.yml.YamlConfig;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

public class WandGenerationSettings extends YamlConfig {

    @Path(value = "wand-id")
    private String wandId;

    @Path(value = "weapon-type")
    private String weaponType = "wand";

    @Path("level.min")
    @Getter
    private int levelMin = 1;

    @Path("level.max")
    @Getter
    private int levelMax = 2;

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

    public WandGenerationSettings(String wandId) {
        this.wandId = wandId;
    }

    public void applySettings(Item item) {
        CompoundTag tag;
        if (!item.hasCompoundTag()) {
            tag = new CompoundTag();
        } else {
            tag = item.getNamedTag();
        }

        int level = NumberUtil.getRandomInRange(levelMin, levelMax);

        int rangeBonusPerLevel = 0;

        if (rangeBonusMinIncrementPerLevel > 0 && rangeBonusMaxIncrementPerLevel >= rangeBonusMinIncrementPerLevel) {
            if (rangeBonusMinIncrementPerLevel == rangeBonusMaxIncrementPerLevel) {
                rangeBonusPerLevel = rangeBonusMaxIncrementPerLevel;
            } else {
                rangeBonusPerLevel = NumberUtil.getRandomInRange(rangeBonusMinIncrementPerLevel, rangeBonusMaxIncrementPerLevel);
            }
        }

        int rangeBonus = NumberUtil.getRandomInRange(rangeBonusBaseMin, rangeBonusBaseMax) + (level * rangeBonusPerLevel);

        tag.putString("wandId", wandId);
        tag.putString("weaponType", weaponType);
        tag.putInt("rangeBonus", rangeBonus);
        tag.putInt("level", level);

        item.setCompoundTag(tag);

        item.setLore(
                TextFormat.colorize(String.format("&d&lLvl &l&e%s", level)),
                TextFormat.colorize(String.format("&b -- &o%s&r&b -- ", StringUtils.capitalize(weaponType))),
                "",
                TextFormat.colorize(String.format("&a&l+%s Range&7", rangeBonus))
        );

    }

}
