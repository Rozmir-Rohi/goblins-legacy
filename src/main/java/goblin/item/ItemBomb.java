
package goblin.item;

import net.minecraft.item.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import goblin.Goblins;
import goblin.entity.projectile.EntityBomb;
import net.minecraft.client.renderer.texture.*;

public class ItemBomb extends GoblinsItem {
	public ItemBomb(String stringName)
	{
		super(stringName);
	}
	
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
	{
		if (!world.isRemote)
		{
			world.spawnEntityInWorld((Entity) new EntityBomb(world, (EntityLivingBase) entityPlayer, 2.0f));
		}
		--itemStack.stackSize;
		return itemStack;
	}
}
