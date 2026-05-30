package com.peak.aortic.core.blood;

import com.peak.aortic.api.Blood;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

/**
 * @author Chemthunder
 */
public class RabbitBlood extends Blood {
    public RabbitBlood() {
        super(EntityType.RABBIT);
    }

    public void trigger(World world, PlayerEntity source) {
        source.setVelocity(source.getRotationVec(0).multiply(1.0F));
        source.velocityModified = true;
        super.trigger(world, source);
    }
}
