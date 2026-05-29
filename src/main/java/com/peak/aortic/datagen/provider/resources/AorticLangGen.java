package com.peak.aortic.datagen.provider.resources;

import com.peak.aortic.core.index.AorticBloodTypes;
import com.peak.aortic.core.index.AorticItems;
import com.peak.aortic.core.index.data.AorticDamageTypes;
import com.peak.omnia.api.util.resources.LangUtils;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

/**
 * @author Chemthunder
 */
public class AorticLangGen extends FabricLanguageProvider {
    public AorticLangGen(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(
            dataOutput,
            registryLookup
        );
    }

    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        AorticItems.ITEMS.registerLang(
            registryLookup,
            translationBuilder
        );

        AorticBloodTypes.TYPES.registerLang(
            registryLookup,
            translationBuilder
        );

        AorticLangGen.registerDamageTypes(translationBuilder);

        // KEYBINDS
        translationBuilder.add(
            "category.aortic",
            "Aortic"
        );
        translationBuilder.add(
            "key.aortic.trigger",
            "Use Blood Ability"
        );

        // SUBTITLES
        translationBuilder.add(
            "subtitles.aortic.item.harvest",
            "Blood is harvested"
        );

        // BLOOD FEEDBACK
        translationBuilder.add(
            "blood.aortic.enderman.set_pos",
            "Set waypoint to %s %s %s" // X, Y, Z of new pos
        );

        // COMMANDS
        translationBuilder.add(
            "command.blood.get.feedback",
            "Current blood type is %s" // Current blood in source
        );
        translationBuilder.add(
            "command.blood.set.feedback",
            "Set blood type to %s" // Blood type to set to
        );
    }

    private static void registerDamageTypes(TranslationBuilder translationBuilder) {
        LangUtils.damageType(translationBuilder, AorticDamageTypes.HARVEST,
                "%1$s couldn't take the heat",
                "%1$s bled out at the hands of %2$s",
                "%1$s bled out at the hands of %2$s, wielding %3$s"
        );

        LangUtils.damageType(translationBuilder, AorticDamageTypes.HYDROPHOBIA,
                "%1$s had an aversion to liquids",
                "%1$s over-hydrated whilst fighting %2$s",
                "%1$s over-hydrated whilst fighting %2$s, wielding %3$s"
        );

        LangUtils.damageType(translationBuilder, AorticDamageTypes.DETONATION,
                "%1$s self-destructed",
                "%1$s got caught in %2$s's radius",
                "%1$s got caught in %2$s's radius who was wielding %3$s"
        );
    }

    public String getName() {
        return "Aortic Lang";
    }
}
