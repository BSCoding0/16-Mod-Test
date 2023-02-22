package com.bsanimation.sigmamod.effect;

import com.bsanimation.sigmamod.SigmaMod;
import com.bsanimation.sigmamod.tab.ModItemGroup;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.item.Item;
import net.minecraft.potion.*;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEffects extends Effects{

    private static int id = 0;

    public static final DeferredRegister<Effect> EFFECTS =
            DeferredRegister.create(ForgeRegistries.POTIONS, SigmaMod.MOD_ID);


    public static final RegistryObject<Effect> LIGHTNING_RESISTANCE = EFFECTS.register("lightning_resistance",
            () -> new EffectLightningResistance(EffectType.BENEFICIAL, 8171462));


    //public static final Effect LIGHTNING_RESISTANCE = register(id, "lightning_resistance", (new Effect(EffectType.BENEFICIAL, 8171462));

    public static void register(IEventBus eventBus) {
        EFFECTS.register(eventBus);
    }
}
