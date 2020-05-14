package com.levelmc.core.wizarding.spells;

import com.levelmc.core.users.User;
import com.levelmc.core.wizarding.Spell;

public class FireSpell extends Spell {
    public FireSpell() {
        super("fire-i", "&cFire &bI");
    }

    @Override
    public boolean canPerform(User user) {
        return true;
    }

    @Override
    public void perform(User user) {

    }
}
