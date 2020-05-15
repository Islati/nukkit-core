package com.levelmc.core.users;

import cn.nukkit.Player;
import com.levelmc.core.Core;
import com.levelmc.core.api.user.BaseUser;
import com.levelmc.core.api.yml.Path;
import com.levelmc.core.wizarding.Spell;
import com.levelmc.core.wizarding.SpellSlot;

import java.util.HashMap;
import java.util.Map;

public class User extends BaseUser {

    @Path("spell-binds")
    private Map<String, String> spellSlots = new HashMap<>();

    public User(Player player) {
        super(player);
    }

    public boolean hasSpellInSlot(SpellSlot slot) {
        return spellSlots.containsKey(slot.getSlotName());
    }

    public String getSpellIdInSlot(SpellSlot slot) {
        return spellSlots.get(slot.getSlotName());
    }

    public Spell getSpellInSlot(SpellSlot slot) {
        return Core.getInstance().getSpellManager().getSpell(getSpellIdInSlot(slot));
    }
}
