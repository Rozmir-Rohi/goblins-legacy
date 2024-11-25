
package goblin.item;

import net.minecraft.item.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import goblin.entity.projectile.EntityOrbExplosive;
import net.minecraft.client.renderer.texture.*;

public class ItemOrbExplosive extends GoblinsItem {
	
	public ItemOrbExplosive(String stringName)
	{
		super(stringName);
	}
	
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
	{
		world.playSoundAtEntity((Entity) entityPlayer, "random.bow", 0.5f, 0.4f / (ItemOrbExplosive.itemRand.nextFloat() * 0.4f + 0.8f));
		EntityOrbExplosive orb = new EntityOrbExplosive(world, (EntityLivingBase) entityPlayer, 2.0f);
		if (!world.isRemote)
		{
			world.spawnEntityInWorld((Entity) orb);
		}
		--itemStack.stackSize;
		return itemStack;
	}
}
