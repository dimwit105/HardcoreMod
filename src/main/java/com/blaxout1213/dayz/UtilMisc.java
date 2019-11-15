package com.blaxout1213.dayz;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class UtilMisc 
{
/**
 * Util Class rules
 * 1. Must be public
 * 2. Must be static
 * 3. Must be useful, at least in 1 case
 */
	private Random rand;
	public static int TenMin = 12000;
    public static float applyPotionDamageCalculations(DamageSource source, float damage, EntityPlayer ep)
    {
        if (source.isDamageAbsolute())
        {
            return damage;
        }
        else
        {

            int i;
            int j;
            float f1;

            if (ep.isPotionActive(Potion.resistance) && source != DamageSource.outOfWorld)
            {
                i = (ep.getActivePotionEffect(Potion.resistance).getAmplifier() + 1) * 5;
                j = 25 - i;
                f1 = damage * (float)j;
                damage = f1 / 25.0F;
            }

            if (damage <= 0.0F)
            {
                return 0.0F;
            }
            else
            {
                i = EnchantmentHelper.getEnchantmentModifierDamage(ep.getLastActiveItems(), source);

                if (i > 20)
                {
                    i = 20;
                }

                if (i > 0 && i <= 20)
                {
                    j = 25 - i;
                    f1 = damage * (float)j;
                    damage = f1 / 25.0F;
                }

                return damage;
            }
        }
    }
	public static String bloodTypeString(int type)
	{
		if(type == 0)
		{
			return "O-";
		}
		if(type == 1)
		{
			return "O+";
		}
		if(type == 2)
		{
			return "A-";
		}
		if(type == 3)
		{
			return "A+";
		}
		if(type == 4)
		{
			return "B-";
		}
		if(type == 5)
		{
			return "B+";
		}
		if(type == 6)
		{
			return "AB-";
		}
		if(type == 7)
		{
			return "AB+";
		}
		return "Unreconized integer";
	}
    public static int getMoonPhase(long par1)
    {
        return (int)(par1 / 24000L % 8L + 8L) % 8;
    }
    public static Item randomItem(Random rand)
    {
    	if(rand.nextInt(6) == 0)
    	{
    		return Items.stick;
    	}
    	if(rand.nextInt(6) == 0)
    	{
    		return Items.bowl;
    	}
    	if(rand.nextInt(6) == 0)
    	{
    		return Items.bone;
    	}
    	if(rand.nextInt(6) == 0)
    	{
    		return Items.book;
    	}
    	if(rand.nextInt(6) == 0)
    	{
    		return Items.brick;
    	}
    	if(rand.nextInt(6) == 0)
    	{
    		return Items.clay_ball;
    	}
    	if(rand.nextInt(6) == 0)
    	{
    		return Items.coal;
    	}
    	if(rand.nextInt(6) == 0)
    	{
    		return Items.flint;
    	}
    	if(rand.nextInt(6) == 0)
    	{
    		return Items.gold_nugget;
    	}
    	if(rand.nextInt(6) == 0)
    	{
    		return Items.iron_ingot;
    	}
    	if(rand.nextInt(6) == 0)
    	{
    		return Items.melon;
    	}
    	if(rand.nextInt(6) == 0)
    	{
    		return Items.poisonous_potato;
    	}
		return Items.blaze_powder;
    }
    public static boolean isKO(EntityPlayer ep)
    {
    	int health = (int) ep.getHealth();
    	if(ep.getDataWatcher().getWatchableObjectInt(DayzMod.shockDataWatcher)/20 > health + ep.getAbsorptionAmount())
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    public static boolean isPlayerWalking(EntityPlayer ep)
    {
    	if(ep.moveForward != 0 || ep.moveStrafing != 0)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    public static void teleportPlayer(EntityPlayer ep, double x, double z)
    {
    	World world = ep.worldObj;
    	int y = 256;
        for(int i=1; i<256; i++)
        {
        	if(world.doesBlockHaveSolidTopSurface(world, (int)x, y - 1, (int)z) && world.getCollidingBoundingBoxes(ep, ep.boundingBox).isEmpty())
        	{
        		i = 300;
        		ep.setPositionAndUpdate(x, (double)y + 1, z);
        		System.out.println("Teleport complete");
        	}
        	else
        	{
        		System.out.println("Teleport failed @ Y " + y);
        		y--;
        	}
        }
    }
    public static float getDifficultyMod(World world)
    {
    	if(world.difficultySetting == EnumDifficulty.EASY)
    	{
    		return DayzMod.easyMod;
    	}
    	if(world.difficultySetting == EnumDifficulty.NORMAL)
    	{
    		return DayzMod.normMod;
    	}
    	if(world.difficultySetting == EnumDifficulty.HARD && !world.getWorldInfo().isHardcoreModeEnabled())
    	{
    		return DayzMod.hardMod;
    	}
    	if(world.difficultySetting == EnumDifficulty.HARD && world.getWorldInfo().isHardcoreModeEnabled())
    	{
    		return DayzMod.hardCMod;
    	}
    	return 1F;
    }
    public static void dropCertainItems(InventoryPlayer ip, String toDrop)
    {
        int i;
        if(toDrop == "main")
        {
            for (i = 0; i < ip.mainInventory.length; ++i)
            {
                if (ip.mainInventory[i] != null)
                {
                	ip.player.func_146097_a(ip.mainInventory[i], true, false);
                	ip.mainInventory[i] = null;
                }
            }
        }
        if(toDrop == "armor")
        {
            for (i = 0; i < ip.armorInventory.length; ++i)
            {
                if (ip.armorInventory[i] != null)
                {
                	ip.player.func_146097_a(ip.armorInventory[i], true, false);
                	ip.armorInventory[i] = null;
                }
            }
        }
    }
    public static String getWorldRankStr(int rank)
    {
    	if(rank >= 481)
    	{
    		return "Diamond V";
    	}
    	else if(rank >= 461)
    	{
    		return "Diamond IV";
    	}
    	else if(rank >= 441)
    	{
    		return "Diamond III";
    	}
    	else if(rank >= 421)
    	{
    		return "Diamond II";
    	}
    	else if(rank >= 401)
    	{
    		return "Diamond I";
    	}
    	else if(rank >= 381)
    	{
    		return "Platinum V";
    	}
    	else if(rank >= 361)
    	{
    		return "Platinum IV";
    	}
    	else if(rank >= 341)
    	{
    		return "Platinum III";
    	}
    	else if(rank >= 321)
    	{
    		return "Platinum II";
    	}
    	else if(rank >= 301)
    	{
    		return "Platinum I";
    	}
    	else if(rank >= 281)
    	{
    		return "Gold V";
    	}
    	else if(rank >= 261)
    	{
    		return "Gold IV";
    	}
    	else if(rank >= 241)
    	{
    		return "Gold III";
    	}
    	else if(rank >= 221)
    	{
    		return "Gold II";
    	}
    	else if(rank >= 201)
    	{
    		return "Gold I";
    	}
    	else if(rank >= 181)
    	{
    		return "Silver V";
    	}
    	else if(rank >= 161)
    	{
    		return "Silver IV";
    	}
    	else if(rank >= 141)
    	{
    		return "Silver III";
    	}
    	else if(rank >= 121)
    	{
    		return "Silver II";
    	}
    	else if(rank >= 101)
    	{
    		return "Silver I";
    	}
    	else if(rank >= 81)
    	{
    		return "Bronze V";
    	}
    	else if(rank >= 61)
    	{
    		return "Bronze IV";
    	}
    	else if(rank >= 41)
    	{
    		return "Bronze III";
    	}
    	else if(rank >= 21)
    	{
    		return "Bronze II";
    	}
    	else if(rank >= 1)
    	{
    		return "Bronze I";
    	}
    	return "Unobtainium III";
    }
    public static int getWorldRankStrInt(int rank)
    {
    	if(rank >= 481)
    	{
    		return 25;
    	}
    	else if(rank >= 461)
    	{
    		return 24;
    	}
    	else if(rank >= 441)
    	{
    		return 23;
    	}
    	else if(rank >= 421)
    	{
    		return 22;
    	}
    	else if(rank >= 401)
    	{
    		return 21;
    	}
    	else if(rank >= 381)
    	{
    		return 20;
    	}
    	else if(rank >= 361)
    	{
    		return  19 ;
    	}
    	else if(rank >= 341)
    	{
    		return  18 ;
    	}
    	else if(rank >= 321)
    	{
    		return  17 ;
    	}
    	else if(rank >= 301)
    	{
    		return  16 ;
    	}
    	else if(rank >= 281)
    	{
    		return  15 ;
    	}
    	else if(rank >= 261)
    	{
    		return  14 ;
    	}
    	else if(rank >= 241)
    	{
    		return  13 ;
    	}
    	else if(rank >= 221)
    	{
    		return  12 ;
    	}
    	else if(rank >= 201)
    	{
    		return  11 ;
    	}
    	else if(rank >= 181)
    	{
    		return  10 ;
    	}
    	else if(rank >= 161)
    	{
    		return  9 ;
    	}
    	else if(rank >= 141)
    	{
    		return  8 ;
    	}
    	else if(rank >= 121)
    	{
    		return  7 ;
    	}
    	else if(rank >= 101)
    	{
    		return  6 ;
    	}
    	else if(rank >= 81)
    	{
    		return  5 ;
    	}
    	else if(rank >= 61)
    	{
    		return  4 ;
    	}
    	else if(rank >= 41)
    	{
    		return  3 ;
    	}
    	else if(rank >= 21)
    	{
    		return  2 ;
    	}
    	else if(rank >= 1)
    	{
    		return  1 ;
    	}
    	return  0;
    }
    public static boolean isWaterNearby(World par1World, int par2, int par3, int par4)
	{
		for (int l = par2 - 4; l <= par2 + 4; ++l)
			for (int i1 = par3; i1 <= par3 + 4; ++i1)
				for (int j1 = par4 - 4; j1 <= par4 + 4; ++j1)
					if (par1World.getBlock(l, i1, j1).getMaterial() == Material.water)
						return true;

		return false;
	}
    public static void deleteObsidian(World world, int par2, int par3, int par4)
	{
		for (int l = par2 - 16; l <= par2 + 16; ++l)
			for (int i1 = par3 - 16; i1 <= par3 + 16; ++i1)
				for (int j1 = par4 - 16; j1 <= par4 + 16; ++j1)
					if (world.getBlock(l, i1, j1) == Blocks.obsidian)
					{
						world.setBlock(l, i1, j1, Blocks.air);
					}
	}
    public static boolean trueDaytime(long time)
    {
    	if(time > 0 && time < 12000)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
}
