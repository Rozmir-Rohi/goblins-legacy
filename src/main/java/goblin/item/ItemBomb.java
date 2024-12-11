
package goblin.item;

import java.util.Random;

import goblin.entity.projectile.EntityBomb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBomb extends GoblinsItem {
	private Random rand;
	
	public ItemBomb(String stringName)
	{
		super(stringName);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
	{
		world.playSoundAtEntity(entityPlayer, "random.bow", 0.5f, 0.4f / (ItemOrbNature.itemRand.nextFloat() * 0.4f + 0.8f));
		if (!world.isRemote)
		{
			world.spawnEntityInWorld(new EntityBomb(world, entityPlayer));
		}
		--itemStack.stackSize;
		return itemStack;
	}
}
