package com.blaxout1213.dayz;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.WorldProvider;

public class WorldProviderSurface2 extends WorldProvider
{

	float one = 0.0625F;
	float two = 0.125F;
	float three = two + one;
	float four = 0.25F;
    /**
     * Returns the dimension's name, e.g. "The End", "Nether", or "Overworld".
     */
    public String getDimensionName()
    {
        return "Overworld";
    }
    private boolean isDay(long time)
    {
    	if(time % 24000 > 6000 || time % 24000 < 18000)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    @Override
    public boolean canCoordinateBeSpawn(int p_76566_1_, int p_76566_2_)
    {
        return this.worldObj.getTopBlock(p_76566_1_, p_76566_2_) == Blocks.sand;
    }
    @Override
    public float getSunBrightnessFactor(float par1)
    {
		WorldData wProps = WorldData.get(worldObj);
		if(worldObj.rand.nextInt(100) == 0)
		{
			wProps.resetCloudiness();
		}
		if(wProps.getEclipse() <= -2400)
		{
			int base = (int) ((24000 * DayzMod.eclipseAverage) / 2);
			int variation = worldObj.rand.nextInt((int) (24000 * DayzMod.eclipseAverage));
			int add = base + variation;
			wProps.setEclipse(add);
		}
		if(wProps.getEclipse() > 1 || wProps.getEclipse() < 0)
		{
			wProps.setEclipse(wProps.getEclipse() - 1);
		}
        float f1 = worldObj.getCelestialAngle(par1);
        float f2 = 1.0F - (MathHelper.cos(f1 * (float)Math.PI * 2.0F) * 2.0F + 0.5F);
        float f3 = one * wProps.getCloudiness();

        if (f2 < 0.0F)
        {
            f2 = 0.0F;
        }

        if (f2 > 1.0F)
        {
            f2 = 1.0F;
        }

        f2 = 1.0F - f2;
        f2 = (float)((double)f2 * (1.0D - (double)(worldObj.getRainStrength(par1) * 5.0F) / 16.0D));
        f2 = (float)((double)f2 * (1.0D - (double)(worldObj.getWeightedThunderStrength(par1) * 5.0F) / 16.0D));
        float inverted =  1.0F - worldObj.getCurrentMoonPhaseFactor();
        float whatToAdd = (worldObj.getCurrentMoonPhaseFactor() * four) - one;
        if(isDay(worldObj.getWorldTime()))
        {
        	f2 = f2 + (whatToAdd);
        }
        f2 = f2 - f3;
        if(worldObj.getWorldTime() % 24000 > 5000 && worldObj.getWorldTime() % 24000 < 7000 && wProps.getEclipse() == 1)
        {
        	wProps.setEclipse(wProps.getEclipse() - 2);
        }
    	if(wProps.getEclipse() <= 0)
    	{
    		f2 = 0F;
    	}  	
        if(f2 > 1F)
        {
        	f2 = 1F;
        }
        if(f2 < -one)
        {
        	f2 = -one;
        }
        return f2;
    }
    @Override
    @SideOnly(Side.CLIENT)
    public float getSunBrightness(float par1)
    {
		WorldData wProps = WorldData.get(worldObj);
        float f1 = worldObj.getCelestialAngle(par1);
        float f2 = 1.0F - (MathHelper.cos(f1 * (float)Math.PI * 2.0F) * 2.0F + 0.5F);
        float f3 = one * wProps.getCloudiness();

        if (f2 < 0.0F)
        {
            f2 = 0.0F;
        }

        if (f2 > 1.0F)
        {
            f2 = 1.0F;
        }

        f2 = 1.0F - f2;
        f2 = (float)((double)f2 * (1.0D - (double)(worldObj.getRainStrength(par1) * 5.0F) / 16.0D));
        f2 = (float)((double)f2 * (1.0D - (double)(worldObj.getWeightedThunderStrength(par1) * 5.0F) / 16.0D));
        float inverted =  1.0F - worldObj.getCurrentMoonPhaseFactor();
        float whatToAdd = (worldObj.getCurrentMoonPhaseFactor() * four) - one;
        if(isDay(worldObj.getWorldTime()))
        {
        	f2 = f2 + (whatToAdd);
        }
        f2 = f2 - f3;
    	if(wProps.getEclipse() <= 0)
    	{
    		f2 = 0F;
    	}  	
        if(f2 > 1F)
        {
        	f2 = 1F;
        }
        if(f2 < -one)
        {
        	f2 = -one;
        }
        return f2;
    }
}