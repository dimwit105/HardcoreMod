package com.blaxout1213.dayz.bloodbags;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BloodBag extends Item
{
	public BloodBag()
    {
    	this.setMaxStackSize(1);
    	this.setCreativeTab(CreativeTabs.tabMisc);
    	this.setUnlocalizedName("bloodbag");
    }
	public ItemStack onEaten(ItemStack item, World world, EntityPlayer ep)
    {
		return item;
    }
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.block;
    }
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 80;
    }
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        return par1ItemStack;
    }
}
