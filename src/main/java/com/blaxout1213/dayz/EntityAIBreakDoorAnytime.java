package com.blaxout1213.dayz;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIDoorInteract;
import net.minecraft.init.Blocks;
import net.minecraft.world.EnumDifficulty;

public class EntityAIBreakDoorAnytime extends EntityAIDoorInteract
{
    private int breakingTime;
    private int field_75358_j = -1;
    //private static final String __OBFID = "CL_00001577";

    public EntityAIBreakDoorAnytime(EntityLiving p_i1618_1_)
    {
        super(p_i1618_1_);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        return !super.shouldExecute() ? false : (!this.theEntity.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing") ? false : !this.field_151504_e.func_150015_f(this.theEntity.worldObj, this.entityPosX, this.entityPosY, this.entityPosZ));
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        super.startExecuting();
        this.breakingTime = 0;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        double d0 = this.theEntity.getDistanceSq((double)this.entityPosX, (double)this.entityPosY, (double)this.entityPosZ);
        return this.breakingTime <= 1200 && !this.field_151504_e.func_150015_f(this.theEntity.worldObj, this.entityPosX, this.entityPosY, this.entityPosZ) && d0 < 4.0D;
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        super.resetTask();
        this.theEntity.worldObj.destroyBlockInWorldPartially(this.theEntity.getEntityId(), this.entityPosX, this.entityPosY, this.entityPosZ, -1);
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        super.updateTask();

        if (this.theEntity.getRNG().nextInt(20) == 0)
        {
            this.theEntity.worldObj.playAuxSFX(1010, this.entityPosX, this.entityPosY, this.entityPosZ, 0);
        }
        if(this.theEntity.getEquipmentInSlot(0) != null)
        {
        	this.breakingTime += 2;
        }
        else
        {
        	++this.breakingTime;
        }
        
        int i = (int)((float)this.breakingTime / 1200.0F * 10.0F);

        if (i != this.field_75358_j)
        {
            this.theEntity.worldObj.destroyBlockInWorldPartially(this.theEntity.getEntityId(), this.entityPosX, this.entityPosY, this.entityPosZ, i);
            this.field_75358_j = i;
        }

        if (this.breakingTime >= 1200)
        {
            this.theEntity.worldObj.setBlockToAir(this.entityPosX, this.entityPosY, this.entityPosZ);
            this.theEntity.worldObj.playAuxSFX(1012, this.entityPosX, this.entityPosY, this.entityPosZ, 0);
            this.theEntity.worldObj.playAuxSFX(2001, this.entityPosX, this.entityPosY, this.entityPosZ, Block.getIdFromBlock(this.field_151504_e));
        }
    }
}