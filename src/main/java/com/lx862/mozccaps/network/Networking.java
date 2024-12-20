package com.lx862.mozccaps.network;

import com.lx862.mozccaps.MainClient;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;

public class Networking {
    public static void registerClient() {
        PayloadTypeRegistry.playS2C().register(PlayerTypePayload.PACKET_ID, PlayerTypePayload.PACKET_CODEC);

        ClientPlayNetworking.registerGlobalReceiver(PlayerTypePayload.PACKET_ID, (payload, context) -> {
            String playerTyped = payload.getPlayerName();
            MainClient.keyPressedList.put(playerTyped, 0.0);
        });
    }

    public static void registerServer() {
        PayloadTypeRegistry.playC2S().register(PlayerTypePayload.PACKET_ID, PlayerTypePayload.PACKET_CODEC);

        ServerPlayNetworking.registerGlobalReceiver(PlayerTypePayload.PACKET_ID, (payload, context) -> {
            String playerId = payload.getPlayerName();

            MinecraftServer server = context.player().getServer();
            if(server == null) return;

            // Rebroadcast key pressed event to all player
            server.getPlayerManager().getPlayerList().forEach(p -> {
                ServerPlayNetworking.send(p, new PlayerTypePayload(playerId));
            });
        });
    }

    public static void sendKeyPressedClient(PlayerEntity player) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeUuid(player.getUuid());
        ClientPlayNetworking.send(new PlayerTypePayload(player.getName().getString()));
    }
}
