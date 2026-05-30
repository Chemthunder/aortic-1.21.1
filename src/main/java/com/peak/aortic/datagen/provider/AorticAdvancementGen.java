package com.peak.aortic.datagen.provider;

import com.peak.aortic.core.Aortic;
import com.peak.aortic.core.index.AorticBlocks;
import com.peak.aortic.core.index.AorticCriterions;
import com.peak.aortic.core.index.AorticItems;
import net.acoyt.acornlib.impl.AcornLib;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.advancement.criterion.TickCriterion;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * @author Chemthunder
 */
@SuppressWarnings("removal")
public class AorticAdvancementGen extends FabricAdvancementProvider {
    public AorticAdvancementGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    public void generateAdvancement(RegistryWrapper.WrapperLookup registryLookup, Consumer<AdvancementEntry> consumer) {
        AdvancementEntry bloodHarvest = Advancement.Builder.createUntelemetered()
                .parent(Identifier.ofVanilla("adventure/root"))
                .display(
                        AorticItems.KNIFE,
                        Text.translatable("advancements.aortic.blood_harvest.title"),
                        Text.translatable("advancements.aortic.blood_harvest.description"),
                        null,
                        AdvancementFrame.GOAL,
                        true,
                        true,
                        false
                ).requirements(AdvancementRequirements.allOf(List.of("harvest")))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.AND)
                .criterion("harvest", AorticCriterions.BLOOD_HARVEST.create(new TickCriterion.Conditions(Optional.empty())))
                .build(Aortic.id("blood_harvest"));

        consumer.accept(bloodHarvest);

        AdvancementEntry useHematologyTable = Advancement.Builder.createUntelemetered()
                .parent(Aortic.id("blood_harvest"))
                .display(
                        Item.fromBlock(AorticBlocks.HEMATOLOGY_TABLE),
                        Text.translatable("advancements.aortic.use_hematology_table.title"),
                        Text.translatable("advancements.aortic.use_hematology_table.description"),
                        null,
                        AdvancementFrame.GOAL,
                        true,
                        true,
                        false
                ).requirements(AdvancementRequirements.allOf(List.of("use")))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.AND)
                .criterion("use", AorticCriterions.USE_HEMATOLOGY_TABLE.create(new TickCriterion.Conditions(Optional.empty())))
                .build(Aortic.id("use_hematology_table"));

        consumer.accept(useHematologyTable);
    }

    public String getName() {
        return "Aortic Advancements";
    }
}
