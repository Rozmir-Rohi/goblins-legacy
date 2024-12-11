
package goblin.world.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenGFlower extends WorldGenerator {
	private int plantBlockId;

	public WorldGenGFlower()
	{
		plantBlockId = 38;
	}

	@Override
	public boolean generate(World world, Random random, int xCoord, int yCoord, int zCoord)
	{
		if (world.getBlock(xCoord, yCoord - 1, zCoord) == Blocks.grass)
		{
			world.setBlock(xCoord, yCoord, zCoord, Block.getBlockById(plantBlockId), 0, 2);
		}
		return true;
	}
}
