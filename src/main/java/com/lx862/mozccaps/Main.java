package com.lx862.mozccaps;

import com.lx862.mozccaps.armor.CapArmorMaterial;
import com.lx862.mozccaps.network.Networking;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class Main implements ModInitializer {
	public static final Item CAPS = new ArmorItem(new CapArmorMaterial(), ArmorItem.Type.HELMET, new FabricItemSettings());
	public static final Item CAPS_STRAPPED = new ArmorItem(new CapArmorMaterial(), ArmorItem.Type.HELMET, new FabricItemSettings());

	@Override
	public void onInitialize() {
		Registry.register(Registries.ITEM, new Identifier("mozc_caps", "caps"), CAPS);
		Registry.register(Registries.ITEM, new Identifier("mozc_caps", "caps_strapped"), CAPS_STRAPPED);
		Networking.registerReceiverServer();
	}
}