package com.peak.aortic.core.blood;

import com.peak.aortic.api.Blood;
import com.peak.aortic.core.index.AorticGameRules;
import com.peak.aortic.core.index.data.AorticDamageTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

/**
 * @author Chemthunder
 */
public class CreeperBlood extends Blood {
    public CreeperBlood() {
        super(EntityType.CREEPER);
    }

    public void trigger(World world, PlayerEntity source) {
        source.damage(
                world.getDamageSources().create(AorticDamageTypes.DETONATION),
                Integer.MAX_VALUE
        );

        if (world.getGameRules().getBoolean(AorticGameRules.allowCreeperLightning)) {
            if (world.getRandom().nextBetween(0, 10) > 8) {
                List<LivingEntity> NEARBY = world.getEntitiesByClass(
                        LivingEntity.class,
                        new Box(source.getBlockPos()).expand(30),
                        entity -> true
                );
                NEARBY.forEach(living -> {
                    living.damage(
                            living.getDamageSources().create(AorticDamageTypes.DETONATION, source),
                            Integer.MAX_VALUE
                    );
                });
            }
        } else {
            List<LivingEntity> NEARBY = world.getEntitiesByClass(
                    LivingEntity.class,
                    new Box(source.getBlockPos()).expand(4),
                    entity -> true
            );
            NEARBY.forEach(living -> {
                living.damage(
                        world.getDamageSources().create(AorticDamageTypes.DETONATION),
                        Integer.MAX_VALUE
                );
            });
        }

        world.addParticle(
                ParticleTypes.EXPLOSION_EMITTER,
                true,
                source.getX() + 0.5F,
                source.getY() + 0.5F,
                source.getZ() + 0.5F,
                0,
                0,
                0
        );

        world.playSound(
                source,
                source.getBlockPos(),
                SoundEvents.ENTITY_GENERIC_EXPLODE.value(),
                SoundCategory.NEUTRAL,
                1,
                1
        );
    }
}
