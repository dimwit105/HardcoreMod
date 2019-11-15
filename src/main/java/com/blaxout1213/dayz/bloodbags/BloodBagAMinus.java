package com.blaxout1213.dayz.bloodbags;

import java.util.List;

import com.blaxout1213.dayz.DayzMod;
import com.blaxout1213.dayz.ExtendedPlayer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class BloodBagAMinus extends BloodBag
{
	@Override
	public ItemStack onEaten(ItemStack item, World world, EntityPlayer ep)
    {
		DataWatcher dw = ep.getDataWatcher();
		ExtendedPlayer props = ExtendedPlayer.get(ep);
		if(props.getBloodType() == 2 || props.getBloodType() == 3 || props.getBloodType() == 6 || props.getBloodType() == 7)
		{
			ep.heal(16F);
		}
		else
		{
			ep.heal(16F);
			props.badBlood("set", true);
		}
        if (!ep.capabilities.isCreativeMode)
        {
        	ep.inventory.setInventorySlotContents(ep.inventory.currentItem, new ItemStack(DayzMod.bloodBagEmpty));
        }
		return item;
    }
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4) 
    {
    	list.add("A-");
    }
}
