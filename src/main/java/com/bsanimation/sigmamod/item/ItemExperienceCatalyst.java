package com.bsanimation.sigmamod.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ItemExperienceCatalyst extends Item {
    public ItemExperienceCatalyst(Properties settings) {
        super(settings.group(ItemGroup.MATERIALS).rarity(Rarity.UNCOMMON).isImmuneToFire().maxStackSize(1).maxDamage(1));

    }

    private boolean right_click = false;

    //gets called when dropped unfortunately
    @Override
    public boolean onDroppedByPlayer(ItemStack item, PlayerEntity player) {
        if(this.right_click) { //if the player right-clicks the set the boolean to true and so the item gets dropped (want to remove it instead, this is a workaround)
            item.setCount((item.getCount()) - 1);
            player.inventory.deleteStack(item);
            player.giveExperiencePoints(100);
            this.right_click = false;
            return false;
        }else{ //the player drops the item normally
            return true;
        }

    }

    //drops the item (and deletes it -> I want it to remove it without dropping)
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        this.right_click = true;
        player.drop(true);
        return super.onItemRightClick(world, player, hand);
    }

    @Override
    public boolean isPiglinCurrency(ItemStack stack) {
        return true;
    }

}
