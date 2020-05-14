package com.levelmc.core.api.cmd.handlers;

import cn.nukkit.command.CommandSender;
import com.levelmc.core.api.cmd.ArgumentHandler;
import com.levelmc.core.api.cmd.CommandArgument;
import com.levelmc.core.api.cmd.TransformError;

public class BooleanArgumentHandler extends ArgumentHandler<Boolean> {

    public BooleanArgumentHandler() {
        setMessage("parse_error", "The parameter [%p] is not a boolean");
    }

    @Override
    public Boolean transform(CommandSender sender, CommandArgument argument, String value) throws TransformError {
        switch (value.toLowerCase()) {
            case "yes":
            case "true":
            case "y":
                return true;
            case "no":
            case "false":
            case "n":
                return false;
            default:
                throw new TransformError(getMessage("parse_error"));
        }
    }
}
