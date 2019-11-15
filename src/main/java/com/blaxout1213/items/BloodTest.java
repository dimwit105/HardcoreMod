package com.blaxout1213.items;

import com.blaxout1213.dayz.ExtendedPlayer;
import com.blaxout1213.dayz.UtilMisc;
import com.blaxout1213.dayz.WorldData;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class BloodTest extends Item
{
	public BloodTest()
    {
    	this.setMaxStackSize(1);
    	this.setCreativeTab(CreativeTabs.tabMisc);
    	this.setUnlocalizedName("bloodtest");
    }
	public ItemStack onEaten(ItemStack item, World world, EntityPlayer ep)
    {
		/** Blood type int to value
		 * O-	0
		 * O+	1
		 * A-	2
		 * A+	3
		 * B-	4
		 * B+	5
		 * AB-	6
		 * AB+	7	
		 */
		DataWatcher dw = ep.getDataWatcher();
		ExtendedPlayer props = ExtendedPlayer.get(ep);
		if(!world.isRemote)
		{
			props.sync(ep);
			props.bloodDiscovered();

			if(props.getBloodType() == 0)
			{
				ep.addChatComponentMessage(new ChatComponentTranslation("blood.O-", new Object[0]));
			}
			if(props.getBloodType() == 1)
			{
				ep.addChatComponentMessage(new ChatComponentTranslation("blood.O+", new Object[0]));
			}
			if(props.getBloodType() == 2)
			{
				ep.addChatComponentMessage(new ChatComponentTranslation("blood.A-", new Object[0]));
			}
			if(props.getBloodType() == 3)
			{
				ep.addChatComponentMessage(new ChatComponentTranslation("blood.A+", new Object[0]));
			}
			if(props.getBloodType() == 4)
			{
				ep.addChatComponentMessage(new ChatComponentTranslation("blood.B-", new Object[0]));
			}
			if(props.getBloodType() == 5)
			{
				ep.addChatComponentMessage(new ChatComponentTranslation("blood.B+", new Object[0]));
			}
			if(props.getBloodType() == 6)
			{
				ep.addChatComponentMessage(new ChatComponentTranslation("blood.AB-", new Object[0]));
			}
			if(props.getBloodType() == 7)
			{
				ep.addChatComponentMessage(new ChatComponentTranslation("blood.AB+", new Object[0]));
			}
			//ep.addChatComponentMessage(new ChatComponentTranslation("Prop value = " + props.getBloodType(), new Object[0]));
			double x = ep.posX + ep.getRNG().nextInt(500) - ep.getRNG().nextInt(500);
			double z = ep.posZ + ep.getRNG().nextInt(500) - ep.getRNG().nextInt(500);
			//UtilMisc.teleportPlayer(ep, x, z);
		}
		return item;
    }
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.block;
    }
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 3;
    }
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        return par1ItemStack;
    }
}
