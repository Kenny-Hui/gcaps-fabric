package com.lx862.mozccaps;

import com.lx862.mozccaps.network.Networking;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.equipment.ArmorMaterials;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class Main implements ModInitializer {
	public static final RegistryKey<Item> CAPS_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of("mozc_caps", "caps"));
	public static final RegistryKey<Item> CAPS_STRAPPED_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of("mozc_caps", "caps_strapped"));
	public static final Item CAPS = Registry.register(Registries.ITEM, CAPS_KEY, new ArmorItem(ArmorMaterials.LEATHER, EquipmentType.HELMET, new Item.Settings().registryKey(CAPS_KEY).useItemPrefixedTranslationKey()));
	public static final Item CAPS_STRAPPED = Registry.register(Registries.ITEM, CAPS_STRAPPED_KEY, new ArmorItem(ArmorMaterials.LEATHER, EquipmentType.HELMET, new Item.Settings().registryKey(CAPS_STRAPPED_KEY).useItemPrefixedTranslationKey()));

	@Override
	public void onInitialize() {
		Networking.registerServer();
	}
}