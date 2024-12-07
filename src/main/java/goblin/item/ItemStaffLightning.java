
package goblin.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import goblin.entity.projectile.EntityLightball;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemStaffLightning extends GoblinsItem {
	public int timer;

	public ItemStaffLightning(String stringName)
	{
		super(stringName);
		maxStackSize = 1;
		setMaxDamage(15);
		timer = 0;
	}
	
	@SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }

	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
	{
		if (timer == 0)
		{
			entityPlayer.swingItem();
			
			EntityLightball entityarrow = new EntityLightball(world, (EntityLivingBase) entityPlayer);
			world.playSoundAtEntity((Entity) entityPlayer, "mob.ghast.fireball", 0.5f, 1.0f / (ItemStaffLightning.itemRand.nextFloat() * 0.4f + 0.8f));
			if (!world.isRemote)
			{
				world.spawnEntityInWorld((Entity) entityarrow);
				itemStack.damageItem(1, (EntityLivingBase) entityPlayer);
				timer = 55;
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
