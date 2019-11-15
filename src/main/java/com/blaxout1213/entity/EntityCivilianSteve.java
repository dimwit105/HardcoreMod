package com.blaxout1213.entity;

import java.util.Calendar;

import com.blaxout1213.dayz.UtilMisc;

import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityCivilianSteve extends EntityMob
{
	public EntityCivilianSteve(World p_i1745_1_) 
	{
		super(p_i1745_1_);
        this.getNavigator().setBreakDoors(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(4, new EntityAIAvoidEntity(this, EntityPlayer.class, 16.0F, 0.8D, 1.0D));
        //this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, true));
        //this.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityVillager.class, 1.0D, true));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        //this.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        //this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityCreature.class, 0, false));
        this.setSize(0.6F, 1.8F);
        this.setCanPickUpLoot(true);
	}
    protected boolean isAIEnabled()
    {
        return true;
    }
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.33D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);
    }
    public boolean getCanSpawnHere()
    {
        return this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox) && this.worldObj.isDaytime() && this.posY > 64 && UtilMisc.getMoonPhase(worldObj.getWorldTime()) == 0;
    }
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData p_110161_1_)
    {
    	
        p_110161_1_ = super.onSpawnWithEgg(p_110161_1_);
        this.setEquipmentDropChance(0, 0.85F);
        int job = getRNG().nextInt(3);
        if (job == 0)
        {
        	//Miner
        	int pickColor = rand.nextInt(4);
        	switch(pickColor)
        	{
        		case 0:
        			this.setCurrentItemOrArmor(0, new ItemStack(Items.wooden_pickaxe));
        			break;
        		case 1:
        			this.setCurrentItemOrArmor(0, new ItemStack(Items.stone_pickaxe));
        			break;
        		case 2:
        			this.setCurrentItemOrArmor(0, new ItemStack(Items.iron_pickaxe));
        			break;
        		case 3:
        			this.setCurrentItemOrArmor(0, new ItemStack(Items.golden_pickaxe));
        			break;
        		default:
        			this.setCurrentItemOrArmor(0, new ItemStack(Items.diamond_pickaxe));
        	}
        	
        }
        else if(job == 1)
        {
        	//Crafter
        	int pickColor = rand.nextInt(3);
        	switch(pickColor)
        	{
        		case 0:
        			this.setCurrentItemOrArmor(0, new ItemStack(Blocks.crafting_table));
        			break;
        		case 1:
        			this.setCurrentItemOrArmor(0, new ItemStack(Blocks.furnace));
        			break;
        		case 2:
        			this.setCurrentItemOrArmor(0, new ItemStack(Blocks.anvil));
        			break;
        		default:
        			this.setCurrentItemOrArmor(0, new ItemStack(Blocks.dispenser));
        	}
        }
        else if(job == 2)
        {
        	//Farmer
        	int pickColor = rand.nextInt(3);
        	switch(pickColor)
        	{
        		case 0:
        			this.setCurrentItemOrArmor(0, new ItemStack(Items.wooden_hoe));
        			break;
        		case 1:
        			this.setCurrentItemOrArmor(0, new ItemStack(Items.stone_hoe));
        			break;
        		case 2:
        			this.setCurrentItemOrArmor(0, new ItemStack(Items.iron_hoe));
        			break;
        		default:
        			this.setCurrentItemOrArmor(0, new ItemStack(Items.golden_hoe));
        	}
        }
        
        this.setCanPickUpLoot(true);

        if (this.getEquipmentInSlot(4) == null)
        {
            Calendar calendar = this.worldObj.getCurrentDate();

            if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.rand.nextFloat() < 0.25F)
            {
                this.setCurrentItemOrArmor(4, new ItemStack(this.rand.nextFloat() < 0.1F ? Blocks.lit_pumpkin : Blocks.pumpkin));
                this.equipmentDropChances[4] = 0.0F;
            }
        }

        return p_110161_1_;
    }
}
