package com.levelmc.core.debug;

import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemID;
import cn.nukkit.utils.TextFormat;
import com.levelmc.core.Core;
import com.levelmc.core.api.chat.Chat;
import com.levelmc.core.api.gadgets.BaseGadget;
import com.levelmc.core.api.item.ItemBuilder;
import com.levelmc.core.users.User;

public class TestGadget extends BaseGadget {
    private static TestGadget instance = null;

    public static TestGadget getInstance() {
        if (instance == null) {
            instance = new TestGadget();
        }

        return instance;
    }

    protected TestGadget() {
        super("testGadget", ItemBuilder.of(ItemID.STICK).name("&7Test Gadget").lore("&7Nefariously Boring").item());
    }

    @Override
    public void onInteractEvent(PlayerInteractEvent e) {
        User user = Core.getInstance().getUserManager().getUser(e.getPlayer());
        Core.getInstance().getSpellManager().getSpell("fire-i").perform(user);
    }
}
