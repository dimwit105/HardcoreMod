package com.blaxout1213.dayz.packet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import com.blaxout1213.dayz.ExtendedPlayer;
import com.blaxout1213.dayz.WorldData;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class SyncWorld implements IMessage{

	public NBTTagCompound data;
	public World w;

	public SyncWorld() {}
	
	public SyncWorld(World world)
	{
		//ep = player;
		data = new NBTTagCompound();
		WorldData.get(world).writeToNBT(data);
	}
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		data = ByteBufUtils.readTag(buf);	
	}
	

	@Override
	public void toBytes(ByteBuf buf) 
	{
		ByteBufUtils.writeTag(buf, data);
	}

}
