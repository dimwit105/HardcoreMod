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

public class ItemNightStick extends Item
{
	public ItemNightStick()
	{
		this.maxStackSize = 1;
		this.setMaxDamage(100);
		this.setUnlocalizedName("nightstick");
		this.setFull3D();
		this.setCreativeTab(CreativeTabs.tabCombat);
	}
	@Override
    public boolean hitEntity(ItemStack item, EntityLivingBase target, EntityLivingBase attacker)
    {
		if(target instanceof EntityPlayer)
		{
			DataWatcher dw = target.getDataWatcher();
			if(target.getDataWatcher().getWatchableObjectInt(DayzMod.shockDataWatcher)/20 < (int)target.getHealth())
			{
				dw.updateObject(DayzMod.shockDataWatcher, ((int)target.getHealth() + 2)*20);
			}		
		}
		else if(target instanceof EntityLiving)
		{
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 120 * 20, 7));
		}
		item.damageItem(1, attacker);
    	return true;
    }
}
