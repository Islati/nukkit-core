package com.levelmc.core.api.cmd.handlers;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.item.Item;
import com.levelmc.core.api.player.HandSlot;
import com.levelmc.core.api.cmd.*;
import com.levelmc.core.api.item.ItemUtils;
import com.levelmc.core.api.player.PlayerUtils;
import org.joor.Reflect;

import java.util.Map;

public class ItemArgumentHandler extends ArgumentHandler<Item> {
    public ItemArgumentHandler() {
        //Add the sender variable,
        addVariable("hand", "item in the hand of the command executor", ItemHandArgumentVariable.getInstance());
//        addVariable("offhand", "item in the off-hand of the command executor", ItemStackHandArgumentVariable.getInstance());
//        addVariable("sender", "item in the hand of the command executor", ItemStackHandArgumentVariable.getInstance());

        Map<String, Integer> itemNamedIds = Reflect.onClass(ItemUtils.class).get("nameToIdMap");

        for (Map.Entry<String, Integer> entry : itemNamedIds.entrySet()) {
            addVariable(entry.getKey(),entry.getKey(),ItemStackArgumentVariable.getInstance());
        }
    }

    @Override
    public Item transform(CommandSender sender, CommandArgument argument, String value) throws TransformError {
        if (value.equalsIgnoreCase("0") || value.equalsIgnoreCase("air")) {
            return null;
        }

        try {
            return ItemUtils.get(value);
        } catch (Exception e) {
            throw new TransformError(e.getMessage());
        }

    }

    private static class ItemStackArgumentVariable implements ArgumentVariable<Item> {

        private static ItemStackArgumentVariable instance;

        public static ItemStackArgumentVariable getInstance() {
            if (instance == null) {
                instance = new ItemStackArgumentVariable();
            }
            return instance;
        }

        private ItemStackArgumentVariable() {
        }

        @Override
        public Item var(CommandSender sender, CommandArgument argument, String varName) throws CommandError {
            if (varName.equalsIgnoreCase("0") || varName.equalsIgnoreCase("air")) {
                return null;
            }

            try {
                return ItemUtils.get(varName);
            } catch (Exception e) {
                throw new TransformError(e.getMessage());
            }

        }
    }

    private static class ItemHandArgumentVariable implements ArgumentVariable<Item> {
        private static ItemHandArgumentVariable instance;

        public static ItemHandArgumentVariable getInstance() {
            if (instance == null) {
                instance = new ItemHandArgumentVariable();
            }
            return instance;
        }

        @Override
        public Item var(CommandSender sender, CommandArgument argument, String varName) throws CommandError {
            if (!(sender instanceof Player)) {
                throw new CommandError("Only players can perform this command");
            }

            if (varName.equalsIgnoreCase("offhand")) {
                Player player = (Player) sender;

                if (PlayerUtils.handIsEmpty(player, HandSlot.OFF_HAND)) {
                    throw new CommandError("Item in off hand is required");
                }


                Item item = player.getOffhandInventory().getItem(0);
                return item;
            }

            Player player = (Player) sender;
            if (PlayerUtils.handIsEmpty(player, HandSlot.MAIN_HAND)) {
                throw new CommandError("Item in hand is required");
            }

            return PlayerUtils.getItemInHand(player, HandSlot.MAIN_HAND);
        }
    }
}
