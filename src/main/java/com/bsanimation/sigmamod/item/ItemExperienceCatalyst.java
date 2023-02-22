package com.bsanimation.sigmamod.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import com.bsanimation.sigmamod.tab.ModItemGroup;

public class ItemExperienceCatalyst extends Item {
    public ItemExperienceCatalyst(Properties settings) {
        super(settings.group(ModItemGroup.SIGMA_GROUP).rarity(Rarity.UNCOMMON).isImmuneToFire().maxStackSize(64));

    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        ItemStack itemStack = new ItemStack(getItem());
        PlayerEntity player = context.getPlayer();
        Hand hand = context.getHand();
        player.getHeldItem(hand).shrink(1);
        player.giveExperiencePoints(100);
        return super.onItemUse(context);
    }

    @Override
    public boolean isPiglinCurrency(ItemStack stack) {
        return true;
    }

    @Override
    public boolean doesSneakBypassUse(ItemStack stack, net.minecraft.world.IWorldReader world, BlockPos pos, PlayerEntity player) {
        return true;
    }

}
