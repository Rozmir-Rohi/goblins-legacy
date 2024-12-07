
package goblin.item;

import goblin.entity.projectile.EntityOrbLightning;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemOrbLightning extends GoblinsItem {
	
	public ItemOrbLightning(String stringName)
	{
		super(stringName);
	}
	
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
	{
		world.playSoundAtEntity((Entity) entityPlayer, "random.bow", 0.5f, 0.4f / (ItemOrbLightning.itemRand.nextFloat() * 0.4f + 0.8f));
		EntityOrbLightning orb = new EntityOrbLightning(world, (EntityLivingBase) entityPlayer);
		if (!world.isRemote)
		{
			world.spawnEntityInWorld((Entity) orb);
		}
		--itemStack.stackSize;
		return itemStack;
	}
}
