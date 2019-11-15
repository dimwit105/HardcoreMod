package com.blaxout1213.dayz;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockDarkWater extends BlockFluidClassic 
{
    @SideOnly(Side.CLIENT)
    protected IIcon stillIcon;
    @SideOnly(Side.CLIENT)
    protected IIcon flowingIcon;
    
	public BlockDarkWater(Fluid fluid, Material material) 
	{
		super(fluid, material);
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.setBlockName("darkwater");
		this.quantaPerBlock = 8;
	}
    
    @Override
    public IIcon getIcon(int side, int meta) {
            return (side == 0 || side == 1)? stillIcon : flowingIcon;
    }
   
    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister register) {
            stillIcon = register.registerIcon("dayz:acid_still");
            flowingIcon = register.registerIcon("dayz:acid_flow");
    }
   
    @Override
    public boolean canDisplace(IBlockAccess world, int x, int y, int z) 
    {
            if (world.getBlock(x,  y,  z) == this) return false;
            return super.canDisplace(world, x, y, z);
    }
   
    @Override
    public boolean displaceIfPossible(World world, int x, int y, int z) 
    {
            if (world.getBlock(x,  y,  z) == this) return false;
            return super.displaceIfPossible(world, x, y, z);
    }
    
    public void onEntityCollidedWithBlock(World w, int p_149670_2_, int p_149670_3_, int p_149670_4_, Entity e)
    {	
    	if(e instanceof EntityLivingBase)
    	{
    		EntityLivingBase elb = (EntityLivingBase) e;
    		elb.attackEntityFrom(DayzMod.acid, Float.MAX_VALUE);
    		//elb.attackEntityFrom(DayzMod.acid, Math.max(elb.getHealth()*0.66F, 1));
    	}
    	if(e instanceof EntityItem)
    	{
    		EntityItem ei = (EntityItem) e;
    	}
    }
    
}