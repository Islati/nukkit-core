package com.levelmc.core.components.wizarding.guis;

import cn.nukkit.Player;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.scheduler.NukkitRunnable;
import cn.nukkit.utils.TextFormat;
import com.levelmc.core.Core;
import com.levelmc.core.api.forms.SimpleButton;
import com.levelmc.core.api.forms.SimpleMenu;
import com.levelmc.core.components.wizarding.MagicType;
import com.levelmc.core.components.wizarding.SpellSlot;
import com.levelmc.core.users.User;

public class MagicCategoryGUI extends SimpleMenu {

    public MagicCategoryGUI(User user, SpellSlot slot) {
        super("Select Magic Type", "Choose wisely, young wizard.");

        for (MagicType type : MagicType.values()) {
            add(new SimpleButton(TextFormat.colorize(type.getDisplayName())).onClick(new SimpleButton.Action() {
                @Override
                public void onClick(Player player) {
                    player.showFormWindow(new SpellSelectionGUI(type, user, slot));
                }
            }));
        }
    }

    @Override
    public void onResponse(PlayerFormRespondedEvent event) {
        super.onResponse(event);
    }
}
