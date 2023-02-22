package com.bsanimation.sigmamod.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.NBTTextComponent;
import net.minecraft.world.Dimension;

public class EffectLightningResistance extends Effect {

    public static DamageSource lightnigDamage;
    public EffectLightningResistance(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void applyAttributesModifiersToEntity(LivingEntity entityLivingBaseIn, AttributeModifierManager attributeMapIn, int amplifier) {
        //entityLivingBaseIn.getLastDamageSource().damageType
        //entityLivingBaseIn.causeLightningStrike();
        //entityLivingBaseIn.hitByEntity(EntityType.LIGHTNING_BOLT.spawn());
        super.applyAttributesModifiersToEntity(entityLivingBaseIn, attributeMapIn, amplifier);
    }
}
