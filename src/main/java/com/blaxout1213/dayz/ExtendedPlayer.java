
package com.blaxout1213.dayz;

import java.util.Random;

import com.blaxout1213.dayz.packet.SyncPropertiesPacket;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedPlayer implements IExtendedEntityProperties
{
	public final static String EXT_PROP_NAME = "PlayerLevels";
	//public static final String SYNC_NEEDED = "PlayerLevelsSync";
	private final EntityPlayer player;
	private int venomed;
	private int venomTime;
	private float resistance;
	private int bloodType;
	private Random rand;
	private int bloodType2;
	private int wetness;
	private int bleed;
	private int bleedout;
	private int tempeture;
	private int TTemp;
	private int targetTemp;
	private boolean sunlight;
	private int suncooldown;
	private boolean bonfire;
	private int bonfireCD;
	private int shock;
	private boolean badblood;
	private boolean bloodKnown;
	private boolean forceNether;
	private int wellness;
	private int feetHealth;
	private int wellnessCD;
	private int brokenLeg;
	private int blood;
	private int lastHit;
	public static final int VENOM_WATCHER = 20;
	public static final int VENOMTIME_WATCHER = 21;
	public static final int RESISTANCE_WATCHER = 22;
	public ExtendedPlayer(EntityPlayer player)
	{
		rand = new Random();
		this.player = player;
		badblood = false;
		bloodKnown = false;
		forceNether = false;
		bleed = 0;
		feetHealth = 10000;
		blood = 5000;
		venomed = 0;
		venomTime = 0;
		TTemp = 0;
		targetTemp = 0;
		brokenLeg = 0;
		resistance = 0.2F;
		bloodType2 = rand.nextInt(8);
		wetness = 0;
		wellnessCD = 0;
		lastHit = 0;
		wellness = 10;
		//bleedout = 0;
		tempeture = 30;
		//player.getDataWatcher().addObject(VENOM_WATCHER, venomed);
		//player.getDataWatcher().addObject(VENOMTIME_WATCHER, venomTime);
		shock = player.getDataWatcher().getWatchableObjectInt(DayzMod.shockDataWatcher);
		venomed = 0; 
		venomTime = 0;
	}
	public static final void register(EntityPlayer player)
	{
		player.registerExtendedProperties(ExtendedPlayer.EXT_PROP_NAME, new ExtendedPlayer(player));
		//player.registerExtendedProperties(ExtendedPlayer.SYNC_NEEDED, new ExtendedPlayer(player));
	}
	
	public static final ExtendedPlayer get(EntityPlayer player)
	{
		return (ExtendedPlayer) player.getExtendedProperties(EXT_PROP_NAME);
	}
	
	public static final void sync(EntityPlayer player) 
	{
		ExtendedPlayer playerData = ExtendedPlayer.get(player);
		DayzMod.packetPipeline.sendTo(new SyncPropertiesPacket(player), (EntityPlayerMP) player);
	}

	@Override
	public void init(Entity entity, World world) 
	{
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) 
	{
		NBTTagCompound prop = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);
		//NBTTagCompound prop2 = (NBTTagCompound) compound.getTag(SYNC_NEEDED);
		//venomTime = compound.getInteger("venomTime");
		//venomed = compound.getBoolean("venomed");
		forceNether = prop.getBoolean("nether");
		bloodKnown = prop.getBoolean("bloodknown");
		badblood = prop.getBoolean("badblood");
		bloodType2 = prop.getInteger("blood");
		bleed = prop.getInteger("bleed");
		feetHealth = prop.getInteger("feethealth");
		TTemp = prop.getInteger("ttemp");
		brokenLeg = prop.getInteger("brokenleg");
		targetTemp = prop.getInteger("targettemp");
		wellnessCD = prop.getInteger("wellnessCD");
		blood = prop.getInteger("bloodlevel");
		wellness = prop.getInteger("wellness");
		//player.getDataWatcher().updateObject(24, prop.getInteger("temp"));
		player.getDataWatcher().updateObject(25, prop.getInteger("wetness"));
		player.getDataWatcher().updateObject(DayzMod.shockDataWatcher, prop.getInteger("shock"));
		venomTime = prop.getInteger("venomTime");
		venomed = prop.getInteger("venomed");
		player.getDataWatcher().updateObject(RESISTANCE_WATCHER, prop.getFloat("resistance"));
	}

	@Override
	public void saveNBTData(NBTTagCompound compound) 
	{
		NBTTagCompound properties = new NBTTagCompound();
		//NBTTagCompound properties2 = new NBTTagCompound();
		//compound.setInteger("venomTime", venomTime);
		//compound.setBoolean("venomed", venomed);
		properties.setBoolean("nether", forceNether);
		properties.setBoolean("bloodknown", bloodKnown);
		properties.setBoolean("badblood", badblood);
		properties.setInteger("bleed", bleed);
		properties.setInteger("feethealth", feetHealth);
		//properties.setInteger("wellness", wellness);
		properties.setInteger("blood", bloodType2);
		properties.setInteger("ttemp", TTemp);
		properties.setInteger("brokenleg", brokenLeg);
		properties.setInteger("wellnessCD", wellnessCD);
		properties.setInteger("targettemp", targetTemp);
		properties.setInteger("wellness", wellness);
		properties.setInteger("shock", player.getDataWatcher().getWatchableObjectInt(DayzMod.shockDataWatcher));
		//properties.setInteger("temp", player.getDataWatcher().getWatchableObjectInt(24));
		properties.setInteger("wetness", player.getDataWatcher().getWatchableObjectInt(25));
		properties.setFloat("resistance", player.getDataWatcher().getWatchableObjectFloat(RESISTANCE_WATCHER));
		properties.setInteger("venomTime", venomTime);
		properties.setInteger("venomed", venomed);
		properties.setInteger("bloodlevel", blood);
		compound.setTag(EXT_PROP_NAME, properties);
		//compound.setTag(SYNC_NEEDED, properties2);
	}
	public boolean bloodKnown()
	{
		return bloodKnown;
	}
	public int getBloodType()
	{
		return bloodType2;
	}
	public boolean nether(String action, boolean set)
	{
		if(action == "get")
		{
			return forceNether;
		}
		else if (action == "set")
		{
			forceNether = set;
		}
		return false;
	}
	public int venomed(String action, int set)
	{
		if(action == "get")
		{
			return venomed;
		}
		else if (action == "set")
		{
			venomed = set;
			return venomed;
		}
		if(action == "add")
		{
			venomed += set;
			return venomed;
		}
		if(action == "subtract")
		{
			venomed -= set;
			return venomed;
		}
		return Integer.MIN_VALUE;
	}
	public int venomTime(String action, int set)
	{
		if(action == "get")
		{
			return venomTime;
		}
		else if (action == "set")
		{
			venomTime = set;
			return venomTime;
		}
		if(action == "add")
		{
			venomTime += set;
			return venomed;
		}
		if(action == "subtract")
		{
			venomTime -= set;
			return venomed;
		}
		return Integer.MIN_VALUE;
	}
	public float resistence(String action, float set)
	{
		if(action == "get")
		{
			return resistance;
		}
		else if (action == "set")
		{
			resistance = set;
			return resistance;
		}
		return Integer.MIN_VALUE;
	}
	public boolean badBlood(String action, boolean set)
	{
		if(action == "get")
		{
			return badblood;
		}
		else if (action == "set")
		{
			badblood = set;
		}
		return false;
	}
	public int bloodLevel(String action, int set)
	{
		if(action == "set")
		{
			blood = set;
			return blood;
		}
		if(action == "get")
		{
			return blood;
		}
		if(action == "add")
		{
			blood += set;
			return blood;
		}
		if(action == "subtract")
		{
			blood -= set;
			return blood;
		}
		return blood;
	}
	public int brokenLeg(String action, int set)
	{
		if(action == "set")
		{
			brokenLeg = set;
			return brokenLeg;
		}
		if(action == "get")
		{
			return brokenLeg;
		}
		if(action == "add")
		{
			brokenLeg += set;
			return brokenLeg;
		}
		if(action == "subtract")
		{
			brokenLeg -= set;
			return brokenLeg;
		}
		return brokenLeg;
	}
	public int feetHealth(String action, int set)
	{
		if(action == "set")
		{
			feetHealth = set;
			return feetHealth;
		}
		if(action == "get")
		{
			return feetHealth;
		}
		if(action == "add")
		{
			feetHealth += set;
			return feetHealth;
		}
		if(action == "subtract")
		{
			feetHealth -= set;
			return feetHealth;
		}
		return feetHealth;
	}
	public int wellness(String action, int set)
	{
		if(action == "set")
		{
			wellness = set;
			return wellness;
		}
		if(action == "get")
		{
			return wellness;
		}
		if(action == "add")
		{
			wellness += set;
			return wellness;
		}
		if(action == "subtract")
		{
			wellness -= set;
			return wellness;
		}
		return wellness;
	}
	public int LastHit(String action, int set)
	{
		if(action == "set")
		{
			lastHit = set;
			return lastHit;
		}
		if(action == "get")
		{
			return lastHit;
		}
		if(action == "add")
		{
			lastHit += set;
			return lastHit;
		}
		if(action == "subtract")
		{
			lastHit -= set;
			return lastHit;
		}
		return wellness;
	}
	public int wellnessCD(String action, int set)
	{
		if(action == "set")
		{
			wellnessCD = set;
			return wellnessCD;
		}
		if(action == "get")
		{
			return wellnessCD;
		}
		if(action == "add")
		{
			wellnessCD += set;
			return wellnessCD;
		}
		if(action == "subtract")
		{
			wellnessCD -= set;
			return wellnessCD;
		}
		return wellness;
	}
	public int TTemp(String action, int set)
	{
		if(action == "set")
		{
			TTemp = set;
			return TTemp;
		}
		if(action == "get")
		{
			return TTemp;
		}
		if(action == "add")
		{
			TTemp += set;
			return TTemp;
		}
		if(action == "subtract")
		{
			TTemp -= set;
			return TTemp;
		}
		return TTemp;
	}
	public int targetTemp(String action, int set)
	{
		if(action == "set")
		{
			targetTemp = set;
			return TTemp;
		}
		if(action == "get")
		{
			return targetTemp;
		}
		if(action == "add")
		{
			targetTemp += set;
			return TTemp;
		}
		if(action == "subtract")
		{
			targetTemp -= set;
			return TTemp;
		}
		return targetTemp;
	}
	public int bleed(String todo, int par1) 
	{
		if(todo == "get")
		{
			return bleed;
		}
		if(todo == "set")
		{
			bleed = par1;
			return 0;
		}
		if(todo == "add")
		{
			bleed += par1;
			return 0;
		}
		if(todo == "subtract")
		{
			bleed -= par1;
			return 0;
		}
		else
		{
			return 0;
		}
		
	}
	public int bleedout(String todo, int par1) 
	{
		if(todo == "get")
		{
			return bleedout;
		}
		if(todo == "set")
		{
			bleedout = par1;
			return 0;
		}
		if(todo == "add")
		{
			bleedout += par1;
			return 0;
		}
		if(todo == "subtract")
		{
			bleedout -= par1;
			return 0;
		}
		else
		{
			return 0;
		}
		
	}
	public float getResistance()
	{
		return resistance;
	}
	public void addVenomTime(int par1)
	{
		int bloodSubtract = player.getDataWatcher().getWatchableObjectInt(VENOMTIME_WATCHER);
		bloodSubtract += par1;
		player.getDataWatcher().updateObject(VENOMTIME_WATCHER, bloodSubtract);
	}
	public void subtractVenomTime(int par1)
	{
		int bloodSubtract = player.getDataWatcher().getWatchableObjectInt(VENOMTIME_WATCHER);
		bloodSubtract -= par1;
		player.getDataWatcher().updateObject(VENOMTIME_WATCHER, bloodSubtract);
	}
	public void bloodDiscovered()
	{
		bloodKnown = true;
	}
	public int getVenomTime()
	{
		return player.getDataWatcher().getWatchableObjectInt(VENOMTIME_WATCHER);
	}
	public void setVenomed(int par1)
	{
		player.getDataWatcher().updateObject(VENOMTIME_WATCHER, par1);
	}
	public int getVenomed()
	{
		return player.getDataWatcher().getWatchableObjectInt(VENOM_WATCHER);
	}
}