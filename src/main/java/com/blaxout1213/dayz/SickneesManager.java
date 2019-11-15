package com.blaxout1213.dayz;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class SickneesManager 
{
	private Random rand;
	public int hasCold;
	private int coldStage;

	public void onUpdate(EntityPlayer ep)
	{
		//Common Cold
		if(hasCold > 0 && coldStage == 0)
		{
			ep.addPotionEffect(new PotionEffect(Potion.weakness.id, 20, 0));
			hasCold--;
			if(hasCold <= 0)
			{
				hasCold = 18000;
				coldStage = 1;
			}
		}
		else if(hasCold > 0 && coldStage == 1)
		{
			ep.addPotionEffect(new PotionEffect(Potion.weakness.id, 20, 1));
			ep.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 20, 0));
			hasCold--;
			if(hasCold <= 0)
			{
				hasCold = 12000;
				coldStage = 2;
			}
		}
		else if(hasCold > 0 && coldStage == 2)
		{
			ep.addPotionEffect(new PotionEffect(Potion.weakness.id, 20, 0));
			hasCold--;
			if(hasCold <= 0)
			{
				hasCold = 0;
				coldStage = 0;
			}
		}
	}
	public void contractCold()
	{
		hasCold = 6000;
	}
	public void cureAll()
	{
		hasCold = 0;
	}
}
