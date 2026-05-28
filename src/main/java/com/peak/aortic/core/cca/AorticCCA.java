package com.peak.aortic.core.cca;

import com.peak.aortic.core.cca.entity.PlayerBloodComponent;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;

/**
 * @author Chemthunder
 */
public class AorticCCA implements EntityComponentInitializer {
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(
                PlayerBloodComponent.KEY,
                PlayerBloodComponent::new,
                RespawnCopyStrategy.NEVER_COPY
        );
    }
}
