
package goblin.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

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
