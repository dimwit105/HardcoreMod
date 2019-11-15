package com.blaxout1213.items;

import com.blaxout1213.dayz.ExtendedPlayer;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemBandage extends Item
{
	public ItemBandage()
	{
		super();
		this.setMaxStackSize(1);
		this.setUnlocalizedName("bandage");
		this.setCreativeTab(CreativeTabs.tabBrewing);
	}
	@Override
    public boolean hitEntity(ItemStack item, EntityLivingBase target, EntityLivingBase attacker)
    {
		if(target instanceof EntityPlayer)
		{
			EntityPlayer ep = (EntityPlayer) target;
			ExtendedPlayer props = ExtendedPlayer.get(ep);
			DataWatcher dw = target.getDataWatcher();
			props.bleed("set", props.bleed("get", 0) / 3);
		}
		item.stackSize--;
		target.addPotionEffect(new PotionEffect(Potion.regeneration.id, 10*20 , 0));
		target.heal(2F);
		return false;
    }
    @Override
    public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer ep)
    {
    	item.stackSize--;
		ExtendedPlayer props = ExtendedPlayer.get(ep);
		props.bleed("set", props.bleed("get", 0) / 3);
    	ep.addPotionEffect(new PotionEffect(Potion.regeneration.id, 8*20 , 0));
		return item;
    }
}
