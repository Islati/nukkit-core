package com.levelmc.core.api.utils;

import cn.nukkit.block.Block;
import cn.nukkit.level.Location;
import cn.nukkit.scheduler.NukkitRunnable;
import com.levelmc.core.Core;

public class BlockUtils {


    public static void set(Location loc, Block block) {
        loc.getLevel().setBlock(loc, block);
        loc.getLevel().updateAround(loc);
    }

    public static void setTemporarily(Location loc, Block block, int stayTimeSeconds) {

        Block prevType = loc.getLevelBlock();

        set(loc, block);

        new NukkitRunnable() {
            @Override
            public void run() {
                set(loc, prevType);
            }
        }.runTaskLater(Core.getInstance(), (int) TimeUtils.getTimeInTicks(stayTimeSeconds, TimeType.SECOND));
    }
}
