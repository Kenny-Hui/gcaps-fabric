package com.lx862.mozccaps;

import com.lx862.mozccaps.armor.CapModel;
import com.lx862.mozccaps.network.Networking;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class Main implements ModInitializer {
	public static final Item CAPS = new ArmorItem(RegistryEntry.of(CapModel.ARMOR_MATERIAL), ArmorItem.Type.HELMET, new Item.Settings());
	public static final Item CAPS_STRAPPED = new ArmorItem(RegistryEntry.of(CapModel.ARMOR_MATERIAL), ArmorItem.Type.HELMET, new Item.Settings());

	@Override
	public void onInitialize() {
		Registry.register(Registries.ARMOR_MATERIAL, new Identifier("mozc_caps", "armor_material"), CapModel.ARMOR_MATERIAL);
		Registry.register(Registries.ITEM, new Identifier("mozc_caps", "caps"), CAPS);
		Registry.register(Registries.ITEM, new Identifier("mozc_caps", "caps_strapped"), CAPS_STRAPPED);

		Networking.registerServer();
	}
}