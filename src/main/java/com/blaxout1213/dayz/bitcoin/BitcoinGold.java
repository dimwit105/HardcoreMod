package com.blaxout1213.dayz.bitcoin;

import java.util.List;

import com.blaxout1213.dayz.DayzMod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BitcoinGold extends BitcoinBase
{
	public BitcoinGold()
	{
		super();
		this.setUnlocalizedName("bitGold");
	}
    public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer ep)
    {
    	if(item.stackSize >= 10)
    	{
    		ep.inventory.setInventorySlotContents(ep.inventory.currentItem, new ItemStack(DayzMod.bitcoinPlatnium));
    		world.playSoundAtEntity(ep, "dayz:razorsharp", 3F, 1F);
    	}
        return item;
    }
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4) 
    {
    	list.add("1 BTC");
    }
}
