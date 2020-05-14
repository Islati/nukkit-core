package com.levelmc.core.api.cmd.handlers;

import cn.nukkit.command.CommandSender;
import com.levelmc.core.api.cmd.CommandArgument;
import com.levelmc.core.api.cmd.TransformError;

public class DoubleArgumentHandler extends NumberArgumentHandler<Double> {
    public DoubleArgumentHandler() {
        setMessage("parse_error", "The parameter [%p] is not a number");
    }

    @Override
    public Double transform(CommandSender sender, CommandArgument argument, String value) throws TransformError {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new TransformError(argument.getMessage("parse_error"));
        }
    }
}
