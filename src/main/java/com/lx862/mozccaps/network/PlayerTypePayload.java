package com.lx862.mozccaps.network;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record PlayerTypePayload(String playerName) implements CustomPayload {
    public static final CustomPayload.Id<PlayerTypePayload> PACKET_ID = new CustomPayload.Id<>(Identifier.of("mozc_caps", "player_typed"));
    public static final PacketCodec<RegistryByteBuf, PlayerTypePayload> PACKET_CODEC = PacketCodec.tuple(PacketCodecs.STRING, PlayerTypePayload::playerName, PlayerTypePayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return PACKET_ID;
    }

    public String getPlayerName() {
        return playerName;
    }
}
