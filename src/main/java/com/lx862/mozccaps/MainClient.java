package com.lx862.mozccaps;

import com.lx862.mozccaps.armor.CapArmorRenderer;
import com.lx862.mozccaps.network.Networking;
import com.lx862.mozccaps.render.HudRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroups;
import net.minecraft.network.PacketByteBuf;
import org.lwjgl.glfw.GLFW;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MainClient implements ClientModInitializer {
	public static HashMap<UUID, Double> keyPressedList = new HashMap<>();
	public static final KeyBinding toggleInput = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.mozc_caps.toggle_input", GLFW.GLFW_KEY_Y, "category.mozc_caps.title"));

	@Override
	public void onInitializeClient() {
		ArmorRenderer.register(new CapArmorRenderer(), Main.CAPS);
		HudRenderCallback.EVENT.register(HudRenderer::draw);
		ClientTickEvents.START_CLIENT_TICK.register(this::handleMouseInput);
		Networking.registerReceiverClient();

		WorldRenderEvents.BEFORE_ENTITIES.register(context -> {
			updateCapPressedAnimation(context.tickDelta());
		});

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
			content.add(Main.CAPS);
		});
	}

	private void handleMouseInput(MinecraftClient minecraft) {
		if(capEquipped()) {
			while(toggleInput.wasPressed()) {
				AtamaInput.inputEnabled = !AtamaInput.inputEnabled;
			}

			if(AtamaInput.inputEnabled) {
				while(minecraft.options.attackKey.wasPressed()) {
					AtamaInput.input(minecraft.player.getHeadYaw());
					minecraft.player.swingHand(minecraft.player.getActiveHand());
					sendTypedPacket(minecraft.player);
				}

				while(minecraft.options.pickItemKey.wasPressed()) {
					AtamaInput.cycleLayout();
				}

				while(minecraft.options.useKey.wasPressed()) {
					AtamaInput.sendMessage(minecraft);
				}
			}
		}
	}

	public static boolean capEquipped() {
		return capEquipped(false) || capEquipped(true);
	}

	public static boolean capEquipped(boolean chinStrapped) {
		MinecraftClient minecraft = MinecraftClient.getInstance();
		if(minecraft.player == null) return false;
		return minecraft.player.getInventory().getArmorStack(3).getItem() == (Main.CAPS);
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

	private static void sendTypedPacket(PlayerEntity player) {
		PacketByteBuf buf = PacketByteBufs.create();
		buf.writeUuid(player.getUuid());
		ClientPlayNetworking.send(Networking.PLAYER_TYPED, buf);
	}
}