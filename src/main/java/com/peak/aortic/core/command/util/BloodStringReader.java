package com.peak.aortic.core.command.util;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.mojang.serialization.DynamicOps;
import com.peak.aortic.api.Blood;
import com.peak.aortic.core.index.AorticRegistries;
import net.minecraft.command.CommandSource;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtOps;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.mutable.MutableObject;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * @author AcoYT
 */
public class BloodStringReader {
    public static final DynamicCommandExceptionType INVALID_EVENT_ID_EXCEPTION = new DynamicCommandExceptionType(
            id -> Text.stringifiedTranslatable("argument.blood.id.invalid", id));

    public static final Function<SuggestionsBuilder, CompletableFuture<Suggestions>> SUGGEST_DEFAULT = SuggestionsBuilder::buildFuture;

    public final RegistryWrapper.Impl<Blood> eventRegistry;
    public final DynamicOps<NbtElement> nbtOps;

    public BloodStringReader(RegistryWrapper.WrapperLookup registryLookup) {
        this.eventRegistry = registryLookup.getWrapperOrThrow(AorticRegistries.BLOOD_KEY);
        this.nbtOps = registryLookup.getOps(NbtOps.INSTANCE);
    }

    public BloodStringReader.BloodResult consume(StringReader reader) throws CommandSyntaxException {
        final MutableObject<RegistryEntry<Blood>> mutableObject = new MutableObject<>();

        this.consume(reader, new BloodStringReader.Callbacks() {
            public void onBlood(RegistryEntry<Blood> item) {
                mutableObject.setValue(item);
            }
        });

        RegistryEntry<Blood> registryEntry = Objects.requireNonNull(mutableObject.getValue(), "Parser gave no item");
        return new BloodStringReader.BloodResult(registryEntry);
    }

    public void consume(StringReader reader, BloodStringReader.Callbacks callbacks) throws CommandSyntaxException {
        int i = reader.getCursor();

        try {
            new BloodStringReader.Reader(reader, callbacks).read();
        } catch (CommandSyntaxException var5) {
            reader.setCursor(i);
            throw var5;
        }
    }

    public CompletableFuture<Suggestions> getSuggestions(SuggestionsBuilder builder) {
        StringReader stringReader = new StringReader(builder.getInput());
        stringReader.setCursor(builder.getStart());
        BloodStringReader.SuggestionCallbacks suggestionCallbacks = new BloodStringReader.SuggestionCallbacks();
        BloodStringReader.Reader reader = new BloodStringReader.Reader(stringReader, suggestionCallbacks);

        try {
            reader.read();
        } catch (CommandSyntaxException ignored) {}

        return suggestionCallbacks.getSuggestions(builder, stringReader);
    }

    public interface Callbacks {
        default void onBlood(RegistryEntry<Blood> item) {}

        default void setSuggester(Function<SuggestionsBuilder, CompletableFuture<Suggestions>> suggester) {}
    }

    public record BloodResult(RegistryEntry<Blood> event) { }

    class Reader {
        private final StringReader reader;
        private final BloodStringReader.Callbacks callbacks;

        Reader(final StringReader reader, final BloodStringReader.Callbacks callbacks) {
            this.reader = reader;
            this.callbacks = callbacks;
        }

        public void read() throws CommandSyntaxException {
            this.callbacks.setSuggester(this::suggestBloods);
            this.readBlood();
            
            if (this.reader.canRead() && this.reader.peek() == '[') {
                this.callbacks.setSuggester(BloodStringReader.SUGGEST_DEFAULT);
            }
        }

        private void readBlood() throws CommandSyntaxException {
            int i = this.reader.getCursor();
            Identifier identifier = Identifier.fromCommandInput(this.reader);

            this.callbacks.onBlood(BloodStringReader.this.eventRegistry.getOptional(RegistryKey.of(AorticRegistries.BLOOD_KEY, identifier)).orElseThrow(() -> {
                this.reader.setCursor(i);
                return BloodStringReader.INVALID_EVENT_ID_EXCEPTION.createWithContext(this.reader, identifier);
            }));
        }

        private CompletableFuture<Suggestions> suggestBloods(SuggestionsBuilder builder) {
            return CommandSource.suggestIdentifiers(BloodStringReader.this.eventRegistry.streamKeys().map(RegistryKey::getValue), builder);
        }
    }

    static class SuggestionCallbacks implements BloodStringReader.Callbacks {
        private Function<SuggestionsBuilder, CompletableFuture<Suggestions>> suggester = BloodStringReader.SUGGEST_DEFAULT;

        public void setSuggester(Function<SuggestionsBuilder, CompletableFuture<Suggestions>> suggester) {
            this.suggester = suggester;
        }

        public CompletableFuture<Suggestions> getSuggestions(SuggestionsBuilder builder, StringReader reader) {
            return this.suggester.apply(builder.createOffset(reader.getCursor()));
        }
    }
}
