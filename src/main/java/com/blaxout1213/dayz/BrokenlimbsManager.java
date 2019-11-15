package com.blaxout1213.dayz;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentTranslation;

public class BrokenlimbsManager 
{
	private Random rand;
	int brokenLeg;
	int brokenArm;
	public void onUpdate(EntityPlayer ep)
	{
		this.rand = new Random();
		if(brokenLeg > 30000)
		{
			brokenLeg = 30000;
		}
		if(brokenLeg > 0)
		{
			brokenLeg--;
			ep.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 20, 3));
			if(rand.nextInt(1200) == 0)
			{
				ep.addChatComponentMessage(new ChatComponentTranslation("leg.broken" + rand.nextInt(3), new Object[0]));
			}
		}
		if(brokenArm > 0)
		{
			brokenArm--;
			ep.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 20, 2));
		}
		if(ep.isDead)
		{
			brokenLeg = 0;
		}
	}
	public void breakArms(int par1)
	{
		brokenArm += (par1 * 20);
	}
	public void breakLegs(int par1)
	{
		brokenLeg += (par1 * 20);
	}
	public String howBadlyBroken()
	{
		if(brokenLeg > 27600) // 23 min
		{
			return "\u00A74Broken Leg!\u00A74";
		}
		if(brokenLeg > 24000) //20 min
		{
			return "\u00A7cBroken Leg!\u00A7c";
		}
		if(brokenLeg > 15600) //13 min
		{
			return "\u00A76Broken Leg!\u00A76";
		}
		if(brokenLeg > 8400) //7 min
		{
			return "\u00A7eBroken Leg!\u00A7e";
		}
		if(brokenLeg > 0)
		{
			return "Broken Leg!";
		}
		return "unknown";
	}
}
