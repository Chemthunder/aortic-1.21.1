package com.peak.aortic.core.index;

import com.peak.aortic.core.Aortic;
import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.index.AcornCriterions;
import net.minecraft.advancement.criterion.Criterion;
import net.minecraft.advancement.criterion.TickCriterion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

/**
 * @author Chemthunder
 */
public interface AorticCriterions {
    TickCriterion BLOOD_HARVEST = create("blood_harvest", new TickCriterion());
    TickCriterion USE_HEMATOLOGY_TABLE = create("use_hematology_table", new TickCriterion());

    static <T extends Criterion<?>> T create(String name, T criterion) {
        return Registry.register(Registries.CRITERION, Aortic.id(name), criterion);
    }

    static void init() {}
}
