package com.lx862.mozccaps.network;

import com.lx862.mozccaps.render.CapArmorRenderer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;

import java.util.UUID;

public class Networking {
    public static void registerClient() {
        ClientPlayNetworking.registerGlobalReceiver(UpdatePlayerTypePayload.PACKET_ID, (payload, context) -> {
            UUID playerUuid = payload.getPlayerUuid();
            CapArmorRenderer.startPlayerTypedAnimation(playerUuid);
        });
    }

    public static void registerServer() {
        PayloadTypeRegistry.playS2C().register(UpdatePlayerTypePayload.PACKET_ID, UpdatePlayerTypePayload.PACKET_CODEC);
        PayloadTypeRegistry.playC2S().register(PlayerTypePayload.PACKET_ID, PlayerTypePayload.PACKET_CODEC);

        ServerPlayNetworking.registerGlobalReceiver(PlayerTypePayload.PACKET_ID, (payload, context) -> {
            UUID playerUuid = payload.getPlayerUuid();

            MinecraftServer server = context.player().getServer();
            if(server == null) return;

            // Rebroadcast key pressed event to all player
            server.getPlayerManager().getPlayerList().forEach(p -> {
                ServerPlayNetworking.send(p, new UpdatePlayerTypePayload(playerUuid));
            });
        });
    }

    public static void sendKeyPressedClient(PlayerEntity player) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeUuid(player.getUuid());
        ClientPlayNetworking.send(new PlayerTypePayload(player.getUuid()));
    }
}
