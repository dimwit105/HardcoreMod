package com.blaxout1213.dayz;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovementInput;

public class BlockMovement extends MovementInput {

	public BlockMovement(MovementInput interceptedMovementInput) 
	{
		underlyingMovementInput = interceptedMovementInput;
		//System.out.println("construct movementinput");
	}

	@Override
	public void updatePlayerMoveState() 
	{
		underlyingMovementInput.updatePlayerMoveState();
		EntityPlayer p = Minecraft.getMinecraft().thePlayer;
		DataWatcher dw = Minecraft.getMinecraft().thePlayer.getDataWatcher();
		ExtendedPlayer props = ExtendedPlayer.get((EntityPlayer) p);
		if((props.venomTime("get", 0) < 1200 && props.venomed("get", 0) == 1) || (dw.getWatchableObjectInt(DayzMod.shockDataWatcher)/20 > p.getHealth()))
		{
	        this.jump = false;
	        this.moveStrafe = 0.0F;
	        this.moveForward = 0.0F;
	        this.sneak = false;
		}
		else
		{
	          this.moveStrafe = underlyingMovementInput.moveStrafe;
	          this.moveForward = underlyingMovementInput.moveForward;
	          this.jump = underlyingMovementInput.jump;
	          this.sneak = underlyingMovementInput.sneak;
		}
	}
	protected MovementInput underlyingMovementInput;
}
