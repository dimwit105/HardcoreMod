/*
package com.blaxout1213.dayz.managers;

import com.blaxout1213.dayz.DayzMod;
import com.blaxout1213.dayz.ExtendedPlayer;
import com.blaxout1213.dayz.PlayerStalker;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

public class TempetureManager 
{
	private int heat;
	private boolean sweating;
	private boolean sun;
	public void onUpdate(EntityPlayer ep, float temp)
	{
		//Minecraft mc = Minecraft.getMinecraft();
		int x = MathHelper.floor_double(ep.posX);
		int y = MathHelper.floor_double(ep.posY);
		int z = MathHelper.floor_double(ep.posZ);
		ExtendedPlayer props = ExtendedPlayer.get(ep);
		BiomeGenBase biome = ep.worldObj.getWorldChunkManager().getBiomeGenAt(x, z);
		DataWatcher dw = ep.getDataWatcher();
		//targetTemp = 0;
		props.targetTemp("set", (int) ((int) 900 * temp) - 350);
		props.TTemp("set", props.targetTemp("get", 0));
		if(!sun && biome.biomeID != 8)
		{
			//TODO: Fix 500 in nether
			props.TTemp("set", 500);
		}
		if(dw.getWatchableObjectInt(24) > 1300)
		{
			sweating = true;
		}
		else
		{
			sweating = false;
		}
		if(ep.worldObj.blockExists(x, y, z))
		{
			Chunk chunk = ep.worldObj.getChunkFromBlockCoords(x, z);
			if(chunk.getSavedLightValue(EnumSkyBlock.Block, x & 15, y, z & 15) >= 14 && ep.worldObj.blockExists(x, y, z))
			{
				props.TTemp("add", 300);
			}
			if(chunk.getSavedLightValue(EnumSkyBlock.Sky, x & 15, y, z & 15) > 0 && ep.worldObj.blockExists(x, y, z))
			{
				this.sun = true;
			}
			else
			{
				this.sun = false;
			}
			if(sun)
			{
				if(chunk.getSavedLightValue(EnumSkyBlock.Sky, x & 15, y, z & 15) - ep.worldObj.skylightSubtracted == 15)
				{
					props.TTemp("add", 200);
				}
				else
				{
					props.TTemp("add", (chunk.getSavedLightValue(EnumSkyBlock.Sky, x & 15, y, z & 15) - ep.worldObj.skylightSubtracted)*10);
				}	
			}
		}
		if(dw.getWatchableObjectInt(25) > 0)
		{
			int subtract = dw.getWatchableObjectInt(25) / 20;
			props.TTemp("subtract", subtract);
		}
		if(ep.inventory.armorItemInSlot(0) != null)
		{
			props.TTemp("add", 100);
		}
		if(ep.inventory.armorItemInSlot(1) != null)
		{
			props.TTemp("add", 200);
		}
		if(ep.inventory.armorItemInSlot(2) != null)
		{
			props.TTemp("add", 200);
		}
		if(ep.inventory.armorItemInSlot(3) != null)
		{
			props.TTemp("add", 100);
		}
		if(props.venomed("get", 0) != 0)
		{
			props.TTemp("add", 150);
		}
		int subtract = (y - 64)*3;
		props.TTemp("subtract", subtract);
		int TTimer = 0;
		if(dw.getWatchableObjectInt(24) == props.TTemp("get", 200))
		{
		}
		if(!ep.worldObj.isRemote)
		{
			if(dw.getWatchableObjectInt(24) > props.TTemp("get", 200))
			{
				//int boost = dw.getWatchableObjectInt(24) - TTemp;
				dw.updateObject(24, dw.getWatchableObjectInt(24) - 1);
			}
			if(sweating)
			{
				ep.addExhaustion(0.001F);
			}
			if(dw.getWatchableObjectInt(24) < props.TTemp("get", 200) && !sweating)
			{
				dw.updateObject(24, dw.getWatchableObjectInt(24) + 1);
				//int boost = TTemp - dw.getWatchableObjectInt(24);
			}
			else if(dw.getWatchableObjectInt(24) < props.TTemp("get", 0) && ep.worldObj.getWorldTime() % 6 == 0)
			{
				dw.updateObject(24, dw.getWatchableObjectInt(24) + 1);
			}
		}
		if(dw.getWatchableObjectInt(24) > 1400)
		{
			heat++;
			if(heat >=300)
			{
				heat = 0;
				ep.attackEntityFrom(DayzMod.hot, 2F);
			}
		}
		if(dw.getWatchableObjectInt(24) < 0)
		{
			heat++;
			if(heat >=300)
			{
				ep.attackEntityFrom(DayzMod.cold, 2F);
				heat = 0;
				
			}
		}
	}
}
*/