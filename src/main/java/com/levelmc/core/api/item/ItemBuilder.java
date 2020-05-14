package com.levelmc.core.api.item;

import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.utils.TextFormat;

public class ItemBuilder {

    private Item item;

    public static ItemBuilder of(int id) {
        return new ItemBuilder(id);
    }

    public static ItemBuilder clone(Item item) {
        return new ItemBuilder(item);
    }

    public ItemBuilder(int id) {
        this.item = Item.get(id);
    }

    public ItemBuilder(Item item) {
        this.item = item.clone();
    }

    public ItemBuilder name(String name) {
        this.item.setCustomName(TextFormat.colorize(name));
        return this;
    }

    public ItemBuilder lore(String... lines) {
        String[] formattedLore = new String[lines.length];

        for(int i = 0; i < lines.length; i++) {
            formattedLore[i] = TextFormat.colorize(lines[i]);
        }

        this.item.setLore(formattedLore);
        return this;
    }

    public ItemBuilder enchant(Enchantment... enchantments) {
        this.item.addEnchantment(enchantments);
        return this;
    }

    public ItemBuilder damage(int dmg) {
        this.item.setDamage(dmg);
        return this;
    }

    public ItemBuilder amount(int count) {
        this.item.setCount(count);
        return this;
    }

    public Item item() {
        return item;
    }
}
