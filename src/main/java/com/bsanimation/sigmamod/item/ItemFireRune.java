package com.bsanimation.sigmamod.item;

import it.unimi.dsi.fastutil.objects.Object2ShortArrayMap;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.Objects;


public class ItemFireRune extends Item {
    public ItemFireRune(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        World world = context.getLevel();

        if(!world.isClientSide){
            PlayerEntity playerEntity = Objects.requireNonNull(context.getPlayer());
            BlockState clickedBlock = world.getBlockState(context.getClickedPos());

            rightClickOnCertainBlockState(clickedBlock, context, playerEntity);
            //stack.hurt(1, playerEntity, player -> player.);
            //stack.damageItem(1, playerEntity);
            //damageItem()
            //damageItem(stack, 1, context.);
        }


        return super.onItemUseFirst(stack, context);
    }

    private void rightClickOnCertainBlockState(BlockState clickedBlock, ItemUseContext context, PlayerEntity playerEntity) {
    }
}
