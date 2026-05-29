package com.peak.aortic.core.item;

import com.peak.aortic.api.Blood;
import com.peak.aortic.core.Aortic;
import com.peak.aortic.core.cca.entity.PlayerBloodComponent;
import com.peak.aortic.core.index.data.AorticDamageTypes;
import net.acoyt.acornlib.api.item.ModelVaryingItem;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
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
        World world = user.getWorld();

        if (!user.getItemCooldownManager().isCoolingDown(this)) {
            Blood toApply = Blood.getBloodFromTarget(entity);
            
            if (toApply != null) {
                PlayerBloodComponent.KEY.get(user).setCurrentBlood(toApply);

                user.damage(
                        user.getDamageSources().create(AorticDamageTypes.HARVEST),
                        world.getRandom().nextBetween(3, 6)
                );

                entity.damage(
                        entity.getDamageSources().create(AorticDamageTypes.HARVEST),
                        world.getRandom().nextBetween(3, 6)
                );

                user.spawnSweepAttackParticles();

                if (user.getWorld().isClient()) {
                    user.swingHand(hand);
                }

                if (!user.isCreative()) {
                    user.getItemCooldownManager().set(
                        this,
                        60
                    );
                }
            }
        }

        return super.useOnEntity(stack, user, entity, hand);
    }

    public Identifier getModel(ModelTransformationMode renderMode, ItemStack stack, @Nullable LivingEntity entity) {
        return Aortic.id("knife");
    }

    public List<Identifier> getModelsToLoad() {
        return Arrays.asList(
                Aortic.id("knife"),
                Aortic.id("knife_bloody")
        );
    }
}
