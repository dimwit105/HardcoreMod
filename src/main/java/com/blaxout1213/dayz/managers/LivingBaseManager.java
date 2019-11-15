package com.blaxout1213.dayz.managers;

import java.util.List;
import java.util.Random;

import com.blaxout1213.dayz.CustomPotion;
import com.blaxout1213.dayz.DayzMod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LivingBaseManager 
{
	double radius = DayzMod.illnessRange;
	public void onUpdate(EntityLivingBase elb, World world)
	{
		double x = elb.posX;
		double y = elb.posY;
		double z = elb.posZ;
		Block blockID2 = elb.worldObj.getBlock((int)x, (int)y - 1, (int)z);
		Random rand = new Random();
		if(!world.isDaytime() && world.canBlockSeeTheSky((int)elb.posX, (int)elb.posY, (int)elb.posZ) && elb.dimension == 0)
		{
			if(this.getMoonPhase(world.getWorldTime()) == 4 && rand.nextInt(4) == 0)
			{
				//New moon code
			}
			if(this.getMoonPhase(world.getWorldTime()) == 5)
			{
				(elb).addPotionEffect(new PotionEffect(Potion.damageBoost.id, 60 ,0));
			}
			if(this.getMoonPhase(world.getWorldTime()) == 0 && !elb.isPotionActive(Potion.field_76444_x))
			{
				(elb).addPotionEffect(new PotionEffect(Potion.field_76444_x.id, 30*20 ,1));
			}
		}
		CustomPotion.checkAndApplyEffects(elb);
		if(elb.isPotionActive(CustomPotion.illness))
		{
			AxisAlignedBB bounds = AxisAlignedBB.getBoundingBox(x - radius, y - radius, z - radius, x + radius, y + radius, z + radius);
			List<EntityLivingBase> allEntities = world.getEntitiesWithinAABB(EntityLivingBase.class, bounds);
			for (EntityLivingBase entity : allEntities)
			{
				if(!entity.isPotionActive(CustomPotion.illness))
				{
					entity.addPotionEffect(new PotionEffect(CustomPotion.illness.id, 3600 ,0));
				}
			}
	    	int c;
	        for (c = 0; c < 2; ++c)
	        {
	            elb.worldObj.spawnParticle("magicCrit", elb.posX + (rand.nextDouble() - 0.5D) * (double)elb.width, elb.posY + rand.nextDouble() * (double)elb.height - 0.25D, elb.posZ + (rand.nextDouble() - 0.5D) * (double)elb.width, (rand.nextDouble() - 0.5D) * 2.0D, -rand.nextDouble(), (rand.nextDouble() - 0.5D) * 2.0D);
	        }
		}
		if(elb.isPotionActive(CustomPotion.corruption) && !world.isRemote)
		{
	        for (int l = 0; l < 4; ++l)
	        {
	            int i = MathHelper.floor_double(x + (double)((float)(l % 2 * 2 - 1) * 0.25F));
	            int j = MathHelper.floor_double(y) - 1;
	            int k = MathHelper.floor_double(z + (double)((float)(l / 2 % 2 * 2 - 1) * 0.25F));

	            if (elb.worldObj.getBlock(i, j, k).getMaterial() == Material.grass || elb.worldObj.getBlock(i, j, k).getMaterial() == Material.ground)
	            {
	                elb.worldObj.setBlock(i, j, k, DayzMod.curroption);
	            }
	        }
		}
		else if(!world.isRemote)
		{
	        for (int l = 0; l < 4; ++l )
	        {
	        	long t = elb.worldObj.getWorldTime() % 4;
	            int i = MathHelper.floor_double(x + (double)((float)(t % 2 * 2 - 1) * 0.25F));
	            int j = MathHelper.floor_double(y) - 1;
	            int k = MathHelper.floor_double(z + (double)((float)(t / 2 % 2 * 2 - 1) * 0.25F));

	            if (elb.worldObj.getBlock(i, j, k) == DayzMod.curroption)
	            {
	            	elb.addPotionEffect(new PotionEffect(CustomPotion.corruption.id, 1200 , 0));
	            }
	        }
		}
	}

	private int getMoonPhase(long worldTime) 
	{
		return (int)(worldTime / 24000L % 8L + 8L) % 8;
	}
}
