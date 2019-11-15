package com.blaxout1213.dayz;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.entity.RenderSpider;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderPoisonSpider extends RenderSpider
{
    private static final ResourceLocation Your_Texture = new ResourceLocation("dayz:textures/entity/spitterspider.png");  //refers to:assets/yourmod/textures/entity/yourtexture.png

    public RenderPoisonSpider(ModelSpider par1ModelBase, float par2)
    {
        super();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return Your_Texture;
    }
}