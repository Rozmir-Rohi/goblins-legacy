
package goblin.world.gen;

import net.minecraft.world.gen.feature.*;
import net.minecraft.world.*;
import java.util.*;
import net.minecraft.init.*;
import net.minecraft.block.*;

public class WorldGenGFlower extends WorldGenerator {
	private int plantBlockId;

	public WorldGenGFlower()
	{
		plantBlockId = 38;
	}

	public boolean generate(World world, Random random, int xCoord, int yCoord, int zCoord)
	{
		if (world.getBlock(xCoord, yCoord - 1, zCoord) == Blocks.grass)
		{
			world.setBlock(xCoord, yCoord, zCoord, Block.getBlockById(plantBlockId), 0, 2);
		}
		return true;
	}
}
