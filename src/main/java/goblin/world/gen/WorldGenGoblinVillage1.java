
package goblin.world.gen;

import java.util.*;

import goblin.Goblins;
import goblin.entity.EntityGoblinLord;
import goblin.entity.EntityGoblinMage;
import goblin.entity.EntityGoblinRangerGuard;
import net.minecraft.init.*;
import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.world.*;
import net.minecraft.world.gen.feature.*;

public class WorldGenGoblinVillage1 extends GoblinsWorldGen {
	int houseLoc1;
	int houseLoc2;

	public boolean generateVillage(World world, Random rand, int xCoord, int yCoord, int zCoord)
	{
		for (int width = 1; width < 20; ++width)
		{
			for (int length = 4; length < 26; ++length)
			{
				if (world.getBlock(xCoord + width, yCoord - 1, zCoord + length) == Blocks.grass)
				{
					world.setBlock(xCoord + width, yCoord, zCoord + length, (Block) Blocks.grass);
					world.setBlock(xCoord + width, yCoord - 1, zCoord + length, Blocks.dirt);
				}
				if (world.getBlock(xCoord + width, yCoord - 2, zCoord + length) == Blocks.grass)
				{
					world.setBlock(xCoord + width, yCoord - 1, zCoord + length, (Block) Blocks.grass);
					world.setBlock(xCoord + width, yCoord - 2, zCoord + length, Blocks.dirt);
				}
			}
		}
		for (int width = 0; width < 5; ++width)
		{
			for (int length = 0; length < 10; ++length)
			{
				if (rand.nextInt(6) == 1)
				{
					world.setBlock(xCoord + 9 + width, yCoord, zCoord + 9 + length, Blocks.mossy_cobblestone, 0, 2);
				}
				else
				{
					world.setBlock(xCoord + 9 + width, yCoord, zCoord + 9 + length, Blocks.cobblestone, 0, 2);
				}
				for (int height = 1; height <= 5; ++height)
				{
					if (world.getBlock(xCoord + 9 + width, yCoord + height, zCoord + 9 + length) != Blocks.log)
					{
						world.setBlock(xCoord + 9 + width, yCoord + height, zCoord + 9 + length, Blocks.air, 0, 2);
					}
				}
				world.setBlock(xCoord + 9 + width, yCoord - 1, zCoord + 9 + length, Blocks.dirt, 0, 2);
			}
		}
		int colour1 = 0;
		switch (rand.nextInt(4))
		{
		case 0:
		{
		}
		case 1:
		{
		}
		}
		world.setBlock(xCoord + 11, yCoord + 1, zCoord + 16, Goblins.totemR, 0, 2);
		world.setBlock(xCoord + 11, yCoord + 2, zCoord + 16, Goblins.totemG, 0, 2);
		world.setBlock(xCoord + 11, yCoord + 3, zCoord + 16, Goblins.totemB, 0, 2);
		world.setBlock(xCoord + 11, yCoord + 4, zCoord + 16, Goblins.totemY, 0, 2);
		world.setBlock(xCoord + 11, yCoord - 1, zCoord + 11, Blocks.netherrack, 0, 2);
		world.setBlock(xCoord + 11, yCoord, zCoord + 11, (Block) Blocks.fire, 0, 2);
		world.setBlock(xCoord + 11, yCoord + 1, zCoord + 11, Blocks.iron_bars, 0, 2);
		world.setBlock(xCoord + 11, yCoord + 1, zCoord + 12, (Block) Blocks.double_stone_slab, 0, 2);
		world.setBlock(xCoord + 11, yCoord + 1, zCoord + 10, (Block) Blocks.double_stone_slab, 0, 2);
		world.setBlock(xCoord + 10, yCoord + 1, zCoord + 11, (Block) Blocks.double_stone_slab, 0, 2);
		world.setBlock(xCoord + 12, yCoord + 1, zCoord + 11, (Block) Blocks.double_stone_slab, 0, 2);
		world.setBlock(xCoord + 12, yCoord + 1, zCoord + 17, Blocks.fence, 0, 2);
		world.setBlock(xCoord + 10, yCoord + 1, zCoord + 17, Blocks.fence, 0, 2);
		world.setBlock(xCoord + 12, yCoord + 1, zCoord + 15, Blocks.fence, 0, 2);
		world.setBlock(xCoord + 10, yCoord + 1, zCoord + 15, Blocks.fence, 0, 2);
		world.setBlock(xCoord + 12, yCoord + 2, zCoord + 17, Blocks.torch, 0, 2);
		world.setBlock(xCoord + 10, yCoord + 2, zCoord + 17, Blocks.torch, 0, 2);
		world.setBlock(xCoord + 12, yCoord + 2, zCoord + 15, Blocks.torch, 0, 2);
		world.setBlock(xCoord + 10, yCoord + 2, zCoord + 15, Blocks.torch, 0, 2);
		for (int width2 = 0; width2 < 3; ++width2)
		{
			for (int length2 = 0; length2 < 3; ++length2)
			{
				world.setBlock(xCoord + 10 + width2, yCoord, zCoord + 15 + length2, (Block) Blocks.grass, 0, 2);
			}
		}
		houseLoc1 = 3 - rand.nextInt(2);
		houseLoc2 = 3 - rand.nextInt(2);
		generateRandomCornerHouse(world, rand, xCoord + houseLoc1, yCoord, zCoord + houseLoc2, 0);
		houseLoc1 = 14 + rand.nextInt(2);
		houseLoc2 = 3 - rand.nextInt(2);
		generateRandomCornerHouse(world, rand, xCoord + houseLoc1, yCoord, zCoord + houseLoc2, 1);
		houseLoc1 = 3 - rand.nextInt(2);
		houseLoc2 = 19 + rand.nextInt(2);
		generateRandomCornerHouse(world, rand, xCoord + houseLoc1, yCoord, zCoord + houseLoc2, 2);
		houseLoc1 = 14 + rand.nextInt(2);
		houseLoc2 = 19 + rand.nextInt(2);
		generateRandomCornerHouse(world, rand, xCoord + houseLoc1, yCoord, zCoord + houseLoc2, 3);
		if (rand.nextInt(4) != 0)
		{
			houseLoc1 = 16 + rand.nextInt(2);
			houseLoc2 = 10 + rand.nextInt(2);
			if (rand.nextInt(2) == 0)
			{
				generateLeftWolfPen(world, rand, xCoord + houseLoc1, yCoord, zCoord + houseLoc2);
			}
			else
			{
				generateLeftHouse(world, rand, xCoord + houseLoc1, yCoord, zCoord + houseLoc2);
			}
		}
		else
		{
			houseLoc1 = 19 + rand.nextInt(2);
			houseLoc2 = 15;
			generateTopLeftCornerHouse(world, rand, xCoord + houseLoc1, yCoord, zCoord + houseLoc2);
			for (int width2 = 0; width2 < 1; ++width2)
			{
				for (int length = 0; length < 1; ++length)
				{
					if (world.getBlock(xCoord + houseLoc1 + width2 + 0, yCoord + 1, zCoord + houseLoc2 + length + 0) != Blocks.log)
					{
						world.setBlock(xCoord + houseLoc1 + width2 + 0, yCoord + 1, zCoord + houseLoc2 + length + 0, Blocks.air, 0, 2);
					}
				}
			}
			houseLoc1 = 19 + rand.nextInt(2);
			houseLoc2 = 7;
			generateBottomLeftCornerHouse(world, rand, xCoord + houseLoc1, yCoord, zCoord + houseLoc2);
			for (int width2 = 0; width2 < 1; ++width2)
			{
				for (int length = 0; length < 1; ++length)
				{
					if (world.getBlock(xCoord + houseLoc1 + width2 + 0, yCoord + 1, zCoord + houseLoc2 + length + 5) != Blocks.log)
					{
						world.setBlock(xCoord + houseLoc1 + width2 + 0, yCoord + 1, zCoord + houseLoc2 + length + 5, Blocks.air, 0, 2);
					}
				}
			}
		}
		if (rand.nextInt(4) != 0)
		{
			houseLoc1 = 1 - rand.nextInt(2);
			houseLoc2 = 10 + rand.nextInt(2);
			if (rand.nextInt(2) == 0)
			{
				generateRightMine(world, rand, xCoord + houseLoc1, yCoord, zCoord + houseLoc2);
			}
			else
			{
				generateRightHouse(world, rand, xCoord + houseLoc1, yCoord, zCoord + houseLoc2);
			}
		}
		else
		{
			houseLoc1 = -3 - rand.nextInt(2);
			houseLoc2 = 15;
			generateTopRightCornerHouse(world, rand, xCoord + houseLoc1, yCoord, zCoord + houseLoc2);
			for (int width2 = 0; width2 < 1; ++width2)
			{
				for (int length = 0; length < 1; ++length)
				{
					if (world.getBlock(xCoord + houseLoc1 + width2 + 0, yCoord + 1, zCoord + houseLoc2 + length + 0) != Blocks.log)
					{
						world.setBlock(xCoord + houseLoc1 + width2 + 0, yCoord + 1, zCoord + houseLoc2 + length + 0, Blocks.air, 0, 2);
					}
				}
			}
			houseLoc1 = -3 - rand.nextInt(2);
			houseLoc2 = 7;
			generateBottomRightCornerHouse(world, rand, xCoord + houseLoc1, yCoord, zCoord + houseLoc2);
			for (int width2 = 0; width2 < 1; ++width2)
			{
				for (int length = 0; length < 1; ++length)
				{
					if (world.getBlock(xCoord + houseLoc1 + width2 + 0, yCoord + 1, zCoord + houseLoc2 + length + 5) != Blocks.log)
					{
						world.setBlock(xCoord + houseLoc1 + width2 + 0, yCoord + 1, zCoord + houseLoc2 + length + 5, Blocks.air, 0, 2);
					}
				}
			}
		}
		houseLoc1 = 8;
		houseLoc2 = 24;
		for (int width3 = 0; width3 <= 5; ++width3)
		{
			for (int width4 = 1; width4 <= 4; ++width4)
			{
				for (int height2 = 1; height2 <= 4; ++height2)
				{
					world.setBlock(xCoord + width3 + houseLoc1, yCoord + height2, zCoord + width4 + houseLoc2, Blocks.air, 0, 2);
				}
			}
		}
		for (int width3 = 0; width3 <= 6; ++width3)
		{
			for (int width4 = 0; width4 <= 5; ++width4)
			{
				for (int height2 = 0; height2 <= 4; ++height2)
				{
					if (height2 == 0)
					{
						world.setBlock(xCoord + width3 + houseLoc1, yCoord - height2, zCoord + width4 + houseLoc2, (Block) Blocks.grass, 0, 2);
					}
					else if (height2 > 0 && height2 < 3)
					{
						world.setBlock(xCoord + width3 + houseLoc1, yCoord - height2, zCoord + width4 + houseLoc2, Blocks.dirt, 0, 2);
					}
					else
					{
						world.setBlock(xCoord + width3 + houseLoc1, yCoord - height2, zCoord + width4 + houseLoc2, Blocks.stone, 0, 2);
					}
				}
			}
		}
		for (int length = 0; length <= 4; ++length)
		{
			world.setBlock(xCoord + 11, yCoord, zCoord + 19 + length, Blocks.cobblestone, 0, 2);
			for (int height = 1; height <= 4; ++height)
			{
				for (int width5 = -1; width5 <= 1; ++width5)
				{
					if (world.getBlock(xCoord + 11 + width5, yCoord + height, zCoord + 19 + length) != Blocks.log)
					{
						world.setBlock(xCoord + 11 + width5, yCoord + height, zCoord + 19 + length, Blocks.air, 0, 2);
					}
				}
			}
		}
		for (int height3 = 1; height3 <= 2; ++height3)
		{
			world.setBlock(xCoord + houseLoc1 + 4, yCoord + height3, zCoord + houseLoc2 + 0, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 2, yCoord + height3, zCoord + houseLoc2 + 0, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 5, yCoord + height3, zCoord + houseLoc2 + 1, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 1, yCoord + height3, zCoord + houseLoc2 + 1, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 0, yCoord + height3, zCoord + houseLoc2 + 4, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 0, yCoord + height3, zCoord + houseLoc2 + 3, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 0, yCoord + height3, zCoord + houseLoc2 + 2, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 1, yCoord + height3, zCoord + houseLoc2 + 5, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 5, yCoord + height3, zCoord + houseLoc2 + 5, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 3, yCoord + height3, zCoord + houseLoc2 + 6, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 2, yCoord + height3, zCoord + houseLoc2 + 6, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 4, yCoord + height3, zCoord + houseLoc2 + 6, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 6, yCoord + height3, zCoord + houseLoc2 + 4, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 6, yCoord + height3, zCoord + houseLoc2 + 3, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 6, yCoord + height3, zCoord + houseLoc2 + 2, Blocks.cobblestone, 0, 2);
		}
		for (int height3 = 3; height3 <= 3; ++height3)
		{
			world.setBlock(xCoord + houseLoc1 + 4, yCoord + height3, zCoord + houseLoc2 + 1, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 2, yCoord + height3, zCoord + houseLoc2 + 1, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 3, yCoord + height3, zCoord + houseLoc2 + 0, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 4, yCoord + height3, zCoord + houseLoc2 + 5, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 3, yCoord + height3, zCoord + houseLoc2 + 5, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 2, yCoord + height3, zCoord + houseLoc2 + 5, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 1, yCoord + height3, zCoord + houseLoc2 + 4, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 1, yCoord + height3, zCoord + houseLoc2 + 3, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 1, yCoord + height3, zCoord + houseLoc2 + 2, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 5, yCoord + height3, zCoord + houseLoc2 + 4, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 5, yCoord + height3, zCoord + houseLoc2 + 3, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 5, yCoord + height3, zCoord + houseLoc2 + 2, Blocks.cobblestone, 0, 2);
		}
		for (int height3 = 4; height3 <= 4; ++height3)
		{
			world.setBlock(xCoord + houseLoc1 + 2, yCoord + height3, zCoord + houseLoc2 + 4, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 2, yCoord + height3, zCoord + houseLoc2 + 3, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 2, yCoord + height3, zCoord + houseLoc2 + 2, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 4, yCoord + height3, zCoord + houseLoc2 + 4, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 4, yCoord + height3, zCoord + houseLoc2 + 3, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 4, yCoord + height3, zCoord + houseLoc2 + 2, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 3, yCoord + height3, zCoord + houseLoc2 + 4, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 3, yCoord + height3, zCoord + houseLoc2 + 1, Blocks.cobblestone, 0, 2);
		}
		for (int height3 = 5; height3 <= 5; ++height3)
		{
			world.setBlock(xCoord + houseLoc1 + 3, yCoord + height3, zCoord + houseLoc2 + 3, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 3, yCoord + height3, zCoord + houseLoc2 + 2, Blocks.cobblestone, 0, 2);
		}
		for (int height3 = 0; height3 <= 0; ++height3)
		{
			world.setBlock(xCoord + houseLoc1 + 4, yCoord + height3, zCoord + houseLoc2 + 1, Blocks.planks, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 2, yCoord + height3, zCoord + houseLoc2 + 1, Blocks.planks, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 3, yCoord + height3, zCoord + houseLoc2 + 0, Blocks.planks, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 4, yCoord + height3, zCoord + houseLoc2 + 5, Blocks.planks, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 3, yCoord + height3, zCoord + houseLoc2 + 5, Blocks.planks, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 2, yCoord + height3, zCoord + houseLoc2 + 5, Blocks.planks, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 1, yCoord + height3, zCoord + houseLoc2 + 4, Blocks.planks, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 1, yCoord + height3, zCoord + houseLoc2 + 3, Blocks.planks, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 1, yCoord + height3, zCoord + houseLoc2 + 2, Blocks.planks, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 5, yCoord + height3, zCoord + houseLoc2 + 4, Blocks.planks, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 5, yCoord + height3, zCoord + houseLoc2 + 3, Blocks.planks, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 5, yCoord + height3, zCoord + houseLoc2 + 2, Blocks.planks, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 2, yCoord + height3, zCoord + houseLoc2 + 4, Blocks.planks, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 2, yCoord + height3, zCoord + houseLoc2 + 3, Blocks.planks, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 2, yCoord + height3, zCoord + houseLoc2 + 2, Blocks.planks, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 4, yCoord + height3, zCoord + houseLoc2 + 4, Blocks.planks, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 4, yCoord + height3, zCoord + houseLoc2 + 3, Blocks.planks, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 4, yCoord + height3, zCoord + houseLoc2 + 2, Blocks.planks, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 3, yCoord + height3, zCoord + houseLoc2 + 4, Blocks.planks, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 3, yCoord + height3, zCoord + houseLoc2 + 1, Blocks.planks, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 3, yCoord + height3, zCoord + houseLoc2 + 3, Blocks.planks, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 3, yCoord + height3, zCoord + houseLoc2 + 2, Blocks.planks, 0, 2);
		}
		for (int height3 = 1; height3 <= 4; ++height3)
		{
			world.setBlock(xCoord + houseLoc1 + 3, yCoord + height3, zCoord + houseLoc2 + 3, Blocks.fence, 0, 2);
		}
		for (int height3 = 2; height3 <= 2; ++height3)
		{
			world.setBlock(xCoord + houseLoc1 + 2, yCoord + height3, zCoord + houseLoc2 - 1, Blocks.torch, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 4, yCoord + height3, zCoord + houseLoc2 - 1, Blocks.torch, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 3, yCoord + height3, zCoord + houseLoc2 + 5, Blocks.torch, 0, 2);
		}
		for (int height3 = 1; height3 <= 1; ++height3)
		{
			world.setBlock(xCoord + houseLoc1 + 1, yCoord + height3, zCoord + houseLoc2 + 2, Blocks.stonebrick, 3, 2);
			world.setBlock(xCoord + houseLoc1 + 1, yCoord + height3, zCoord + houseLoc2 + 3, Blocks.stone, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 1, yCoord + height3 + 1, zCoord + houseLoc2 + 3, Blocks.brewing_stand, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 1, yCoord + height3, zCoord + houseLoc2 + 4, Blocks.stonebrick, 3, 2);
			world.setBlock(xCoord + houseLoc1 + 2, yCoord + height3, zCoord + houseLoc2 + 5, Blocks.stonebrick, 3, 2);
			world.setBlock(xCoord + houseLoc1 + 3, yCoord + height3, zCoord + houseLoc2 + 5, Blocks.stone_brick_stairs, 2, 2);
			world.setBlock(xCoord + houseLoc1 + 4, yCoord + height3, zCoord + houseLoc2 + 5, Blocks.stonebrick, 3, 2);
			world.setBlock(xCoord + houseLoc1 + 5, yCoord + height3, zCoord + houseLoc2 + 2, Blocks.stonebrick, 3, 2);
			world.setBlock(xCoord + houseLoc1 + 5, yCoord + height3, zCoord + houseLoc2 + 3, (Block) Blocks.cauldron, 0, 2);
			world.setBlock(xCoord + houseLoc1 + 5, yCoord + height3, zCoord + houseLoc2 + 4, Blocks.stonebrick, 3, 2);
		}
		int boss = rand.nextInt(2);
		if (boss == 0)
		{
			EntityGoblinMage goblinmage = new EntityGoblinMage(world);
			goblinmage.setLocationAndAngles((double) (xCoord + houseLoc1 + 3), (double) (yCoord + 1), (double) (zCoord + houseLoc2 - 1), world.rand.nextFloat() * 360.0f, 0.0f);
			goblinmage.setPosition((double) (xCoord + houseLoc1 + 3), (double) (yCoord + 1), (double) (zCoord + houseLoc2 - 1));
			world.spawnEntityInWorld((Entity) goblinmage);
		}
		else
		{
			EntityGoblinLord goblinlord = new EntityGoblinLord(world);
			goblinlord.setLocationAndAngles((double) (xCoord + houseLoc1 + 3), (double) (yCoord + 1), (double) (zCoord + houseLoc2 - 1), world.rand.nextFloat() * 360.0f, 0.0f);
			goblinlord.setPosition((double) (xCoord + houseLoc1 + 3), (double) (yCoord + 1), (double) (zCoord + houseLoc2 - 1));
			world.spawnEntityInWorld((Entity) goblinlord);
		}
		generateTrees(world, rand, xCoord + 11, yCoord, zCoord + 16, 15 + rand.nextInt(15));
		generatePoles(world, rand, xCoord + 11, yCoord, zCoord + 16, rand.nextInt(10));
		if (rand.nextInt(2) == 0)
		{
			generateWalls(world, rand, xCoord + 11, yCoord, zCoord + 16, 4 + rand.nextInt(3));
		}
		return true;
	}

	public boolean generate(World world, Random rand, int i, int j, int k)
	{
		if (world.getBlock(i, j, k) == Blocks.grass && world.getBlock(i + 21, j, k + 30) == Blocks.grass && canGenerate(world, rand, i, j, k) && world.getBlock(i + 21, j + 10, k + 30) == Blocks.air && world.getBlock(i, j + 10, k) == Blocks.air)
		{
			for (int a = -15; a <= 45; ++a)
			{
				for (int b = -15; b <= 55; ++b)
				{
					if (world.getBlock(i + a, j + 3, k + b) == Blocks.planks || world.getBlock(i + a, j + 3, k + b) == Blocks.cobblestone || world.getBlock(i + a, j + 3, k + b) == Blocks.stonebrick)
					{
						return false;
					}
				}
			}
			generateVillage(world, rand, i, j, k);
		}
		return false;
	}

	public void generateRandomCornerHouse(World world, Random rand, int i, int j, int k, int preference)
	{
		int choice = rand.nextInt(4);
		if (rand.nextInt(3) == 0)
		{
			choice = preference;
		}
		switch (choice)
		{
		case 0:
		{
			generateBottomRightCornerHouse(world, rand, i, j, k);
			for (int width = 0; width < 1; ++width)
			{
				for (int length = 0; length < 1; ++length)
				{
					if (world.getBlock(i + width + 5, j + 1, k + length + 5) != Blocks.log)
					{
						world.setBlock(i + width + 5, j + 1, k + length + 5, Blocks.air, 0, 2);
					}
				}
			}
			break;
		}
		case 1:
		{
			generateBottomLeftCornerHouse(world, rand, i, j, k);
			for (int width = 0; width < 1; ++width)
			{
				for (int length = 0; length < 1; ++length)
				{
					if (world.getBlock(i + width + 0, j + 1, k + length + 5) != Blocks.log)
					{
						world.setBlock(i + width + 0, j + 1, k + length + 5, Blocks.air, 0, 2);
					}
				}
			}
			break;
		}
		case 2:
		{
			generateTopRightCornerHouse(world, rand, i, j, k);
			for (int width = 0; width < 1; ++width)
			{
				for (int length = 0; length < 1; ++length)
				{
					if (world.getBlock(i + width + 5, j + 1, k + length + 0) != Blocks.log)
					{
						world.setBlock(i + width + 5, j + 1, k + length + 0, Blocks.air, 0, 2);
					}
				}
			}
			break;
		}
		case 3:
		{
			generateTopLeftCornerHouse(world, rand, i, j, k);
			for (int width = 0; width < 1; ++width)
			{
				for (int length = 0; length < 1; ++length)
				{
					if (world.getBlock(i + width + 0, j + 1, k + length + 0) != Blocks.log)
					{
						world.setBlock(i + width + 0, j + 1, k + length + 0, Blocks.air, 0, 2);
					}
				}
			}
			break;
		}
		}
	}

	public boolean generateWallSouth(World world, Random rand, int i, int j, int k)
	{
		int limit = 17 - rand.nextInt(7);
		int rangeLeft = 0;
		int rangeRight = 0;
		for (int c = 0; c < limit / 2; ++c)
		{
			Block block = world.getBlock(i + c, j, k);
			if (block != Blocks.air && !block.isReplaceable((IBlockAccess) world, i + c, j, k))
			{
				break;
			}
			if (world.getBlock(i + c, j - 1, k) != Blocks.grass)
			{
				break;
			}
			block = world.getBlock(i + c, j, k - 1);
			if (block != Blocks.air && !block.isReplaceable((IBlockAccess) world, i + c, j, k - 1))
			{
				break;
			}
			if (world.getBlock(i + c, j - 1, k - 1) != Blocks.grass)
			{
				break;
			}
			++rangeRight;
		}
		for (int c = 0; c > -limit / 2; --c)
		{
			Block block = world.getBlock(i + c, j, k);
			if (block != Blocks.air && !block.isReplaceable((IBlockAccess) world, i + c, j, k))
			{
				break;
			}
			if (world.getBlock(i + c, j - 1, k) != Blocks.grass)
			{
				break;
			}
			block = world.getBlock(i + c, j, k - 1);
			if (block != Blocks.air && !block.isReplaceable((IBlockAccess) world, i + c, j, k - 1))
			{
				break;
			}
			if (world.getBlock(i + c, j - 1, k - 1) != Blocks.grass)
			{
				break;
			}
			--rangeLeft;
		}
		for (int i2 = rangeLeft; i2 < rangeRight; ++i2)
		{
			for (int k2 = rangeLeft; k2 < rangeRight; ++k2)
			{
				for (int j2 = rangeLeft; j2 < rangeRight; ++j2)
				{
					if (world.getBlock(i + i2, j + j2, k + k2) == Blocks.planks || world.getBlock(i + i2, j + j2, k + k2) == Blocks.oak_stairs)
					{
						return false;
					}
				}
			}
		}
		++rangeLeft;
		if (rangeRight - rangeLeft > 6)
		{
			for (int x = rangeLeft; x < rangeRight; ++x)
			{
				if (x == rangeLeft)
				{
					world.setBlock(i + x, j, k, Blocks.planks);
					world.setBlock(i + x, j + 1, k, Blocks.oak_stairs, 0, 3);
					world.setBlock(i + x, j, k - 1, Blocks.oak_stairs, 0, 3);
				}
				else if (x == rangeRight - 1)
				{
					world.setBlock(i + x, j, k, Blocks.planks);
					world.setBlock(i + x, j + 1, k, Blocks.oak_stairs, 1, 3);
					world.setBlock(i + x, j, k - 1, Blocks.oak_stairs, 1, 3);
				}
				else if (x == rangeLeft + 1)
				{
					world.setBlock(i + x, j, k, Blocks.planks);
					world.setBlock(i + x, j + 1, k, Blocks.planks);
					world.setBlock(i + x, j + 2, k, Blocks.oak_stairs, 1, 3);
					world.setBlock(i + x, j + 1, k - 1, Blocks.oak_stairs, 0, 3);
					world.setBlock(i + x, j, k - 1, Blocks.planks);
				}
				else if (x == rangeRight - 2)
				{
					world.setBlock(i + x, j, k, Blocks.planks);
					world.setBlock(i + x, j + 1, k, Blocks.planks);
					world.setBlock(i + x, j + 2, k, Blocks.oak_stairs, 1, 3);
					world.setBlock(i + x, j + 1, k - 1, Blocks.oak_stairs, 1, 3);
					world.setBlock(i + x, j, k - 1, Blocks.planks);
				}
				else
				{
					world.setBlock(i + x, j, k, Blocks.planks);
					world.setBlock(i + x, j + 1, k, Blocks.planks);
					world.setBlock(i + x, j + 1, k - 1, Blocks.oak_stairs, 6, 3);
					world.setBlock(i + x, j + 2, k, Blocks.oak_stairs, 1, 3);
					world.setBlock(i + x, j + 2, k - 1, Blocks.air);
					world.setBlock(i + x, j + 3, k - 1, Blocks.air);
					world.setBlock(i + x, j + 3, k, Blocks.air);
					if (rand.nextInt(2) == 0 && world.getBlock(i + x + 1, j + 2, k - 1) != Blocks.wooden_slab && world.getBlock(i + x - 1, j + 2, k - 1) != Blocks.wooden_slab)
					{
						world.setBlock(i + x, j + 2, k - 1, (Block) Blocks.wooden_slab);
						EntityGoblinRangerGuard guard = new EntityGoblinRangerGuard(world);
						guard.setLocationAndAngles(i + x + 0.5, (double) (j + 3), k - 1 + 0.5, world.rand.nextFloat() * 360.0f, 0.0f);
						guard.setPosition(i + x + 0.5, (double) (j + 3), k - 1 + 0.5);
						world.spawnEntityInWorld((Entity) guard);
					}
				}
			}
			return true;
		}
		return false;
	}

	public boolean generateWallNorth(World world, Random rand, int i, int j, int k)
	{
		int limit = 17 - rand.nextInt(7);
		int rangeLeft = 0;
		int rangeRight = 0;
		for (int c = 0; c < limit / 2; ++c)
		{
			Block block = world.getBlock(i + c, j, k);
			if (block != Blocks.air && !block.isReplaceable((IBlockAccess) world, i + c, j, k))
			{
				break;
			}
			if (world.getBlock(i + c, j - 1, k) != Blocks.grass)
			{
				break;
			}
			block = world.getBlock(i + c, j, k + 1);
			if (block != Blocks.air && !block.isReplaceable((IBlockAccess) world, i + c, j, k + 1))
			{
				break;
			}
			if (world.getBlock(i + c, j - 1, k + 1) != Blocks.grass)
			{
				break;
			}
			++rangeRight;
		}
		for (int c = 0; c > -limit / 2; --c)
		{
			Block block = world.getBlock(i + c, j, k);
			if (block != Blocks.air && !block.isReplaceable((IBlockAccess) world, i + c, j, k))
			{
				break;
			}
			if (world.getBlock(i + c, j - 1, k) != Blocks.grass)
			{
				break;
			}
			block = world.getBlock(i + c, j, k + 1);
			if (block != Blocks.air && !block.isReplaceable((IBlockAccess) world, i + c, j, k + 1))
			{
				break;
			}
			if (world.getBlock(i + c, j - 1, k + 1) != Blocks.grass)
			{
				break;
			}
			--rangeLeft;
		}
		for (int i2 = rangeLeft; i2 < rangeRight; ++i2)
		{
			for (int k2 = rangeLeft; k2 < rangeRight; ++k2)
			{
				for (int j2 = rangeLeft; j2 < rangeRight; ++j2)
				{
					if (world.getBlock(i + i2, j + j2, k + k2) == Blocks.planks || world.getBlock(i + i2, j + j2, k + k2) == Blocks.oak_stairs)
					{
						return false;
					}
				}
			}
		}
		++rangeLeft;
		if (rangeRight - rangeLeft > 6)
		{
			for (int x = rangeLeft; x < rangeRight; ++x)
			{
				if (x == rangeLeft)
				{
					world.setBlock(i + x, j, k, Blocks.planks);
					world.setBlock(i + x, j + 1, k, Blocks.oak_stairs, 0, 3);
					world.setBlock(i + x, j, k + 1, Blocks.oak_stairs, 0, 3);
				}
				else if (x == rangeRight - 1)
				{
					world.setBlock(i + x, j, k, Blocks.planks);
					world.setBlock(i + x, j + 1, k, Blocks.oak_stairs, 1, 3);
					world.setBlock(i + x, j, k + 1, Blocks.oak_stairs, 1, 3);
				}
				else if (x == rangeLeft + 1)
				{
					world.setBlock(i + x, j, k, Blocks.planks);
					world.setBlock(i + x, j + 1, k, Blocks.planks);
					world.setBlock(i + x, j + 2, k, Blocks.oak_stairs, 1, 3);
					world.setBlock(i + x, j + 1, k + 1, Blocks.oak_stairs, 0, 3);
					world.setBlock(i + x, j, k + 1, Blocks.planks);
				}
				else if (x == rangeRight - 2)
				{
					world.setBlock(i + x, j, k, Blocks.planks);
					world.setBlock(i + x, j + 1, k, Blocks.planks);
					world.setBlock(i + x, j + 2, k, Blocks.oak_stairs, 1, 3);
					world.setBlock(i + x, j + 1, k + 1, Blocks.oak_stairs, 1, 3);
					world.setBlock(i + x, j, k + 1, Blocks.planks);
				}
				else
				{
					world.setBlock(i + x, j, k, Blocks.planks);
					world.setBlock(i + x, j + 1, k, Blocks.planks);
					world.setBlock(i + x, j + 1, k + 1, Blocks.oak_stairs, 7, 3);
					world.setBlock(i + x, j + 2, k, Blocks.oak_stairs, 1, 3);
					world.setBlock(i + x, j + 2, k + 1, Blocks.air);
					world.setBlock(i + x, j + 3, k + 1, Blocks.air);
					world.setBlock(i + x, j + 3, k, Blocks.air);
					if (rand.nextInt(2) == 0 && world.getBlock(i + x + 1, j + 2, k + 1) != Blocks.wooden_slab && world.getBlock(i + x - 1, j + 2, k + 1) != Blocks.wooden_slab)
					{
						world.setBlock(i + x, j + 2, k + 1, (Block) Blocks.wooden_slab);
						EntityGoblinRangerGuard guard = new EntityGoblinRangerGuard(world);
						guard.setLocationAndAngles(i + x + 0.5, (double) (j + 3), k + 1 + 0.5, world.rand.nextFloat() * 360.0f, 0.0f);
						guard.setPosition(i + x + 0.5, (double) (j + 3), k + 1 + 0.5);
						world.spawnEntityInWorld((Entity) guard);
					}
				}
			}
			return true;
		}
		return false;
	}

	public boolean generateWallWest(World world, Random rand, int i, int j, int k)
	{
		int limit = 17 - rand.nextInt(7);
		int rangeLeft = 0;
		int rangeRight = 0;
		for (int c = 0; c < limit / 2; ++c)
		{
			Block block = world.getBlock(i, j, k + c);
			if (block != Blocks.air && !block.isReplaceable((IBlockAccess) world, i, j, k + c))
			{
				break;
			}
			if (world.getBlock(i, j - 1, k + c) != Blocks.grass)
			{
				break;
			}
			block = world.getBlock(i - 1, j, k + c);
			if (block != Blocks.air && !block.isReplaceable((IBlockAccess) world, i - 1, j, k + c))
			{
				break;
			}
			if (world.getBlock(i - 1, j - 1, k + c) != Blocks.grass)
			{
				break;
			}
			++rangeRight;
		}
		for (int c = 0; c > -limit / 2; --c)
		{
			Block block = world.getBlock(i, j, k + c);
			if (block != Blocks.air && !block.isReplaceable((IBlockAccess) world, i, j, k + c))
			{
				break;
			}
			if (world.getBlock(i, j - 1, k + c) != Blocks.grass)
			{
				break;
			}
			block = world.getBlock(i - 1, j, k + c);
			if (block != Blocks.air && !block.isReplaceable((IBlockAccess) world, i - 1, j, k + c))
			{
				break;
			}
			if (world.getBlock(i - 1, j - 1, k + c) != Blocks.grass)
			{
				break;
			}
			--rangeLeft;
		}
		for (int i2 = rangeLeft; i2 < rangeRight; ++i2)
		{
			for (int k2 = rangeLeft; k2 < rangeRight; ++k2)
			{
				for (int j2 = rangeLeft; j2 < rangeRight; ++j2)
				{
					if (world.getBlock(i + i2, j + j2, k + k2) == Blocks.planks || world.getBlock(i + i2, j + j2, k + k2) == Blocks.oak_stairs)
					{
						return false;
					}
				}
			}
		}
		++rangeLeft;
		if (rangeRight - rangeLeft > 6)
		{
			for (int x = rangeLeft; x < rangeRight; ++x)
			{
				if (x == rangeLeft)
				{
					world.setBlock(i, j, k + x, Blocks.planks);
					world.setBlock(i, j + 1, k + x, Blocks.oak_stairs, 2, 3);
					world.setBlock(i - 1, j, k + x, Blocks.oak_stairs, 2, 3);
				}
				else if (x == rangeRight - 1)
				{
					world.setBlock(i, j, k + x, Blocks.planks);
					world.setBlock(i, j + 1, k + x, Blocks.oak_stairs, 3, 3);
					world.setBlock(i - 1, j, k + x, Blocks.oak_stairs, 3, 3);
				}
				else if (x == rangeLeft + 1)
				{
					world.setBlock(i, j, k + x, Blocks.planks);
					world.setBlock(i, j + 1, k + x, Blocks.planks);
					world.setBlock(i, j + 2, k + x, Blocks.oak_stairs, 3, 3);
					world.setBlock(i - 1, j + 1, k + x, Blocks.oak_stairs, 2, 3);
					world.setBlock(i - 1, j, k + x, Blocks.planks);
				}
				else if (x == rangeRight - 2)
				{
					world.setBlock(i, j, k + x, Blocks.planks);
					world.setBlock(i, j + 1, k + x, Blocks.planks);
					world.setBlock(i, j + 2, k + x, Blocks.oak_stairs, 3, 3);
					world.setBlock(i - 1, j + 1, k + x, Blocks.oak_stairs, 3, 3);
					world.setBlock(i - 1, j, k + x, Blocks.planks);
				}
				else
				{
					world.setBlock(i, j, k + x, Blocks.planks);
					world.setBlock(i, j + 1, k + x, Blocks.planks);
					world.setBlock(i - 1, j + 1, k + x, Blocks.oak_stairs, 4, 3);
					world.setBlock(i, j + 2, k + x, Blocks.oak_stairs, 3, 3);
					world.setBlock(i - 1, j + 2, k + x, Blocks.air);
					world.setBlock(i - 1, j + 3, k + x, Blocks.air);
					world.setBlock(i, j + 3, k + x, Blocks.air);
					if (rand.nextInt(2) == 0 && world.getBlock(i - 1, j + 2, k + x + 1) != Blocks.wooden_slab && world.getBlock(i - 1, j + 2, k + x - 1) != Blocks.wooden_slab)
					{
						world.setBlock(i - 1, j + 2, k + x, (Block) Blocks.wooden_slab);
						EntityGoblinRangerGuard guard = new EntityGoblinRangerGuard(world);
						guard.setLocationAndAngles(i - 1 + 0.5, (double) (j + 3), k + x + 0.5, world.rand.nextFloat() * 360.0f, 0.0f);
						guard.setPosition(i - 1 + 0.5, (double) (j + 3), k + x + 0.5);
						world.spawnEntityInWorld((Entity) guard);
					}
				}
			}
			return true;
		}
		return false;
	}

	public boolean generateWallEast(World world, Random rand, int i, int j, int k)
	{
		int limit = 17 - rand.nextInt(7);
		int rangeLeft = 0;
		int rangeRight = 0;
		for (int c = 0; c < limit / 2; ++c)
		{
			Block block = world.getBlock(i, j, k + c);
			if (block != Blocks.air && !block.isReplaceable((IBlockAccess) world, i, j, k + c))
			{
				break;
			}
			if (world.getBlock(i, j - 1, k + c) != Blocks.grass)
			{
				break;
			}
			block = world.getBlock(i + 1, j, k + c);
			if (block != Blocks.air && !block.isReplaceable((IBlockAccess) world, i + 1, j, k + c))
			{
				break;
			}
			if (world.getBlock(i + 1, j - 1, k + c) != Blocks.grass)
			{
				break;
			}
			++rangeRight;
		}
		for (int c = 0; c > -limit / 2; --c)
		{
			Block block = world.getBlock(i, j, k + c);
			if (block != Blocks.air && !block.isReplaceable((IBlockAccess) world, i, j, k + c))
			{
				break;
			}
			if (world.getBlock(i, j - 1, k + c) != Blocks.grass)
			{
				break;
			}
			block = world.getBlock(i + 1, j, k + c);
			if (block != Blocks.air && !block.isReplaceable((IBlockAccess) world, i + 1, j, k + c))
			{
				break;
			}
			if (world.getBlock(i + 1, j - 1, k + c) != Blocks.grass)
			{
				break;
			}
			--rangeLeft;
		}
		for (int i2 = rangeLeft; i2 < rangeRight; ++i2)
		{
			for (int k2 = rangeLeft; k2 < rangeRight; ++k2)
			{
				for (int j2 = rangeLeft; j2 < rangeRight; ++j2)
				{
					if (world.getBlock(i + i2, j + j2, k + k2) == Blocks.planks || world.getBlock(i + i2, j + j2, k + k2) == Blocks.oak_stairs)
					{
						return false;
					}
				}
			}
		}
		++rangeLeft;
		if (rangeRight - rangeLeft > 6)
		{
			for (int x = rangeLeft; x < rangeRight; ++x)
			{
				if (x == rangeLeft)
				{
					world.setBlock(i, j, k + x, Blocks.planks);
					world.setBlock(i, j + 1, k + x, Blocks.oak_stairs, 2, 3);
					world.setBlock(i + 1, j, k + x, Blocks.oak_stairs, 2, 3);
				}
				else if (x == rangeRight - 1)
				{
					world.setBlock(i, j, k + x, Blocks.planks);
					world.setBlock(i, j + 1, k + x, Blocks.oak_stairs, 3, 3);
					world.setBlock(i + 1, j, k + x, Blocks.oak_stairs, 3, 3);
				}
				else if (x == rangeLeft + 1)
				{
					world.setBlock(i, j, k + x, Blocks.planks);
					world.setBlock(i, j + 1, k + x, Blocks.planks);
					world.setBlock(i, j + 2, k + x, Blocks.oak_stairs, 3, 3);
					world.setBlock(i + 1, j + 1, k + x, Blocks.oak_stairs, 2, 3);
					world.setBlock(i + 1, j, k + x, Blocks.planks);
				}
				else if (x == rangeRight - 2)
				{
					world.setBlock(i, j, k + x, Blocks.planks);
					world.setBlock(i, j + 1, k + x, Blocks.planks);
					world.setBlock(i, j + 2, k + x, Blocks.oak_stairs, 3, 3);
					world.setBlock(i + 1, j + 1, k + x, Blocks.oak_stairs, 3, 3);
					world.setBlock(i + 1, j, k + x, Blocks.planks);
				}
				else
				{
					world.setBlock(i, j, k + x, Blocks.planks);
					world.setBlock(i, j + 1, k + x, Blocks.planks);
					world.setBlock(i + 1, j + 1, k + x, Blocks.oak_stairs, 5, 3);
					world.setBlock(i, j + 2, k + x, Blocks.oak_stairs, 3, 3);
					world.setBlock(i + 1, j + 2, k + x, Blocks.air);
					world.setBlock(i + 1, j + 3, k + x, Blocks.air);
					world.setBlock(i, j + 3, k + x, Blocks.air);
					if (rand.nextInt(2) == 0 && world.getBlock(i + 1, j + 2, k + x + 1) != Blocks.wooden_slab && world.getBlock(i + 1, j + 2, k + x - 1) != Blocks.wooden_slab)
					{
						world.setBlock(i + 1, j + 2, k + x, (Block) Blocks.wooden_slab);
						EntityGoblinRangerGuard guard = new EntityGoblinRangerGuard(world);
						guard.setLocationAndAngles(i + 1 + 0.5, (double) (j + 3), k + x + 0.5, world.rand.nextFloat() * 360.0f, 0.0f);
						guard.setPosition(i + 1 + 0.5, (double) (j + 3), k + x + 0.5);
						world.spawnEntityInWorld((Entity) guard);
					}
				}
			}
			return true;
		}
		return false;
	}

	public void generateTrees(World world, Random rand, int i, int j, int k, int numTrees)
	{
		for (int x = 0; x < numTrees; ++x)
		{
			int i2;
			int k2;
			do
			{
				i2 = rand.nextInt(30) - rand.nextInt(30);
				k2 = rand.nextInt(30) - rand.nextInt(30);
			}
			while ((i2 < 8 && i2 > -8) || (k2 < 8 && k2 > -8));
			int j2 = -10;
			while (j2 < 10)
			{
				if (world.getBlock(i2 + i, j + j2, k2 + k) == Blocks.grass && world.getBlock(i2 + i, j + j2 + 1, k2 + k) == Blocks.air)
				{
					if (rand.nextInt(2) == 0)
					{
						new WorldGenTrees(true).generate(world, rand, i + i2, j + j2 + 1, k + k2);
						break;
					}
					world.setBlock(i + i2, j + j2 + 1, k + k2, Blocks.log);
					break;
				}
				else
				{
					++j2;
				}
			}
		}
	}

	public void eliminateTree(World world, Random rand, int i, int j, int k)
	{
		for (int i2 = -3; i2 <= 3; ++i2)
		{
			for (int k2 = -3; k2 <= 3; ++k2)
			{
				for (int j2 = 0; j2 <= 15; ++j2)
				{
					if (world.getBlock(i2 + i, j + j2, k2 + k) == Blocks.log || world.getBlock(i2 + i, j + j2, k2 + k) == Blocks.leaves)
					{
						world.setBlock(i + i2, j + j2, k + k2, Blocks.air);
					}
				}
			}
		}
	}

	public void generatePoles(World world, Random rand, int i, int j, int k, int numPoles)
	{
		for (int x = 0; x < numPoles; ++x)
		{
			int i2;
			int k2;
			do
			{
				i2 = rand.nextInt(20) - rand.nextInt(20);
				k2 = rand.nextInt(20) - rand.nextInt(20);
			}
			while ((i2 < 8 && i2 > -8) || (k2 < 8 && k2 > -8));
			for (int j2 = -10; j2 < 10; ++j2)
			{
				if (world.getBlock(i2 + i, j + j2, k2 + k) == Blocks.grass && world.getBlock(i2 + i, j + j2 + 1, k2 + k) == Blocks.air)
				{
					world.setBlock(i + i2, j + j2 + 1, k + k2, Blocks.fence);
					world.setBlock(i + i2, j + j2 + 2, k + k2, Blocks.skull, 1, 3);
					break;
				}
			}
		}
	}

	public void generateWalls(World world, Random rand, int i, int j, int k, int numWalls)
	{
		for (int x = 0; x < numWalls; ++x)
		{
			int generateCount = 0;
			int i2 = 0;
			int k2 = 0;
			do
			{
				if (rand.nextInt(2) == 0)
				{
					i2 = 14 + rand.nextInt(5);
					if (rand.nextInt(2) == 0)
					{
						i2 *= -1;
					}
					k2 = rand.nextInt(13) - rand.nextInt(13);
				}
				if (rand.nextInt(2) == 0)
				{
					k2 = 16 + rand.nextInt(5);
					if (rand.nextInt(2) == 0)
					{
						k2 *= -1;
					}
					i2 = rand.nextInt(13) - rand.nextInt(13);
				}
				for (int j2 = -10; j2 < 10; ++j2)
				{
					Block block = world.getBlock(i2 + i, j + j2 + 1, k2 + k);
					if (block == Blocks.air || block.isReplaceable((IBlockAccess) world, i2 + i, j + j2 + 1, k2 + k))
					{
						if (world.getBlock(i2 + i, j + j2, k2 + k) == Blocks.grass)
						{
							int i3 = Math.abs(i2);
							int k3 = Math.abs(k2);
							if (k2 < 0)
							{
								if (k3 > i3)
								{
									if (generateWallNorth(world, rand, i + i2, j + j2 + 1, k + k2))
									{
										generateCount = 1000;
										break;
									}
									++generateCount;
								}
								else if (i2 < 0)
								{
									if (generateWallEast(world, rand, i + i2, j + j2 + 1, k + k2))
									{
										generateCount = 1000;
										break;
									}
									++generateCount;
								}
								else
								{
									if (generateWallWest(world, rand, i + i2, j + j2 + 1, k + k2))
									{
										generateCount = 1000;
										break;
									}
									++generateCount;
								}
							}
							else if (k2 >= 0)
							{
								if (k3 > i3)
								{
									if (generateWallSouth(world, rand, i + i2, j + j2 + 1, k + k2))
									{
										generateCount = 1000;
										break;
									}
									++generateCount;
								}
								else if (i2 < 0)
								{
									if (generateWallEast(world, rand, i + i2, j + j2 + 1, k + k2))
									{
										generateCount = 1000;
										break;
									}
									++generateCount;
								}
								else
								{
									if (generateWallWest(world, rand, i + i2, j + j2 + 1, k + k2))
									{
										generateCount = 1000;
										break;
									}
									++generateCount;
								}
							}
						}
					}
				}
			}
			while (generateCount < 800);
		}
	}

	public boolean canGenerate(World world, Random rand, int i, int j, int k)
	{
		int countGrass = 0;
		for (int i2 = 0; i2 <= 20; ++i2)
		{
			for (int k2 = 0; k2 <= 30; ++k2)
			{
				for (int j2 = -1; j2 <= 1; ++j2)
				{
					if (world.getBlock(i + i2, j + j2, k + k2) == Blocks.grass)
					{
						if (j2 == 1)
						{
							++countGrass;
						}
						else
						{
							countGrass += 2;
						}
					}
				}
			}
		}
		return countGrass > 1100;
	}
}
