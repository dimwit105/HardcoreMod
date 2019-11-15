package com.blaxout1213.dayz.bitcoin;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BitcoinBase extends Item
{
	public BitcoinBase()
	{
		this.maxStackSize = 10;
		this.setCreativeTab(CreativeTabs.tabMisc);
	}
}
