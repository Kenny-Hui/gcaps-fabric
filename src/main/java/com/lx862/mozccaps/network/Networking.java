package com.lx862.mozccaps.network;

import com.lx862.mozccaps.MainClient;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

import java.util.UUID;

public class Networking {
    public static final Identifier PLAYER_TYPED = new Identifier("mozc_caps", "player_typed");

    public static void registerReceiverClient() {
        ClientPlayNetworking.registerGlobalReceiver(PLAYER_TYPED, (client, handler, buf, responseSender) -> {
            UUID playerTyped = buf.readUuid();
            MainClient.keyPressedList.put(playerTyped, 0.0);
        });
    }

    public static void registerReceiverServer() {
        ServerPlayNetworking.registerGlobalReceiver(PLAYER_TYPED, (server, player, handler, buf, responseSender) -> {
            UUID playerUuid = buf.readUuid();
            server.execute(() -> {
                // Rebroadcast key pressed event to all player
                server.getPlayerManager().getPlayerList().forEach(p -> {
                    PacketByteBuf buf2 = PacketByteBufs.create();
                    buf2.writeUuid(playerUuid);
                    ServerPlayNetworking.send(p, PLAYER_TYPED, buf2);
                });
            });
        });
    }

    public static void sendKeyPressedClient(PlayerEntity player) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeUuid(player.getUuid());
        ClientPlayNetworking.send(PLAYER_TYPED, buf);
    }
}
