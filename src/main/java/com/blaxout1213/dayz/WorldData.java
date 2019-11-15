package com.blaxout1213.dayz;

import java.util.Random;

import com.blaxout1213.dayz.packet.SyncPropertiesPacket;
import com.blaxout1213.dayz.packet.SyncWorldPacket;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;

public class WorldData extends WorldSavedData
{

	private MapStorage world2;
	private World world;
	private Random rand;
	private int steves;
	private int zombies;
	private int guardianPower;
	private int rank;
	private int cloudiness;
	private int solarEclipse;
	private static final String IDENTIFIER = "hcmod";
	

	public WorldData() 
	{
		super(IDENTIFIER);
		this.steves = 1;
		this.guardianPower = 0;
		this.rank = 250;
		this.cloudiness = 0;
		this.solarEclipse = (int) (DayzMod.eclipseAverage * 24000);
		// TODO Auto-generated constructor stub
	}
	public WorldData(String ID) 
	{
		super(ID);
	}
	

	@Override
	public void readFromNBT(NBTTagCompound nbt) 
	{
		this.steves = nbt.getInteger("Steves");
		this.guardianPower = nbt.getInteger("GuardianPower");
		this.rank = nbt.getInteger("Rank");
		this.cloudiness = nbt.getInteger("Clouds");
		this.solarEclipse = nbt.getInteger("Eclipse");
		
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) 
	{
		nbt.setInteger("Steves", steves);
		nbt.setInteger("GuardianPower", guardianPower);
		nbt.setInteger("Rank", this.rank);
		nbt.setInteger("Clouds", cloudiness);
		nbt.setInteger("Eclipse", solarEclipse);
		//this.setDirty(true);
	}

	public static WorldData get(World world) 
	{
		WorldData data = (WorldData)world.loadItemData(WorldData.class, IDENTIFIER);
		if (data == null) 
		{
			data = new WorldData();
			world.setItemData(IDENTIFIER, data);
		}
		return data;
	}
	private void sendData()
	{
	}
	public void setRank(int par1)
	{
		rank = par1;
		this.markDirty();
	}
	public int getRank()
	{
		return rank;
	}
	public int getSteves()
	{
		return steves;
	}
	
	public void setSteves(int par1)
	{
		steves = par1;
		this.markDirty();
	}
	public int getPower()
	{
		return this.guardianPower;
	}
	public void setPower(int par1)
	{
		guardianPower = par1;
		this.markDirty();
	}
	public int getCloudiness()
	{
		return this.cloudiness;
	}
	public void setEclipse(int par1)
	{
		this.solarEclipse = par1;
		this.markDirty();
	}
	public int getEclipse()
	{
		return this.solarEclipse;
	}
	public void resetCloudiness()
	{
		this.rand = new Random();
		this.cloudiness += rand.nextInt(3);
		this.cloudiness -= rand.nextInt(3);
		if(cloudiness > 7)
		{
			cloudiness = 7;
		}
		if(cloudiness < 0)
		{
			cloudiness = 0;
		}
		this.setDirty(true);
	}
	
	public static final void sync(World world, EntityPlayer player) 
	{
		WorldData playerData = WorldData.get(world);
		DayzMod.packetPipeline.sendTo(new SyncWorldPacket(world), (EntityPlayerMP)player);
	}
}
