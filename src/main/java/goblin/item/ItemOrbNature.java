
package goblin.item;

import goblin.entity.projectile.EntityOrbNature;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemOrbNature extends GoblinsItem {
	
	public ItemOrbNature(String stringName)
	{
		super(stringName);
	}
	
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
	{
		world.playSoundAtEntity((Entity) entityPlayer, "random.bow", 0.5f, 0.4f / (ItemOrbNature.itemRand.nextFloat() * 0.4f + 0.8f));
		EntityOrbNature orb = new EntityOrbNature(world, (EntityLivingBase) entityPlayer, 2.0f);
		if (!world.isRemote)
		{
			world.spawnEntityInWorld((Entity) orb);
		}
		--itemStack.stackSize;
		return itemStack;
	}
}
