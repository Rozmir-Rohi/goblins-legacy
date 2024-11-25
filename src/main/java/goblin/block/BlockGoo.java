
package goblin.block;

import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.init.Blocks;
import net.minecraft.potion.*;
import net.minecraft.util.*;
import java.util.*;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.*;

public class BlockGoo extends Block {
	public BlockGoo()
	{
		super(Material.leaves);
		setTickRandomly(true);
		setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.125f, 1.0f);
	}

	public void setBlockBoundsForItemRender()
	{
		setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.125f, 1.0f);
	}

	public boolean canPlaceBlockAt(World world, int xCoord, int yCoord, int zCoord)
	{
		Block blockBelow = world.getBlock(xCoord, yCoord - 1, zCoord);
		return
		(
			(blockBelow != null || blockBelow != Blocks.air|| blockBelow != this)
			&& (
					(
							(blockBelow.isLeaves((IBlockAccess) world, xCoord, yCoord - 1, zCoord)
							|| blockBelow.isOpaqueCube()) && world.getBlock(xCoord, yCoord - 1, zCoord).getMaterial().blocksMovement()
					)
				)
		);
	}

	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.125f, 1.0f);
	}

	public int getRenderBlockPass()
	{
		return 1;
	}

	public void onEntityCollidedWithBlock(World world, int xCoord, int yCoord, int zCoord, Entity entityPlayer)
	{
		if (entityPlayer instanceof EntityPlayer)
		{
			((EntityPlayer) entityPlayer).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 20, 2));
			world.setBlockToAir(xCoord, yCoord, zCoord);
		}
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int xCoord, int yCoord, int zCoord)
	{
		return null;
	}

	public void updateTick(World world, int xCoord, int yCoord, int zCoord, Random random)
	{
		world.setBlockToAir(xCoord, yCoord, zCoord);
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}

	public int quantityDropped(Random random)
	{
		return 0;
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iIconRegister)
	{
		blockIcon = iIconRegister.registerIcon("goblin:goo");
	}
}
