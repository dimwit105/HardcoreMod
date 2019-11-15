package com.blaxout1213.dayz.bloodbags;

import com.blaxout1213.dayz.DayzMod;
import com.blaxout1213.dayz.ExtendedPlayer;

import net.minecraft.block.Block;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class BloodBagEmpty extends BloodBag
{

	public ItemStack onEaten(ItemStack item, World world, EntityPlayer ep)
    {
		DataWatcher dw = ep.getDataWatcher();
		ExtendedPlayer props = ExtendedPlayer.get(ep);
        if (item != null && item.getItem() == this && ep.getHealth() > 16)
        {
        	if(props.getBloodType() == 0)
        	{
        		ep.inventory.setInventorySlotContents(ep.inventory.currentItem, new ItemStack(DayzMod.bloodBagOminus));
        	}
        	if(props.getBloodType() == 1)
        	{
        		ep.inventory.setInventorySlotContents(ep.inventory.currentItem, new ItemStack(DayzMod.bloodBagOplus));
        	}	
        	if(props.getBloodType() == 2)
        	{
        		ep.inventory.setInventorySlotContents(ep.inventory.currentItem, new ItemStack(DayzMod.bloodBagAminus));
        	}	
        	if(props.getBloodType() == 3)
        	{
        		ep.inventory.setInventorySlotContents(ep.inventory.currentItem, new ItemStack(DayzMod.bloodBagAplus));
        	}
        	if(props.getBloodType() == 4)
        	{
        		ep.inventory.setInventorySlotContents(ep.inventory.currentItem, new ItemStack(DayzMod.bloodBagBminus));
        	}	
        	if(props.getBloodType() == 5)
        	{
        		ep.inventory.setInventorySlotContents(ep.inventory.currentItem, new ItemStack(DayzMod.bloodBagBplus));
        	}	
        	if(props.getBloodType() == 6)
        	{
        		ep.inventory.setInventorySlotContents(ep.inventory.currentItem, new ItemStack(DayzMod.bloodBagABminus));
        	}	
        	if(props.getBloodType() == 7)
        	{
        		ep.inventory.setInventorySlotContents(ep.inventory.currentItem, new ItemStack(DayzMod.bloodBagABplus));
        	}
        	ep.attackEntityFrom(DayzMod.venom, 16);
        }
		return item;
    }
}
