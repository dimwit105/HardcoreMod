/*package com.blaxout1213.dayz;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedMob implements IExtendedEntityProperties
{
	public final static String EXT_PROP_NAME = "MobLevels";
	private int wetness;
	private Object entity;

	public ExtendedMob(EntityLiving el)
	{
		this.entity = el;
		this.wetness = 0;
	}
	public static final void register(EntityLiving el)
	{
		el.registerExtendedProperties(ExtendedPlayer.EXT_PROP_NAME, new ExtendedMob(el));
	}
	@Override
	public void saveNBTData(NBTTagCompound nbt) 
	{
		NBTTagCompound prop = new NBTTagCompound();
		prop.setInteger("wet", this.wetness);
		nbt.setTag(EXT_PROP_NAME, prop);
	}

	@Override
	public void loadNBTData(NBTTagCompound nbt)
	{
		NBTTagCompound prop = (NBTTagCompound) nbt.getTag(EXT_PROP_NAME);
		this.wetness = prop.getInteger("wet");
	}

	@Override
	public void init(Entity entity, World world)
	{
		this.wetness = 0;
	}
	public static final ExtendedMob get(EntityLiving el)
	{
		return (ExtendedMob) el.getExtendedProperties(EXT_PROP_NAME);
	}
	public int wetness(String action, int change)
	{
		if(action == "get")
		{
			return this.wetness;
		}
		else if(action == "set")
		{
			this.wetness = change;
			return this.wetness;
		}
		else if(action == "add")
		{
			this.wetness += change;
			return this.wetness;
		}
		else if(action == "subtract")
		{
			this.wetness -= change;
			return this.wetness;
		}
		else
		{
			return 0;
		}
	}

}
*/