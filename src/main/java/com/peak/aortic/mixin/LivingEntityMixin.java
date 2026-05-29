package com.peak.aortic.mixin;

import com.peak.aortic.core.cca.entity.CombatComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Chemthunder
 */
@Mixin(value = LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method = "applyDamage", at = @At(value = "HEAD"))
    private void aortic$combatTicks(DamageSource source, float amount, CallbackInfo ci) {
        if (source.getAttacker() != null) {
            LivingEntity entity = (LivingEntity) (Object) this;

            if (entity instanceof PlayerEntity player) {
                CombatComponent component = CombatComponent.KEY.get(player);
                component.setCombatTicks(400);
            }
        }
    }
}
