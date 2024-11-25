
package goblin.world.gen;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import goblin.Goblins;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class Generate implements IWorldGenerator {
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		switch (world.provider.dimensionId)
		{
		case -1:
		{
			generateNether(world, random, chunkX * 16, chunkZ * 16);
		}
		case 0:
		{
			generateSurface(world, random, chunkX * 16, chunkZ * 16);
			break;
		}
		}
	}

	private void generateSurface(World world, Random rand, int xCoord, int zCoord)
	{
		for (int c = 0; c < Goblins.structureSpawnrate; ++c)
		{
			if (rand.nextInt(Goblins.villageSpawnrate) == 0)
			{
				int randPosX = xCoord + rand.nextInt(32);
				int randPosY = rand.nextInt(56) + 60;
				int randPosZ = zCoord + rand.nextInt(32);
				new WorldGenGoblinVillage1().generate(world, rand, randPosX, randPosY, randPosZ);
			}
			if (rand.nextInt(Goblins.hutsSpawnrate) == 0)
			{
				int randPosX2 = xCoord + rand.nextInt(32);
				int randPosY2 = rand.nextInt(56) + 60;
				int randPosZ2 = zCoord + rand.nextInt(32);
				new WorldGenFireplace().generate(world, rand, randPosX2, randPosY2, randPosZ2);
			}
			if (rand.nextInt(Goblins.fireplaceSpawnrate) == 0)
			{
				int randPosX3 = xCoord + rand.nextInt(32);
				int randPosY3 = rand.nextInt(56) + 60;
				int randPosZ3 = zCoord + rand.nextInt(32);
				new WorldGenHuts().generate(world, rand, randPosX3, randPosY3, randPosZ3);
			}
		}
	}

	private void generateNether(World world, Random random, int xCoord, int zCoord)
	{
	}
}
