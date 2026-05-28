package com.peak.aortic.core.command.util;

import com.peak.aortic.api.Blood;
import net.minecraft.registry.entry.RegistryEntry;

/**
 * @author AcoYT
 */
public class BloodArgument {
    private final RegistryEntry<Blood> event;

    public BloodArgument(RegistryEntry<Blood> event) {
        this.event = event;
    }

    public Blood getEvent() {
        return this.event.value();
    }

    private String getIdString() {
        return this.event.getKey().map(key -> key.getValue().toString()).orElseGet(() -> "unknown[" + this.event + "]");
    }
}
