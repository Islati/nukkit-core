package com.levelmc.core.api.forms;

import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.utils.TextFormat;
import com.levelmc.core.Core;

import java.util.List;

public class SimpleMenu extends FormWindowSimple implements Menu {
    public SimpleMenu(String title, String content) {
        super(TextFormat.colorize(title), TextFormat.colorize(content));
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
            Core.getInstance().getLogger().info("Button clicked has no bound action");
            return;
        }

        ((SimpleButton) button).handleClick(event.getPlayer());
    }
}
