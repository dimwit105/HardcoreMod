package com.blaxout1213.dayz.bitcoin;

import java.util.Random;

import com.blaxout1213.dayz.DayzMod;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockBitcoinMiner extends BlockContainer
{
	protected int hashes;

	public BlockBitcoinMiner() 
	{
		super(Material.wood);
	}
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase el, ItemStack item)
    {
        world.scheduleBlockUpdate(x, y, z, this, 1);
    }
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
    	if(rand.nextInt(Integer.MAX_VALUE) == 0)
    	{
    		float f = 0.7F;
    		double d0 = (double)(rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
            double d1 = (double)(rand.nextFloat() * f) + (double)(1.0F - f) * 0.2D + 0.6D;
            double d2 = (double)(rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
    		ItemStack itemstack1 = new ItemStack(DayzMod.bitcoinWood);
    		EntityItem entityitem = new EntityItem(world, (double)x + d0, (double)y + d1, (double)z + d2, itemstack1);
    		world.spawnEntityInWorld(entityitem);
    	}
    	world.scheduleBlockUpdate(x, y, z, this, 1);
    }
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) 
	{
		return new TileEntityMiner();
	}
}
