package com.blaxout1213.dayz.managers;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

import com.blaxout1213.dayz.CustomPotion;
import com.blaxout1213.dayz.DayzMod;
import com.blaxout1213.dayz.ExtendedPlayer;
import com.blaxout1213.dayz.PlayerStalker;
import com.blaxout1213.dayz.UtilMisc;
import com.blaxout1213.dayz.WorldData;
import com.blaxout1213.entity.EntityCorruptedZombie;

public class EverythingManager 
{
	private int posY;
	private int posX;
	private int posZ;
	private int range;
	private int healthCD;
	public int daycheck;
	private Random rand;
    private void generateRandomParticles(String par1Str, EntityPlayer ep)
    {
        for (int i = 0; i < 5; ++i)
        {
            double d0 = this.rand.nextGaussian() * 0.02D;
            double d1 = this.rand.nextGaussian() * 0.02D;
            double d2 = this.rand.nextGaussian() * 0.02D;
            ep.worldObj.spawnParticle(par1Str, this.posX + (double)(this.rand.nextFloat() * ep.width * 2.0F) - (double)ep.width, this.posY + 1.0D + (double)(this.rand.nextFloat() * ep.height), this.posZ + (double)(this.rand.nextFloat() * ep.width * 2.0F) - (double)ep.width, d0, d1, d2);
        }
    }
	public void onUpdate(EntityPlayer ep, World w)
	{
		this.rand = new Random();
		//Minecraft mc = Minecraft.getMinecraft();
		World world = ep.worldObj;
		DataWatcher dw = ep.getDataWatcher();
		ExtendedPlayer props = ExtendedPlayer.get((EntityPlayer) ep);
		EntityPlayer player = (EntityPlayer) ep;
		posX = (int)ep.posX;
		posY = (int)ep.posY;
		posZ = (int)ep.posZ;
		int x = MathHelper.floor_double(ep.posX);
		int y = MathHelper.floor_double(ep.posY);
		int z = MathHelper.floor_double(ep.posZ);
		float healthPercent = ep.getHealth() / ep.getMaxHealth();
		Chunk chunk = ep.worldObj.getChunkFromBlockCoords(x, z);
		//String biome = chunk.getBiomeGenForWorldCoords((int)ep.posX & 15, (int)ep.posZ & 15, mc.theWorld.getWorldChunkManager()).biomeName;
		Block blockID = ep.worldObj.getBlock(posX, posY, posZ);
		Block blockID2 = ep.worldObj.getBlock(posX, posY - 1, posZ);
		//BiomeGenBase biomegenbase2 = ep.worldObj.getWorldChunkManager().getBiomeGenAt(posX, posZ);
		BiomeGenBase biome = chunk.getBiomeGenForWorldCoords(x & 15, z & 15, ep.worldObj.getWorldChunkManager());
		//biome4 = biome;
		//temptimer ++;
		//ep.capabilities.setPlayerWalkSpeed(0.1F * healthPercent);
		healthCD --;
		int wellness = props.wellness("get", 0);
		props.LastHit("add", 1);
		//ep.getEntityAttribute(SharedMonsterAttributes.maxHealth).getBaseValue();
		ep.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(wellness*2);
		if(props.bloodLevel("get", 0) < 500 && dw.getWatchableObjectInt(DayzMod.shockDataWatcher) < 150)
		{
			Object update = dw.getWatchableObjectInt(DayzMod.shockDataWatcher) + (((int)ep.getHealth() + 2)*20);
			dw.updateObject(DayzMod.shockDataWatcher,  update);
		}
		if(ep.getHealth() == ep.getMaxHealth() && ep.ticksExisted % 4 == 0)
		{
			props.bloodLevel("add", 1);
		}
		if(props.bloodLevel("get", 0) > 5000)
		{
			props.bloodLevel("subtract", 1);
		}
		if(world.getWorldTime() % 24000 <= 5 && !world.isRemote)
		{
			double rankCont = WorldData.get(w).getRank() / 500;
			int buildUp = (int) ((int) ((world.getWorldTime() /24000) * world.difficultySetting.getDifficultyId()));
			WorldData.get(world).setSteves(10 + buildUp);
		}
		if(WorldData.get(world).getSteves() == 0 && !world.isRemote)
		{
			ep.addChatComponentMessage(new ChatComponentTranslation("All steves have been slain", new Object[0]));
			WorldData.get(world).setSteves(-1);
		}
		if(ep.getHealth() > ep.getMaxHealth())
		{
			ep.setHealth(ep.getMaxHealth());
		}
		if(props.brokenLeg("get", 0) > 0)
		{
			int power = Math.min(props.brokenLeg("get", 0) / 6000, 4);
			((EntityLivingBase)ep).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 25 , power));
			props.brokenLeg("subtract", 1);
		}
		if(wellness > 15)
		{
			props.wellness("subtract", 1);
		}
		if(wellness < 3)
		{
			props.wellness("add", 1);
		}
		if(UtilMisc.isKO(ep))
		{
			UtilMisc.dropCertainItems(ep.inventory, "main");
		}
		if(!UtilMisc.isKO(ep) && props.bleed("get", 0) == 0 && ep.getFoodStats().getFoodLevel() > 0 && ep.ticksExisted % 1200 <= 10 && healthCD < 0 && !w.isRemote)
		{
			ep.heal(1F);
			healthCD = 50;
		}
		if(props.venomTime("get", 0) >= 0)
		{
			props.venomTime("subtract", 1);
		}
		if(props.nether("get", true))
		{
			if(UtilMisc.getMoonPhase(world.getWorldTime()) != 4)
			{
				props.nether("set", false);
			}
		}
		if(ep.getDataWatcher().getWatchableObjectInt(DayzMod.shockDataWatcher)/20 > (int)ep.getHealth())
		{
			this.generateRandomParticles("snowshovel", ep);
		}
		if((world.getWorldTime() % 60 <= 2) && dw.getWatchableObjectInt(DayzMod.shockDataWatcher) > 0 && !world.isRemote)
		{
			int reduction = Math.min(props.LastHit("get", 0)/100, 10);
			dw.updateObject(DayzMod.shockDataWatcher, dw.getWatchableObjectInt(DayzMod.shockDataWatcher) - reduction);
		}
		if(!ep.worldObj.isRemote)
		{
			if(ep.worldObj.getWorldTime() % 20 == 0)
			{
				EntityPlayerMP player1 = (EntityPlayerMP) ep;
				props.sync(player1);
				WorldData.get(world).sync(world, player1);
				//System.out.println("Update");
			}

		}
		if(dw.getWatchableObjectInt(25) > 0)
		{
			float rndX = (((EntityLivingBase) ep).getRNG().nextFloat() * ep.width * 2) - ep.width;
			float rndY = ((EntityLivingBase) ep).getRNG().nextFloat() * ep.height;
			float rndZ = (((EntityLivingBase) ep).getRNG().nextFloat() * ep.width * 2) - ep.width;
			rndY = -rndY;
			ep.worldObj.spawnParticle("dripWater", ep.posX + rndX, ep.posY + rndY, ep.posZ + rndZ, 0.0D, 0D, 0.0D);
		}
		// -2, 2
		// -3, 3
		// -1, 1
		//resistanceTimer ++;
		/*
		if(props.fire("get", false) == true)
		{
			healz++;
			if(healz >= 300)
			{
				player.heal(1F);
				healz = 0;
			}
		}
		*/
		if(!w.isRemote && ep.lastTickPosX != ep.posX && ep.lastTickPosZ != ep.posZ)
		{
			ItemStack boots = ep.inventory.armorItemInSlot(3);
			if(boots != null)
			{
				props.feetHealth("subtract", 1);
				boots.damageItem(1, ep);
			}
			else if(ep.onGround)
			{
				if(blockID2 == Blocks.bedrock || blockID2 == Blocks.netherrack)
				{
					props.feetHealth("subtract", 3);
				}
				else if(blockID2 == Blocks.grass || blockID2 == Blocks.dirt || blockID2 == Blocks.stone)
				{
					props.feetHealth("subtract", 2);
				}
				else
				{
					props.feetHealth("subtract", 1);
				}
			}
			
		}
		
		if(props.badBlood("get", true) && !ep.isPotionActive(CustomPotion.badblood))
		{
			((EntityLivingBase)ep).addPotionEffect(new PotionEffect(CustomPotion.badblood.id, 3600 ,0));
		}
		if(props.bleed("get", 0) > 0)
		{
			props.bloodLevel("subtract", (int) ((props.bloodLevel("get", 0) * 0.001) * w.difficultySetting.getDifficultyId()));
			if(ep.ticksExisted % 600 == 0)
			{
				ep.attackEntityFrom(DayzMod.bleed, props.bleed("get", 0));
				props.bleed("subtract", 1);
				if(props.bleed("get", 0) == 0 && !ep.worldObj.isRemote)
				{
					
					((EntityPlayer) ep).addChatComponentMessage(new ChatComponentTranslation("bleed.clot" + rand.nextInt(3), new Object[0]));
				}
			}
		}

		props.wellnessCD("subtract", 1);
		if(dw.getWatchableObjectFloat(22) < 0.2F && ep.worldObj.getWorldTime() % 1200 == 0)
		{
			dw.updateObject(22, dw.getWatchableObjectFloat(22) + 0.01F);
		}
		if(dw.getWatchableObjectFloat(22) < 0F)
		{
			dw.updateObject(22, 0F);
		}

		if(dw.getWatchableObjectInt(25) < -1)
		{
			dw.updateObject(25, 0);
		}
		
		if(dw.getWatchableObjectInt(25)/20 > 300)
		{
			int power = Math.min(((dw.getWatchableObjectInt(25)/20) / 300) - 1, 4);
			((EntityLivingBase)ep).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 300 ,power));
			((EntityLivingBase)ep).addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 300 ,power));
			/*if(dw.getWatchableObjectInt(25)/20 > 500)
			{
				if(ep.worldObj.getWorldTime() % 1200 == 0 && player.getHealth() > 0)
				{
					dw.updateObject(22, (float)dw.getWatchableObjectFloat(22) - 0.02F * (power + 1));
					float damage = Math.min((dw.getWatchableObjectInt(25)/20) / 100, player.getHealth() - 1);
					ep.attackEntityFrom(DayzMod.wet, damage);
				}
			}
			*/
		}
		
		/*
		if(dw.getWatchableObjectFloat(22) >= 0.2F)
		{
			resistanceAdd ++;
			if(resistanceAdd >= 3600 && dw.getWatchableObjectFloat(22) <= 0.45F)
			{				
				dw.updateObject(22, dw.getWatchableObjectFloat(22) + 0.01F);
				resistanceAdd = 0;
			}
		}
		*/
		if(props.venomed("get", 0) == 1)
		{
			if(props.venomTime("get", 0) <= 0)
			{
				ep.attackEntityFrom(DayzMod.venom, Float.MAX_VALUE);
				props.venomed("set", 0);
				props.venomTime("set", 0);
			}
		}
		if(props.venomed("get", 0) == 2)
		{
			if(props.venomTime("get", 0) <= 0 && rand.nextFloat() > dw.getWatchableObjectFloat(22))
			{
				ep.attackEntityFrom(DayzMod.venom, Float.MAX_VALUE);
				props.venomed("set", 0);
				props.venomTime("set", 0);
			}
			else if(props.venomTime("get", 0) <= 0)
			{
				((EntityLivingBase)ep).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 3600 ,3));
				((EntityLivingBase)ep).addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 3600 ,3));
				((EntityLivingBase)ep).addPotionEffect(new PotionEffect(Potion.weakness.id, 3600 ,3));
				props.venomed("set", 0);
				props.venomTime("set", 0);
				props.resistence("add", 0.1F);
			}
		}
		if(props.venomed("get", 0) == 3)
		{
			if(props.venomTime("get", 0) <= 0 && (rand.nextFloat() - 0.5F) > dw.getWatchableObjectFloat(22))
			{
				ep.attackEntityFrom(DayzMod.venom, Float.MAX_VALUE);
				props.venomed("set", 0);
				props.venomTime("set", 0);
			}
			else if(props.venomTime("get", 0) <= 0)
			{
				((EntityLivingBase)ep).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 2400 ,2));
				((EntityLivingBase)ep).addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 2400 ,2));
				((EntityLivingBase)ep).addPotionEffect(new PotionEffect(Potion.weakness.id, 2400 ,1));
				props.venomed("set", 0);
				props.venomTime("set", 0);
				props.resistence("add", 0.08F);
			}
		}
		if(props.venomed("get", 0) == 4)
		{
			if(props.venomTime("get", 0) <= 0)
			{
				ep.attackEntityFrom(DayzMod.zombieInfection, 1000);
				props.venomed("set", 0);
				props.venomTime("set", 0);
				EntityCorruptedZombie ez1 = new EntityCorruptedZombie(world);
				ez1.setPosition(x, y, z);
				world.spawnEntityInWorld(ez1);
			}
		}
	}
}
