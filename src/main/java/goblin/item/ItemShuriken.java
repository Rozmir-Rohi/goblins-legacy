
package goblin.item;

import goblin.entity.projectile.EntityShuriken;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemShuriken extends GoblinsItem {
	public int timer;

	public ItemShuriken(String stringName)
	{
		super(stringName);
	}

	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon("goblin:shuriken");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
	{
		if (timer <= 0)
		{
			if (!entityPlayer.capabilities.isCreativeMode)
			{
				--itemStack.stackSize;
			}
			world.playSoundAtEntity(entityPlayer, "random.bow", 0.5f, 0.4f / (ItemShuriken.itemRand.nextFloat() * 0.4f + 0.8f));
			if (!world.isRemote)
			{
				world.spawnEntityInWorld(new EntityShuriken(world, entityPlayer, 2.0f));
				timer = 10;
			}
		}
		return itemStack;
	}

	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int i, boolean flag)
	{
		if (timer > 0)
		{
			--timer;
		}
	}
}
