package com.levelmc.core.api.cmd;

import cn.nukkit.command.CommandSender;

public interface ExecutableArgument {
    public Object execute(CommandSender sender, Arguments args) throws CommandError;
}
