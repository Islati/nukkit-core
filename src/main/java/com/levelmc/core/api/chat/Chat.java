package com.levelmc.core.api.chat;

import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

public class Chat {

    public static void format(CommandSender sender, String msg, Object... args) {
        msg(sender, String.format(msg, args));
    }

    public static void msg(CommandSender sender, String... messages) {
        for (String s : messages) {
            sender.sendMessage(TextFormat.colorize(s));
        }
    }

}
