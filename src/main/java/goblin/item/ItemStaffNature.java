
package goblin.item;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.BonemealEvent;

public class ItemStaffNature extends GoblinsItem {
	private final static int SECONDS_FOR_POTION_EFFECT = 12;
	
	public ItemStaffNature(String stringName)
	{
		super(stringName);
		maxStackSize = 1;
		setMaxDamage(50);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int xCoord, int yCoord, int zCoord, int par7, float par8, float par9, float par10)
	{
		
		Block block = world.getBlock(xCoord, yCoord, zCoord);
		BonemealEvent event = new BonemealEvent(player, world, block, xCoord, yCoord, zCoord);
		if (MinecraftForge.EVENT_BUS.post(event))
		{
			return false;
		}
		if (event.getResult() == Event.Result.ALLOW)
		{
			damageItemAndPerformActionOnPlayer(itemStack, player, world);
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
				}
				damageItemAndPerformActionOnPlayer(itemStack, player, world);
				return true;
			}
		}
		return false;
	}

	private void damageItemAndPerformActionOnPlayer(ItemStack itemStack, EntityPlayer player, World world)
	{
		if (!world.isRemote)
		{
			itemStack.damageItem(1, player);
		}
		player.swingItem();
		
		world.playSoundAtEntity(player, "random.orb", 0.5f, 1.0f / (itemRand.nextFloat() * 0.4f + 0.8f));
		player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 20 * SECONDS_FOR_POTION_EFFECT, 1));
	}
}
