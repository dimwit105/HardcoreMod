package com.blaxout1213.dayz;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderEvilSteave extends RenderBiped
{
	 private static final ResourceLocation steveTextures = new ResourceLocation("textures/entity/steve.png");

	public RenderEvilSteave(ModelBiped p_i1257_1_, float p_i1257_2_) 
	{
		super(p_i1257_1_, p_i1257_2_);
	}
    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        return steveTextures;
    }
	
}
