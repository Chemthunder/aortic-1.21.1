package com.peak.aortic.core.keybinds;

import com.peak.aortic.core.Aortic;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.ladysnake.cca.api.v3.component.tick.ClientTickingComponent;
import org.lwjgl.glfw.GLFW;

/**
 * @author Chemthunder
 */
public class AorticKeybinds {
    public static KeyBinding trigger;

    public static void bootstrap() {
        trigger = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.aortic.trigger",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                "category.aortic"
        ));

        AorticKeybinds.bootstrapPressDetection();
    }

    private static void bootstrapPressDetection() {
        ClientTickEvents.START_CLIENT_TICK.register(minecraftClient -> {
            if (minecraftClient.player != null && trigger.isPressed()) {
                try {

                } catch (Exception e) {
                    Aortic.LOGGER.error("Failed to send TriggerAbilityPayload");
                }
            }
        });
    }
}
