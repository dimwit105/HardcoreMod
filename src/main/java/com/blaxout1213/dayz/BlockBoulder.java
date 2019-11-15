package com.blaxout1213.dayz;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.world.World;

public class BlockBoulder extends BlockFallingBoulder
{
	public BlockBoulder() 
	{
		this.setBlockTextureName("bedrock");
		this.setBlockUnbreakable();
		this.blockResistance = Float.MAX_VALUE;
		this.fallInstantly = false;
	}
    public void breakBlock(World world, int x, int y, int z, Block p_149749_5_, int p_149749_6_)
    {
    	super.breakBlock(world, x, y, z, p_149749_5_, p_149749_6_);
    }
}
