package com.peak.aortic.core.blood;

import com.peak.aortic.api.Blood;
import com.peak.aortic.api.BloodUtils;
import com.peak.aortic.core.cca.entity.CombatComponent;
import com.peak.aortic.core.cca.entity.sub.EnderComponent;
import com.peak.aortic.core.index.data.AorticDamageTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;

/**
 * @author Chemthunder
 */
public class EndermanBlood extends Blood {
    public EndermanBlood() {
        super(EntityType.ENDERMAN);
    }

    public void trigger(World world, PlayerEntity source) {
        EnderComponent ender = EnderComponent.KEY.get(source);

        if (source.isSneaking()) {
            BlockPos pos = source.getBlockPos();
            ender.setAsPosition(pos);

            source.sendMessage(Text.translatable("blood.aortic.enderman.set_pos",
                    pos.getX(),
                    pos.getY(),
                    pos.getZ()
            ), true);
        } else {
            if (!CombatComponent.KEY.get(source).isInCombat()) {
                BlockPos pos = ender.getAsPosition();

                source.teleport(
                        pos.getX(),
                        pos.getY(),
                        pos.getZ(),
                        true
                );

                world.playSound(
                        source,
                        source.getBlockPos(),
                        SoundEvents.ENTITY_ENDERMAN_TELEPORT,
                        SoundCategory.PLAYERS,
                        1,
                        1
                );
            }
        }
    }

    public void passive(World world, PlayerEntity source) {
        if (source.isTouchingWaterOrRain()) {
            if (source.canTakeDamage()) {
                source.damage(source.getDamageSources().create(AorticDamageTypes.HYDROPHOBIA), 2.0F);
            }
        }
    }
}

/*
 * Teleportation takes a few seconds and person must be stationary during the process
 */