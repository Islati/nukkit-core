package com.levelmc.core.api.effects;

import cn.nukkit.Player;
import cn.nukkit.entity.item.EntityFirework;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemFirework;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import cn.nukkit.level.ParticleEffect;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.level.particle.Particle;
import cn.nukkit.nbt.NBTIO;
import cn.nukkit.nbt.tag.*;
import cn.nukkit.utils.DyeColor;

import java.util.Random;

public class EffectUtils {

    public static void spawnParticles(Particle effect, Level lvl, int amt) {
        for (int i = 0; i < amt; i++) {
            lvl.addParticle(effect);
        }
    }

    public static void spawnParticles(ParticleEffect effect, Location loc, int amt) {
        Level lvl = loc.getLevel();
        for (int i = 0; i < amt; i++) {
            lvl.addParticleEffect(loc, effect);
        }
    }

    public static void spawnFirework(Player p) {
        double x = p.getX();
        double y = p.getY();
        double z = p.getZ();
        ItemFirework item = new ItemFirework();
        CompoundTag tag = new CompoundTag();
        Random random = new Random();
        CompoundTag ex = (new CompoundTag())
                .putByteArray("FireworkColor", new byte[]{(byte) DyeColor.values()[random.nextInt((ItemFirework.FireworkExplosion.ExplosionType.values()).length)].getDyeData()}).putByteArray("FireworkFade", new byte[0])
                .putBoolean("FireworkFlicker", random.nextBoolean())
                .putBoolean("FireworkTrail", random.nextBoolean())
                .putByte("FireworkType", ItemFirework.FireworkExplosion.ExplosionType.values()[random.nextInt((ItemFirework.FireworkExplosion.ExplosionType.values()).length)].ordinal());
        tag.putCompound("Fireworks", (new CompoundTag("Fireworks"))
                .putList((new ListTag("Explosions")).add((Tag) ex))
                .putByte("Flight", 1));
        item.setNamedTag(tag);
        CompoundTag nbt = (new CompoundTag())
                .putList((new ListTag("Pos"))
                        .add((Tag) new DoubleTag("", x + 0.5D))
                        .add((Tag) new DoubleTag("", y + 0.5D))
                        .add((Tag) new DoubleTag("", z + 0.5D)))
                .putList((new ListTag("Motion"))
                        .add((Tag) new DoubleTag("", 0.0D))
                        .add((Tag) new DoubleTag("", 0.0D))
                        .add((Tag) new DoubleTag("", 0.0D)))
                .putList((new ListTag("Rotation"))
                        .add((Tag) new FloatTag("", 0.0F))
                        .add((Tag) new FloatTag("", 0.0F)))
                .putCompound("FireworkItem", NBTIO.putItemHelper((Item) item));
        Level l = p.getLevel();
        EntityFirework entity = new EntityFirework((FullChunk) l.getChunk((int) x >> 4, (int) z >> 4), nbt);
        entity.spawnToAll();
    }

}
