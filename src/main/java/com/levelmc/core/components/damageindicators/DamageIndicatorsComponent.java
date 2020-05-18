package com.levelmc.core.components.damageindicators;

import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.scheduler.NukkitRunnable;
import com.levelmc.core.Core;
import com.levelmc.core.api.effects.HoveringTextEffect;
import com.levelmc.core.api.utils.TimeType;
import com.levelmc.core.api.utils.TimeUtils;

public class DamageIndicatorsComponent implements Listener {

    private Core parent;

    public DamageIndicatorsComponent(Core core) {
        this.parent = core;
    }

    public void init() {
        parent.getServer().getPluginManager().registerEvents(this, parent);
        parent.getLogger().info("Registered listeners inside DamageIndicatorsComponent");
    }

    @EventHandler
    public void onEntityDamageListener(EntityDamageEvent e) {
        Entity entity = e.getEntity();


        float damage = e.getDamage();

        HoveringTextEffect effect = HoveringTextEffect.attach(entity, String.format("&c- %s", damage));
        new NukkitRunnable() {
            @Override
            public void run() {
                Core.getInstance().getLogger().info("Cleaning up damage indicator (Entity ID " + effect.getId() + ")");
                effect.getLevel().removeEntity(effect);
            }
        }.runTaskLater(parent, Math.toIntExact(TimeUtils.getTimeInTicks(2, TimeType.SECOND)));
    }

}
