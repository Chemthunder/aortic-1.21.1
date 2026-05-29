package com.peak.aortic.api;

import com.peak.aortic.core.Aortic;
import com.peak.aortic.core.index.AorticRegistries;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

/**
 * @author Chemthunder
 */
public class Blood {
    private final @Nullable EntityType<?> type;

    public Blood(@Nullable EntityType<?> type) {
        this.type = type;
    }

    public void trigger(World world, PlayerEntity source) {}

    public void passive(World world, PlayerEntity source) {}

    public void altPassive(World world, PlayerEntity source) {}

    public Identifier getId() {
        return AorticRegistries.BLOOD.getId(this);
    }

    public @Nullable EntityType<?> getType() {
        return this.type;
    }

    public static Blood getBloodFromTarget(LivingEntity entity) {
        Blood toApply = null;

        for (Blood blood : AorticRegistries.BLOOD) {
            if (blood.getType().equals(entity.getType())) {
                Aortic.LOGGER.info(entity.getType().getTranslationKey());
                Aortic.LOGGER.info(blood.getId().toString());
                toApply = blood;
                break;
            }
        }
        return toApply;
    }
}
