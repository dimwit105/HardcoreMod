package com.blaxout1213.dayz;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class AntiDote extends Item
{
    public String texture;
	public AntiDote()
    {
    	this.setMaxStackSize(1);
    	this.setCreativeTab(CreativeTabs.tabMisc);
    	this.setUnlocalizedName("antidote");
    	this.setMaxDamage(4);
    	this.setTextureName(texture);
    }
	
	public ItemStack onEaten(ItemStack item, World par2World, EntityPlayer ep)
    {
		DataWatcher dw = ep.getDataWatcher();
		ExtendedPlayer props = ExtendedPlayer.get(ep);
		if(props.venomed("get", 0) == 1 && this.getDamage(item) <= 3)
		{
			item.damageItem(1, ep);
			props.resistence("subtract", 0.05F);
			props.venomTime("set", 0);
			props.venomed("set", 0);
			
		}
		else if(props.venomed("get", 0) == 0 || this.getDamage(item) == 4 && !par2World.isRemote)
		{
			if(!par2World.isRemote)
			{
				ep.addChatComponentMessage(new ChatComponentTranslation("cant.use", new Object[0]));
			}
		}
		return item;
    }
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.block;
    }
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 32;
    }
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        return par1ItemStack;
    }
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4) 
    {
    	list.add("Class-1 Anti-venom");
    }
    public void onUpdate(ItemStack stack, World par2World, Entity par3Entity, int par4, boolean par5) 
    {
    	if(this.getDamage(stack) == 0)
    	{
    		texture = "dayz:antidote2";
    	}
    	if(this.getDamage(stack) == 1)
    	{
    		texture = "dayz:antidote1";
    	}
    	if(this.getDamage(stack) == 2)
    	{
    		texture = "dayz:antidote0";
    	}
    	if(this.getDamage(stack) == 3)
    	{
    		texture = "dayz:syringe";
    	}
    }
}
