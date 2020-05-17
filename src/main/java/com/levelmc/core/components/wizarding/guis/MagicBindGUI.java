package com.levelmc.core.components.wizarding.guis;

import cn.nukkit.Player;
import com.levelmc.core.Core;
import com.levelmc.core.api.forms.SimpleButton;
import com.levelmc.core.api.forms.SimpleMenu;
import com.levelmc.core.components.wizarding.SpellSlot;
import com.levelmc.core.users.User;

public class MagicBindGUI extends SimpleMenu {
    public MagicBindGUI(Player player) {
        super("Select your Slot", "");

        User user = Core.getInstance().getUserManager().getUser(player);

        add(new SimpleButton(String.format("Right Click (%s)", user.hasSpellInSlot(SpellSlot.RIGHT_CLICK) ? user.getSpellInSlot(SpellSlot.RIGHT_CLICK).getName() : "None")).onClick(new SimpleButton.Action() {
            @Override
            public void onClick(Player player) {
                player.showFormWindow(new MagicCategoryGUI(user,SpellSlot.RIGHT_CLICK));
            }
        }));

        add(new SimpleButton(String.format("Left Click (%s)", user.hasSpellInSlot(SpellSlot.LEFT_CLICK) ? user.getSpellInSlot(SpellSlot.LEFT_CLICK).getName() : "None")).onClick(new SimpleButton.Action() {
            @Override
            public void onClick(Player player) {
                player.showFormWindow(new MagicCategoryGUI(user,SpellSlot.LEFT_CLICK));
            }
        }));

        add(new SimpleButton(String.format("Crouch + Right Click (%s)", user.hasSpellInSlot(SpellSlot.CROUCH_RIGHT_CLICK) ? user.getSpellInSlot(SpellSlot.CROUCH_RIGHT_CLICK).getName() : "None")).onClick(new SimpleButton.Action() {
            @Override
            public void onClick(Player player) {
                player.showFormWindow(new MagicCategoryGUI(user,SpellSlot.CROUCH_RIGHT_CLICK));

            }
        }));


        add(new SimpleButton(String.format("Crouch + Left Click (%s)", user.hasSpellInSlot(SpellSlot.CROUCH_LEFT_CLICK) ? user.getSpellInSlot(SpellSlot.CROUCH_LEFT_CLICK).getName() : "None")).onClick(new SimpleButton.Action() {
            @Override
            public void onClick(Player player) {
                player.showFormWindow(new MagicCategoryGUI(user,SpellSlot.CROUCH_LEFT_CLICK));
            }
        }));
    }
}
