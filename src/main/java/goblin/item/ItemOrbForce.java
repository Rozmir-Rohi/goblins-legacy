
package goblin.item;

import net.minecraft.item.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import goblin.entity.projectile.EntityOrbForce;
import net.minecraft.client.renderer.texture.*;

public class ItemOrbForce extends GoblinsItem {
	
	public ItemOrbForce(String stringName)
	{
		super(stringName);
	}
	
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
	{
		world.playSoundAtEntity((Entity) entityPlayer, "random.bow", 0.5f, 0.4f / (ItemOrbForce.itemRand.nextFloat() * 0.4f + 0.8f));
		EntityOrbForce orb = new EntityOrbForce(world, (EntityLivingBase) entityPlayer, 2.0f);
		if (!world.isRemote)
		{
			world.spawnEntityInWorld((Entity) orb);
		}
		--itemStack.stackSize;
		return itemStack;
	}
}
