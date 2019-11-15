package com.blaxout1213.dayz;

import org.lwjgl.opengl.GL11;

import com.blaxout1213.entity.EntitySuicideSpider;

import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.entity.RenderSpider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.util.ResourceLocation;

public class RenderSuicideSpider extends RenderSpider
{
    private static final ResourceLocation Your_Texture = new ResourceLocation("textures/entity/spider/spider.png");  //refers to:assets/yourmod/textures/entity/yourtexture.png

    public RenderSuicideSpider(ModelSpider par1ModelBase, float par2)
    {
        super();
        this.shadowSize *= 0.7F;
        
    }
    protected void preRenderCallback(EntitySuicideSpider p_77041_1_, float p_77041_2_)
    {
        GL11.glScalef(0.7F, 0.7F, 0.7F);
    }
    protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_)
    {
        this.preRenderCallback((EntitySuicideSpider)p_77041_1_, p_77041_2_);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return Your_Texture;
    }
}
