package com.levelmc.core.api.cmd;

public interface HelpHandler {
    public String[] getHelpMessage(RegisteredCommand command);

    public String getUsage(RegisteredCommand command);
}
