package com.blaxout1213.dayz.packet;

import net.minecraft.entity.player.EntityPlayer;

import com.blaxout1213.dayz.ExtendedPlayer;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class SyncPropertiesHandler implements IMessageHandler<SyncProperties, IMessage>
{

	private EntityPlayer player;

	@Override
	public IMessage onMessage(SyncProperties message, MessageContext ctx) 
	{
		ExtendedPlayer.get(message.ep).loadNBTData(message.data);
		return null;
	}

}
