package com.blaxout1213.entity;

import com.blaxout1213.dayz.DayzMod;
import com.blaxout1213.dayz.WorldData;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityBoulderSpider extends EntitySuicideSpider
{

	public EntityBoulderSpider(World world) 
	{
		super(world);
		
	}
    protected void applyEntityAttributes()
    {
    	super.applyEntityAttributes();
    	WorldData wProps = WorldData.get(worldObj);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.3D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1D + ((double)wProps.getPower() / 2));
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(80D + ((double)wProps.getPower() * 2));
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue((double)wProps.getPower() * 0.01);
    }
    public boolean attackEntityFrom(DamageSource dm, float f)
    {
    	if(dm.getEntity() == null)
    	{
    		return false;
    	}
    	return super.attackEntityFrom(dm, f);
    }
    public int getTotalArmorValue()
    {
    	WorldData wProps = WorldData.get(worldObj);
        int i = super.getTotalArmorValue() + wProps.getPower() / 12;

        if (i > 20)
        {
            i = 20;
        }

        return i;
    }
    public void setDead()
    {
    	super.setDead();
    	WorldData wProps = WorldData.get(worldObj);
    	wProps.setPower(wProps.getPower() + 1);
    }
    protected void dropFewItems(boolean p_70628_1_, int p_70628_2_)
    {
        super.dropFewItems(p_70628_1_, p_70628_2_);
        this.dropItem(DayzMod.endChunk, 1);
    }
}
