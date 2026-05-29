package com.peak.aortic.datagen.provider.resources;

import com.peak.aortic.core.Aortic;
import com.peak.aortic.core.index.AorticSoundEvents;
import com.peak.omnia.api.provider.ModdedSoundsProvider;
import com.peak.omnia.api.provider.SoundTypeBuilder;
import net.minecraft.data.DataOutput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

/**
 * @author Chemthunder
 */
public class AorticSoundsGen extends ModdedSoundsProvider {
    public AorticSoundsGen(DataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(
            output,
            registriesFuture
        );
    }

    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup, SoundExporter soundExporter) {
        soundExporter.add(
            AorticSoundEvents.ITEM_HARVEST,
            sound(Aortic.id("item/harvest"))
            .subtitle("subtitles.aortic.item.harvest")
        );
    }

    public String getName() {
        return "Aortic Sounds";
    }

    private static SoundTypeBuilder sound(Identifier id) {
        return SoundTypeBuilder.of().sound(
            SoundTypeBuilder.EntryBuilder.create(
                SoundTypeBuilder.RegistrationType.FILE,
                id
            )
        );
    }
}
