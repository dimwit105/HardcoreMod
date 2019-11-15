package com.blaxout1213.dayz;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenPoisonCactus extends WorldGenerator implements IWorldGenerator
{
    private static final String __OBFID = "CL_00000860";
    public static PoisonCactus PC = new PoisonCactus();
	private int zChunk;
	private int xChunk;
	private int yLevel;
    
    @Override
    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {	
    	System.out.println("It ran");
        for (int l = 0; l < 10; ++l)
        {
            int i1 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
            int j1 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
            int k1 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);

            if (par1World.isAirBlock(i1, j1, k1))
            {
                int l1 = 1 + par2Random.nextInt(par2Random.nextInt(3) + 1);

                for (int i2 = 0; i2 < l1; ++i2)
                {
                    if (DayzMod.poisonCactus.canBlockStay(par1World, i1, j1 + i2, k1))
                    {
                        par1World.setBlock(i1, j1 + i2, k1, DayzMod.poisonCactus, 0, 2);
                        System.out.println("Cactus generated");
                    }
                }
            }
        }
        return true;
        
    }
    
	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world,IChunkProvider chunkGenerator, IChunkProvider chunkProvider)	 
	{
		//System.out.println("It ran # 2");
		
        for (int l = 0; l < 3; ++l)
        {
        	//System.out.println("Step #1");
            xChunk = chunkX* 16+ rand.nextInt(8) - rand.nextInt(8);
            yLevel = 64 + rand.nextInt(12) - rand.nextInt(12);
            zChunk = chunkZ*16+ rand.nextInt(8) - rand.nextInt(8);
            BiomeGenBase b = world.getBiomeGenForCoords(chunkX, chunkZ);
            if (world.isAirBlock(xChunk, yLevel, zChunk))
            {
            	//System.out.println("Step #2");
                int l1 = 1 + rand.nextInt(rand.nextInt(3) + 1);

                for (int i2 = 0; i2 < l1; ++i2)
                {
                	//System.out.println("Step #3");
                    if (PC.canBlockStay(world, xChunk, yLevel, zChunk))
                    {
                        world.setBlock(xChunk, yLevel + i2, zChunk, DayzMod.poisonCactus, 0, 2);
                        System.out.println("Cactus generated " + xChunk+" " + yLevel+" " + zChunk);
                    }
                }
            }
        }
	}
}