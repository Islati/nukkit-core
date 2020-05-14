package com.levelmc.core.api.cmd.handlers;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Level;
import com.levelmc.core.api.cmd.*;

public class LevelArgumentHandler extends ArgumentHandler<Level> {
    public LevelArgumentHandler() {
        setMessage("world_not_found", "The world \"%1\" was not found");

        addVariable("sender", "The command executor", new ArgumentVariable<Level>() {
            @Override
            public Level var(CommandSender sender, CommandArgument argument, String varName) throws CommandError {
                if (!(sender instanceof Player)) {
                    throw new CommandError("Only players can execute this command");
                }

                return ((Player) sender).getLevel();
            }
        });
    }

    @Override
    public Level transform(CommandSender sender, CommandArgument argument, String value) throws TransformError {
        Level world = Server.getInstance().getLevelByName(value);
        if (world == null) {
            throw new TransformError(String.format("Unable to find world named '%s'", value));
        }
        return world;
    }
}
