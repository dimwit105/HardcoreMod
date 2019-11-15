package com.blaxout1213.dayz.packet;

import com.blaxout1213.dayz.ExtendedPlayer;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class SyncPropertiesPacket extends AbstractPacket
{

	private NBTTagCompound data;

	public SyncPropertiesPacket() {}
	
	public SyncPropertiesPacket(EntityPlayer player)
	{
		data = new NBTTagCompound();
		ExtendedPlayer.get(player).saveNBTData(data);
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		ByteBufUtils.writeTag(buffer, data);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		data = ByteBufUtils.readTag(buffer);
	}

	@Override
	public void handleClientSide(EntityPlayer player) 
	{
		ExtendedPlayer.get(player).loadNBTData(data);	
	}

	@Override
	public void handleServerSide(EntityPlayer player) 
	{
	}

}
