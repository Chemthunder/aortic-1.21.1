package com.peak.aortic.core.networking.c2s;

import com.peak.aortic.core.Aortic;
import com.peak.aortic.core.cca.entity.PlayerBloodComponent;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.text.Text;

/**
 * @author Chemthunder
 */
public record TriggerAbilityPayload() implements CustomPayload {
    public static final Id<TriggerAbilityPayload> ID = new Id<>(Aortic.id("trigger_ability"));
    public static final PacketCodec<RegistryByteBuf, TriggerAbilityPayload> CODEC = PacketCodec.unit(new TriggerAbilityPayload());

    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public static void send() {
        ClientPlayNetworking.send(new TriggerAbilityPayload());
    }

    public static class Receiver implements ServerPlayNetworking.PlayPayloadHandler<TriggerAbilityPayload> {
        public void receive(TriggerAbilityPayload payload, ServerPlayNetworking.Context context) {
            PlayerEntity player = context.player();
            if (PlayerBloodComponent.KEY.get(player).getCooldownTicks() == 0) {
                PlayerBloodComponent.KEY.get(player).getCurrentBlood().trigger(
                        player.getWorld(),
                        player
                );
            } else {
                player.sendMessage(Text.literal("fuck you"), true);
            }
        }
    }
}
