
package goblin.item;

import goblin.entity.projectile.EntityOrbNature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemOrbNature extends GoblinsItem {
	
	public ItemOrbNature(String stringName)
	{
		super(stringName);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
	{
		world.playSoundAtEntity(entityPlayer, "random.bow", 0.5f, 0.4f / (ItemOrbNature.itemRand.nextFloat() * 0.4f + 0.8f));
		EntityOrbNature orb = new EntityOrbNature(world, entityPlayer);
		if (!world.isRemote)
		{
			world.spawnEntityInWorld(orb);
		}
		--itemStack.stackSize;
		return itemStack;
	}
}
