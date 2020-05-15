package com.levelmc.core.api.chat;

import cn.nukkit.Player;
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

    public static void actionMsg(Player player, String msg) {
        player.sendActionBar(TextFormat.colorize(msg));
    }

    public static void actionMsg(Player player, String msg, int fadeIn, int duration, int fadeOut) {
        player.sendActionBar(TextFormat.colorize(msg), fadeIn, duration, fadeOut);
    }

}
