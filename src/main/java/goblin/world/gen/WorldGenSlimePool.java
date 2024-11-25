
package goblin.world.gen;

import net.minecraft.world.gen.feature.*;
import net.minecraft.world.*;
import java.util.*;

import goblin.Goblins;
import net.minecraft.init.*;

public class WorldGenSlimePool extends WorldGenerator {
	public boolean generate(World world, Random random, int xCoord, int yCoord, int zCoord)
	{
		createPool(world, random, xCoord, yCoord, zCoord);
		return true;
	}

	public void createPool(World world, Random rand, int xCoord, int yCoord, int zCoord)
	{
		for (int radius = 4, i2 = -radius; i2 <= radius; ++i2)
		{
			for (int k2 = -radius; k2 <= radius; ++k2)
			{
				if (Math.pow(i2, 2.0) + Math.pow(k2, 2.0) < Math.pow(radius, 2.0) && world.getBlock(xCoord + i2, yCoord - 1, zCoord + k2) != Blocks.air && world.getBlock(xCoord + i2, yCoord, zCoord + k2) == Blocks.air)
				{
					world.setBlock(xCoord + i2, yCoord, zCoord + k2, Goblins.goo);
				}
			}
		}
	}
}
