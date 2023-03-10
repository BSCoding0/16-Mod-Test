package com.bsanimation.sigmamod.item;

import com.bsanimation.sigmamod.tab.ModItemGroup;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.command.arguments.EntitySummonArgument;
import net.minecraft.command.impl.SummonCommand;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Dimension;
import net.minecraft.world.World;
import com.bsanimation.sigmamod.effect.ModEffects;

import java.util.Objects;


public class ItemLightningRune extends Item {
    public ItemLightningRune(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        World world = context.getWorld();

        if(!world.isRemote){
            PlayerEntity playerEntity = Objects.requireNonNull(context.getPlayer());
            BlockState clickedBlock = world.getBlockState(context.getPos());

            context.getItem().damageItem(1, playerEntity, (player) -> {player.sendBreakAnimation(context.getHand());});

            rightClickOnCertainBlockState(clickedBlock, context, playerEntity);

        }


        return super.onItemUseFirst(stack, context);
    }

    /*
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        letLightningStrikeGround(worldIn, playerIn, handIn);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
     */

    private void rightClickOnCertainBlockState(BlockState clickedBlock, ItemUseContext context, PlayerEntity playerEntity) {

        boolean playerIsNotOnFire = !playerEntity.isBurning();

        if(playerIsNotOnFire && blockIsValidForResistance(clickedBlock)){
            gainLightingResistanceAndDestroyBlock(playerEntity, context.getWorld(), context.getPos());
        } else {
            letLightningStrikeGround(context);
        }

    }

    private boolean blockIsValidForResistance(BlockState clickedBlock){
        return clickedBlock.getBlock() == Blocks.CRYING_OBSIDIAN;
    }

    private void gainLightingResistanceAndDestroyBlock(PlayerEntity playerEntity, World world, BlockPos pos){
        gainLightningResistance(playerEntity);
        world.destroyBlock(pos, false);
    }

    public static void gainLightningResistance(PlayerEntity playerEntity){
        playerEntity.addPotionEffect(new EffectInstance(ModEffects.LIGHTNING_RESISTANCE.get(), 200));
    }

    public static void letLightningStrikeGround(ItemUseContext context){

        PlayerEntity playerentity = context.getPlayer();
        World world = context.getWorld();
        //BlockPos blockpos = context.getPos().offset(context.getFace());

        //code from SpawnEggItem
        /*
        BlockPos blockpos = context.getPos();
        Direction direction = context.getFace();
        BlockPos pos;
        BlockState blockstate = world.getBlockState(blockpos);
        Double xPos = playerentity.getLookVec().x;
        Double yPos = playerentity.getLookVec().y;
        Double zPos = playerentity.getLookVec().z;

        blockpos.offset(context.getFace());

        if (blockstate.getCollisionShapeUncached(world, blockpos).isEmpty()) {
            pos = blockpos;
        } else {
            pos = blockpos.offset(direction);
        }

         */
        Fluid containedBlock = null;
        RayTraceResult raytraceresult = rayTrace(world, playerentity, containedBlock == Fluids.EMPTY ? RayTraceContext.FluidMode.SOURCE_ONLY : RayTraceContext.FluidMode.NONE);
        ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onBucketUse(playerentity, world, context.getItem(), raytraceresult);

        BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult)raytraceresult;
        BlockPos blockpos = blockraytraceresult.getPos();
        Direction direction = blockraytraceresult.getFace();
        BlockPos blockpos1 = blockpos.offset(direction);


        /*
        double range = 50;

        float f = playerentity.rotationYaw;
        float f1 = playerentity.rotationYawHead;
        Vector3d vector3d = playerentity.getEyePosition(1.0F);
        float f2 = MathHelper.cos(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f3 = MathHelper.sin(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f4 = -MathHelper.cos(-f * ((float)Math.PI / 180F));
        float f5 = MathHelper.sin(-f * ((float)Math.PI / 180F));
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        Vector3d vector3d1 = vector3d.add((double)f6 * range, (double)f5 * range, (double)f7 * range);



        BlockRayTraceResult ray = new BlockRayTraceResult(vector3d, vector3d1, RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE , playerentity);
        BlockPos pos = ray.getPos();*/




        //EntityType.LIGHTNING_BOLT.create(world).setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ(), 0.0F, 0.0F);
        EntityType.LIGHTNING_BOLT.spawn(context.getWorld().getServer().getWorld(context.getWorld().getDimensionKey()), context.getItem(), playerentity, blockpos1, SpawnReason.MOB_SUMMONED, true, true);

        //world.playSound(playerentity, blockpos, SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, SoundCategory.WEATHER, 1.0F, random.nextFloat() * 0.4F + 0.8F);

    }

    /*
    public static void letLightningStrikeGround(World world, PlayerEntity playerentity, Hand hand){


        //BlockPos blockpos = context.getPos().offset(context.getFace());

        //code from SpawnEggItem


        Direction direction = playerentity.getAdjustedHorizontalFacing();
        BlockPos pos;
        BlockPos blockpos = playerentity.getPosition();
        Double xPos = Math.min(100, playerentity.getLookVec().x);
        Double yPos = Math.min(100, playerentity.getLookVec().y);
        Double zPos = Math.min(100, playerentity.getLookVec().z);
        BlockState blockstate = world.getBlockState(blockpos);

        //blockpos.offset(direction);


        if (blockstate.getCollisionShapeUncached(world, blockpos).isEmpty()) {
            pos = blockpos;
        } else {
            pos = blockpos.offset(direction);
        }
        //EntityType.LIGHTNING_BOLT.create(world).setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ(), 0.0F, 0.0F);
        EntityType.LIGHTNING_BOLT.spawn(world.getServer().getWorld(world.getDimensionKey()), playerentity.getHeldItem(hand), playerentity, pos, SpawnReason.MOB_SUMMONED, true, true);

        //world.playSound(playerentity, blockpos, SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, SoundCategory.WEATHER, 1.0F, random.nextFloat() * 0.4F + 0.8F);

    }
     */
}
