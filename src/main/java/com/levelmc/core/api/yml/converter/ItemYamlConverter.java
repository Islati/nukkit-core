package com.levelmc.core.api.yml.converter;

import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;
import com.levelmc.core.api.item.ItemBuilder;
import com.levelmc.core.api.yml.ConfigSection;
import com.levelmc.core.api.yml.InternalConverter;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemYamlConverter implements Converter {
    private InternalConverter converter;

	public ItemYamlConverter(InternalConverter converter) {
		this.converter = converter;
	}

	@Override
	public Object toConfig(Class<?> type, Object obj, ParameterizedType genericType) throws Exception {
		Item item = (Item) obj;

		Map<String, Object> saveMap = new HashMap<>();
		saveMap.put("id", item.getId());
		saveMap.put("damage",item.getDamage());
		saveMap.put("amount", item.getCount());

		Converter listConverter = converter.getConverter(List.class);
		Converter arrayConverter = converter.getConverter(Array.class);

		Enchantment[] enchantments = item.getEnchantments();

		List<String> enchantData = new ArrayList<>();

		if (enchantments.length > 0) {
			for (Enchantment enchant : enchantments) {
				enchantData.add(String.format("%s:%s", enchant.getName(), enchant.getLevel()));
			}

			saveMap.put("enchantments", listConverter.toConfig(List.class, enchantData, null));

		}

		if (item.hasCustomName()) {
			saveMap.put("name",item.getCustomName());
		}

		if (item.getLore().length > 0) {
			saveMap.put("lore",arrayConverter.toConfig(Array.class,item.getLore(),null));
		}

		return saveMap;
	}

	@Override
	public Object fromConfig(Class type, Object section, ParameterizedType genericType) throws Exception {
		Map<String, Object> itemstackMap;

		if (section == null) {
			throw new NullPointerException("Item configuration section is null");
		}

		if (section instanceof Map) {
			itemstackMap = (Map<String, Object>) section;
		} else {
			itemstackMap = (Map<String, Object>)((ConfigSection) section).getRawMap();
		}

		int id = (int)itemstackMap.get("id");

		ItemBuilder itemBuilder = ItemBuilder.of(id);

		if (itemstackMap.containsKey("damage")) {
			int damage = (int)itemstackMap.get("damage");
			itemBuilder.damage(damage);
		}

		if (itemstackMap.containsKey("amount")) {
			int amount = (int)itemstackMap.get("amount");
			itemBuilder.amount(amount);
		}

		if (itemstackMap.containsKey("name")) {
			itemBuilder.name((String)itemstackMap.get("name"));
		}

		if (itemstackMap.containsKey("lore")) {
			Converter arrayConverter = converter.getConverter(Array.class);
			String[] lore = (String[])arrayConverter.fromConfig(Array.class,itemstackMap.get("lore"),null);
			itemBuilder.lore(lore);
		}
//
//		if (metaMap != null) {
//			if (metaMap.containsKey("name")) {
//				itemBuilder.name((String)metaMap.get("name"));
//			}
//
//			if (metaMap.containsKey("lore")) {
//				Converter listConverter = converter.getConverter(List.class);
//				List<String> lore = new ArrayList<>();
//
//				lore = (List<String>)listConverter.fromConfig(List.class,metaMap.get("lore"),null);
//
//				itemBuilder.lore(lore);
//			}
//		}

		/*
		If there's enchantments listed in the yml file for this item, then we're going to parse
		that section of the configuration, and then add it to the item.
		 */
//		if (itemstackMap.containsKey("enchantments")) {
//
//		    Map<String, Object> enchantmentMap = new HashMap<>();
//
//		    Converter mapConverter = converter.getConverter(Map.class);
//
////		    enchantmentMap = ((ConfigSection)converter.getConverter(enchantmentMap.getClass()).fromConfig(enchantmentMap.getClass(),itemstackMap.get("enchantments"),TypeUtils.parameterize(enchantmentMap.getClass(),enchantmentMap.getClass().getGenericInterfaces()))).getRawMap();
//			enchantmentMap = (Map<String, Object>)mapConverter.fromConfig(Map.class,itemstackMap.get("enchantments"),null);
//
//		    for (Map.Entry<String, Object> enchant : enchantmentMap.entrySet()) {
//		        Enchantment enchantment = Enchantment.getByName(enchant.getKey());
//		        itemBuilder.enchant(enchantment,(int)enchant.getValue());
//            }
//
//		}

		return itemBuilder.item();
	}

	@Override
	public boolean supports(Class<?> type) {
		return Item.class.isAssignableFrom(type);
	}

}
