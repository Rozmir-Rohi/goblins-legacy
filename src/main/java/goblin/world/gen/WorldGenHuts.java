
package goblin.world.gen;

import net.minecraft.world.*;
import java.util.*;

import goblin.Goblins;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.init.*;

public class WorldGenHuts extends GoblinsWorldGen {
	int houseLoc1;
	int houseLoc2;

	public boolean generate(World world, Random rand, int xCoord, int yCoord, int zCoord)
	{
		if (world.getBlock(xCoord, yCoord, zCoord) == Blocks.grass && world.getBlock(xCoord + 12, yCoord, zCoord + 12) == Blocks.grass && world.getBlock(xCoord + 12, yCoord, zCoord) == Blocks.grass && world.getBlock(xCoord, yCoord, zCoord + 12) == Blocks.grass)
		{
			for (int a = 0; a <= 12; ++a)
			{
				for (int b = 0; b <= 12; ++b)
				{
					if (world.getBlock(xCoord + a, yCoord + 2, zCoord + b) == Blocks.planks)
					{
						return false;
					}
				}
			}
			if (rand.nextInt(2) == 0)
			{
				generateHuts1(world, rand, xCoord, yCoord, zCoord);
			}
			else
			{
				generateHuts2(world, rand, xCoord, yCoord, zCoord);
			}
			return true;
		}
		return false;
	}

	private void generateHuts1(World world, Random rand, int xCoord, int yCoord, int zCoord)
	{
		generateFireplace(world, rand, xCoord + 8, yCoord, zCoord);
		generateRightHouse(world, rand, xCoord, yCoord, zCoord);
		generateTopHouse(world, rand, xCoord + 6, yCoord, zCoord + 7);
		world.setBlock(xCoord + 9, yCoord, zCoord + 6, Blocks.cobblestone, 0, 2);
		world.setBlock(xCoord + 9, yCoord, zCoord + 5, Blocks.cobblestone, 0, 2);
		world.setBlock(xCoord + 8, yCoord, zCoord + 5, Blocks.cobblestone, 0, 2);
		world.setBlock(xCoord + 8, yCoord, zCoord + 4, Blocks.cobblestone, 0, 2);
		world.setBlock(xCoord + 7, yCoord, zCoord + 4, Blocks.cobblestone, 0, 2);
		world.setBlock(xCoord + 7, yCoord, zCoord + 3, Blocks.cobblestone, 0, 2);
		world.setBlock(xCoord + 6, yCoord, zCoord + 3, Blocks.cobblestone, 0, 2);
		world.setBlock(xCoord + 9, yCoord + 1, zCoord + 6, Blocks.air, 0, 2);
		world.setBlock(xCoord + 9, yCoord + 1, zCoord + 5, Blocks.air, 0, 2);
		world.setBlock(xCoord + 8, yCoord + 1, zCoord + 5, Blocks.air, 0, 2);
		world.setBlock(xCoord + 8, yCoord + 1, zCoord + 4, Blocks.air, 0, 2);
		world.setBlock(xCoord + 7, yCoord + 1, zCoord + 4, Blocks.air, 0, 2);
		world.setBlock(xCoord + 7, yCoord + 1, zCoord + 3, Blocks.air, 0, 2);
		world.setBlock(xCoord + 6, yCoord + 1, zCoord + 3, Blocks.air, 0, 2);
		for (int height = 1; height <= 4; ++height)
		{
			if (height == 4)
			{
				world.setBlock(xCoord + 7, yCoord + height, zCoord + 5, chooseTotem(rand), 0, 2);
			}
			else
			{
				world.setBlock(xCoord + 7, yCoord + height, zCoord + 5, Blocks.log, 0, 2);
			}
		}
	}

	private void generateHuts2(World world, Random rand, int xCoord, int yCoord, int zCoord)
	{
		generateFireplace(world, rand, xCoord, yCoord, zCoord);
		generateTopHouse(world, rand, xCoord, yCoord, zCoord + 7);
		generateLeftHouse(world, rand, xCoord + 7, yCoord, zCoord);
		world.setBlock(xCoord + 6, yCoord, zCoord + 3, Blocks.cobblestone, 0, 2);
		world.setBlock(xCoord + 5, yCoord, zCoord + 3, Blocks.cobblestone, 0, 2);
		world.setBlock(xCoord + 5, yCoord, zCoord + 4, Blocks.cobblestone, 0, 2);
		world.setBlock(xCoord + 4, yCoord, zCoord + 4, Blocks.cobblestone, 0, 2);
		world.setBlock(xCoord + 4, yCoord, zCoord + 5, Blocks.cobblestone, 0, 2);
		world.setBlock(xCoord + 3, yCoord, zCoord + 5, Blocks.cobblestone, 0, 2);
		world.setBlock(xCoord + 3, yCoord, zCoord + 6, Blocks.cobblestone, 0, 2);
		world.setBlock(xCoord + 6, yCoord + 1, zCoord + 3, Blocks.air, 0, 2);
		world.setBlock(xCoord + 5, yCoord + 1, zCoord + 3, Blocks.air, 0, 2);
		world.setBlock(xCoord + 5, yCoord + 1, zCoord + 4, Blocks.air, 0, 2);
		world.setBlock(xCoord + 4, yCoord + 1, zCoord + 4, Blocks.air, 0, 2);
		world.setBlock(xCoord + 4, yCoord + 1, zCoord + 5, Blocks.air, 0, 2);
		world.setBlock(xCoord + 3, yCoord + 1, zCoord + 5, Blocks.air, 0, 2);
		world.setBlock(xCoord + 3, yCoord + 1, zCoord + 6, Blocks.air, 0, 2);
		for (int height = 1; height <= 4; ++height)
		{
			if (height == 4)
			{
				world.setBlock(xCoord + 5, yCoord + height, zCoord + 5, chooseTotem(rand), 0, 2);
			}
			else
			{
				world.setBlock(xCoord + 5, yCoord + height, zCoord + 5, Blocks.log, 0, 2);
			}
		}
	}

	private void generateFireplace(World world, Random rand, int xCoord, int yCoord, int zCoord)
	{
		for (int x = 0; x <= 4; ++x)
		{
			for (int y = 0; y <= 4; ++y)
			{
				for (int height = 0; height <= 6; ++height)
				{
					if (x != 0 || (y != 0 && y != 4))
					{
						if (x == 4)
						{
							if (y == 0)
							{
								continue;
							}
							if (y == 4)
							{
								continue;
							}
						}
						if (height == 0)
						{
							world.setBlock(xCoord + x, yCoord + height, zCoord + y, Blocks.cobblestone, 0, 2);
						}
						else
						{
							world.setBlock(xCoord + x, yCoord + height, zCoord + y, Blocks.air, 0, 2);
						}
					}
				}
			}
		}
		world.setBlock(xCoord + 2, yCoord, zCoord + 2, Blocks.netherrack, 0, 2);
		world.setBlock(xCoord + 2, yCoord + 1, zCoord + 2, (Block) Blocks.fire, 0, 2);
		world.setBlock(xCoord + 2, yCoord + 1, zCoord + 1, (Block) Blocks.double_stone_slab, 0, 2);
		world.setBlock(xCoord + 1, yCoord + 1, zCoord + 2, (Block) Blocks.double_stone_slab, 0, 2);
		world.setBlock(xCoord + 2, yCoord + 1, zCoord + 3, (Block) Blocks.double_stone_slab, 0, 2);
		world.setBlock(xCoord + 3, yCoord + 1, zCoord + 2, (Block) Blocks.double_stone_slab, 0, 2);
	}

	public Block chooseTotem(Random rand)
	{
		switch (rand.nextInt(4))
		{
		case 1:
		{
			return Goblins.totemR;
		}
		case 2:
		{
			return Goblins.totemG;
		}
		case 3:
		{
			return Goblins.totemB;
		}
		case 0:
		{
			return Goblins.totemY;
		}
		default:
		{
			return Goblins.totemR;
		}
		}
	}

	protected ItemStack pickCheckLootItem(Random random)
	{
		int i = random.nextInt(7);
		if (i == 0)
		{
			return new ItemStack(Items.apple, random.nextInt(3) + 1);
		}
		if (i == 1)
		{
			return new ItemStack(Items.gold_ingot, random.nextInt(3) + 3);
		}
		if (i == 2)
		{
			return new ItemStack(Items.bread, random.nextInt(3) + 1);
		}
		if (i == 3)
		{
			return new ItemStack(Items.cooked_beef, random.nextInt(3) + 1);
		}
		if (i == 4)
		{
			return new ItemStack(Items.iron_ingot, random.nextInt(3) + 3);
		}
		if (i == 5 && random.nextInt(10) == 1)
		{
			return new ItemStack(Goblins.powderR);
		}
		if (i == 6)
		{
			return new ItemStack(Items.coal, random.nextInt(5) + 5);
		}
		return null;
	}
}
