package com.levelmc.core.api.forms;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import com.levelmc.core.Core;

public class FormListener implements Listener {

    private Core core = null;
    public FormListener(Core core) {
        this.core = core;
    }

    @EventHandler
    public void onFormRespondedEvent(PlayerFormRespondedEvent e) {
        if (e.getResponse() == null) {
            return;
        }

        if (!(e.getWindow() instanceof Menu)) {
            return;
        }

        ((Menu) e.getWindow()).onResponse(e);
    }
}
