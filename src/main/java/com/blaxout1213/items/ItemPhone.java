package com.blaxout1213.items;

import java.util.List;

import com.blaxout1213.dayz.PlayerStalker;
import com.blaxout1213.dayz.UtilMisc;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class ItemPhone extends Item
{
	private int mode;
	private int maxMode = 1;
	public ItemPhone()
	{
		this.maxStackSize = 1;
		this.setUnlocalizedName("phone");
		this.setCreativeTab(CreativeTabs.tabMisc);
	}
    public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer ep)
    {
    	if(ep.isSneaking() && !world.isRemote)
    	{
    		ep.addChatComponentMessage(new ChatComponentTranslation("Mode added", new Object[0]));
    		mode ++;
    	}
    	if(mode > maxMode )
    	{
    		System.out.println("Mode reset");
    		mode = 0;
    	}
    	if(!world.isRemote && !ep.isSneaking())
    	{
    		int raintime = world.getWorldInfo().getRainTime() / 20;
    		int raintimeM = 0;
    		int moonphase = UtilMisc.getMoonPhase(world.getWorldTime());
    		if(mode == 0)
    		{
    			if(raintime > 60)
    			{
    				raintimeM = raintime / 60;
    				raintime = raintime -= raintimeM*60;
    			}
        		if(!world.isRaining())
        		{    	
                	ep.addChatComponentMessage(new ChatComponentTranslation("Time until rain: " + raintimeM + "m " + raintime + "s ", new Object[0]));
        		}
        		else
        		{
        			ep.addChatComponentMessage(new ChatComponentTranslation("Time until sun: " + raintimeM + "m " + raintime + "s ", new Object[0]));
        		}
    		}
    		if(mode == 1)
    		{
    			ep.addChatComponentMessage(new ChatComponentTranslation("Moon phase: " + this.getPhase(moonphase), new Object[0]));
    		}
    	}
        return item;
    }
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4) 
    {
    	list.add(this.getMode(mode));
    }
    public String getPhase(int par1)
    {
		if(par1 == 0)
		{
			return "Full moon";
		}
		if(par1 == 1)
		{
			return "Waning gibbious";
		}
		if(par1 == 2)
		{
			return "Waning quarter moon";
		}
		if(par1 == 3)
		{
			return "Waning cresent";
		}
		if(par1 == 4)
		{
			return "New moon";
		}
		if(par1 == 5)
		{
			return "Waxing cresent";
		}
		if(par1 == 6)
		{
			return "Waxing quarter moon";
		}
		if(par1 == 7)
		{
			return "Waxing gibbious";
		}
		return "Error, integer above 7 or below 0";
    }
    public String getMode(int par1)
    {
    	if(par1 == 0)
    	{
    		return "Weather mode";
    	}
    	if(par1 == 1)
    	{
    		return "Moon phase mode";
    	}
    	return "Invalid integer: " + par1;
    }
}
