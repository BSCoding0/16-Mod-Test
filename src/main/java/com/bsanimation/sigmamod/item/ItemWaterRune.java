package com.bsanimation.sigmamod.item;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;


public class ItemWaterRune extends Item {
    public ItemWaterRune(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        World world = context.getWorld();

        if(!world.isRemote){
            PlayerEntity playerEntity = Objects.requireNonNull(context.getPlayer());
            BlockState clickedBlock = world.getBlockState(context.getPos());

            if(blockIsValidForResistance(clickedBlock, context.getWorld(), context.getPos(), playerEntity)){
                context.getItem().damageItem(1, playerEntity, (player) -> {
                    player.sendBreakAnimation(context.getHand());});

            }else {
                destroyBlock(playerEntity, context.getWorld(), context.getPos());
            }
        }

        return super.onItemUseFirst(stack, context);
    }

    private boolean blockIsValidForResistance(BlockState clickedBlock, World world, BlockPos pos, PlayerEntity playerEntity){
        return clickedBlock.getBlock() == Blocks.WATER;
    }

    private void destroyBlock(PlayerEntity playerEntity, World world, BlockPos pos){
            world.destroyBlock(pos, false, playerEntity);
            //world.addBlockEvent();
            //world.updateBlock(pos, Blocks.WATER);
            world.playSound(playerEntity, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.4F + 0.8F);
    }

    public static void gainHunger(PlayerEntity playerEntity){
        playerEntity.addPotionEffect(new EffectInstance(Effects.HUNGER, 60, 1, false, false));
    }
}
