package com.peak.aortic.datagen.provider.resources;

import com.peak.aortic.core.index.AorticItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

/**
 * @author Chemthunder
 */
public class AorticModelGen extends FabricModelProvider {
    public AorticModelGen(FabricDataOutput output) {
        super(output);
    }

    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {}

    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(AorticItems.KNIFE, Models.HANDHELD);
        itemModelGenerator.register(AorticItems.KNIFE, "_bloody", Models.HANDHELD);
    }

    public String getName() {
        return "Aortic Models";
    }
}
