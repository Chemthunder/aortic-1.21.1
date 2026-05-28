package com.peak.aortic.core;

import com.peak.aortic.core.command.BloodCommand;
import com.peak.aortic.core.index.*;
import com.peak.aortic.core.keybinds.AorticKeybinds;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Chemthunder
 */
public class Aortic implements ModInitializer {
	public static final String MOD_ID = "aortic";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public void onInitialize() {
        AorticBloodTypes.init();
        AorticItems.init();
        AorticRegistries.init();
        AorticComponentTypes.init();

        CommandRegistrationCallback.EVENT.register(new BloodCommand());

        AorticNetworking.init();
        AorticNetworking.registerC2SPackets();

        AorticKeybinds.bootstrap();

		LOGGER.info("Aortic has been initialized successfully!");
	}

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
}