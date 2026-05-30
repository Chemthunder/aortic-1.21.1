package com.peak.aortic.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.peak.aortic.core.cca.entity.PlayerBloodComponent;
import com.peak.aortic.core.cca.entity.sub.EnderComponent;
import com.peak.aortic.core.index.AorticBloodTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author Chemthunder
 */
@Mixin(value = LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method = "applyMovementInput", at = @At(value = "HEAD"))
    private void aortic$haltTeleportIfMove(Vec3d movementInput, float slipperiness, CallbackInfoReturnable<Vec3d> cir) {
        if (movementInput.getX() != 0 || movementInput.getZ() != 0) {
            if ((Object) this instanceof PlayerEntity player) {
                EnderComponent ender = EnderComponent.KEY.get(player);

                if (ender.isTeleporting()) {
                    ender.halt();
                    player.sendMessage(Text.literal("Resetting teleportation..."));
                }
            }
        }
    }

    @WrapMethod(method = "getNextAirUnderwater")
    private int aortic$noDrown(int air, Operation<Integer> original) {
        if ((Object) this instanceof PlayerEntity player) {
            if (PlayerBloodComponent.KEY.get(player).getCurrentBlood().equals(AorticBloodTypes.PUFFERFISH)) {
                return player.getMaxAir();
            }
        }

        return original.call(air);
    }
}
