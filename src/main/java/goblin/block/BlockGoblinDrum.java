package goblin.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import goblin.tileEntity.TileEntityGoblinDrum;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockGoblinDrum extends BlockContainer {
	@SideOnly(Side.CLIENT)
	private IIcon side;
	@SideOnly(Side.CLIENT)
	private IIcon top;
	@SideOnly(Side.CLIENT)
	private IIcon bottom;

	public BlockGoblinDrum()
	{
		super(Material.wood);
		setStepSound(soundTypeWood);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iIconRegister)
	{
		side = iIconRegister.registerIcon("goblin:DrumSide");
		top = iIconRegister.registerIcon("goblin:DrumTop");
		bottom = iIconRegister.registerIcon("goblin:DrumBot");
	}

	@Override
	public IIcon getIcon(int i, int j)
	{
		if (i == 1)
		{
			return top;
		}
		if (i == 0)
		{
			return bottom;
		}
		return side;
	}

	@Override
	public void onNeighborBlockChange(World world, int xCoord, int yCoord, int zCoord, Block par5)
	{
		if (par5 != Blocks.air)
		{
			boolean flag = world.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
			TileEntityGoblinDrum tileEntityNote = (TileEntityGoblinDrum) world.getTileEntity(xCoord, yCoord, zCoord);
			if (tileEntityNote != null && tileEntityNote.previousRedstoneState != flag)
			{
				if (flag)
				{
					tileEntityNote.triggerNote(world, xCoord, yCoord, zCoord);
				}
				tileEntityNote.previousRedstoneState = flag;
			}
		}
	}

	@Override
	public boolean onBlockActivated(World world, int xCoord, int yCoord, int zCoord, EntityPlayer entityPlayer, int par6, float par7, float par8, float par9)
	{
		if (world.isRemote)
		{
			return true;
		}
		TileEntityGoblinDrum tileEntityNote = (TileEntityGoblinDrum) world.getTileEntity(xCoord, yCoord, zCoord);
		if (tileEntityNote != null)
		{
			if (
					entityPlayer != null
					&& entityPlayer.isSneaking()
					&& entityPlayer.getHeldItem() == null	
				)
			{
				tileEntityNote.changePitch();
			}
			
			if (
					entityPlayer == null
					|| (
							entityPlayer != null
							&& entityPlayer.getHeldItem() == null
						)
				)
			{
				tileEntityNote.triggerNote(world, xCoord, yCoord, zCoord);
			}
		}
		return true;
	}

	@Override
	public void onBlockClicked(World world, int xCoord, int yCoord, int zCoord, EntityPlayer entityPlayer)
	{
		if (world.isRemote)
		{
			return;
		}
		TileEntityGoblinDrum tileEntityNote = (TileEntityGoblinDrum) world.getTileEntity(xCoord, yCoord, zCoord);
		if (tileEntityNote != null)
		{
			tileEntityNote.triggerNote(world, xCoord, yCoord, zCoord);
		}
	}

	@Override
	public boolean onBlockEventReceived(World world, int xCoord, int yCoord, int zCoord, int par5, int par6)
	{
		float f = (float) Math.pow(2.0, (par6 - 12) / 12.0);

		world.playSoundEffect(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, "goblin:goblindrum", 3.0f, f);
		world.spawnParticle("note", xCoord + 0.5, yCoord + 1.2, zCoord + 0.5, par6 / 24.0, 0.0, 0.0);
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int var2)
	{
		return new TileEntityGoblinDrum();
	}
}
