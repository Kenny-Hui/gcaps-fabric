package com.lx862.mozccaps.network;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record UpdatePlayerTypePayload(String playerName) implements CustomPayload {
    public static final Id<UpdatePlayerTypePayload> PACKET_ID = new Id<>(Identifier.of("mozc_caps", "update_player_typed"));
    public static final PacketCodec<RegistryByteBuf, UpdatePlayerTypePayload> PACKET_CODEC = PacketCodec.tuple(PacketCodecs.STRING, UpdatePlayerTypePayload::playerName, UpdatePlayerTypePayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return PACKET_ID;
    }

    public String getPlayerName() {
        return playerName;
    }
}
