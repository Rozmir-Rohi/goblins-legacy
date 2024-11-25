
package goblin.item;

import net.minecraft.item.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.renderer.texture.*;

public class ItemGoblinFlesh extends Item {
	private int healAmount;

	public ItemGoblinFlesh(int var2, boolean var3)
	{
		healAmount = var2;
		maxStackSize = 1;
	}

	public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3)
	{
		--var1.stackSize;
		var3.heal((float) healAmount);
		return var1;
	}

	public int getHealAmount()
	{
		return healAmount;
	}

	public void registerIcons(IIconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon("goblin:goblinMeat");
	}
}
