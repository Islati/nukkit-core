package com.levelmc.core.components.wizarding.commands;

import cn.nukkit.Player;
import com.levelmc.core.Core;
import com.levelmc.core.api.chat.Chat;
import com.levelmc.core.api.chat.HelpScreen;
import com.levelmc.core.api.cmd.Arg;
import com.levelmc.core.api.cmd.Command;
import com.levelmc.core.api.cmd.commands.CommandInfo;
import com.levelmc.core.components.wizarding.Spell;
import com.levelmc.core.components.wizarding.guis.MagicCategoryGUI;
import com.levelmc.core.users.User;

@CommandInfo(name = "spell", aliases = {"sp"})
public class SpellCommand {

    private HelpScreen help = new HelpScreen("Spells Help Menu")
            .addEntry("spell", "View this help menu")
            .addEntry("spell unlock <id> [target]", "Unlock a spell with a specific id for the target", "levelmc.admin");

    public SpellCommand() {

    }

    @Command(identifier = "spell")
    public void onSpellsCommand(Player player) {
        help.sendTo(player,1,8);
    }

    @Command(identifier = "spell unlock")
    public void onSpellUnlock(Player player, @Arg(name = "id") String id, @Arg(name = "target", def = "?sender") Player target) {
        User user = Core.getInstance().getUserManager().getUser(target);

        Spell spell = Core.getInstance().getWizardingComponent().getSpellManager().getSpell(id);

        if (spell == null) {
            Chat.msg(player, "&cNo spell with that id was found");
            return;
        }

        if (user.hasSpellUnlocked(spell)) {
            Chat.msg(player,"&cThey already have that spell unlocked");
        }

        user.unlockSpell(spell);
        Chat.title(target, "", String.format("&aSpell Unlocked&r: %s", spell.getName()));
    }
}
