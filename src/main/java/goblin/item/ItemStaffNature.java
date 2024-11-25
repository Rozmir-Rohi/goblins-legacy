
package goblin.item;

import net.minecraft.client.renderer.texture.*;
import net.minecraft.item.*;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.common.*;
import cpw.mods.fml.common.eventhandler.*;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.*;

public class ItemStaffNature extends GoblinsItem {
	public ItemStaffNature(String stringName)
	{
		super(stringName);
		maxStackSize = 1;
		setMaxDamage(50);
	}
	
	@SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }

	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int xCoord, int yCoord, int zCoord, int par7, float par8, float par9, float par10)
	{
		Block block = world.getBlock(xCoord, yCoord, zCoord);
		BonemealEvent event = new BonemealEvent(player, world, block, xCoord, yCoord, zCoord);
		if (MinecraftForge.EVENT_BUS.post((Event) event))
		{
			return false;
		}
		if (event.getResult() == Event.Result.ALLOW)
		{
			if (!world.isRemote)
			{
				itemStack.damageItem(1, (EntityLivingBase) player);
			}
			player.swingItem();
			return true;
		}
		if (block instanceof IGrowable)
		{
			IGrowable iGrowable = (IGrowable) block;
			if (iGrowable.func_149851_a(world, xCoord, yCoord, zCoord, world.isRemote))
			{
				if (!world.isRemote)
				{
					if (iGrowable.func_149852_a(world, world.rand, xCoord, yCoord, zCoord))
					{
						iGrowable.func_149853_b(world, world.rand, xCoord, yCoord, zCoord);
					}
					itemStack.damageItem(1, (EntityLivingBase) player);
				}
				player.swingItem();
				return true;
			}
		}
		return false;
	}
}
