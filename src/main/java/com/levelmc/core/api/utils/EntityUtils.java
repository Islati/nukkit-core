package com.levelmc.core.api.utils;

import cn.nukkit.entity.Entity;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import com.levelmc.core.Core;

import java.util.HashSet;
import java.util.Set;

public class EntityUtils {

    public static Set<Entity> getNearbyEntities(Location center, double radius) {
        Set<Entity> entity = new HashSet<>();

        Level level = center.getLevel();

        for (Entity e : level.getEntities()) {

            if (LocationUtils.isInRadius(center, e.getLocation(), radius)) {
                entity.add(e);
            }
        }

        return entity;
    }
}
