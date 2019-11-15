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
import net.minecraft.world.World;

public class AntiDoteC2 extends Item
{
    private String texture;
	public AntiDoteC2()
    {
    	this.setMaxStackSize(1);
    	this.setCreativeTab(CreativeTabs.tabMisc);
    	this.setUnlocalizedName("antidote");
    	this.setMaxDamage(8);
    }
	
	public ItemStack onEaten(ItemStack item, World par2World, EntityPlayer ep)
    {
    	//Minecraft mc = Minecraft.getMinecraft();
		DataWatcher dw = ep.getDataWatcher();
		ExtendedPlayer props = ExtendedPlayer.get(ep);
		if(props.venomed("get", 0) == 3 && this.getDamage(item) <= 7)
		{
			item.damageItem(1, ep);
			props.resistence("subtract", 0.05F);
			props.venomTime("set", 0);
			props.venomed("set", 0);
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
    	list.add("Class-3 Anti-venom");
    }
}
