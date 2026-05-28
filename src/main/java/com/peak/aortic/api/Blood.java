package com.peak.aortic.api;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.peak.aortic.core.index.AorticRegistries;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

/**
 * @author Chemthunder
 */
public class Blood {
    private final EntityType<?> type;

    public Blood(EntityType<?> type) {
        this.type = type;
    }

    public void trigger(World world, PlayerEntity source) {}

    public void passive(World world, PlayerEntity source) {}

    public void altPassive(World world, PlayerEntity source) {}

    public Identifier getId() {
        return AorticRegistries.BLOOD.getId(this);
    }

    public EntityType<?> getType() {
        return this.type;
    }

    public static final Codec<Blood> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Registries.ENTITY_TYPE.getCodec().optionalFieldOf("donatorType", EntityType.PLAYER).forGetter(Blood::getType)
    ).apply(instance, Blood::new));

    public static final PacketCodec<ByteBuf, Blood> PACKET_CODEC = PacketCodecs.codec(CODEC);
}
