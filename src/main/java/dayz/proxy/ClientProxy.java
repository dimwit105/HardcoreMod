package dayz.proxy;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderFallingBlock;
import net.minecraft.client.renderer.entity.RenderPlayer;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

import com.blaxout1213.dayz.DayzMod;
import com.blaxout1213.dayz.RenderCorruptZombie;
import com.blaxout1213.dayz.RenderEvilSteave;
import com.blaxout1213.dayz.RenderGreenCreeps;
import com.blaxout1213.dayz.RenderPhoenix;
import com.blaxout1213.dayz.RenderPoisonSpider;
import com.blaxout1213.dayz.RenderRedCreeps;
import com.blaxout1213.dayz.RenderSuicideSpider;
import com.blaxout1213.entity.EntityCivilianSteve;
import com.blaxout1213.entity.EntityCorruptedZombie;
import com.blaxout1213.entity.EntityEvilSteave;
import com.blaxout1213.entity.EntityFallingBoulder;
import com.blaxout1213.entity.EntityGreenCreep;
import com.blaxout1213.entity.EntityPhoenix;
import com.blaxout1213.entity.EntityPoisonSpider;
import com.blaxout1213.entity.EntityRedCreep;
import com.blaxout1213.entity.EntitySuicideSpider;
import com.blaxout1213.entity.ModelPhoenix;
import com.blaxout1213.entity.EntityDovahkiin;

public class ClientProxy extends CommonProxy
{
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	 //DayzMod.channel.register(new ClientPacketHandler());
    }
	public void registerLocalization() 
	{	
	}
	public void registerRenderers() 
	{
		 RenderingRegistry.registerEntityRenderingHandler(EntityPoisonSpider.class, new RenderPoisonSpider(new ModelSpider(), 1.0F));
		 RenderingRegistry.registerEntityRenderingHandler(EntityCorruptedZombie.class, new RenderCorruptZombie(new ModelZombie(), 1.0F));
		 RenderingRegistry.registerEntityRenderingHandler(EntitySuicideSpider.class, new RenderSuicideSpider(new ModelSpider(), 0.7F));
		 RenderingRegistry.registerEntityRenderingHandler(EntityEvilSteave.class, new RenderEvilSteave(new ModelBiped(), 1.0F));
		 RenderingRegistry.registerEntityRenderingHandler(EntityCivilianSteve.class, new RenderEvilSteave(new ModelBiped(), 1.0F));
		 RenderingRegistry.registerEntityRenderingHandler(EntityFallingBoulder.class, new RenderFallingBlock());
		 RenderingRegistry.registerEntityRenderingHandler(EntityGreenCreep.class, new RenderGreenCreeps(new ModelBiped(), 1.0F));
		 RenderingRegistry.registerEntityRenderingHandler(EntityRedCreep.class, new RenderRedCreeps(new ModelBiped(), 1.0F));
		 RenderingRegistry.registerEntityRenderingHandler(EntityDovahkiin.class, new RenderEvilSteave(new ModelBiped(), 1.0F));
		 RenderingRegistry.registerEntityRenderingHandler(EntityPhoenix.class, new RenderPhoenix(new ModelPhoenix(), 0.3F));
	}
}