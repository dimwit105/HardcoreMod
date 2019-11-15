package com.blaxout1213.items;

import com.google.common.collect.Multimap;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;

public class ItemEndSword extends ItemSword
{

	public ItemEndSword(ToolMaterial tm) 
	{
		super(tm.EMERALD);
		this.setMaxDamage(300);
		this.setUnlocalizedName("endsword");
	}
	@Override
    public Multimap getItemAttributeModifiers()
    {
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.removeAll(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName());
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", (double)10D, 0));
        
        return multimap;
    }
}
