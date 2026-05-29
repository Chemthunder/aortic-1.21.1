package com.peak.aortic.datagen.provider.tag;

import com.peak.aortic.core.index.data.AorticDamageTypes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.DamageTypeTags;

import java.util.concurrent.CompletableFuture;

/**
 * @author Chemthunder
 */
public class AorticDamageTypeTagGen extends FabricTagProvider<DamageType> {
    public AorticDamageTypeTagGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.DAMAGE_TYPE, registriesFuture);
    }

    protected void configure(RegistryWrapper.WrapperLookup lookup) {
        this.getOrCreateTagBuilder(DamageTypeTags.NO_KNOCKBACK)
                .add(AorticDamageTypes.HARVEST)
                .setReplace(false);

        this.getOrCreateTagBuilder(DamageTypeTags.BYPASSES_ARMOR)
                .add(AorticDamageTypes.HYDROPHOBIA)
                .setReplace(false);
    }
}
