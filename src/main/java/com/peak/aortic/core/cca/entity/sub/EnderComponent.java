package com.peak.aortic.core.cca.entity.sub;

import com.peak.aortic.core.Aortic;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

/**
 * @author Chemthunder
 */
public class EnderComponent implements AutoSyncedComponent {
    public static final ComponentKey<EnderComponent> KEY = MiscUtils.getOrCreateKey(
            Aortic.id("ender"),
            EnderComponent.class
    );
    private final PlayerEntity player;

    private int x = 0;
    private int y = 0;
    private int z = 0;

    public EnderComponent(PlayerEntity player) {
        this.player = player;
    }

    public void sync() {
        KEY.sync(this.player);
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
}
