package com.levelmc.core.users;

import cn.nukkit.Player;
import com.levelmc.core.Core;
import com.levelmc.core.api.user.BaseUser;
import com.levelmc.core.api.yml.Path;
import com.levelmc.core.components.wizarding.MagicType;
import com.levelmc.core.components.wizarding.Spell;
import com.levelmc.core.components.wizarding.SpellManager;
import com.levelmc.core.components.wizarding.SpellSlot;

import java.util.*;

public class User extends BaseUser {

    @Path("spell-binds")
    private Map<String, String> spellSlots = new HashMap<>();

    @Path("spell-levels")
    private Map<String, Integer> spellLevels = new HashMap<>();

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
        return Core.getInstance().getWizardingComponent().getSpellManager().getSpell(getSpellIdInSlot(slot));
    }

    public boolean hasSpellUnlocked(String id) {
        return spellLevels.containsKey(id);
    }

    public boolean hasSpellUnlocked(Spell spell) {
        return hasSpellUnlocked(spell.getId());
    }

    public int getSpellLevel(String id) {
        return spellLevels.get(id);
    }

    public int getSpellLevel(Spell spell) {
        return getSpellLevel(spell.getId());
    }

    public Set<Spell> getSpellsByType(MagicType type) {
        Set<Spell> spells = new HashSet<>();

        for (String s : spellLevels.keySet()) {
            Spell spell = Core.getInstance().getWizardingComponent().getSpellManager().getSpell(s);

            if (spell.getMagicType() != type) {
                continue;
            }

            spells.add(spell);
        }

        return spells;
    }

    public boolean hasSpellInType(MagicType type) {
        return getSpellsByType(type).size() > 0;
    }
}
