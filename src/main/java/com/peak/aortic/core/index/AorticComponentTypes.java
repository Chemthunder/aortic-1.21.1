package com.peak.aortic.core.index;

import com.peak.aortic.core.Aortic;
import com.peak.aortic.core.component.HeldBloodComponentType;
import net.acoyt.acornlib.api.registrants.ComponentTypeRegistrant;
import net.minecraft.component.ComponentType;

/**
 * @author Chemthunder
 */
public interface AorticComponentTypes {
    ComponentTypeRegistrant COMPONENTS = new ComponentTypeRegistrant(Aortic.MOD_ID);

    ComponentType<HeldBloodComponentType> HELD_BLOOD = COMPONENTS.register(
            "held_blood", builder -> builder
                    .codec(HeldBloodComponentType.CODEC)
                    .packetCodec(HeldBloodComponentType.PACKET_CODEC)
    );

    static void init() {}
}
