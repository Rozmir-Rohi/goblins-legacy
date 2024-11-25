package goblin.block;

import net.minecraft.util.*;
import cpw.mods.fml.relauncher.*;
import goblin.tileEntity.TileEntityGoblinDrum;
import net.minecraft.block.material.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.world.*;
import net.minecraft.block.*;
import net.minecraft.init.*;
import net.minecraft.entity.player.*;
import net.minecraft.tileentity.*;

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
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iIconRegister)
	{
		side = iIconRegister.registerIcon("goblin:DrumSide");
		top = iIconRegister.registerIcon("goblin:DrumTop");
		bottom = iIconRegister.registerIcon("goblin:DrumBot");
	}

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

	public boolean onBlockActivated(World world, int xCoord, int yCoord, int zCoord, EntityPlayer entityPlayer, int par6, float par7, float par8, float par9)
	{
		if (world.isRemote)
		{
			return true;
		}
		TileEntityGoblinDrum tileEntityNote = (TileEntityGoblinDrum) world.getTileEntity(xCoord, yCoord, zCoord);
		if (tileEntityNote != null)
		{
			tileEntityNote.changePitch();
			tileEntityNote.triggerNote(world, xCoord, yCoord, zCoord);
		}
		return true;
	}

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

	public boolean onBlockEventReceived(World world, int xCoord, int yCoord, int zCoord, int par5, int par6)
	{
		float f = (float) Math.pow(2.0, (par6 - 12) / 12.0);
		String soundName = "harp";
		if (par5 == 1)
		{
			soundName = "bd";
		}
		if (par5 == 2)
		{
			soundName = "snare";
		}
		if (par5 == 3)
		{
			soundName = "hat";
		}
		if (par5 == 4)
		{
			soundName = "bassattack";
		}
		world.playSoundEffect(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, "note." + soundName, 3.0f, f);
		world.spawnParticle("note", xCoord + 0.5, yCoord + 1.2, zCoord + 0.5, par6 / 24.0, 0.0, 0.0);
		return true;
	}

	public TileEntity createNewTileEntity(World world, int var2)
	{
		return new TileEntityGoblinDrum();
	}
}
