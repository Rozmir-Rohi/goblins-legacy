
package goblin.item;

import goblin.entity.projectile.EntityOrbForce;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemOrbForce extends GoblinsItem {
	
	public ItemOrbForce(String stringName)
	{
		super(stringName);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
	{
		world.playSoundAtEntity(entityPlayer, "random.bow", 0.5f, 0.4f / (ItemOrbForce.itemRand.nextFloat() * 0.4f + 0.8f));
		EntityOrbForce orb = new EntityOrbForce(world, entityPlayer);
		if (!world.isRemote)
		{
			world.spawnEntityInWorld(orb);
		}
		--itemStack.stackSize;
		return itemStack;
	}
}
