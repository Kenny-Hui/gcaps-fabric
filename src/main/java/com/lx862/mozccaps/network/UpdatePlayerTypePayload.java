package com.lx862.mozccaps.network;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.Uuids;

import java.util.UUID;

public record UpdatePlayerTypePayload(UUID uuid) implements CustomPayload {
    public static final Id<UpdatePlayerTypePayload> PACKET_ID = new Id<>(Identifier.of("mozc_caps", "update_player_typed"));
    public static final PacketCodec<RegistryByteBuf, UpdatePlayerTypePayload> PACKET_CODEC = Uuids.PACKET_CODEC.xmap(UpdatePlayerTypePayload::new, UpdatePlayerTypePayload::uuid).cast();

    @Override
    public Id<? extends CustomPayload> getId() {
        return PACKET_ID;
    }

    public UUID getPlayerUuid() {
        return uuid;
    }
}
