
package goblin.item;

import goblin.entity.projectile.EntityBomb;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBomb extends GoblinsItem {
	public ItemBomb(String stringName)
	{
		super(stringName);
	}
	
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
	{
		if (!world.isRemote)
		{
			world.spawnEntityInWorld(new EntityBomb(world, entityPlayer));
		}
		--itemStack.stackSize;
		return itemStack;
	}
}
