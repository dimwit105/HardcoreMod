package com.blaxout1213.entity;

import java.util.Random;

import com.blaxout1213.dayz.ExtendedPlayer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityPoisonSpider extends EntitySpider
{
	private EntityAIAvoidEntity runaway = new EntityAIAvoidEntity(this, EntityPlayer.class, 16.0F, 1.0D, 1.2D);
	private EntityAINearestAttackableTarget fight = new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, false);
	private EntityAIAttackOnCollide fight2 =  new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false);
	
	public EntityPoisonSpider(World par1World) 
	{
		super(par1World);
		this.setSize(1.75F, 1.12F);
		/*
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.46D, false));
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityVillager.class, 1.0D, true));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.46D));
        this.tasks.addTask(7, new EntityAIWander(this, 0.46D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, false));
        */
        this.setCanPickUpLoot(true);
	}
    protected boolean isAIEnabled()
    {
        return false;
    }
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.3D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0D);
    }
    
    public void onLivingUpdate()
    {
    	super.onLivingUpdate();
    }
    public boolean attackEntityAsMob(Entity entity)
    {
        if (super.attackEntityAsMob(entity))
        {
        	if(entity instanceof EntityPlayer)
        	{
        		EntityPlayer ep = (EntityPlayer) entity;
        		ExtendedPlayer props = ExtendedPlayer.get((EntityPlayer) entity);
        		if(ep.inventory.hasItem(Items.apple))
        		{
        			ep.dropItem(Items.apple, 1);
        			ep.inventory.consumeInventoryItem(Items.apple);
        		}
                if(props.venomed("get", 0) == 0)
                {
                	this.rand = new Random();
        			props.venomTime("set", 7200 + rand.nextInt(4800));
        			props.venomed("set", 2);
        			
                }
        	}
            return true;
        }
        else
        {
            return false;
        }
    }
    protected void attackEntity(Entity par1Entity, float par2)
    {
        float f1 = this.getBrightness(1.0F);

        if (f1 > 0.5F && this.rand.nextInt(100) == 0)
        {
            this.entityToAttack = null;
        }
        else
        {
            if (par2 > 2.0F && par2 < 6.0F && this.rand.nextInt(10) == 0)
            {
                if (this.onGround)
                {
                    double d0 = par1Entity.posX - this.posX;
                    double d1 = par1Entity.posZ - this.posZ;
                    float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
                    this.motionX = d0 / (double)f2 * 0.5D * 0.800000011920929D + this.motionX * 0.20000000298023224D;
                    this.motionZ = d1 / (double)f2 * 0.5D * 0.800000011920929D + this.motionZ * 0.20000000298023224D;
                    this.motionY = 0.4000000059604645D;
                }
            }
            else
            {
                super.attackEntity(par1Entity, par2);
            }
        }
    }
    protected Entity findPlayerToAttack()
    {
        float f = this.getBrightness(1.0F);

        if (f < 0.5F)
        {
            double d0 = 32.0D;
            return this.worldObj.getClosestVulnerablePlayerToEntity(this, d0);
        }
        else
        {
            return null;
        }
    }
    //@SideOnly(Side.CLIENT)
    private void generateRandomParticles(String par1Str)
    {
        for (int i = 0; i < 5; ++i)
        {
            double d0 = this.rand.nextGaussian() * 0.02D;
            double d1 = this.rand.nextGaussian() * 0.02D;
            double d2 = this.rand.nextGaussian() * 0.02D;
            this.worldObj.spawnParticle(par1Str, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 1.0D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
        }
    }
    //@SideOnly(Side.CLIENT)
    public void spewHearts()
    {
    	this.generateRandomParticles("heart");
    }
    public void wipeItems()
    {
    	this.setCurrentItemOrArmor(0, null);
    	this.setCurrentItemOrArmor(1, null);
    	this.setCurrentItemOrArmor(2, null);
    	this.setCurrentItemOrArmor(3, null);
    	this.setCurrentItemOrArmor(4, null);
    }
    public void onUpdate()
    {
    	super.onUpdate();
    	ItemStack item = this.getHeldItem();
    	if(item != null)
    	{
    		if(item.getItem() == Items.apple)
    		{
    			this.heal(4F);
    			this.spewHearts();
    			this.addPotionEffect(new PotionEffect(Potion.regeneration.id, 60*20));
    		}
    		wipeItems();
    	}
    }
    public void fightOrFlight()
    {
        this.tasks.removeTask(this.runaway);
        this.tasks.removeTask(this.fight);
        this.tasks.removeTask(this.fight2);
        if (this.getHealth() > 6.0F)
        {
            this.tasks.addTask(4, this.fight);
            this.tasks.addTask(4, this.fight2);
        }
        else
        {
            this.tasks.addTask(4, this.runaway);
        }
    }
    /*
    public boolean getCanSpawnHere()
    {
        if(super.getCanSpawnHere() && worldObj.getMoonPhase() == 3)
        {
        	return true;
        }
        else
        {
        	return false;
        }
    }
    */
}
