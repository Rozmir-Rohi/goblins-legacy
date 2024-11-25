
package goblin.world.gen;

import net.minecraft.world.gen.feature.*;
import net.minecraft.world.*;
import java.util.*;
import net.minecraft.init.*;

public class WorldGenGCactus extends WorldGenerator {
	public boolean generate(World world, Random random, int xCoord, int yCoord, int zCoord)
	{
		for (int l = 0; l < 1; ++l)
		{
			int i2 = xCoord;
			int j2 = yCoord;
			int k2 = zCoord;
			if (world.isAirBlock(i2, j2, k2))
			{
				for (int l2 = 2 + random.nextInt(random.nextInt(2) + 1), i3 = 0; i3 < l2; ++i3)
				{
					if (Blocks.cactus.canBlockStay(world, i2, j2 + i3, k2))
					{
						world.setBlock(i2, j2 + i3, k2, Blocks.cactus, 0, 2);
					}
				}
			}
		}
		return true;
	}
}
