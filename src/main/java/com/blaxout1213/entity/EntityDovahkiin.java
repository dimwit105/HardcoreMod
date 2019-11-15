package com.blaxout1213.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityDovahkiin extends EntityMob
{

	public EntityDovahkiin(World w) 
	{
		super(w);
        this.getNavigator().setAvoidsWater(true);
        this.getNavigator().setBreakDoors(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, false));
        this.setSize(0.6F, 1.8F);
        this.setCanPickUpLoot(true);
	}
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(64.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.33D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);
    }
    public boolean attackEntityAsMob(Entity e)
    {
    	float angle = this.rotationYaw % 360;
    	if(super.attackEntityAsMob(e) && e instanceof EntityLivingBase)
    	{
    		EntityPlayer ep = (EntityPlayer) e;
    		double x = (3 * angle) / 180;
    		double z = 3 - x;
    		if(x < 0)
    		{
    			z = -3 - x;
    		}
    		ep.addChatComponentMessage(new ChatComponentTranslation("X / Z: " + x + ", " + z, new Object[0]));
            double d1 = e.posX - this.posX;
            double d0;

            for (d0 = e.posZ - this.posZ; d1 * d1 + d0 * d0 < 1.0E-4D; d0 = (Math.random() - Math.random()) * 0.01D)
            {
                d1 = (Math.random() - Math.random()) * 0.01D;
            }
            d1 *= 5;
            d0 *= 5;
    		((EntityLivingBase) e).knockBack(this, 12F, d1, d0);
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    public void knockBack(EntityLivingBase e, float p_70653_2_, double p_70653_3_, double p_70653_5_)
    {
        if (this.rand.nextDouble() >= e.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).getAttributeValue())
        {
            e.isAirBorne = true;
            float f1 = MathHelper.sqrt_double(p_70653_3_ * p_70653_3_ + p_70653_5_ * p_70653_5_);
            float f2 = 0.000001F;
            //this.motionX /= 2.0D;
            //this.motionY /= 2.0D;
            //this.motionZ /= 2.0D;
            e.motionX -= p_70653_3_ ;/// (double)f1 ;//* (double)f2;
            e.motionY += (double)f2;
            e.motionZ -= p_70653_5_ ;/// (double)f1 ;//* (double)f2;

            if (e.motionY > 0.4000000059604645D)
            {
                e.motionY = 0.4000000059604645D;
            }
        }
    }

    //On a real grid this is X
    private double getDirectionZ(float angle)
    {
    	if(angle > 270)
    	{
    		float acute = angle - 270;
    		return Math.cos(acute) *3;
    	}
    	else if(angle > 180)
    	{
    		float acute = angle - 180;
    		return Math.cos(acute) *3;
    	}
    	else if(angle > 90)
    	{
    		float acute = angle - 90;
    		return Math.cos(acute) *3;
    	}
    	else
    	{
    		float acute = angle - 90;
    		return Math.cos(acute) *3;
    	}
    }
    //On a real grid this is Y
    private double getDirectionX(float angle)
    {
    	if(angle > 270)
    	{
    		float acute = angle - 270;
    		return Math.sin(acute) *3;
    	}
    	else if(angle > 180)
    	{
    		float acute = angle - 180;
    		return Math.sin(acute) *3;
    	}
    	else if(angle > 90)
    	{
    		float acute = angle - 90;
    		return Math.sin(acute) *3;
    	}
    	else
    	{
    		float acute = angle - 90;
    		return Math.sin(acute) *3;
    	}
    }
    protected boolean isAIEnabled()
    {
        return true;
    }
}
