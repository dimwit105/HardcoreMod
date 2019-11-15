package com.blaxout1213.items;

import java.util.Random;

import com.blaxout1213.dayz.ExtendedPlayer;
import com.google.common.collect.Multimap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class VenomSword extends ItemSword
{
	private Random rand;
	private int corrosion;
	public VenomSword(ToolMaterial tm)
	{
		super(tm.IRON);
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabs.tabCombat);
		this.setUnlocalizedName("venomsword");
    	this.setMaxDamage(35);
    	
	}
	@Override
    public boolean hitEntity(ItemStack item, EntityLivingBase target, EntityLivingBase attacker)
    {
		this.rand = new Random();
		if(target instanceof EntityPlayer)
		{
			ExtendedPlayer props = ExtendedPlayer.get((EntityPlayer) target);
			props.venomTime("set", 7200 + rand.nextInt(4800));
			props.venomed("set", 1);
			//System.out.println("Target envenomed.");
		}
		else if(target instanceof EntityLiving)
		{
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(Potion.wither.id, 300 * 20, 0));
		}
		item.damageItem(1, attacker);
    	return true;
    }
	/*
	@Override
	public void onUpdate(ItemStack item, World world, Entity entity, int par4, boolean par5)
	{
		if(!world.isRemote)
		{
			//if(item != null && item instanceof VenomSword)
			{
				Minecraft mc = Minecraft.getMinecraft();
				EntityPlayer player = mc.thePlayer;
				corrosion++;
				if(corrosion >= 80 && item.getItemDamage() < 975)
				{
					item.damageItem(1, player);
					corrosion = 0;
				}
			}
		}
	}
	*/
    @SideOnly(Side.CLIENT)
    @Override
    public boolean isFull3D()
    {
        return true;
    }
    
    public Multimap getItemAttributeModifiers()
    {
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", (double)6.0F, 0));
        return multimap;
    }
}
