package com.peak.aortic.api.registration;

import com.peak.aortic.api.Blood;
import com.peak.aortic.core.index.AorticRegistries;
import net.acoyt.acornlib.api.template.RegistrantBase;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

/**
 * @author Chemthunder
 */
public class BloodTypeRegistrant extends RegistrantBase<Blood> {
    public BloodTypeRegistrant(String modId) {
        super(modId, AorticRegistries.BLOOD);
    }

    public void registerLang(RegistryWrapper.WrapperLookup wrapperLookup, FabricLanguageProvider.TranslationBuilder translationBuilder) {
        this.toRegister.forEach(blood -> {
            translationBuilder.add(
                "blood." + blood.getId().getNamespace() + "." + blood.getId().getPath(),
                MiscUtils.formatString(blood.getId().getPath()) + " Blood"
            );
        });
    }
}
