package com.peak.aortic.datagen.provider.resources;

import com.peak.aortic.core.index.AorticBlocks;
import com.peak.aortic.core.index.AorticBloodTypes;
import com.peak.aortic.core.index.AorticItems;
import com.peak.aortic.core.index.AorticStatusEffects;
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
        super(dataOutput, registryLookup);
    }

    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        AorticItems.ITEMS.registerLang(registryLookup, translationBuilder);
        AorticBloodTypes.TYPES.registerLang(registryLookup, translationBuilder);
        AorticStatusEffects.STATUS_EFFECTS.registerLang(registryLookup, translationBuilder);
        AorticBlocks.BLOCKS.registerLang(registryLookup, translationBuilder);

        AorticLangGen.registerDamageTypes(translationBuilder);

        translationBuilder.add("category.aortic", "Aortic");
        translationBuilder.add("key.aortic.trigger", "Use Blood Ability");

        translationBuilder.add("item.aortic.knife.refuse", "You cannot splice your blood with this entity!");

        translationBuilder.add("subtitles.aortic.item.harvest", "Blood is harvested");

        translationBuilder.add("blood.aortic.enderman.set_pos", "Set waypoint to %s %s %s");
        translationBuilder.add("blood.aortic.enderman.teleport", "You are now teleporting!");

        translationBuilder.add("blood.aortic.cannot_use.combat", "You are in combat right now!");

        translationBuilder.add("command.blood.set.feedback", "Set blood to %s");
        translationBuilder.add("command.blood.get.feedback", "Current blood is %s");

        // BLOOD
        translationBuilder.add("blood.aortic.enderman.trigger", "When sneaking, saves a position to teleport back to. When not sneaking, teleports to said position over ~5s. If hit during this time, the timer will stop.");
        translationBuilder.add("blood.aortic.enderman.passive", "Will take damage whilst touching water or when exposed to rain.");

        translationBuilder.add("blood.aortic.rabbit.trigger", "Dashes forward with great speed in whichever direction currently being looked in.");
        translationBuilder.add("blood.aortic.rabbit.passive", "Fall damage is doubled.");

        translationBuilder.add("blood.aortic.pufferfish.trigger", "Releases an invisible cloud of toxins, poisoning all nearby enemies with an armor-piercing sting.");
        translationBuilder.add("blood.aortic.pufferfish.passive", "Can breathe underwater indefinitely.");

        // ADVANCEMENT
        translationBuilder.add("advancements.aortic.blood_harvest.title", "A Taste so Divine...");
        translationBuilder.add("advancements.aortic.blood_harvest.description", "Sample another being's bloodflow.");

        translationBuilder.add("advancements.aortic.use_hematology_table.title", "An Aortic Work of Art");
        translationBuilder.add("advancements.aortic.use_hematology_table.description", "Take a peek into the inner-workings of your body.");
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

        LangUtils.damageType(translationBuilder, AorticDamageTypes.HEMATOLIC,
                "%1$s's heart gave out",
                "%1$s's heart gave out whilst fighting %2$s",
                "%1$s's heart gave out whilst fighting %2$s who was wielding %3$s"
        );

        LangUtils.damageType(translationBuilder, AorticDamageTypes.TOXIN,
                "%1$s's immune system failed",
                "%1$s got stung by %2$s",
                "%1$s got stung by %2$s who was wielding %3$s"
        );
    }

    public String getName() {
        return "Aortic Language";
    }
}
