package com.levelmc.core.api.cmd.handlers;

import cn.nukkit.command.CommandSender;
import com.levelmc.core.api.cmd.CommandArgument;
import com.levelmc.core.api.cmd.TransformError;

public class IntegerArgumentHandler extends NumberArgumentHandler<Integer> {
    public IntegerArgumentHandler() {
        setMessage("parse_error", "The parameter [%p] is not an integer");
    }

    @Override
    public Integer transform(CommandSender sender, CommandArgument argument, String value) throws TransformError {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new TransformError(argument.getMessage("parse_error"));
        }
    }
}
