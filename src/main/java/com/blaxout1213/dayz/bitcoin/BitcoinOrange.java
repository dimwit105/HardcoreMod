package com.blaxout1213.dayz.bitcoin;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BitcoinOrange extends BitcoinBase
{
	public BitcoinOrange()
	{
		super();
		this.setUnlocalizedName("bitOrange");
	}
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4) 
    {
    	list.add("100 BTC");
    }
}
