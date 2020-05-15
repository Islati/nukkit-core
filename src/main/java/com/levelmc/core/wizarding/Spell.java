package com.levelmc.core.wizarding;

import cn.nukkit.Player;
import com.levelmc.core.users.User;
import lombok.Getter;

public abstract class Spell {

    @Getter
    private String id;

    @Getter
    private String name;

    @Getter
    private int range = 10;

    @Getter
    private int radius = 4;

    public Spell(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Spell range(int range) {
        this.range = range;
        return this;
    }

    public Spell radius(int radius) {
        this.radius = radius;
        return this;
    }

    public abstract boolean canPerform(User user);

    public abstract void perform(User user);
}
