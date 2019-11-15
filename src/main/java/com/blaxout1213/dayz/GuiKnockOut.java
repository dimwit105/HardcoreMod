package com.blaxout1213.dayz;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.Iterator;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

import org.lwjgl.opengl.GL11;

public class GuiKnockOut extends Gui
{
	private ResourceLocation hd = new ResourceLocation("dayz:textures/gui/kohd.png");
    private ResourceLocation normal = new ResourceLocation("dayz:textures/gui/knockout.png");
    private ResourceLocation test = new ResourceLocation("textures/misc/pumpkinblur.png");
	private Minecraft mc;
    GuiKnockOut(Minecraft mc)
    {
    	this.mc = mc;
    }
    @SideOnly(Side.CLIENT)
	@SubscribeEvent
    public void onRenderOverLay(RenderGameOverlayEvent event)
    {
	    if(event.isCancelable() || event.type != ElementType.EXPERIENCE)
	    {      
	      return;
	    }
        //ScaledResolution scaledresolution = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
        int k = event.resolution.getScaledWidth();
        int l = event.resolution.getScaledHeight();
        FontRenderer fontrenderer = this.mc.fontRenderer;
        this.mc.entityRenderer.setupOverlayRendering();
        GL11.glEnable(GL11.GL_BLEND);
        if(mc.thePlayer.getDataWatcher().getWatchableObjectInt(DayzMod.shockDataWatcher)/20 > mc.thePlayer.getHealth() && !mc.thePlayer.capabilities.isCreativeMode && !mc.thePlayer.isDead)
        {	
        	String stringToDisplay = "Shock: " + this.mc.thePlayer.getDataWatcher().getWatchableObjectInt(DayzMod.shockDataWatcher)/20;
        	String stringToDisplay2 = "Health: " + this.mc.thePlayer.getHealth();
        	int k1 = (k - fontrenderer.getStringWidth(stringToDisplay)) / 2;
        	int k2 = (k - fontrenderer.getStringWidth(stringToDisplay2)) / 2;
        	knockout(k,l);
        	fontrenderer.drawStringWithShadow(stringToDisplay, k1, l/2, 16777215);
        	fontrenderer.drawStringWithShadow(stringToDisplay2, k2, l/2 - 30, 16777215);
        	
        }
    }
    @SideOnly(Side.CLIENT)
    protected void knockout(int par1, int par2)
    {
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        this.mc.getTextureManager().bindTexture(hd);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(0.0D, (double)par2, -90.0D, 0.0D, 1.0D);
        tessellator.addVertexWithUV((double)par1, (double)par2, -90.0D, 1.0D, 1.0D);
        tessellator.addVertexWithUV((double)par1, 0.0D, -90.0D, 1.0D, 0.0D);
        tessellator.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
        tessellator.draw();
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }
}