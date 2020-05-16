package com.levelmc.core.components.wizarding.guis;

import cn.nukkit.Player;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import com.levelmc.core.Core;
import com.levelmc.core.api.forms.SimpleButton;
import com.levelmc.core.api.forms.SimpleMenu;
import com.levelmc.core.components.wizarding.MagicType;

public class MagicCategoryGUI extends SimpleMenu {

    public MagicCategoryGUI() {
        super("Select Magic Type", "Choose wisely, young wizard.");

        for(MagicType type : MagicType.values()) {
            add(new SimpleButton(type.getDisplayName()).onClick(new SimpleButton.Action() {
                @Override
                public void onClick(Player player) {
                    player.showFormWindow(new SpellSelectionGUI(type, Core.getInstance().getUserManager().getUser(player)));
                    return;
                }
            }));
        }
    }

    @Override
    public void onResponse(PlayerFormRespondedEvent event) {
        int buttonId = getResponse().getClickedButtonId();

        switch (buttonId) {
            case 0:
                break;
            case 1:
                break;
            default:
                break;
        }
    }
}
