package com.peak.aortic.core.index.data;

import com.peak.aortic.core.Aortic;
import com.peak.omnia.api.registration.core.DamageTypeRegistry;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;

/**
 * @author Chemthunder
 */
public interface AorticDamageTypes {
    DamageTypeRegistry DAMAGE_TYPES = new DamageTypeRegistry(Aortic.MOD_ID);

    RegistryKey<DamageType> HARVEST = DAMAGE_TYPES.register("harvest", 2.0F);
    RegistryKey<DamageType> HYDROPHOBIA = DAMAGE_TYPES.register("hydrophobia", 1.0F);
    RegistryKey<DamageType> DETONATION = DAMAGE_TYPES.register("detonation", 0.0F);
}
