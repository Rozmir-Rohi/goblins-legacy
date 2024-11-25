
package goblin.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import goblin.world.gen.WorldGenGoblinVillage1;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.init.*;

public class BlockVillageSpawner extends Block {
	public BlockVillageSpawner()
	{
		super(Material.ground);
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iIconRegister)
	{
		blockIcon = iIconRegister.registerIcon("goblin:VillageSpawn");
	}

	public boolean onBlockActivated(World world, int xCoord, int yCoord, int zCoord, EntityPlayer entityPlayer, int par6, float par7, float par8, float par9)
	{
		world.setBlock(xCoord, yCoord, zCoord, Blocks.air, 0, 3);
		blockCreate(world, xCoord, yCoord, zCoord);
		return super.onBlockActivated(world, xCoord, yCoord, zCoord, entityPlayer, par6, par7, par8, par9);
	}

	public void blockCreate(World world, int xCoord, int yCoord, int zCoord)
	{
		if (!world.isRemote)
		{
			new WorldGenGoblinVillage1().generateVillage(world, world.rand, xCoord, yCoord, zCoord);
		}
	}
}
