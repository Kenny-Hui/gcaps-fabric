package com.lx862.mozccaps.network;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.Uuids;

import java.util.UUID;

public record PlayerTypePayload(UUID player) implements CustomPayload {
    public static final CustomPayload.Id<PlayerTypePayload> PACKET_ID = new CustomPayload.Id<>(Identifier.of("mozc_caps", "player_typed"));
    public static final PacketCodec<RegistryByteBuf, PlayerTypePayload> PACKET_CODEC = Uuids.PACKET_CODEC.xmap(PlayerTypePayload::new, PlayerTypePayload::player).cast();

    @Override
    public Id<? extends CustomPayload> getId() {
        return PACKET_ID;
    }

    public UUID getPlayerUuid() {
        return player;
    }
}
