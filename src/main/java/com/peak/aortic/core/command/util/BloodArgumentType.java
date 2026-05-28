package com.peak.aortic.core.command.util;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.command.CommandRegistryAccess;

import java.util.concurrent.CompletableFuture;

/**
 * @author AcoYT
 */
public class BloodArgumentType implements ArgumentType<BloodArgument> {
    private final BloodStringReader reader;

    public BloodArgumentType(CommandRegistryAccess registryAccess) {
        this.reader = new BloodStringReader(registryAccess);
    }

    public static BloodArgumentType blood(CommandRegistryAccess registryAccess) {
        return new BloodArgumentType(registryAccess);
    }

    public BloodArgument parse(StringReader stringReader) throws CommandSyntaxException {
        BloodStringReader.BloodResult eventResult = this.reader.consume(stringReader);
        return new BloodArgument(eventResult.event());
    }

    public static <S> BloodArgument getBloodArgument(CommandContext<S> context, String name) {
        return context.getArgument(name, BloodArgument.class);
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return this.reader.getSuggestions(builder);
    }
}
