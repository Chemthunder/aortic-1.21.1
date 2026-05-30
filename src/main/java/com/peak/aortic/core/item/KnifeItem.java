package com.peak.aortic.core.item;

import com.peak.aortic.api.Blood;
import com.peak.aortic.core.cca.entity.PlayerBloodComponent;
import com.peak.aortic.core.index.AorticSoundEvents;
import com.peak.aortic.core.index.data.AorticDamageTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

/**
 * @author Chemthunder
 */
public class KnifeItem extends Item {
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

                if (!user.isCreative()) {
                    user.getItemCooldownManager().set(
                        this,
                        60
                    );
                }

                world.playSound(
                        user,
                        user.getBlockPos(),
                        AorticSoundEvents.ITEM_HARVEST,
                        SoundCategory.PLAYERS,
                        1,
                        1
                );
            } else {
                user.sendMessage(Text.translatable("item.aortic.knife.refuse"), true);
            }

            if (user.getWorld().isClient()) {
                user.swingHand(hand);
            }
        }

        return super.useOnEntity(stack, user, entity, hand);
    }

    public static AttributeModifiersComponent createAttributes() {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(
                                BASE_ATTACK_DAMAGE_MODIFIER_ID,
                                3.0F,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(
                                BASE_ATTACK_SPEED_MODIFIER_ID,
                                -2.2F,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .build();
    }
}
