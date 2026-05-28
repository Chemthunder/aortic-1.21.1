package com.peak.aortic.core.cca.entity;

import com.peak.aortic.api.Blood;
import com.peak.aortic.core.Aortic;
import com.peak.aortic.core.index.AorticBloodTypes;
import com.peak.aortic.core.index.AorticItems;
import com.peak.aortic.core.index.AorticRegistries;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;

/**
 * @author Chemthunder
 */
public class PlayerBloodComponent implements AutoSyncedComponent, CommonTickingComponent {
    public static final ComponentKey<PlayerBloodComponent> KEY = MiscUtils.getOrCreateKey(
            Aortic.id("player_blood"),
            PlayerBloodComponent.class
    );
    private final PlayerEntity player;

    private Blood currentBlood = AorticBloodTypes.BASE;

    public PlayerBloodComponent(PlayerEntity player) {
        this.player = player;
    }

    public void tick() {
        currentBlood.passive(player.getWorld(), player);
    }

    public void sync() {
        KEY.sync(this.player);
    }

    public void readFromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        if (nbt.contains("Blood")) {
            this.currentBlood = AorticRegistries.BLOOD.get(Identifier.of(nbt.getString("Blood")));
        } else {
            this.currentBlood = AorticBloodTypes.BASE;
        }
    }

    public void writeToNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        if (this.currentBlood != null) {
            Identifier identifier = AorticRegistries.BLOOD.getId(this.currentBlood);
            nbt.putString("Blood", identifier != null ? identifier.toString() : AorticBloodTypes.BASE.getId().toString());
        }
    }

    public Blood getCurrentBlood() {
        return this.currentBlood;
    }

    public void setCurrentBlood(Blood blood) {
        this.currentBlood = blood;
        this.sync();
    }
}
