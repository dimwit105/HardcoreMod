package com.blaxout1213.items;

import java.util.Random;

import com.blaxout1213.dayz.DayzMod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemEpiPen extends Item
{
	public ItemEpiPen()
	{
		super();
		this.setUnlocalizedName("epipen");
		this.setCreativeTab(CreativeTabs.tabBrewing);
	}
	@Override
    public boolean hitEntity(ItemStack item, EntityLivingBase target, EntityLivingBase attacker)
    {
		if(target instanceof EntityPlayer)
		{
			DataWatcher dw = target.getDataWatcher();
			if(dw.getWatchableObjectInt(DayzMod.shockDataWatcher) > target.getHealth())
			{
				dw.updateObject(DayzMod.shockDataWatcher, (int)target.getHealth());
				item.stackSize--;
				return false;
			}
		}
		item.stackSize--;
		target.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 600 , 0));
		target.heal(2F);
		return false;
    }
    @Override
    public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer ep)
    {
    	item.stackSize--;
    	ep.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 600 , 0));
		DataWatcher dw = ep.getDataWatcher();
		if(dw.getWatchableObjectInt(DayzMod.shockDataWatcher) > ep.getHealth())
		{
			dw.updateObject(DayzMod.shockDataWatcher, (int)ep.getHealth());
			item.stackSize--;
			return item;
		}
		item.stackSize--;
		return item;
    }
}
