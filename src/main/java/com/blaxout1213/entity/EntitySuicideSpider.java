package com.blaxout1213.entity;

import com.blaxout1213.dayz.UtilMisc;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntitySuicideSpider extends EntitySpider
{
	private int items;
	public boolean fromBedrock;
	public EntitySuicideSpider(World world) 
	{
		super(world);
		this.setCanPickUpLoot(true);
        this.setSize(0.7F, 0.5F);
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
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1.0D);
    }
    public void wipeItems()
    {
    	this.setCurrentItemOrArmor(0, null);
    	this.setCurrentItemOrArmor(1, null);
    	this.setCurrentItemOrArmor(2, null);
    	this.setCurrentItemOrArmor(3, null);
    	this.setCurrentItemOrArmor(4, null);
    }
    public void onLivingUpdate()
    {
    	if(this.getEquipmentInSlot(0) != null || this.getEquipmentInSlot(1) != null || this.getEquipmentInSlot(2) != null || this.getEquipmentInSlot(3) != null || this.getEquipmentInSlot(4) != null )
    	{
    		items++;
    	}
    	if(items > 13)
    	{
    		items--;
    	}
    	this.wipeItems();
    	super.onLivingUpdate();
    }
    protected Entity findPlayerToAttack()
    {
        float f = this.getBrightness(1.0F);

        if (f < 0.5F)
        {
            double d0 = 16.0D;
            return this.worldObj.getClosestVulnerablePlayerToEntity(this, d0);
        }
        else
        {
            return null;
        }
    }
    protected void dropFewItems(boolean p_70628_1_, int p_70628_2_)
    {
        super.dropFewItems(p_70628_1_, p_70628_2_);
        if(items > 0)
        {
        	this.dropItem(UtilMisc.randomItem(rand), 1);
        	items--;
        	dropFewItems(p_70628_1_, p_70628_2_);
        }
    }
    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        super.writeEntityToNBT(nbt);
        nbt.setInteger("Items", this.items);
        nbt.setBoolean("Bedrock", this.fromBedrock);
    }
    
    public void readEntityFromNBT(NBTTagCompound nbt)
    {
        super.readEntityFromNBT(nbt);
        items = nbt.getInteger("Items");
        fromBedrock = nbt.getBoolean("Bedrock");
    }
}
