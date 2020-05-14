package com.levelmc.core.wizarding;

import cn.nukkit.Player;
import com.levelmc.core.users.User;
import lombok.Getter;

public abstract class Spell {

    @Getter
    private String id;

    @Getter
    private String name;

    public Spell(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public abstract boolean canPerform(User user);

    public abstract void perform(User user);
}
