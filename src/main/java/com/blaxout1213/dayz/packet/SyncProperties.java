package com.blaxout1213.dayz.packet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;

import com.blaxout1213.dayz.ExtendedPlayer;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class SyncProperties implements IMessage{

	public NBTTagCompound data;
	public EntityPlayer ep;

	public SyncProperties() {}
	
	public SyncProperties(EntityPlayer player)
	{
		//ep = player;
		data = new NBTTagCompound();
		ExtendedPlayer.get(player).saveNBTData(data);
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
