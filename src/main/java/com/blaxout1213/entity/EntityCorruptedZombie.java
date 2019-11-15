package com.blaxout1213.entity;

import com.blaxout1213.dayz.CustomPotion;
import com.blaxout1213.dayz.DayzMod;
import com.blaxout1213.dayz.ExtendedPlayer;

import net.minecraft.block.material.Material;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityCorruptedZombie extends EntityZombie {

	public EntityCorruptedZombie(World world) 
	{
		super(world);
		 this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityIronGolem.class, 1.0D, false));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntitySpider.class, 1.0D, false));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntitySkeleton.class, 1.0D, false));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityCreeper.class, 1.0D, false));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntitySlime.class, 1.0D, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, 0, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntitySpider.class, 0, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntitySkeleton.class, 0, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityCreeper.class, 0, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntitySlime.class, 0, true));
        
	}
	
    public void onLivingUpdate()
    {
    	super.onLivingUpdate();
    	int c;
        for (c = 0; c < 2; ++c)
        {
            this.worldObj.spawnParticle("portal", this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height - 0.25D, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D);
        }
        for (int l = 0; l < 4; ++l)
        {
            int i = MathHelper.floor_double(this.posX + (double)((float)(l % 2 * 2 - 1) * 0.25F));
            int j = MathHelper.floor_double(this.posY) - 1;
            int k = MathHelper.floor_double(this.posZ + (double)((float)(l / 2 % 2 * 2 - 1) * 0.25F));

            if (this.worldObj.getBlock(i, j, k).getMaterial() == Material.grass || this.worldObj.getBlock(i, j, k).getMaterial() == Material.ground)
            {
                this.worldObj.setBlock(i, j, k, DayzMod.curroption);
            }
        }
    }
    public boolean getCanSpawnHere()
    {
    	int x = MathHelper.floor_double(this.posX);
    	int y = MathHelper.floor_double(this.posY);
    	int z = MathHelper.floor_double(this.posZ);
		if(super.getCanSpawnHere() && this.worldObj.getBlock(x, y - 1, z) == DayzMod.curroption)
		{
			return true;
		}
		else
		{
			return false;
		}
    }
    public boolean attackEntityAsMob(Entity ent)
    {
        boolean flag = super.attackEntityAsMob(ent);

        if (flag)
        {
        	if(ent instanceof EntityPlayer)
        	{
        		ExtendedPlayer props = ExtendedPlayer.get((EntityPlayer) ent);
				props.venomTime("set", 1200 + rand.nextInt(1200));
				props.venomed("set", 4);
        	}
        	((EntityLivingBase)ent).addPotionEffect(new PotionEffect(CustomPotion.illness.id, 3600,0));
        }

        return flag;
    }
    public boolean attackEntityFrom(DamageSource ds, float par1)
    {
        if (!super.attackEntityFrom(ds, par1))
        {
            return false;
        }
        else
        {
        	if(!(ds.getEntity() instanceof EntityPlayer) && ds.getEntity() != null)
        	{
        		par1 = par1 *0.25F;
        	}
        	return true;
        }
    }
}
