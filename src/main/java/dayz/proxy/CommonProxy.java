package dayz.proxy;

import java.util.HashMap;
import java.util.Map;

import com.blaxout1213.dayz.DayzMod;

import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraft.nbt.NBTTagCompound;

public class CommonProxy 
{
	private static final Map<String, Integer> extendedEntityData = new HashMap<String, Integer>();
	public void registerLocalization() 
	{	
	}
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	//DayzMod.channel.register(new ServerPacketHandler());
    }
	public void registerRenderers() 
	{
		// Nothing here as the server doesn't render graphics or entities!
	}
	public static void storeEntityData(String name, int compound)
	{
		extendedEntityData.put(name, compound);
	}

	/**
	* Removes the compound from the map and returns the NBT tag stored for name or null if none exists
	*/
	public static Integer getEntityData(String name)
	{
		return extendedEntityData.remove(name);
	}
	public static Map getHashMap()
	{
		return extendedEntityData;
	}
}