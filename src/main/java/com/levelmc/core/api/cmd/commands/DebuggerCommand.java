package com.levelmc.core.api.cmd.commands;

import cn.nukkit.Player;
import com.levelmc.core.Core;
import com.levelmc.core.api.chat.Chat;
import com.levelmc.core.api.chat.HelpScreen;
import com.levelmc.core.api.cmd.Arg;
import com.levelmc.core.api.cmd.Command;
import com.levelmc.core.api.cmd.Wildcard;
import com.levelmc.core.api.debug.DebugAction;
import com.levelmc.core.api.debug.Debugger;

@CommandInfo(name="debug",description = "Debugger (Trap Core)")
public class DebuggerCommand {

    private HelpScreen helpMenu = new HelpScreen("Debugger Commands")
            .addEntry("debug", "(This Menu)")
            .addEntry("debug run <id> <...args>", "Run a command")
            .addEntry("debug list", "List the commands");

    private Debugger debugger = null;

    public DebuggerCommand() {
        debugger = Debugger.getInstance();

        Core.getInstance().getLogger().info("Registered debug actions");
    }

    @Command(identifier = "debug", permissions = "trapped.debugger")
    public void onDebugCommand(Player player) {
        helpMenu.sendTo(player, 1, 7);
    }

    @Command(identifier = "debug run", permissions = "trapped.debugger")
    public void onDebugRunCommand(Player player, @Arg(name = "id", description = "id") String command, @Wildcard @Arg(name = "args") String args) {
        if (!Debugger.getInstance().getDebugActions().containsKey(command)) {
            Chat.msg(player, "Invalid debug action id");
            return;
        }

        DebugAction action = Debugger.getInstance().getDebugActions().get(command);
        action.onDebug(player, args.split(" "));
    }

    @Command(identifier = "debug list", permissions = "trapped.debugger")
    public void onDebugListCommand(Player player) {
        Chat.msg(player, "&6======== &c[Debug Actions] &6========");
        for (String id : Debugger.getInstance().getDebugActions().keySet()) {
            Chat.format(player, "&e- &a%s", id);
        }

        Chat.msg(player, "&6=======================");
    }

}
