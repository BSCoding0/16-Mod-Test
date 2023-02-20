package com.bsanimation.sigmamod.tab;

import com.bsanimation.sigmamod.item.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {

    public static final ItemGroup SIGMA_GROUP = new ItemGroup("sigmaModTab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.CRYSTALLIZED_IRON.get());
        }
    };

}
