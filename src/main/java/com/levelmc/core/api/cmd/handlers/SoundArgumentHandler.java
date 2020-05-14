package com.levelmc.core.api.cmd.handlers;

import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import com.levelmc.core.api.cmd.ArgumentHandler;
import com.levelmc.core.api.cmd.CommandArgument;
import com.levelmc.core.api.cmd.TransformError;


public class SoundArgumentHandler extends ArgumentHandler<Sound> {
    public SoundArgumentHandler() {
        setMessage("parse_error", "There is no sound named %1");
        setMessage("include_error", "There is no sound named %1");
        setMessage("exclude_error", "There is no sound named %1");
    }

    @Override
    public Sound transform(CommandSender sender, CommandArgument argument, String value) throws TransformError {
        return Sound.valueOf(value.toUpperCase());
    }


}
