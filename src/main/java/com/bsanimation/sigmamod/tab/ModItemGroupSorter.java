package com.bsanimation.sigmamod.tab;

import com.bsanimation.sigmamod.block.ModBlocks;
import com.bsanimation.sigmamod.item.ModItems;
import com.google.common.collect.Ordering;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.util.NonNullList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ModItemGroupSorter extends ItemGroup {

    public ModItemGroupSorter(String label) {
        super(label);
    }

    @Override
    public ItemStack createIcon() {
        return null;
    }

    @Override
    public void fill(NonNullList<ItemStack> itemStacks) {
        /*
        List<ItemStack> myItemsInOrder = Arrays.asList(ModItems.CRYSTALLIZED_IRON);
        Comparator<ItemStack> comparator = Ordering.explicit(myItemsInOrder).onResultOf(ItemStack::getItem);
        items.sort(comparator);
        super.fill(items);
        */
        /*
        List<Item> items = Arrays.asList(ModItems.CRYSTALLIZED_IRON);

        itemStacks.clear();

        for (Item item : items)
        {
            if(item.getCreativeTabs().contains(ModItemGroup.SIGMA_GROUP))
            {
                itemStacks.add(new ItemStack(item));
            }
        }*/
    }
}
