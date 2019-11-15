package com.blaxout1213.dayz.bloodbags;

import java.util.List;
import java.util.Random;

import com.blaxout1213.dayz.DayzMod;
import com.blaxout1213.dayz.ExtendedPlayer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BloodBagRandom extends BloodBag
{
	private Random rand;
	/** Blood type int to value
	 * O-	0
	 * O+	1
	 * A-	2
	 * A+	3
	 * B-	4
	 * B+	5
	 * AB-	6
	 * AB+	7	
	 */
	@Override
	public ItemStack onEaten(ItemStack item, World world, EntityPlayer ep)
    {
		this.rand = new Random();
		
		DataWatcher dw = ep.getDataWatcher();
		ExtendedPlayer props = ExtendedPlayer.get(ep);
		int b = props.getBloodType();
		if(b == 0 && rand.nextInt(8) == 0)
		{
			ep.heal(16F);
		}
		else if(b == 1 && rand.nextInt(4) == 0)
		{
			ep.heal(16F);
		}
		else if(b == 2 && rand.nextInt(4) == 0)
		{
			ep.heal(16F);
		}
		else if(b == 3 && rand.nextInt(2) == 0)
		{
			ep.heal(16F);
		}
		else if(b == 4 && rand.nextInt(4) == 0)
		{
			ep.heal(16F);
		}
		else if(b == 5 && rand.nextInt(2) == 0)
		{
			ep.heal(16F);
		}
		else if(b == 6 && rand.nextInt(2) == 0)
		{
			ep.heal(16F);
		}
		else if(b == 7)
		{
			ep.heal(16F);
		}
		else
		{
			ep.heal(8F);
			props.badBlood("set", true);
		}
		
        if (!ep.capabilities.isCreativeMode)
        {
        	ep.inventory.setInventorySlotContents(ep.inventory.currentItem, new ItemStack(DayzMod.bloodBagEmpty));
        }
		return item;
    }
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4) 
    {
    	list.add("Unlabeled");
    }
}
