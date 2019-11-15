package com.blaxout1213.dayz;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderGreenCreeps extends RenderBiped
{

	private static final ResourceLocation steveTextures = new ResourceLocation("textures/entity/steve.png");
	private static final ResourceLocation greenSteveTextures = new ResourceLocation("dayz:textures/entity/greensteve.png");
	private static final ResourceLocation redSteveTextures = new ResourceLocation("dayz:redsteve");
	private String texture;

	public RenderGreenCreeps(ModelBiped p_i1257_1_, float p_i1257_2_) 
	{
		super(p_i1257_1_, p_i1257_2_);
	}
    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        return greenSteveTextures;
    }
}
