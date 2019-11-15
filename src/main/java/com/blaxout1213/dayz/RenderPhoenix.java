package com.blaxout1213.dayz;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderPhoenix extends RenderLiving
{

	private static final ResourceLocation textures = new ResourceLocation("dayz:textures/entity/Phoenix.png");
	public RenderPhoenix(ModelBase mb, float f) 
	{
		super(mb, f);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		// TODO Auto-generated method stub
		return textures;
	}

}
