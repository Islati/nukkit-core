package com.levelmc.core.components.wizarding;

import com.levelmc.core.Core;
import com.levelmc.core.components.wizarding.commands.SpellsCommand;
import com.levelmc.core.components.wizarding.spells.FireISpell;
import lombok.Getter;

public class WizardingComponent {

    @Getter
    private SpellManager spellManager = null;


    private Core parent;

    public WizardingComponent(Core core) {
        this.parent = core;
    }

    public void init() {
        spellManager = new SpellManager(parent);

        spellManager.registerSpells(new FireISpell());

        parent.getCommandHandler().registerCommands(
                new SpellsCommand()
        );
    }
}
