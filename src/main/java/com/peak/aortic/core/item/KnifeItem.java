package com.peak.aortic.core.item;

import com.peak.aortic.api.Blood;
import com.peak.aortic.api.registration.BloodTypeRegistrant;
import com.peak.aortic.core.Aortic;
import com.peak.aortic.core.component.HeldBloodComponentType;
import com.peak.aortic.core.index.AorticBloodTypes;
import com.peak.aortic.core.index.AorticComponentTypes;
import com.peak.aortic.core.index.AorticRegistries;
import net.acoyt.acornlib.api.item.ModelVaryingItem;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

/**
 * @author Chemthunder
 */
public class KnifeItem extends Item implements ModelVaryingItem {
    public KnifeItem(Settings settings) {
        super(settings);
    }

    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        Blood toApply = null;

        for (Blood blood : AorticRegistries.BLOOD) {
            if (blood.getType() == entity.getType()) {
                toApply = blood;
            } else {
                if (user.getWorld().isClient()) {
                    user.sendMessage(Text.of("skiped"));
                }
            }
        }

        if (toApply != null) {
            if (user.getWorld().isClient()) {
                user.swingHand(hand);
            }

            user.sendMessage(Text.of(stack.get(AorticComponentTypes.HELD_BLOOD).blood().getId()));
        } else {
            user.sendMessage(Text.of("unable!!!!"), true);
        }
        return super.useOnEntity(stack, user, entity, hand);
    }

    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        Blood blood = stack.get(AorticComponentTypes.HELD_BLOOD).blood();

        tooltip.add(Text.literal("Blood type: ").append(blood.getId().toString()));
        tooltip.add(Text.literal("giver: ").append(stack.get(AorticComponentTypes.HELD_BLOOD).giverName()));
    }

    public Identifier getModel(ModelTransformationMode renderMode, ItemStack stack, @Nullable LivingEntity entity) {
        if (stack.get(AorticComponentTypes.HELD_BLOOD).blood() != AorticBloodTypes.BASE) {
            return Aortic.id("knife_bloody");
        } else {
            return Aortic.id("knife");
        }
    }

    public List<Identifier> getModelsToLoad() {
        return Arrays.asList(
                Aortic.id("knife"),
                Aortic.id("knife_bloody")
        );
    }
}
