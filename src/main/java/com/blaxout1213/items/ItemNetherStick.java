package com.blaxout1213.items;

import java.util.Random;

import com.blaxout1213.dayz.CustomPotion;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class ItemNetherStick extends Item
{
	public ItemNetherStick()
	{
		this.maxStackSize = 1;
		this.setMaxDamage(1);
		this.setUnlocalizedName("netherstick");
		this.setFull3D();
		this.setCreativeTab(CreativeTabs.tabCombat);
	}
	@Override
    public boolean hitEntity(ItemStack item, EntityLivingBase target, EntityLivingBase attacker)
    {
		if(target instanceof EntityPlayer)
		{
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(CustomPotion.netherBound.id, 40,0));
		}
		else if(target instanceof EntityLiving && target.dimension != -1)
		{
			target.travelToDimension(-1);
		}
		item.damageItem(1, attacker);
    	return true;
    }
}
