package com.levelmc.core.wizarding.spells;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.entity.Entity;
import cn.nukkit.level.Location;
import cn.nukkit.level.ParticleEffect;
import com.levelmc.core.api.chat.Chat;
import com.levelmc.core.api.effects.EffectUtils;
import com.levelmc.core.api.world.LocationUtils;
import com.levelmc.core.users.User;
import com.levelmc.core.wizarding.Spell;

import java.util.List;

public class FireISpell extends Spell {
    public FireISpell() {
        super("fire-i", "&cFire &bI");
    }

    @Override
    public boolean canPerform(User user) {
        return true;
    }

    @Override
    public void perform(User user) {
        Player player = user.getPlayer();

        Block targetBlock = player.getTargetBlock(getRange());
        List<Location> locations = LocationUtils.getCircle(targetBlock.getLocation(), getRadius());

        for (Location loc : locations) {
            EffectUtils.spawnParticles(ParticleEffect.MOBFLAME, loc, 5);
        }

        Entity[] entities = targetBlock.getLevel().getNearbyEntities(targetBlock);

        if (entities.length == 0) {
            Chat.actionMsg(player, "&cNo nearby mobs to attack");
            return;
        }

        for (Entity e : entities) {
            Chat.format(player, "&eEntity &b%s &ehas &c%s&e/&c%s&e health", e.getId(), e.getHealth(), e.getMaxHealth());
            e.setOnFire(2);
            e.setHealth(e.getHealth() - 1);
            EffectUtils.spawnParticles(ParticleEffect.LAVA_DRIP, e.getLocation(), 3);
        }
    }
}
