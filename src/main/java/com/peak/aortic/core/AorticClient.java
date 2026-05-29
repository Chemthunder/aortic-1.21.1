package com.peak.aortic.core;

import com.peak.aortic.core.keybinds.AorticKeybinds;
import net.fabricmc.api.ClientModInitializer;

/**
 * @author Chemthunder
 */
public class AorticClient implements ClientModInitializer {
    public void onInitializeClient() {
        AorticKeybinds.bootstrap();
    }
}
