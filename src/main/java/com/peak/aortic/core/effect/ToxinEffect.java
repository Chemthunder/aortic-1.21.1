package com.peak.aortic.core.effect;

import com.peak.aortic.core.index.data.AorticDamageTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.sound.SoundEvent;

/**
 * @author Chemthunder
 */
public class ToxinEffect extends StatusEffect {
    public ToxinEffect() {
        super(StatusEffectCategory.HARMFUL, 0xFF3d8d50);
    }

    public StatusEffect applySound(SoundEvent sound) {
        return super.applySound(sound);
    }

    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        entity.damage(entity.getDamageSources().create(AorticDamageTypes.TOXIN), 1.5F);
        return super.applyUpdateEffect(entity, amplifier);
    }

    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration % 25 == 0;
    }
}
