package com.levelmc.core.components.wizarding;

import com.levelmc.core.Core;
import com.levelmc.core.components.wizarding.commands.SpellCommand;
import com.levelmc.core.components.wizarding.gadgets.MagicWand;
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
                new SpellCommand()
        );

        parent.getGadgetManager().registerGadget(
                new MagicWand()
        );
    }
}
