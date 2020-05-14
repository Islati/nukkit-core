package com.levelmc.core.api.cmd.handlers;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import com.levelmc.core.api.cmd.*;


public class PlayerArgumentHandler extends ArgumentHandler<Player> {
    public PlayerArgumentHandler() {
        setMessage("player_not_online", "The player %1 is not online");

        addVariable("sender", "The command executor", new ArgumentVariable<Player>() {
            @Override
            public Player var(CommandSender sender, CommandArgument argument, String varName) throws CommandError {
                if (!(sender instanceof Player)) {
                    throw new CommandError(argument.getMessage("cant_as_console"));
                }

                return ((Player) sender);
            }
        });
    }

    @Override
    public Player transform(CommandSender sender, CommandArgument argument, String value) throws TransformError {
        Player p = Server.getInstance().getPlayer(value);
        if (p == null) {
            throw new TransformError(argument.getMessage("player_not_online", value));
        }

        return p;
    }
}
