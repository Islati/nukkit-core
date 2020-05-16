package com.levelmc.core.components.wizarding.guis;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import com.levelmc.core.api.chat.Chat;
import com.levelmc.core.api.forms.SimpleButton;
import com.levelmc.core.api.forms.SimpleMenu;
import com.levelmc.core.components.wizarding.MagicType;
import com.levelmc.core.components.wizarding.Spell;
import com.levelmc.core.users.User;

import java.util.List;
import java.util.Set;

public class SpellSelectionGUI extends SimpleMenu {
    public SpellSelectionGUI(MagicType type, User user) {
        super(String.format("%s Spells", type.getDisplayName()), user.hasSpellInType(type) ? "Select which spell you wish to use" : "You have no spells of this type");

        Set<Spell> spells = user.getSpellsByType(type);

        if (spells.isEmpty()) {
            return;
        }

        for (Spell spell : spells) {
            add(new SimpleButton(spell.getName()).onClick(new SimpleButton.Action() {
                @Override
                public void onClick(Player player) {
                    Chat.format(player, "You chose the %s&r spell", spell.getName());
                    //todo switch to slot selection
                }
            }));
        }
    }
}
