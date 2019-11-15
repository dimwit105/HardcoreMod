package com.blaxout1213.dayz;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenLargeDungeon extends WorldGenerator implements IWorldGenerator
{
    private static final String __OBFID = "CL_00000860";
    public static PoisonCactus PC = new PoisonCactus();
	private int z;
	private int x;
	private int y;
    
    @Override
    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
    	return true;
    }
    
	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world,IChunkProvider chunkGenerator, IChunkProvider chunkProvider)	 
	{
		//System.out.println("It ran # 2");
		
        for (int l = 0; l < 1; ++l)
        {
            x = chunkX* 16+ rand.nextInt(8) - rand.nextInt(8);
            y = 12 + rand.nextInt(64);
            z = chunkZ*16+ rand.nextInt(8) - rand.nextInt(8);
            if(world.getBlock(x, y, z) == Blocks.stone && world.getBlock(x, y, z) != null && rand.nextInt(6) == 0)
            {
            	this.generateFloor(world, y);
            	this.generatePillar(world, x, z);
            	this.generatePillar(world, x +1, z);
            	this.generatePillar(world, x +2, z);
            	this.generatePillar(world, x +3, z);
            	this.generatePillar(world, x +4, z);
            	this.generatePillar(world, x +5, z);
            	this.generatePillar(world, x, z +1);
            	this.generatePillar(world, x, z +2);
            	this.generatePillar(world, x, z +3);
            	this.generatePillar(world, x, z +4);
            	this.generatePillar(world, x, z +5);
            	this.generatePillar(world, x, z +6);

            	this.generateFloor(world, y + 6);
            	System.out.println("Room at: " + x +" " + y + " " + z);
            }
        }
	}
	private void generateFloor(World world, int y)
	{
		world.setBlock(x, y, z, Blocks.mossy_cobblestone);
		world.setBlock(x, y, z +1, Blocks.mossy_cobblestone);
		world.setBlock(x, y, z +2, Blocks.mossy_cobblestone);
		world.setBlock(x, y, z +3, Blocks.mossy_cobblestone);
		world.setBlock(x, y, z +4, Blocks.mossy_cobblestone);
		world.setBlock(x, y, z +5, Blocks.mossy_cobblestone);
		
		world.setBlock(x +1, y, z, Blocks.mossy_cobblestone);
		world.setBlock(x +1, y, z +1, Blocks.mossy_cobblestone);
		world.setBlock(x +1, y, z +2, Blocks.mossy_cobblestone);
		world.setBlock(x +1, y, z +3, Blocks.mossy_cobblestone);
		world.setBlock(x +1, y, z +4, Blocks.mossy_cobblestone);
		world.setBlock(x +1, y, z +5, Blocks.mossy_cobblestone);
		world.setBlock(x +1, y, z +6, Blocks.mossy_cobblestone);
		
		world.setBlock(x +2, y, z, Blocks.mossy_cobblestone);
		world.setBlock(x +2, y, z +1, Blocks.mossy_cobblestone);
		world.setBlock(x +2, y, z +2, Blocks.mossy_cobblestone);
		world.setBlock(x +2, y, z +3, Blocks.mossy_cobblestone);
		world.setBlock(x +2, y, z +4, Blocks.mossy_cobblestone);
		world.setBlock(x +2, y, z +5, Blocks.mossy_cobblestone);
		world.setBlock(x +2, y, z +6, Blocks.mossy_cobblestone);
		
		world.setBlock(x +3, y, z, Blocks.mossy_cobblestone);
		world.setBlock(x +3, y, z +1, Blocks.mossy_cobblestone);
		world.setBlock(x +3, y, z +2, Blocks.mossy_cobblestone);
		world.setBlock(x +3, y, z +3, Blocks.mossy_cobblestone);
		world.setBlock(x +3, y, z +4, Blocks.mossy_cobblestone);
		world.setBlock(x +3, y, z +5, Blocks.mossy_cobblestone);
		world.setBlock(x +3, y, z +6, Blocks.mossy_cobblestone);
		
		world.setBlock(x +4, y, z, Blocks.mossy_cobblestone);
		world.setBlock(x +4, y, z +1, Blocks.mossy_cobblestone);
		world.setBlock(x +4, y, z +2, Blocks.mossy_cobblestone);
		world.setBlock(x +4, y, z +3, Blocks.mossy_cobblestone);
		world.setBlock(x +4, y, z +4, Blocks.mossy_cobblestone);
		world.setBlock(x +4, y, z +5, Blocks.mossy_cobblestone);
		world.setBlock(x +4, y, z +6, Blocks.mossy_cobblestone);
		
		world.setBlock(x +5, y, z, Blocks.mossy_cobblestone);
		world.setBlock(x +5, y, z +1, Blocks.mossy_cobblestone);
		world.setBlock(x +5, y, z +2, Blocks.mossy_cobblestone);
		world.setBlock(x +5, y, z +3, Blocks.mossy_cobblestone);
		world.setBlock(x +5, y, z +4, Blocks.mossy_cobblestone);
		world.setBlock(x +5, y, z +5, Blocks.mossy_cobblestone);
		world.setBlock(x +5, y, z +6, Blocks.mossy_cobblestone);
	}
	private void generatePillar(World world, int x, int z)
	{
		world.setBlock(x, y, z, Blocks.mossy_cobblestone);
		world.setBlock(x, y +1, z, Blocks.mossy_cobblestone);
		world.setBlock(x, y +2, z, Blocks.mossy_cobblestone);
		world.setBlock(x, y +3, z, Blocks.mossy_cobblestone);
		world.setBlock(x, y +4, z, Blocks.mossy_cobblestone);
	}
}