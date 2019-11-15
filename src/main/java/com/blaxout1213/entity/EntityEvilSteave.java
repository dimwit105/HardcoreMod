package com.blaxout1213.entity;

import java.util.Calendar;

import com.blaxout1213.dayz.DayzMod;
import com.blaxout1213.dayz.EntityAIBreakDoorAnytime;
import com.blaxout1213.dayz.EntityAICarryPrisoner;
import com.blaxout1213.dayz.EntityAIFleeDark;
import com.blaxout1213.dayz.ExtendedPlayer;
import com.blaxout1213.dayz.UtilMisc;
import com.blaxout1213.dayz.WorldData;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderHell;
import net.minecraftforge.common.ForgeModContainer;

public class EntityEvilSteave extends EntityMob implements IRangedAttackMob
{
    private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 1.0D, 20, 60, 15.0F);
    private EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, true);
    private EntityAIAttackOnCollide aiAttackOnCollide2 = new EntityAIAttackOnCollide(this, EntityZombie.class, 1.0D, true);
    private EntityAIAttackOnCollide aiAttackOnCollide3 = new EntityAIAttackOnCollide(this, EntitySkeleton.class, 1.0D, true);
    private EntityAINearestAttackableTarget aiattack = new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, false);
    private EntityAIAvoidEntity runAway = new EntityAIAvoidEntity(this, EntityPlayer.class, 128F, 0.8D, 1.33D);
	private int ammo = 16;
	private int KO;

	public EntityEvilSteave(World p_i1745_1_) 
	{
		super(p_i1745_1_);
        this.getNavigator().setAvoidsWater(true);
        this.getNavigator().setBreakDoors(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(11, new EntityAICarryPrisoner(this, 1.0D));
        //this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, true));
        //this.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityVillager.class, 1.0D, true));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(12, new EntityAIFleeDark(this, 1.0D));
        //this.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.tasks.addTask(1, new EntityAIBreakDoorAnytime(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityZombie.class, 0, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntitySkeleton.class, 0, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityCreature.class, 0, false));
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
    protected void addRandomArmor()
    {
        super.addRandomArmor();
        this.setCurrentItemOrArmor(0, new ItemStack(Items.bow));
    }
    protected boolean isAIEnabled()
    {
        return true;
    }
    public boolean getCanSpawnHere()
    {
        return this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL && this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox) && this.worldObj.isDaytime() && this.posY > 64 && UtilMisc.getMoonPhase(this.worldObj.getWorldTime()) != 0 && WorldData.get(this.worldObj).getSteves() > 0 && worldObj.getWorldTime() > 24000*3;
    }
    private void explode()
    {
        if (!this.worldObj.isRemote)
        {
            boolean flag = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
            {
                this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 3F, flag);
            }

            this.setDead();
        }
    }
    private void dropBow()
    {
    	if(ammo <= 0 && 
    			this.getEquipmentInSlot(0) != null 
    			&& this.getEquipmentInSlot(0).getItem() == Items.bow)
    	{
    		this.setCurrentItemOrArmor(0, new ItemStack(DayzMod.woodnightStick));
    		this.dropItem(Items.bow, 1);
    		this.ammo = -1;
    	}
    }
    public void onLivingUpdate()
    {
    	super.onLivingUpdate();
    	if(KO > 0)
    	{
    		this.KO--;
    	}	
        if(ammo == 0)
        {
        	this.dropBow();
        }
        if(rand.nextInt(300) == 0)
        {
        	this.setCombatTask();
        }
    }
    public boolean attackEntityFrom(DamageSource dm, float f)
    {
    	if(super.attackEntityFrom(dm, f))
    	{
    		if(dm.getEntity() instanceof EntityZombie)
			{
    			EntityZombie em = (EntityZombie) dm.getEntity();
    			ItemStack item = em.getEquipmentInSlot(0);
    			if(item !=null)
    			{
    				if((item.getItem() == DayzMod.nightStick || item.getItem() == DayzMod.woodnightStick))
    				{
    					this.setCanPickUpLoot(false);
    					this.KO = 20*120;
    					this.setCurrentItemOrArmor(0, null);
    					this.setCurrentItemOrArmor(1, null);
    					this.setCurrentItemOrArmor(2, null);
    					this.setCurrentItemOrArmor(3, null);
    					this.setCurrentItemOrArmor(4, null);
    				}
    			}
			}
    		if(dm.getEntity() instanceof EntityEvilSteave)
			{
    			EntityEvilSteave em = (EntityEvilSteave) dm.getEntity();
    			ItemStack item = em.getEquipmentInSlot(0);
    			if(item !=null)
    			{
    				if((item.getItem() == DayzMod.nightStick || item.getItem() == DayzMod.woodnightStick))
    				{
    					this.setCanPickUpLoot(false);
    					this.KO = 20*120;
    					this.setCurrentItemOrArmor(0, null);
    					this.setCurrentItemOrArmor(1, null);
    					this.setCurrentItemOrArmor(2, null);
    					this.setCurrentItemOrArmor(3, null);
    					this.setCurrentItemOrArmor(4, null);
    				}
    			}
			}
			
    		if(dm.getEntity() instanceof EntityPlayer)
			{
    			EntityPlayer em = (EntityPlayer) dm.getEntity();
    			ItemStack item = em.getEquipmentInSlot(0);
    			if(item !=null)
    			{
    				if((item.getItem() == DayzMod.nightStick || item.getItem() == DayzMod.woodnightStick))
    				{
    					this.setCanPickUpLoot(false);
    					this.KO = 20*120;
    					this.setCurrentItemOrArmor(0, null);
    					this.setCurrentItemOrArmor(1, null);
    					this.setCurrentItemOrArmor(2, null);
    					this.setCurrentItemOrArmor(3, null);
    					this.setCurrentItemOrArmor(4, null);
    				}
    			}
			}
    	}
		return true; 	
    }
    public boolean attackEntityAsMob(Entity e)
    {
    	if(this.KO > 0)
    	{
    		return false;
    	}
    	if(super.attackEntityAsMob(e))
    	{
    		if(e instanceof EntityPlayer)
    		{
    			EntityPlayer ep = (EntityPlayer) e;
    			DataWatcher dw = ep.getDataWatcher();
    			ExtendedPlayer props = ExtendedPlayer.get(ep);
    			if(this.getEquipmentInSlot(0) != null)
    			{
    				Item item = this.getEquipmentInSlot(0).getItem();
    				if(props.venomed("get", 0) == 0 && item == DayzMod.envenomedSword)
    				{
    					props.venomTime("set", 7200 + rand.nextInt(4800));
    					props.venomed("set", 1);
    				}
    				if(item != null && (item == DayzMod.nightStick || item == DayzMod.woodnightStick) && dw.getWatchableObjectInt(DayzMod.shockDataWatcher) *20 < ep.getHealth())
    				{
    					Object update = dw.getWatchableObjectInt(DayzMod.shockDataWatcher) + (((int)ep.getHealth() + 2)*20);
    					dw.updateObject(DayzMod.shockDataWatcher, (int)ep.getHealth()*20 + 3*20);
    				}
    			}
    			if(this.getEquipmentInSlot(0) == null && this.rand.nextInt(33) == 0)
    			{
    				ep.mountEntity(this);
    				dw.updateObject(DayzMod.shockDataWatcher, (int)ep.getHealth()*20 + 3*20);
    				//this.setCombatTask();
    			}
    			if(this.riddenByEntity != null)
    			{
    				return false;
    			}
    		}
    	}
		return true;
    }
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData p_110161_1_)
    {
        p_110161_1_ = super.onSpawnWithEgg(p_110161_1_);
        int shootOrStab = getRNG().nextInt(10);
        if (shootOrStab != 0)
        {
        	//0-99
        	int armorColor = getRNG().nextInt(100) + (WorldData.get(worldObj).getRank()/10);
        	int fullArmor = (WorldData.get(worldObj).getRank()/20);
            this.tasks.addTask(4, this.aiAttackOnCollide);
            if(armorColor >= 99)
            {
            	this.setCurrentItemOrArmor(0, new ItemStack(Items.diamond_sword));
            	if(getRNG().nextInt(25) <= fullArmor)
            	{
            		this.setCurrentItemOrArmor(1, new ItemStack(Items.diamond_helmet));
            		this.setCurrentItemOrArmor(2, new ItemStack(Items.diamond_chestplate));
            		this.setCurrentItemOrArmor(3, new ItemStack(Items.diamond_leggings));
            		this.setCurrentItemOrArmor(4, new ItemStack(Items.diamond_boots));
            	}
            }
            else if(armorColor > 85)
            {
            	this.setCurrentItemOrArmor(0, new ItemStack(Items.iron_sword));
            	if(getRNG().nextInt(25) <= fullArmor)
            	{
            		this.setCurrentItemOrArmor(1, new ItemStack(Items.iron_helmet));
            		this.setCurrentItemOrArmor(2, new ItemStack(Items.iron_chestplate));
            		this.setCurrentItemOrArmor(3, new ItemStack(Items.iron_leggings));
            		this.setCurrentItemOrArmor(4, new ItemStack(Items.iron_boots));
            	}
            }
            else if(armorColor > 66)
            {
            	this.setCurrentItemOrArmor(0, new ItemStack(Items.stone_sword));
            	if(getRNG().nextInt(25) <= fullArmor)
            	{
            		this.setCurrentItemOrArmor(1, new ItemStack(Items.chainmail_helmet));
            		this.setCurrentItemOrArmor(2, new ItemStack(Items.chainmail_chestplate));
            		this.setCurrentItemOrArmor(3, new ItemStack(Items.chainmail_leggings));
            		this.setCurrentItemOrArmor(4, new ItemStack(Items.chainmail_boots));
            	}
            }
            else if(armorColor > 25)
            {
            	this.setCurrentItemOrArmor(0, new ItemStack(Items.wooden_sword));
            	if(getRNG().nextInt(25) <= fullArmor)
            	{
            		this.setCurrentItemOrArmor(1, new ItemStack(Items.leather_helmet));
            		this.setCurrentItemOrArmor(2, new ItemStack(Items.leather_chestplate));
            		this.setCurrentItemOrArmor(3, new ItemStack(Items.leather_leggings));
            		this.setCurrentItemOrArmor(4, new ItemStack(Items.leather_boots));
            	}
            }
            
        }
        else
        {
            this.tasks.addTask(4, this.aiArrowAttack);
            this.addRandomArmor();
            this.enchantEquipment();
        }

        this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * this.worldObj.func_147462_b(this.posX, this.posY, this.posZ));

        if (this.getEquipmentInSlot(4) == null)
        {
            Calendar calendar = this.worldObj.getCurrentDate();

            if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.rand.nextFloat() < 0.25F)
            {
                this.setCurrentItemOrArmor(4, new ItemStack(this.rand.nextFloat() < 0.1F ? Blocks.lit_pumpkin : Blocks.pumpkin));
                this.equipmentDropChances[4] = 0.0F;
            }
        }
        this.setCombatTask();
        return p_110161_1_;
    }
    public void setCombatTask()
    {
        this.tasks.removeTask(this.aiAttackOnCollide);
        this.tasks.removeTask(this.aiAttackOnCollide2);
        this.tasks.removeTask(this.aiAttackOnCollide3);
        this.tasks.removeTask(this.aiArrowAttack);
        this.tasks.removeTask(this.runAway);
        ItemStack itemstack = this.getHeldItem();

        if(WorldData.get(this.worldObj).getSteves() <= 0)
        {
        	this.tasks.addTask(0, this.runAway);
        }
        else if (itemstack != null && itemstack.getItem() == Items.bow)
        {
            this.tasks.addTask(4, this.aiArrowAttack);
        }
        else
        {
            this.tasks.addTask(5, this.aiAttackOnCollide);
            this.tasks.addTask(4, this.aiAttackOnCollide2);
            this.tasks.addTask(4, this.aiAttackOnCollide3);
        }
    }
    
    public void setCurrentItemOrArmor(int p_70062_1_, ItemStack p_70062_2_)
    {
        super.setCurrentItemOrArmor(p_70062_1_, p_70062_2_);

        if (!this.worldObj.isRemote && p_70062_1_ == 0)
        {
            this.setCombatTask();
        }
    }
    
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase p_82196_1_, float p_82196_2_) 
	{
		if(ammo > 0)
		{
			EntityArrow entityarrow = null;
			if(WorldData.get(this.worldObj).getRank() >= 401)
			{
				entityarrow = new EntityTranqArrow(this.worldObj, this, p_82196_1_, 1.6F, (float)(14 - this.worldObj.difficultySetting.getDifficultyId() * 4));
			}
			else
			{
				entityarrow = new EntityArrow(this.worldObj, this, p_82196_1_, 1.6F, (float)(14 - this.worldObj.difficultySetting.getDifficultyId() * 4));
			}
	        int i = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, this.getHeldItem());
	        int j = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, this.getHeldItem());
	        entityarrow.setDamage((double)(p_82196_2_ * 2.0F) + this.rand.nextGaussian() * 0.25D + (double)((float)this.worldObj.difficultySetting.getDifficultyId() * 0.11F));
	
	        if (i > 0)
	        {
	            entityarrow.setDamage(entityarrow.getDamage() + (double)i * 0.5D + 0.5D);
	        }
	
	        if (j > 0)
	        {
	            entityarrow.setKnockbackStrength(j);
	        }
	
	        if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, this.getHeldItem()) > 0)
	        {
	            entityarrow.setFire(100);
	        }
	
	        this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
	        this.worldObj.spawnEntityInWorld(entityarrow);
	        ammo--;
		}
		
	}
    public void readEntityFromNBT(NBTTagCompound nbt)
    {
        super.readEntityFromNBT(nbt);
        this.ammo = nbt.getInteger("Ammo");
        this.KO = nbt.getInteger("KO");
        this.setCombatTask();
    }
    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        super.writeEntityToNBT(nbt);
        nbt.setInteger("Ammo", this.ammo);
        nbt.setInteger("KO", this.KO);
    }


}
