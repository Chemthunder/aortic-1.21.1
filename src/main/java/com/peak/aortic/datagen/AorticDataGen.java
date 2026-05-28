package com.peak.aortic.datagen;

import com.peak.aortic.core.Aortic;
import com.peak.aortic.datagen.provider.resources.AorticLangGen;
import com.peak.omnia.api.registration.DataInitializer;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryWrapper;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

/**
 * @author Chemthunder
 */
public class AorticDataGen implements DataGeneratorEntrypoint {
	public static final DataInitializer DATA = new DataInitializer(Aortic.MOD_ID, Arrays.asList(

    ));

    public void onInitializeDataGenerator(FabricDataGenerator fdg) {
        var pack = fdg.createPack();

        pack.addProvider(Dyn::new);
        pack.addProvider(AorticLangGen::new);
	}

    public void buildRegistry(RegistryBuilder registryBuilder) {
        DATA.buildRegistries(registryBuilder);
    }

    public static final class Dyn extends FabricDynamicRegistryProvider {
        public Dyn(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, registriesFuture);
        }

        protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
            DATA.loadConfigurations(registries, entries);
        }

        public String getName() {
            return "Aortic Registries";
        }
    }
}
