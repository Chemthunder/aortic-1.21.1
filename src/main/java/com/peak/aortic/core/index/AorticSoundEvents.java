package com.peak.aortic.core.index;

import com.peak.aortic.core.Aortic;
import net.acoyt.acornlib.api.registrants.SoundEventRegistrant;
import net.minecraft.sound.SoundEvent;

/**
 * @author Chemthunder
 */
public interface AorticSoundEvents {
    SoundEventRegistrant SOUND_EVENTS = new SoundEventRegistrant(Aortic.MOD_ID);

    SoundEvent ITEM_HARVEST = SOUND_EVENTS.register("item.harvest");

    static void init() {}
}
