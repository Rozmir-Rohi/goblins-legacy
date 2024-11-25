
package goblin.block;

import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.client.renderer.texture.*;
import java.util.*;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import goblin.tileEntity.TileEntityMobGMSpawner;
import net.minecraft.item.*;
import net.minecraft.init.*;
import net.minecraft.world.*;
import net.minecraft.tileentity.*;

public class BlockMobGMSpawner extends BlockContainer {
	int spawnrate;

	public BlockMobGMSpawner(int k)
	{
		super(Material.rock);
		spawnrate = k;
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iIconRegister)
	{
		blockIcon = iIconRegister.registerIcon("goblin:GoblSpawner");
	}

	public Item getItemDropped(int meta, Random random, int fortune)
	{
		return Item.getItemFromBlock(Blocks.cobblestone);
	}

	public int quantityDropped(Random random)
	{
		return 0;
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileEntityMobGMSpawner();
	}
}
