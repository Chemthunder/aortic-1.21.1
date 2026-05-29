package com.peak.aortic.core.cca;

import com.peak.aortic.core.cca.entity.CombatComponent;
import com.peak.aortic.core.cca.entity.PlayerBloodComponent;
import com.peak.aortic.core.cca.entity.sub.EnderComponent;
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

        registry.registerForPlayers(
                EnderComponent.KEY,
                EnderComponent::new,
                RespawnCopyStrategy.ALWAYS_COPY
        );

        registry.registerForPlayers(
                CombatComponent.KEY,
                CombatComponent::new,
                RespawnCopyStrategy.NEVER_COPY
        );
    }
}
