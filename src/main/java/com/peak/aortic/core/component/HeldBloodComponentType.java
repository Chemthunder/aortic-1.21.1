package com.peak.aortic.core.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.peak.aortic.api.Blood;
import com.peak.aortic.core.index.AorticBloodTypes;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityType;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;

import java.lang.reflect.RecordComponent;

/**
 * @author Chemthunder
 */
public record HeldBloodComponentType(Blood blood, String giverName) {
    public static final Codec<HeldBloodComponentType> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Blood.CODEC.optionalFieldOf("blood", AorticBloodTypes.BASE).forGetter(HeldBloodComponentType::blood),
            Codec.STRING.optionalFieldOf("giver", "").forGetter(HeldBloodComponentType::giverName)
    ).apply(instance, HeldBloodComponentType::new));

    public static final PacketCodec<ByteBuf, HeldBloodComponentType> PACKET_CODEC = PacketCodecs.codec(CODEC);
}
