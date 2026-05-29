package com.peak.aortic.core.index;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;

/**
 * @author Chemthunder
 */
public interface AorticGameRules {
    GameRules.Key<GameRules.BooleanRule> allowCreeperLightning = GameRuleRegistry.register(
            "allowBloodCreeperLightning",
            GameRules.Category.PLAYER,
            GameRuleFactory.createBooleanRule(false)
    );

    static void init() {}
}
