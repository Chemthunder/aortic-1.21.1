package com.peak.aortic.core.cca.entity.sub;

import com.peak.aortic.core.Aortic;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;

/**
 * @author Chemthunder
 */
public class EnderComponent implements AutoSyncedComponent, CommonTickingComponent {
    public static final ComponentKey<EnderComponent> KEY = MiscUtils.getOrCreateKey(
            Aortic.id("ender"),
            EnderComponent.class
    );
    private final PlayerEntity player;

    private int x = 0;
    private int y = 0;
    private int z = 0;

    private int teleportingTicks = 0;

    public EnderComponent(PlayerEntity player) {
        this.player = player;
    }

    public void sync() {
        KEY.sync(this.player);
    }

    public void tick() {
        World world = this.player.getWorld();

        if (this.teleportingTicks > 0) {
            this.teleportingTicks--;

            if (this.player.getWorld() instanceof ServerWorld serverWorld) {
                serverWorld.spawnParticles(ParticleTypes.PORTAL,
                        this.player.getX(),
                        this.player.getY(),
                        this.player.getZ(),
                        6,
                        0.3F,
                        1,
                        0.3F,
                        0.08F
                );
            }

            if (this.teleportingTicks == 0) {
                this.sync();

                this.player.teleport(
                        this.x + 0.5F,
                        this.y,
                        this.z + 0.5F,
                        true
                );

                world.playSound(
                        this.player,
                        this.player.getBlockPos(),
                        SoundEvents.ENTITY_ENDERMAN_TELEPORT,
                        SoundCategory.PLAYERS,
                        1,
                        1
                );
            }
        }
    }

    public void readFromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        this.x = nbt.getInt("X");
        this.y = nbt.getInt("Y");
        this.z = nbt.getInt("Z");
    }

    public void writeToNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbt.putInt(
            "X",
            this.x
        );
        
        nbt.putInt(
            "Y",
            this.y
        );

        nbt.putInt(
            "Z",
            this.z
        );
    }

    public BlockPos getAsPosition() {
        return new BlockPos(
                this.x,
                this.y,
                this.z
        );
    }

    public void setAsPosition(BlockPos pos) {
        this.x = pos.getX();
        this.y = pos.getY();
        this.z = pos.getZ();

        this.sync();
    }

    public boolean isViable() {
        return this.x != 0 && this.y != 0 && this.z != 0;
    }

    public boolean isTeleporting() {
        return this.teleportingTicks > 0;
    }

    public void teleport() {
        this.teleportingTicks = 60;
        this.sync();
    }

    public void halt() {
        this.teleportingTicks = 0;
        this.sync();
    }
}
