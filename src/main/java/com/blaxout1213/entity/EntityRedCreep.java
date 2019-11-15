package com.blaxout1213.entity;

import com.blaxout1213.dayz.UtilMisc;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityRedCreep extends EntityMob
{

	public EntityRedCreep(World p_i1738_1_) 
	{
		super(p_i1738_1_);
		this.getNavigator().setCanSwim(false);
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityGreenCreep.class, 1.0D, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityGreenCreep.class, 0, false));
	}
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        int level = rand.nextInt(5) + 1;
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.33D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((double)1.0D * (level * 0.33));
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)20.0D * (level * 0.33));
        this.setCustomNameTag("Level: " + level);
    }
    public boolean getCanSpawnHere()
    {
        return this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL && this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox) && this.worldObj.isDaytime() && this.posY > 64 && UtilMisc.getMoonPhase(this.worldObj.getWorldTime()) != 0;
    }
    protected boolean isAIEnabled()
    {
        return true;
    }
}
