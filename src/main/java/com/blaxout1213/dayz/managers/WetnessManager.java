package com.blaxout1213.dayz.managers;

import com.blaxout1213.dayz.ExtendedPlayer;

import net.minecraft.block.Block;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

public class WetnessManager 
{
	public void onUpdate(EntityPlayer ep)
	{
		DataWatcher dw = ep.getDataWatcher();
		ExtendedPlayer props = ExtendedPlayer.get(ep);
		int x = MathHelper.floor_double(ep.posX);
		int y = MathHelper.floor_double(ep.posY);
		int z = MathHelper.floor_double(ep.posZ);
		Chunk chunk = ep.worldObj.getChunkFromBlockCoords(x, z);
		BiomeGenBase biomegenbase2 = chunk.getBiomeGenForWorldCoords(x & 15, z & 15, ep.worldObj.getWorldChunkManager());
		Block blockID = ep.worldObj.getBlock(x, y, z);
		Block blockID2 = ep.worldObj.getBlock(x, y - 1, z);
		if(ep.isInWater() == false && ep.isWet() && ep.getEquipmentInSlot(4) != null)
		{
			Object time2 = dw.getWatchableObjectInt(25) + 1;
			dw.updateObject(25, time2);
		}
		else if(ep.isInWater() == false && ep.isWet())
		{
			Object time2 = dw.getWatchableObjectInt(25) + 2;
			dw.updateObject(25, time2);
		}
		else if(ep.isInWater() && !ep.isRiding())
		{
			Object time2 = dw.getWatchableObjectInt(25) + 33;
			dw.updateObject(25, time2);
		}
		else if(blockID == Blocks.snow_layer || blockID2 == Blocks.snow)
		{
			Object time2 = dw.getWatchableObjectInt(25) + 2;
			dw.updateObject(25, time2);
		}
		else if((biomegenbase2 == BiomeGenBase.jungle || biomegenbase2 == BiomeGenBase.jungleHills) && ep.worldObj.canBlockSeeTheSky(x, y, z))
		{
			Object time2 = dw.getWatchableObjectInt(25) + 1;
			dw.updateObject(25, time2);
		}
		/*
		else if(dw.getWatchableObjectInt(24) > 1300)
		{
			Object time2 = dw.getWatchableObjectInt(25) + 1;
			dw.updateObject(25, time2);
		}
		*/
		else
		{
			Object time2 = dw.getWatchableObjectInt(25) - 1;
			dw.updateObject(25, time2);
		}
	
		
		if(dw.getWatchableObjectInt(25) > 2000*20)
		{
			dw.updateObject(25, 2000*20);
		}
	}
}