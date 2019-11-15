package com.blaxout1213.dayz;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderCorruptZombie extends RenderZombie 
{
    private static final ResourceLocation Your_Texture = new ResourceLocation("dayz:textures/entity/corruptzombie.png");  //refers to:assets/yourmod/textures/entity/yourtexture.png

    public RenderCorruptZombie(ModelZombie par1ModelBase, float par2)
    {
        super();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return Your_Texture;
    }
}
