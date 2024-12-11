
package goblin.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGoo extends Block {
	public BlockGoo()
	{
		super(Material.leaves);
		setTickRandomly(true);
		setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.125f, 1.0f);
		setStepSound(soundTypeSnow);
	}

	@Override
	public void setBlockBoundsForItemRender()
	{
		setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.125f, 1.0f);
	}

	@Override
	public boolean canPlaceBlockAt(World world, int xCoord, int yCoord, int zCoord)
	{
		Block blockBelow = world.getBlock(xCoord, yCoord - 1, zCoord);
		return
		(
			(blockBelow != null || blockBelow != Blocks.air|| blockBelow != this)
			&& (
					(
							(blockBelow.isLeaves(world, xCoord, yCoord - 1, zCoord)
							|| blockBelow.isOpaqueCube()) && world.getBlock(xCoord, yCoord - 1, zCoord).getMaterial().blocksMovement()
					)
				)
		);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.125f, 1.0f);
	}

	@Override
	public int getRenderBlockPass()
	{
		return 1;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int xCoord, int yCoord, int zCoord, Entity entityPlayer)
	{
		if (entityPlayer instanceof EntityPlayer)
		{
			((EntityPlayer) entityPlayer).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 20, 2));
			world.setBlockToAir(xCoord, yCoord, zCoord);
		}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int xCoord, int yCoord, int zCoord)
	{
		return null;
	}

	@Override
	public void updateTick(World world, int xCoord, int yCoord, int zCoord, Random random)
	{
		world.setBlockToAir(xCoord, yCoord, zCoord);
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public int quantityDropped(Random random)
	{
		return 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iIconRegister)
	{
		blockIcon = iIconRegister.registerIcon("goblin:goo");
	}
}
