package com.bsanimation.sigmamod.item;

import com.bsanimation.sigmamod.SigmaMod;
import com.bsanimation.sigmamod.tab.ModItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, SigmaMod.MOD_ID);

    public static final RegistryObject<Item> CRYSTALLIZED_IRON = ITEMS.register("crystallized_iron",
            () -> new Item(new Item.Properties().group(ModItemGroup.SIGMA_GROUP)));

    public static final RegistryObject<Item> FIRE_RUNE = ITEMS.register("fire_rune",
            () -> new ItemFireRune(new Item.Properties().group(ModItemGroup.SIGMA_GROUP).isImmuneToFire().maxDamage(16)));

    public static final RegistryObject<Item> LIGHTNING_RUNE = ITEMS.register("lightning_rune",
            () -> new ItemLightningRune(new Item.Properties().group(ModItemGroup.SIGMA_GROUP).maxDamage(16)));

    public static final RegistryObject<Item> EARTH_RUNE = ITEMS.register("earth_rune",
            () -> new ItemEarthRune(new Item.Properties().group(ModItemGroup.SIGMA_GROUP).maxDamage(64)));

    public static final RegistryObject<Item> WATER_RUNE = ITEMS.register("water_rune",
            () -> new ItemWaterRune(new Item.Properties().group(ModItemGroup.SIGMA_GROUP).maxDamage(16)));
    public static final RegistryObject<Item> EXPERIENCE_CATALYST = ITEMS.register("experience_catalyst",
                    () -> new ItemExperienceCatalyst(new Item.Properties()));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
