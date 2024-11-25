
package goblin.item;

import goblin.Goblins;
import goblin.entity.projectile.EntityShuriken;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.item.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;

public class ItemShuriken extends GoblinsItem {
	public int timer;

	public ItemShuriken(String stringName)
	{
		super(stringName);
	}

	public void registerIcons(IIconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon("goblin:shuriken2");
	}

	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
	{
		if (timer <= 0)
		{
			if (!entityPlayer.capabilities.isCreativeMode)
			{
				--itemStack.stackSize;
			}
			world.playSoundAtEntity((Entity) entityPlayer, "random.bow", 0.5f, 0.4f / (ItemShuriken.itemRand.nextFloat() * 0.4f + 0.8f));
			if (!world.isRemote)
			{
				world.spawnEntityInWorld((Entity) new EntityShuriken(world, (EntityLivingBase) entityPlayer, 2.0f));
				timer = 10;
			}
		}
		return itemStack;
	}

	public void onUpdate(ItemStack itemStack, World world, Entity entity, int i, boolean flag)
	{
		if (timer > 0)
		{
			--timer;
		}
	}
}
