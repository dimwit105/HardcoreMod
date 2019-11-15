package com.blaxout1213.dayz.bitcoin;

import java.util.List;

import com.blaxout1213.dayz.DayzMod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BitcoinPlatnium extends BitcoinBase
{
	public BitcoinPlatnium()
	{
		super();
		this.setUnlocalizedName("bitPlatnium");
	}
    public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer ep)
    {
    	if(item.stackSize >= 10)
    	{
    		ep.inventory.setInventorySlotContents(ep.inventory.currentItem, new ItemStack(DayzMod.bitcoinOrange));
    		world.playSoundAtEntity(ep, "dayz:razorsharp", 3F, 1F);
    	}
        return item;
    }
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4) 
    {
    	list.add("10 BTC");
    }
}
