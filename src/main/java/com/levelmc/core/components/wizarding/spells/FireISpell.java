package com.levelmc.core.components.wizarding.spells;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockID;
import cn.nukkit.entity.Entity;
import cn.nukkit.level.Location;
import cn.nukkit.level.particle.*;
import cn.nukkit.math.Vector3;
import com.levelmc.core.api.chat.Chat;
import com.levelmc.core.api.effects.EffectUtils;
import com.levelmc.core.api.utils.EntityUtils;
import com.levelmc.core.api.utils.LocationUtils;
import com.levelmc.core.api.utils.NumberUtil;
import com.levelmc.core.users.User;
import com.levelmc.core.components.wizarding.Spell;

import java.util.List;
import java.util.Set;

public class FireISpell extends Spell {
    public FireISpell() {
        super("fire-i", "&cFire &bI");

        radius(2);
    }

    @Override
    public boolean canPerform(User user) {
        return true;
    }

    @Override
    public void perform(User user) {
        Player player = user.getPlayer();

        Block targetBlock = player.getTargetBlock(getRange());

        if (targetBlock == null) {
            return;
        }

        if (targetBlock.getItemId() == BlockID.AIR) {
            for (int i = 0; i < 5; i++) {
                targetBlock.getLevel().addParticle(new SmokeParticle(targetBlock.getLocation()));
            }
            return;
        }

        Set<Entity> entities = EntityUtils.getNearbyEntities(targetBlock.getLocation(), getRadius());
        List<Location> locations = LocationUtils.getCircle(targetBlock.getLocation(), getRadius() > 2 ? (int) (getRadius() / 2) : getRadius());

        if (entities.size() == 0) {
            Chat.actionMsg(player, "&cNo nearby mobs to attack");
            for (int i = 0; i < 5; i++) {
                targetBlock.getLevel().addParticle(new SmokeParticle(targetBlock.getLocation().clone().add(NumberUtil.randomDouble(0.5, 1.1), NumberUtil.randomDouble(0.5, 1.1), NumberUtil.randomDouble(0.5, 1.1)), 3));
            }
            return;
        }

        //Effects in the circle
        for (Location loc : locations) {
            Location l = loc.clone().add(0, NumberUtil.randomDouble(0.4, 1.1), 0);

            EffectUtils.spawnParticles(new EntityFlameParticle((Vector3) l), l.getLevel(), 3);
        }

        for (Entity e : entities) {

            if (e instanceof Player) {
                Player hit = (Player) e;
                if (hit.getUniqueId().equals(player.getUniqueId())) {
                    continue;
                }
            }

            EffectUtils.spawnParticles(new EntityFlameParticle(e), e.getLevel(), 3);
            e.setOnFire(4);

            e.setHealth(e.getHealth() - NumberUtil.getRandomInRange(2, 3));
            Chat.format(player, "&eEntity &b%s &ehas &c%s&e/&c%s&e health", e.getId(), e.getHealth(), e.getMaxHealth());
            //todo implement health bar and damage indicators.
        }
    }
}
