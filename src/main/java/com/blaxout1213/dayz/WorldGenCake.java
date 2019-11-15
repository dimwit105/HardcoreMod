package com.blaxout1213.dayz;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCake extends WorldGenerator implements IWorldGenerator
{
    private static final String __OBFID = "CL_00000860";
    public static PoisonCactus PC = new PoisonCactus();
	private int zChunk;
	private int xChunk;
	private int yLevel;
	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world,IChunkProvider chunkGenerator, IChunkProvider chunkProvider)	 
	{
		//System.out.println("It ran # 2");
		
        for (int l = 0; l < 1; ++l)
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
                	int chance = rand.nextInt(8);
                	int type = rand.nextInt(3);
                	//System.out.println("Step #3");
                    if (world.getBlock(xChunk, yLevel - 1, zChunk) == Blocks.grass && chance == 0)
                    {
                    	if(type <= 1 )
                    	{
                    		world.setBlock(xChunk, yLevel, zChunk, DayzMod.poisonCake, 0, 2);
                    	}
                    	else
                    	{
                    		world.setBlock(xChunk, yLevel, zChunk, Blocks.cake, 0, 2);
                    	}
                        System.out.println("Cake generated " + xChunk+" " + yLevel+" " + zChunk);
                    }
                }
            }
        }
	}
	@Override
	public boolean generate(World var1, Random var2, int var3, int var4,
			int var5) {
		// TODO Auto-generated method stub
		return false;
	}
}