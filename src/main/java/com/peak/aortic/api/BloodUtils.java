package com.peak.aortic.api;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * @author Chemthunder
 * @author AcoYT
 */
public class BloodUtils {

    /**
     * Checks if a BlockPos is exposed to the sky.
     * @param pos The pos to check
     * @param world The world to check with
     * @author AcoYT
     */
    public static boolean isExposedToSky(BlockPos pos, World world) {
        boolean cannotSeeSky = false;
        for (int i = pos.getY(); i < world.getTopY(); i++) {
            BlockPos blockPos = new BlockPos(pos.getX(), i, pos.getZ());
            if (!world.getBlockState(blockPos).isAir() && !world.getBlockState(blockPos).isReplaceable()) {
                cannotSeeSky = true;
                break;
            }
        }

        return !cannotSeeSky;
    }

    /**
     * Checks if an entity is exposed to the sky.
     * @param entity The entity to check
     * @param world The world to check with
     * @author AcoYT
     */
    public static boolean isExposedToSky(Entity entity, World world) {
        boolean cannotSeeSky = false;
        for (int i = entity.getBlockPos().getY(); i < world.getTopY(); i++) {
            BlockPos blockPos = new BlockPos(entity.getBlockPos().getX(), i, entity.getBlockPos().getZ());
            if (!world.getBlockState(blockPos).isAir() && !world.getBlockState(blockPos).isReplaceable()) {
                cannotSeeSky = true;
                break;
            }
        }

        return !cannotSeeSky;
    }
}
