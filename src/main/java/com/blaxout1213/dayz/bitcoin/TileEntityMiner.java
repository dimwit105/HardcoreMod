package com.blaxout1213.dayz.bitcoin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMiner extends TileEntity 
{
    private static final String __OBFID = "I need $300!";
	private static int coinDiff;

    public void readFromNBT(NBTTagCompound nbt)
    {
    	coinDiff = nbt.getInteger("CD");
        super.readFromNBT(nbt);
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
    	//nbt.setInteger("CD", coinDiff);
        //super.writeToNBT(nbt);
    }
    public void addCD()
    {
    	coinDiff++;
    	this.markDirty();
    }
    public static int getCD()
    {
    	return 1200;
    }
}