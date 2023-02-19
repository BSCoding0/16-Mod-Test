package com.bsanimation.sigmamod.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ItemExperienceCatalyst extends Item {
    public ItemExperienceCatalyst(Properties settings) {
        super(settings.group(ItemGroup.MATERIALS).rarity(Rarity.UNCOMMON).isImmuneToFire().maxStackSize(1));

    }

    //doesn't work with itemStacks that are bigger than 1 -> maxStackSize(1)
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = new ItemStack(getItem());
        itemStack.getStack().setCount((itemStack.getStack().getCount())-1);
        ActionResult actionResult = new ActionResult(ActionResultType.SUCCESS, itemStack);
        player.giveExperiencePoints(100);
        return ActionResult.resultSuccess(itemStack);
    }

    @Override
    public boolean isPiglinCurrency(ItemStack stack) {
        return true;
    }

}
