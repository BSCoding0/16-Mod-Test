package com.bsanimation.sigmamod.item;

import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;


public class ItemEarthRune extends Item {
    public ItemEarthRune(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        World world = context.getWorld();

        if(!world.isRemote){
            PlayerEntity playerEntity = Objects.requireNonNull(context.getPlayer());
            BlockState clickedBlock = world.getBlockState(context.getPos());

            if(blockIsValidForResistance(clickedBlock, context.getWorld(), context.getPos(), playerEntity)){
                if(clickedBlock.getBlock() != Blocks.STONE) {
                    context.getItem().damageItem(1, playerEntity, (player) -> {
                        player.sendBreakAnimation(context.getHand());
                    });
                }
                destroyBlock(playerEntity, context.getWorld(), context.getPos());
                playerEntity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 80, 1, false, false));
                playerEntity.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 400, 0, false, false));
            }
        }


        return super.onItemUseFirst(stack, context);
    }

    private boolean blockIsValidForResistance(BlockState clickedBlock, World world, BlockPos pos, PlayerEntity playerEntity){
        if(clickedBlock.getBlock() == Blocks.STONE){
            gainHunger(playerEntity);
            return true;
        } else if(clickedBlock.getBlockHardness(world, pos) < 5f){
            return true;
        }else {
            return false;
        }
    }

    private void destroyBlock(PlayerEntity playerEntity, World world, BlockPos pos){
            world.destroyBlock(pos, true, playerEntity);
            world.playSound(playerEntity, pos, SoundEvents.BLOCK_STONE_BREAK, SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.4F + 0.8F);
    }

    public static void gainHunger(PlayerEntity playerEntity){
        playerEntity.addPotionEffect(new EffectInstance(Effects.HUNGER, 60, 1, false, false));
    }
}
