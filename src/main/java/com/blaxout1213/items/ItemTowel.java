package com.blaxout1213.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemTowel extends Item
{
	public ItemTowel()
	{
		super();
		this.setMaxDamage(10);
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.maxStackSize = 1;
		this.setUnlocalizedName("towel");
	}
    public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer ep)
    {
    	DataWatcher dw = ep.getDataWatcher();
    	if(dw.getWatchableObjectInt(25) > 0)
    	{
    		dw.updateObject(25, dw.getWatchableObjectInt(25) - 100*20);
    		item.damageItem(1, ep);
    	}
		return item;
    }
}
