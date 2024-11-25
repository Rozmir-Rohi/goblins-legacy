
package goblin.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import goblin.tileEntity.TileEntityMobGSpawner;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMobGSpawner extends BlockContainer {
	int spawnrate;

	public BlockMobGSpawner(int k)
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
		return new TileEntityMobGSpawner();
	}
}
