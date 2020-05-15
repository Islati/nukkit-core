package com.levelmc.core.wizarding;

import com.levelmc.core.Core;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class SpellManager {
    private Map<String, Spell> spells = new HashMap<>();

    private Core parent;

    public SpellManager(Core parent) {
        this.parent = parent;
    }

    public void registerSpells(Spell... spells) {
        for (Spell spell : spells) {
            this.spells.put(spell.getId(), spell);
        }
    }

    public Spell getSpell(String id) {
        return this.spells.get(id);
    }

    public boolean isSpell(String id) {
        return spells.containsKey(id);
    }
}
