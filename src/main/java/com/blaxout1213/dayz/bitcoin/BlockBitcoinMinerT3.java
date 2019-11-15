package com.blaxout1213.dayz.bitcoin;

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

import com.blaxout1213.dayz.DayzMod;

public class BlockBitcoinMinerT3 extends BlockBitcoinMiner
{
	@Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
    	if(rand.nextInt(1200) == 0)
    	{
    		float f = 0.7F;
    		double d0 = (double)(rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
            double d1 = (double)(rand.nextFloat() * f) + (double)(1.0F - f) * 0.2D + 0.6D;
            double d2 = (double)(rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
    		ItemStack itemstack1 = new ItemStack(DayzMod.bitcoinWood, 3);
    		EntityItem entityitem = new EntityItem(world, (double)x + d0, (double)y + d1, (double)z + d2, itemstack1);
    		world.spawnEntityInWorld(entityitem);
    	}
    	world.scheduleBlockUpdate(x, y, z, this, 1);
    }
}
