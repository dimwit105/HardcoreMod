package com.blaxout1213.dayz;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCurroption extends Block{
	private IIcon top;
	private IIcon bottom;
	private Random rand;
	protected BlockCurroption() 
	{
		super(Material.grass);
        this.setTickRandomly(true);
        this.setHarvestLevel("shovel", 0);
	}
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.blockIcon = p_149651_1_.registerIcon("dayz:grass_side");
        this.top = p_149651_1_.registerIcon("dayz:grass_top");
        this.bottom = p_149651_1_.registerIcon("dayz:dirt");
    }
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        return p_149691_1_ == 1 ? this.top : (p_149691_1_ == 0 ? this.bottom : this.blockIcon);
    }
    public void onEntityCollidedWithBlock(World p_149670_1_, int p_149670_2_, int p_149670_3_, int p_149670_4_, Entity ent)
    {
    	if(ent instanceof EntityLivingBase)
    	{
    		((EntityLivingBase)ent).addPotionEffect(new PotionEffect(CustomPotion.corruption.id, 1200 , 0));
    	}
    }

    public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_)
    {
    	this.rand = new Random();
        if (!p_149674_1_.isRemote)
        {
            for (int l = 0; l < 4; ++l)
            {
                int i1 = p_149674_2_ + p_149674_5_.nextInt(3) - 1;
                int j1 = p_149674_3_ + p_149674_5_.nextInt(5) - 3;
                int k1 = p_149674_4_ + p_149674_5_.nextInt(3) - 1;
                Block block = p_149674_1_.getBlock(i1, j1 + 1, k1);
                if (p_149674_1_.getBlock(i1, j1, k1) == Blocks.dirt && p_149674_1_.getBlockMetadata(i1, j1, k1) == 0 && p_149674_1_.getBlockLightOpacity(i1, j1 + 1, k1) <= 2 && rand.nextInt(8) == 0)
                {
                    p_149674_1_.setBlock(i1, j1, k1, DayzMod.curroption);
                }
                if (p_149674_1_.getBlock(i1, j1, k1) == Blocks.grass && p_149674_1_.getBlockMetadata(i1, j1, k1) == 0  && p_149674_1_.getBlockLightOpacity(i1, j1 + 1, k1) <= 2 && rand.nextInt(8) == 0)
                {
                    p_149674_1_.setBlock(i1, j1, k1, DayzMod.curroption);
                }
            }
        }
    }
}
