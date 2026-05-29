package com.peak.aortic.core.index;

import com.peak.aortic.core.networking.c2s.TriggerAbilityPayload;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

/**
 * @author Chemthunder
 */
public interface AorticNetworking {
    static void init() {
        PayloadTypeRegistry.playC2S().register(TriggerAbilityPayload.ID, TriggerAbilityPayload.CODEC);
    }

    static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(TriggerAbilityPayload.ID, new TriggerAbilityPayload.Receiver());
    }

    @Environment(EnvType.CLIENT)
    static void registerS2CPackets() {}
}
