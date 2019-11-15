package com.blaxout1213.dayz.packet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.blaxout1213.dayz.ExtendedPlayer;
import com.blaxout1213.dayz.WorldData;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class SyncWorldHandler implements IMessageHandler<SyncWorld, IMessage>
{

	private World world;

	@Override
	public IMessage onMessage(SyncWorld message, MessageContext ctx) 
	{
		WorldData.get(message.w).readFromNBT(message.data);
		return null;
	}

}
