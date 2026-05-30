package com.peak.aortic.core.block;

import com.peak.aortic.core.cca.entity.PlayerBloodComponent;
import com.peak.aortic.core.client.screen.HematologyScreen;
import com.peak.aortic.core.index.AorticCriterions;
import com.peak.aortic.core.index.AorticSoundEvents;
import com.peak.aortic.core.index.data.AorticDamageTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * @author Chemthunder
 */
public class HematologyTableBlock extends Block {
    public HematologyTableBlock(Settings settings) {
        super(settings);
    }

    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        PlayerBloodComponent bloodComponent = PlayerBloodComponent.KEY.get(player);

        if (bloodComponent.getCurrentBlood().isSpliced()) {
            player.damage(
                    player.getDamageSources().create(AorticDamageTypes.HEMATOLIC),
                    5.0F
            );

            player.playSoundToPlayer(
                    AorticSoundEvents.ITEM_HARVEST,
                    SoundCategory.PLAYERS,
                    1,
                    1
            );

            if (world.isClient()) {
                player.swingHand(player.getActiveHand());

                MinecraftClient.getInstance().setScreen(new HematologyScreen(bloodComponent.getCurrentBlood()));
            }

            if (player instanceof ServerPlayerEntity serverPlayer) {
                AorticCriterions.USE_HEMATOLOGY_TABLE.trigger(serverPlayer);
            }
        }

        return super.onUse(state, world, pos, player, hit);
    }
}
