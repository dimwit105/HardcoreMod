package com.blaxout1213.dayz;

import java.util.Random;

import com.blaxout1213.entity.EntityCorruptedZombie;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class CustomPotion extends Potion
{
	public static CustomPotion hypothermia;
	public static CustomPotion heatstroke;
	public static CustomPotion frostbite;
	public static CustomPotion dehydration;
	public static CustomPotion insanity;
	
	//public static ResourceLocation textureResource = new ResourceLocation("dayz", "textures/gui/potion.png");
	public static CustomPotion illness;
	public static CustomPotion badblood;
	public static CustomPotion corruption;
	public static CustomPotion netherBound;
	public static CustomPotion tranquilized;
	
	public CustomPotion(int par1, boolean par2, int par3)
	{
		super(par1, par2, par3);
	}
	
	public static void checkAndApplyEffects(EntityLivingBase el)
	{
		Random rand = new Random();
		if(el.worldObj.isRemote)
		{
			return;
		}
		if(el.isPotionActive(tranquilized))
		{
			if(el instanceof EntityPlayer && el.getActivePotionEffect(tranquilized).getDuration() % 2 == 0)
			{
				EntityPlayer ep = (EntityPlayer) el;
				ExtendedPlayer props = ExtendedPlayer.get((EntityPlayer) ep);
				props.LastHit("set", 0);
				ep.getDataWatcher().updateObject(DayzMod.shockDataWatcher, ep.getDataWatcher().getWatchableObjectInt(DayzMod.shockDataWatcher) + 1 + el.getActivePotionEffect(tranquilized).getAmplifier());
			}
		}
		if(el.isPotionActive(illness))
		{
			if(el.getActivePotionEffect(illness).getDuration() == 0)
			{
				el.removePotionEffect(illness.id);
			}
			if(el.getActivePotionEffect(illness).getDuration() % 100 == 0 && !(el instanceof EntityZombie))
			{
				int damage = el.getActivePotionEffect(illness).getDuration() / 100;
				damage = 36 - damage;
				if(damage > 0)
				{
					el.attackEntityFrom(DayzMod.sickness, damage);
				}		
			}		
		}
		if(el.isPotionActive(corruption))
		{
			if(el.getActivePotionEffect(corruption).getDuration() == 0)
			{
				el.removePotionEffect(corruption.id);
			}
			if(el.getActivePotionEffect(corruption).getDuration() % 100 == 0 && !(el instanceof EntityCorruptedZombie))
			{
				el.attackEntityFrom(DayzMod.corruption, 1);
			}		
		}
		if(el.isPotionActive(badblood))
		{
			if(el.getActivePotionEffect(badblood).getDuration() == 0)
			{
				el.removePotionEffect(badblood.id);
			}
			if(el.getActivePotionEffect(badblood).getDuration() % 40 == 0 && el instanceof EntityPlayer)
			{
				EntityPlayer ep = (EntityPlayer) el;
				ExtendedPlayer props = ExtendedPlayer.get((EntityPlayer) ep);
				props.LastHit("set", 0);
				if(ep.getDataWatcher().getWatchableObjectInt(DayzMod.shockDataWatcher)/20 > (int)ep.getHealth())
				{
					el.attackEntityFrom(DayzMod.badBlood, 1);
				}
				else
				{
					ep.getDataWatcher().updateObject(DayzMod.shockDataWatcher, ep.getDataWatcher().getWatchableObjectInt(DayzMod.shockDataWatcher) + 20);
				}
			}		
		}
		if(el.isPotionActive(netherBound))
		{
	        if (el.ridingEntity == null && el.riddenByEntity == null && !el.worldObj.isRemote && el instanceof EntityPlayer)
	        {
				EntityPlayer ep = (EntityPlayer) el;
	        	if(el.getActivePotionEffect(netherBound).getDuration() == 6)
	        	{
	        		ep.inventory.dropAllItems();
					ExtendedPlayer props = ExtendedPlayer.get(ep);
					el.travelToDimension(-1);
					props.nether("set", true);
					ep.worldObj.playSoundAtEntity(ep, "portal.travel", 1F, 1F);
	        	}
				UtilMisc.deleteObsidian(ep.worldObj, MathHelper.floor_double(ep.posX), MathHelper.floor_double(ep.posY), MathHelper.floor_double(ep.posZ));
	        }
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * This method returns true if the potion effect is bad - negative - for the entity.
	 */
	public boolean isBadEffect()
	{
		return true;
	}
}
