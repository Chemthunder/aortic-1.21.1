package com.peak.aortic.core.client.screen;

import com.peak.aortic.api.Blood;
import com.peak.aortic.core.index.AorticItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.ColorHelper;

import java.util.List;

/**
 * @author Chemthunder
 */
public class HematologyScreen extends Screen {
    private final Blood blood;
    private final MinecraftClient client;

    public HematologyScreen(Blood blood) {
        super(Text.empty());
        this.blood = blood;
        this.client = MinecraftClient.getInstance();
    }

    protected void init() {

    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        List<Text> TEXTS = List.of(
                Text.translatable(blood.createTranslationKey()).formatted(Formatting.DARK_RED),
                Text.literal("- ").formatted(Formatting.RED).append(Text.translatable(blood.createTranslationKey() + ".trigger").formatted(Formatting.DARK_GRAY)),
                Text.literal("- ").formatted(Formatting.RED).append(Text.translatable(blood.createTranslationKey() + ".passive").formatted(Formatting.DARK_GRAY))
        );

        context.drawTooltip(
                client.textRenderer,
                TEXTS,
                context.getScaledWindowWidth() / 2 - 190,
                context.getScaledWindowHeight() / 2 - 55
        );

        context.fillGradient(
                0,
                0,
                context.getScaledWindowWidth(),
                context.getScaledWindowHeight(),
                0xFF000000,
                ColorHelper.Argb.getArgb(0, 255, 0, 0)
        );

        context.getMatrices().push();

        context.getMatrices().translate(-(float) context.getScaledWindowWidth() / 2, -110, 0);
        context.getMatrices().scale(2.0F, 2.0F, 2.0F);

        context.drawItem(
                new ItemStack(AorticItems.KNIFE),
                context.getScaledWindowWidth() / 2 - 110,
                context.getScaledWindowHeight() / 2 - 40
        );

        context.getMatrices().pop();



        super.render(context, mouseX, mouseY, delta);
    }

    public void blur() {}

    protected void applyBlur(float delta) {}

    public boolean shouldPause() {
        return false;
    }
}
