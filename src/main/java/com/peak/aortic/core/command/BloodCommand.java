package com.peak.aortic.core.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.peak.aortic.core.cca.entity.PlayerBloodComponent;
import com.peak.aortic.core.command.util.BloodArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

/**
 * @author Chemthunder
 */
public class BloodCommand implements CommandRegistrationCallback {
    public void register(CommandDispatcher<ServerCommandSource> commandDispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        commandDispatcher.register(literal("blood")
                .then(literal("set").then(argument("bloodType", BloodArgumentType.blood(commandRegistryAccess)).executes(context -> {
                    PlayerEntity player = context.getSource().getPlayer();
                    if (player != null) {
                        PlayerBloodComponent bloodComponent = PlayerBloodComponent.KEY.get(player);

                        bloodComponent.setCurrentBlood(
                            BloodArgumentType.getBloodArgument(
                                context,
                                "bloodType"
                            ).getEvent()
                        );

                        context.getSource().sendFeedback(() -> 
                            Text.translatable(
                                "command.blood.set.feedback",
                                bloodComponent.getCurrentBlood().getId()
                            ),
                            false
                        );
                    }
                    return Command.SINGLE_SUCCESS;
                })))

                .then(literal("get").executes(context -> {
                    PlayerEntity player = context.getSource().getPlayer();
                    if (player != null) {
                        PlayerBloodComponent bloodComponent = PlayerBloodComponent.KEY.get(player);

                        context.getSource().sendFeedback(() -> 
                            Text.translatable(
                                "command.blood.get.feedback",
                                bloodComponent.getCurrentBlood().getId()
                            ),
                            false
                        );
                    }

                    return Command.SINGLE_SUCCESS;
                }))
        );
    }
}
