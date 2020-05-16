package com.levelmc.core.api.forms;

import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.window.FormWindowSimple;

import java.util.List;

public class SimpleMenu extends FormWindowSimple implements Menu {
    public SimpleMenu(String title, String content) {
        super(title, content);
    }

    public SimpleMenu(String title, String content, List<ElementButton> buttons) {
        super(title, content, buttons);
    }

    public SimpleMenu add(ElementButton button) {
        this.addButton(button);
        return this;
    }

    @Override
    public void onResponse(PlayerFormRespondedEvent event) {
        ElementButton button = getResponse().getClickedButton();

        if (!(button instanceof SimpleButton)) {
            return;
        }

        ((SimpleButton) button).handleClick(event.getPlayer());
    }
}
