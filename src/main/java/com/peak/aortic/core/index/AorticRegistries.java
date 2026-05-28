package com.peak.aortic.core.index;

import com.peak.aortic.api.Blood;
import com.peak.aortic.core.Aortic;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;

/**
 * @author Chemthunder
 */
public interface AorticRegistries {
    RegistryKey<Registry<Blood>> BLOOD_KEY = RegistryKey.ofRegistry(Aortic.id("blood"));
    Registry<Blood> BLOOD = FabricRegistryBuilder.createSimple(BLOOD_KEY)
            .attribute(RegistryAttribute.MODDED)
            .buildAndRegister();

    static void init() {}
}
