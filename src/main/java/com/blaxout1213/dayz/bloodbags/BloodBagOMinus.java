package com.blaxout1213.dayz.bloodbags;

import java.util.List;

import com.blaxout1213.dayz.DayzMod;
import com.blaxout1213.dayz.ExtendedPlayer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BloodBagOMinus extends BloodBag
{
	@Override
	public ItemStack onEaten(ItemStack item, World world, EntityPlayer ep)
    {
		ExtendedPlayer props = ExtendedPlayer.get(ep);
		ep.heal(16F);
        if (!ep.capabilities.isCreativeMode)
        {
        	ep.inventory.setInventorySlotContents(ep.inventory.currentItem, new ItemStack(DayzMod.bloodBagEmpty));
        }
		return item;
    }
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4) 
    {
    	list.add("O-");
    }
}
