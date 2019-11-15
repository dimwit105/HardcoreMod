package com.blaxout1213.dayz;

import java.util.Random;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityAIFleeDark extends EntityAIBase
{
    private EntityCreature theCreature;
    private double shelterX;
    private double shelterY;
    private double shelterZ;
    private double movementSpeed;
    private World theWorld;
    private static final String __OBFID = "CL_00001583";

    public EntityAIFleeDark(EntityCreature p_i1623_1_, double p_i1623_2_)
    {
        this.theCreature = p_i1623_1_;
        this.movementSpeed = p_i1623_2_;
        this.theWorld = p_i1623_1_.worldObj;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if(this.theWorld.getBlockLightValue(MathHelper.floor_double(this.theCreature.posX), (int)this.theCreature.boundingBox.minY, MathHelper.floor_double(this.theCreature.posZ)) <= 8)
        {
        	//System.out.println("So scared");
            Vec3 vec3 = this.findPossibleShelter();
            
            if (vec3 == null)
            {
                return false;
            }
            else
            {
                this.shelterX = vec3.xCoord;
                this.shelterY = vec3.yCoord;
                this.shelterZ = vec3.zCoord;
                return true;
                
            }
        }
        else
        {
        	return false;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return !this.theCreature.getNavigator().noPath();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
    	//System.out.println("Going home");
        this.theCreature.getNavigator().tryMoveToXYZ(this.shelterX, this.shelterY, this.shelterZ, this.movementSpeed);
    }

    private Vec3 findPossibleShelter()
    {
        Random random = this.theCreature.getRNG();

        for (int i = 0; i < 32; ++i)
        {
        	
            int j = MathHelper.floor_double(this.theCreature.posX + (double)random.nextInt(26) - 26.0D);
            int k = MathHelper.floor_double(this.theCreature.boundingBox.minY + (double)random.nextInt(6) - 3.0D);
            int l = MathHelper.floor_double(this.theCreature.posZ + (double)random.nextInt(26) - 26.0D);
            if (this.theWorld.getBlockLightValue(j, k, l) >= 8 && this.theCreature.getBlockPathWeight(j, k, l) < 0.0F)
            {
            	//System.out.println("Found shelter");
                return Vec3.createVectorHelper((double)j, (double)k, (double)l);
            }
        }

        return null;
    }
}