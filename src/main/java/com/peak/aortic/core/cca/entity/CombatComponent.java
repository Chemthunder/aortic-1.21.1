package com.peak.aortic.core.cca.entity;

import com.peak.aortic.core.Aortic;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;

/**
 * @author Chemthunder
 */
public class CombatComponent implements AutoSyncedComponent, CommonTickingComponent {
    public static final ComponentKey<CombatComponent> KEY = MiscUtils.getOrCreateKey(
            Aortic.id("combat"),
            CombatComponent.class
    );
    private final PlayerEntity player;

    private int combatTicks = 0;

    public CombatComponent(PlayerEntity player) {
        this.player = player;
    }

    public void tick() {
        if (this.combatTicks > 0) {
            this.combatTicks--;
            if (this.combatTicks == 0) {
                this.sync();
                this.player.sendMessage(Text.literal("you're cool fr"), true);
            }
        }
    }

    public void sync() {
        KEY.sync(this.player);
    }

    public void readFromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        this.combatTicks = nbt.getInt("CombatTicks");
    }

    public void writeToNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbt.putInt(
            "CombatTicks",
            this.combatTicks
        );
    }

    public int getCombatTicks() {
        return this.combatTicks;
    }

    public void setCombatTicks(int combatTicks) {
        this.combatTicks = combatTicks;
        this.sync();
    }

    public boolean isInCombat() {
        return this.combatTicks > 0;
    }
}
