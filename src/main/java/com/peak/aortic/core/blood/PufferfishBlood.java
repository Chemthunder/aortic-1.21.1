package com.peak.aortic.core.blood;

import com.peak.aortic.api.Blood;
import com.peak.aortic.core.index.AorticStatusEffects;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

/**
 * @author Chemthunder
 */
public class PufferfishBlood extends Blood {
    public PufferfishBlood() {
        super(EntityType.PUFFERFISH);
    }

    public void trigger(World world, PlayerEntity source) {
        List<LivingEntity> NEARBY = world.getEntitiesByClass(
                LivingEntity.class,
                new Box(source.getBlockPos()).expand(5),
                entity -> true
        );
        NEARBY.forEach(living -> {
            if (living != source) {
                living.addStatusEffect(
                        new StatusEffectInstance(
                                AorticStatusEffects.TOXIN,
                                400
                        )
                );
            }
        });
    }
}
