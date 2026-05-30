package com.peak.aortic.mixin;

import com.peak.aortic.core.cca.entity.CombatComponent;
import com.peak.aortic.core.cca.entity.sub.EnderComponent;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author Chemthunder
 */
@Mixin(value = PlayerEntity.class)
public abstract class PlayerEntityMixin {
    @Shadow public abstract void sendMessage(Text message, boolean overlay);
    @Shadow public abstract void playSoundToPlayer(SoundEvent sound, SoundCategory category, float volume, float pitch);

    @Inject(method = "damage", at = @At(value = "HEAD"))
    private void aortic$combat(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (source.getAttacker() != null) {
            CombatComponent.KEY.get(this).setCombatTicks(400);
            this.sendMessage(Text.literal("you're in combat now"), true);
            this.sendMessage(Text.literal(CombatComponent.KEY.get(this).getCombatTicks() + ""), false);
        }
    }

    @Inject(method = "damage", at = @At(value = "HEAD"))
    private void aortic$endermanHalt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        EnderComponent ender = EnderComponent.KEY.get(this);

        if (ender.isTeleporting()) {
            ender.halt();
            this.sendMessage(Text.literal("Teleportation halted!"), true);
            this.playSoundToPlayer(SoundEvents.ENTITY_ENDERMAN_HURT, SoundCategory.HOSTILE, 1, 1);
        }
    }
}
