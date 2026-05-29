package com.peak.aortic.core.index;

import com.mojang.serialization.Codec;
import com.peak.aortic.core.Aortic;
import net.acoyt.acornlib.api.registrants.ComponentTypeRegistrant;
import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodecs;

/**
 * @author Chemthunder
 */
public interface AorticComponentTypes {
    ComponentTypeRegistrant COMPONENTS = new ComponentTypeRegistrant(Aortic.MOD_ID);

    static void init() {}
}
