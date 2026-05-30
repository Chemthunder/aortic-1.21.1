package com.peak.aortic.core.index;

import com.peak.aortic.api.Blood;
import com.peak.aortic.api.registration.BloodTypeRegistrant;
import com.peak.aortic.core.Aortic;
import com.peak.aortic.core.blood.*;
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

    Blood RABBIT = TYPES.register(
            "rabbit",
            new RabbitBlood()
    );

    Blood PUFFERFISH = TYPES.register(
            "pufferfish",
            new PufferfishBlood()
    );

    static void init() {}
}
