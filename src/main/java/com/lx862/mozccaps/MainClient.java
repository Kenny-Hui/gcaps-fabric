package com.lx862.mozccaps;

import com.lx862.mozccaps.render.CapArmorRenderer;
import com.lx862.mozccaps.network.Networking;
import com.lx862.mozccaps.render.HudOverlayRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import org.lwjgl.glfw.GLFW;

public class MainClient implements ClientModInitializer {
	private static final AtamaInput atamaInput = new AtamaInput();
	public static final KeyBinding toggleInputKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.mozc_caps.toggle_input", GLFW.GLFW_KEY_Y, "category.mozc_caps.title"));

	@Override
	public void onInitializeClient() {
		ArmorRenderer.register(new CapArmorRenderer(false), Main.CAPS);
		ArmorRenderer.register(new CapArmorRenderer(true), Main.CAPS_STRAPPED);

		HudRenderCallback.EVENT.register(HudOverlayRenderer::draw);
		ClientTickEvents.START_CLIENT_TICK.register(this::handleInput);
		Networking.registerClient();

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
			content.add(Main.CAPS);
			content.add(Main.CAPS_STRAPPED);
		});
	}

	private void handleInput(MinecraftClient minecraft) {
		if(minecraft.player == null || !capEquipped()) return;

		while(toggleInputKey.wasPressed()) {
			atamaInput.toggleInput();
		}

		if(atamaInput.inputEnabled()) {
			// LMB
			while(minecraft.options.attackKey.wasPressed()) {
				atamaInput.input(minecraft.player.getHeadYaw());
				minecraft.player.swingHand(minecraft.player.getActiveHand());
				Networking.sendKeyPressedClient(minecraft.player);
			}

			// MMB
			while(minecraft.options.pickItemKey.wasPressed()) {
				atamaInput.cycleLayout();
			}

			// RMB
			while(minecraft.options.useKey.wasPressed()) {
				atamaInput.sendMessage(minecraft);
			}
		}
	}

	public static boolean capEquipped() {
		return capEquipped(false) || capEquipped(true);
	}

	public static boolean capEquipped(boolean chinStrapped) {
		MinecraftClient minecraft = MinecraftClient.getInstance();
		if(minecraft.player == null) return false;
		Item helmetItem = minecraft.player.getInventory().getArmorStack(3).getItem();
		return chinStrapped ? helmetItem == Main.CAPS_STRAPPED : helmetItem == Main.CAPS;
	}

	public static AtamaInput getAtamaInput() {
		return atamaInput;
	}
}