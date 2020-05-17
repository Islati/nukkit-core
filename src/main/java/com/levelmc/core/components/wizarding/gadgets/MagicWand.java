package com.levelmc.core.components.wizarding.gadgets;

import cn.nukkit.Player;
import cn.nukkit.event.player.PlayerDropItemEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.item.Item;
import com.levelmc.core.Core;
import com.levelmc.core.api.chat.Chat;
import com.levelmc.core.api.gadgets.BaseGadget;
import com.levelmc.core.components.wizarding.Spell;
import com.levelmc.core.components.wizarding.SpellSlot;
import com.levelmc.core.components.wizarding.guis.MagicBindGUI;
import com.levelmc.core.components.wizarding.guis.MagicCategoryGUI;
import com.levelmc.core.users.User;

public class MagicWand extends BaseGadget {
    public MagicWand() {
        super("wand");
    }

    @Override
    public void onInteractEvent(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        PlayerInteractEvent.Action action = e.getAction();

        User user = Core.getInstance().getUserManager().getUser(player);

        SpellSlot slot = null;

        if (action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK || action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR) {
            if (player.isSneaking()) {
                slot = SpellSlot.CROUCH_RIGHT_CLICK;
            } else {
                slot = SpellSlot.RIGHT_CLICK;
            }
        } else {
            if (player.isSneaking()) {
                slot = SpellSlot.CROUCH_LEFT_CLICK;
            } else {
                slot = SpellSlot.LEFT_CLICK;
            }
        }

        Spell spell = user.getSpellInSlot(slot);
        if (spell == null) {
            Chat.actionMsg(player, "&cNo spell bound to this slot");
            return;
        }

        if (!spell.canPerform(user)) {
            Chat.actionMsg(player, "&cYou cannot perform this right now");
            return;
        }

        spell.perform(user);
    }

    @Override
    public void onPlayerDropItemEvent(PlayerDropItemEvent e) {
        Player player = e.getPlayer();
        player.showFormWindow(new MagicBindGUI(player));
        e.setCancelled(true);
    }
}
