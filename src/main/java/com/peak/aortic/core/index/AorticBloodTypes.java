package com.peak.aortic.core.index;

import com.peak.aortic.api.Blood;
import com.peak.aortic.api.registration.BloodTypeRegistrant;
import com.peak.aortic.core.Aortic;
import com.peak.aortic.core.blood.CreeperBlood;
import com.peak.aortic.core.blood.EndermanBlood;
import com.peak.aortic.core.blood.PigBlood;
import net.minecraft.entity.EntityType;

/**
 * @author Chemthunder
 */
public interface AorticBloodTypes {
    BloodTypeRegistrant TYPES = new BloodTypeRegistrant(Aortic.MOD_ID);

    Blood PLAYER = TYPES.register("player", new Blood(EntityType.PLAYER));

    Blood PIG = TYPES.register(
        "pig",
        new PigBlood()
    );

    Blood ENDERMAN = TYPES.register(
        "enderman",
        new EndermanBlood()
    );

    Blood CREEPER = TYPES.register(
        "creeper",
        new CreeperBlood()
    );

    static void init() {}
}
