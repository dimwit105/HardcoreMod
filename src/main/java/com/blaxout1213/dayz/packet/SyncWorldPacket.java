package com.blaxout1213.dayz.packet;

import com.blaxout1213.dayz.ExtendedPlayer;
import com.blaxout1213.dayz.WorldData;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class SyncWorldPacket extends AbstractPacket
{

	private NBTTagCompound data;

	public SyncWorldPacket() {}
	
	public SyncWorldPacket(World world)
	{
		data = new NBTTagCompound();
		WorldData.get(world).writeToNBT(data);
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
		WorldData.get(player.worldObj).readFromNBT(data);
	}

	@Override
	public void handleServerSide(EntityPlayer player) 
	{
	}

}
