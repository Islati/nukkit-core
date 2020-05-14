package com.levelmc.core.api.cmd;

import cn.nukkit.utils.TextFormat;

public class CommandError extends Exception {
    private static final long serialVersionUID = 1L;

    private boolean showUsage;

    public CommandError(String msg) {
        this(msg, false);
    }

    public CommandError(String msg, boolean showUsage) {
        super(msg);
        this.showUsage = showUsage;
    }

    public String getColorizedMessage() {
        //todo load from config what color to use here.
        String msg = getMessage();
        msg = msg.replaceAll("\\[", TextFormat.AQUA + "[");
        msg = msg.replaceAll("\\]", "]" + TextFormat.RED);
        return TextFormat.RED + msg;
    }

    public boolean showUsage() {
        return showUsage;
    }
}
