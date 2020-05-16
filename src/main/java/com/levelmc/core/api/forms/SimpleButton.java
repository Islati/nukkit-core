package com.levelmc.core.api.forms;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;

public class SimpleButton extends ElementButton {

    private Action clickHandler = null;
    public SimpleButton(String text) {
        super(text);
    }

    public SimpleButton(String text, ElementButtonImageData image) {
        super(text, image);
    }

    public SimpleButton onClick(Action action) {
        this.clickHandler = action;
        return this;
    }

    public void handleClick(Player player) {
        if (clickHandler==null) {
            return;
        }

        clickHandler.onClick(player);
    }

    public static interface Action {

        void onClick(Player player);

    }
}
