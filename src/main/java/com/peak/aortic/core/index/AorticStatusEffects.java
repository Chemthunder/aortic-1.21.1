package com.peak.aortic.core.index;

import com.peak.aortic.core.Aortic;
import com.peak.aortic.core.effect.ToxinEffect;
import net.acoyt.acornlib.api.registrants.StatusEffectRegistrant;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.entry.RegistryEntry;

/**
 * @author Chemthunder
 */
public interface AorticStatusEffects {
    StatusEffectRegistrant STATUS_EFFECTS = new StatusEffectRegistrant(Aortic.MOD_ID);

    RegistryEntry<StatusEffect> TOXIN = STATUS_EFFECTS.registerRef("toxin", new ToxinEffect());

    static void init() {}
}
