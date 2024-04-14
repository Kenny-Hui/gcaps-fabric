package com.lx862.mozccaps;

import com.lx862.mozccaps.armor.CapArmorRenderer;
import com.lx862.mozccaps.network.Networking;
import com.lx862.mozccaps.render.HudRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import org.lwjgl.glfw.GLFW;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MainClient implements ClientModInitializer {
	public static HashMap<UUID, Double> keyPressedList = new HashMap<>();
	public static final KeyBinding toggleInputKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.mozc_caps.toggle_input", GLFW.GLFW_KEY_Y, "category.mozc_caps.title"));

	@Override
	public void onInitializeClient() {
		ArmorRenderer.register(new CapArmorRenderer(false), Main.CAPS);
		ArmorRenderer.register(new CapArmorRenderer(true), Main.CAPS_STRAPPED);

		HudRenderCallback.EVENT.register(HudRenderer::draw);
		ClientTickEvents.START_CLIENT_TICK.register(this::handleInput);
		Networking.registerClient();

		WorldRenderEvents.BEFORE_ENTITIES.register(context -> {
			updateCapPressedAnimation(context.tickDelta());
		});

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
			content.add(Main.CAPS);
			content.add(Main.CAPS_STRAPPED);
		});
	}

	private void handleInput(MinecraftClient minecraft) {
		if(minecraft.player == null || !capEquipped()) return;

		while(toggleInputKey.wasPressed()) {
			AtamaInput.toggleInput();
		}

		if(AtamaInput.inputEnabled()) {
			// LMB
			while(minecraft.options.attackKey.wasPressed()) {
				AtamaInput.input(minecraft.player.getHeadYaw());
				minecraft.player.swingHand(minecraft.player.getActiveHand());
				Networking.sendKeyPressedClient(minecraft.player);
			}

			// MMB
			while(minecraft.options.pickItemKey.wasPressed()) {
				AtamaInput.cycleLayout();
			}

			// RMB
			while(minecraft.options.useKey.wasPressed()) {
				AtamaInput.sendMessage(minecraft);
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

	private static void updateCapPressedAnimation(float delta) {
		for(Map.Entry<UUID, Double> entry : keyPressedList.entrySet()) {
			double newProgress = entry.getValue() + (delta / 8);
			if(newProgress >= 1) {
				keyPressedList.remove(entry.getKey());
			} else {
				keyPressedList.put(entry.getKey(), newProgress);
			}
		}
	}
}