package com.peak.aortic.datagen.provider.resources;

import com.peak.aortic.core.index.AorticBloodTypes;
import com.peak.aortic.core.index.AorticItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

/**
 * @author Chemthunder
 */
public class AorticLangGen extends FabricLanguageProvider {
    public AorticLangGen(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        AorticItems.ITEMS.registerLang(registryLookup, translationBuilder);
        AorticBloodTypes.TYPES.registerLang(registryLookup, translationBuilder);

        translationBuilder.add("category.aortic", "Aortic");
        translationBuilder.add("key.aortic.trigger", "Use Blood Ability");
    }

    public String getName() {
        return "Aortic EN_US";
    }
}
