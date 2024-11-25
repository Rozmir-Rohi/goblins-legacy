
package goblin.world.gen;

import net.minecraft.world.gen.feature.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import java.util.*;

import goblin.Goblins;
import goblin.entity.EntityDirewolf;
import net.minecraft.block.*;
import net.minecraft.tileentity.*;
import net.minecraftforge.common.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.*;
import net.minecraft.init.*;
import net.minecraft.item.*;

public class GoblinsWorldGen extends WorldGenerator {
	public static WeightedRandomChestContent[] goblinStandardChest;
	public static WeightedRandomChestContent[] goblinRiderChest;
	public static WeightedRandomChestContent[] goblinMinerChest;

	public boolean generate(World world, Random rand, int xCoord, int yCoord, int zCoord)
	{
		return false;
	}

	protected void generateSkullPole(World world, Random rand, int xCoord, int yCoord, int zCoord)
	{
		for (int maxHeight = 2 + rand.nextInt(2), height = 1; height <= maxHeight; ++height)
		{
			if (height == maxHeight)
			{
				world.setBlock(xCoord, yCoord + height, zCoord, Blocks.skull, 0, 2);
			}
			else
			{
				world.setBlock(xCoord, yCoord + height, zCoord, Blocks.fence, 0, 2);
			}
		}
	}

	protected void generateCornerHouse(World world, Random rand, int xCoord, int yCoord, int zCoord)
	{
		for (int width1 = 0; width1 <= 5; ++width1)
		{
			for (int width2 = 0; width2 <= 5; ++width2)
			{
				for (int height = 0; height <= 4; ++height)
				{
					if (height == 0)
					{
						world.setBlock(xCoord + width1, yCoord - height, zCoord + width2, (Block) Blocks.grass, 0, 2);
					}
					else if (height > 0 && height < 3)
					{
						world.setBlock(xCoord + width1, yCoord - height, zCoord + width2, Blocks.dirt, 0, 2);
					}
					else
					{
						world.setBlock(xCoord + width1, yCoord - height, zCoord + width2, Blocks.stone, 0, 2);
					}
				}
			}
		}
		for (int width1 = 1; width1 <= 5; ++width1)
		{
			for (int width2 = 1; width2 <= 5; ++width2)
			{
				for (int height = 1; height <= 2; ++height)
				{
					world.setBlock(xCoord + width1, yCoord + height, zCoord + width2, Blocks.air, 0, 2);
				}
			}
		}
		for (int height2 = 1; height2 <= 2; ++height2)
		{
			world.setBlock(xCoord + 0, yCoord + height2, zCoord + 3, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 0, yCoord + height2, zCoord + 2, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 5, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 5, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 5, yCoord + height2, zCoord + 3, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 5, yCoord + height2, zCoord + 2, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 4, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 1, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 1, yCoord + height2, zCoord + 4, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 1, yCoord + height2, zCoord + 1, Blocks.planks, 0, 2);
		}
		world.setBlock(xCoord + 1, yCoord + 3, zCoord + 3, Blocks.planks, 0, 2);
		world.setBlock(xCoord + 1, yCoord + 3, zCoord + 2, Blocks.planks, 0, 2);
		world.setBlock(xCoord + 2, yCoord + 3, zCoord + 1, Blocks.planks, 0, 2);
		world.setBlock(xCoord + 2, yCoord + 3, zCoord + 4, Blocks.planks, 0, 2);
		world.setBlock(xCoord + 3, yCoord + 3, zCoord + 1, Blocks.planks, 0, 2);
		world.setBlock(xCoord + 3, yCoord + 3, zCoord + 4, Blocks.planks, 0, 2);
		world.setBlock(xCoord + 4, yCoord + 3, zCoord + 3, Blocks.planks, 0, 2);
		world.setBlock(xCoord + 4, yCoord + 3, zCoord + 2, Blocks.planks, 0, 2);
		world.setBlock(xCoord + 1, yCoord, zCoord + 3, Blocks.cobblestone, 0, 2);
		world.setBlock(xCoord + 1, yCoord, zCoord + 2, Blocks.cobblestone, 0, 2);
		world.setBlock(xCoord + 2, yCoord, zCoord + 1, Blocks.cobblestone, 0, 2);
		world.setBlock(xCoord + 2, yCoord, zCoord + 4, Blocks.cobblestone, 0, 2);
		world.setBlock(xCoord + 3, yCoord, zCoord + 1, Blocks.cobblestone, 0, 2);
		world.setBlock(xCoord + 3, yCoord, zCoord + 4, Blocks.cobblestone, 0, 2);
		world.setBlock(xCoord + 4, yCoord, zCoord + 3, Blocks.cobblestone, 0, 2);
		world.setBlock(xCoord + 4, yCoord, zCoord + 2, Blocks.cobblestone, 0, 2);
		world.setBlock(xCoord + 1, yCoord + 1, zCoord + 3, Blocks.air, 0, 2);
		world.setBlock(xCoord + 1, yCoord + 1, zCoord + 2, Blocks.air, 0, 2);
		world.setBlock(xCoord + 2, yCoord + 1, zCoord + 1, Blocks.air, 0, 2);
		world.setBlock(xCoord + 2, yCoord + 1, zCoord + 4, Blocks.air, 0, 2);
		world.setBlock(xCoord + 3, yCoord + 1, zCoord + 1, Blocks.air, 0, 2);
		world.setBlock(xCoord + 3, yCoord + 1, zCoord + 4, Blocks.air, 0, 2);
		world.setBlock(xCoord + 4, yCoord + 1, zCoord + 3, Blocks.air, 0, 2);
		world.setBlock(xCoord + 4, yCoord + 1, zCoord + 2, Blocks.air, 0, 2);
		world.setBlock(xCoord + 3, yCoord + 4, zCoord + 3, (Block) Blocks.wooden_slab, 0, 2);
		world.setBlock(xCoord + 3, yCoord + 4, zCoord + 2, (Block) Blocks.wooden_slab, 0, 2);
		world.setBlock(xCoord + 2, yCoord + 4, zCoord + 3, (Block) Blocks.wooden_slab, 0, 2);
		world.setBlock(xCoord + 2, yCoord + 4, zCoord + 2, (Block) Blocks.wooden_slab, 0, 2);
		world.setBlock(xCoord + 3, yCoord, zCoord + 3, Blocks.cobblestone, 0, 2);
		world.setBlock(xCoord + 3, yCoord, zCoord + 2, Blocks.cobblestone, 0, 2);
		world.setBlock(xCoord + 2, yCoord, zCoord + 3, Blocks.cobblestone, 0, 2);
		world.setBlock(xCoord + 2, yCoord, zCoord + 2, Blocks.cobblestone, 0, 2);
		world.setBlock(xCoord + 3, yCoord + 1, zCoord + 3, Blocks.air, 0, 2);
		world.setBlock(xCoord + 3, yCoord + 1, zCoord + 2, Blocks.air, 0, 2);
		world.setBlock(xCoord + 2, yCoord + 1, zCoord + 3, Blocks.air, 0, 2);
		world.setBlock(xCoord + 2, yCoord + 1, zCoord + 2, Blocks.air, 0, 2);
	}

	protected void generateTopLeftCornerHouse(World world, Random rand, int xCoord, int yCoord, int zCoord)
	{
		generateCornerHouse(world, rand, xCoord, yCoord, zCoord);
		if (rand.nextInt(4) == 1)
		{
			world.setBlock(xCoord + 3, yCoord + 3, zCoord + 3, Blocks.web, 0, 2);
		}
		if (rand.nextInt(5) == 0)
		{
			world.setBlock(xCoord + 3, yCoord + 3, zCoord + 2, Blocks.web, 0, 2);
		}
		if (rand.nextInt(4) == 0)
		{
			world.setBlock(xCoord + 2, yCoord + 3, zCoord + 3, Blocks.web, 0, 2);
		}
		if (rand.nextInt(5) == 0)
		{
			world.setBlock(xCoord + 2, yCoord + 3, zCoord + 2, Blocks.web, 0, 2);
		}
		world.setBlock(xCoord + 1, yCoord + 3, zCoord + 1, Blocks.planks, 0, 2);
		world.setBlock(xCoord + 1, yCoord + 2, zCoord + 1, Blocks.air, 0, 2);
		world.setBlock(xCoord + 1, yCoord + 1, zCoord + 1, Blocks.air, 0, 2);
		world.setBlock(xCoord + 0, yCoord + 2, zCoord + 1, Blocks.torch, 0, 2);
		world.setBlock(xCoord + 1, yCoord + 2, zCoord + 0, Blocks.torch, 0, 2);
		world.setBlock(xCoord + 4, yCoord + 2, zCoord + 3, Blocks.torch, 0, 2);
		world.setBlock(xCoord + 3, yCoord + 2, zCoord + 4, Blocks.torch, 0, 2);
		world.setBlock(xCoord + 3, yCoord + 1, zCoord + 3, Blocks.fence, 0, 2);
		world.setBlock(xCoord + 3, yCoord + 2, zCoord + 3, Blocks.fence, 0, 2);
		world.setBlock(xCoord + 3, yCoord + 3, zCoord + 3, Blocks.fence, 0, 2);
		if (rand.nextInt(3) == 0)
		{
			world.setBlock(xCoord + 4, yCoord + 1, zCoord + 2, (Block) Blocks.chest, 0, 2);
			TileEntityChest tileentitychest = (TileEntityChest) world.getTileEntity(xCoord + 4, yCoord + 1, zCoord + 2);
			if (tileentitychest != null)
			{
				ChestGenHooks info = ChestGenHooks.getInfo("dungeonChest");
				WeightedRandomChestContent.generateChestContents(rand, GoblinsWorldGen.goblinStandardChest, (IInventory) tileentitychest, rand.nextInt(3) + 7);
			}
		}
		world.setBlock(xCoord + 2, yCoord, zCoord + 2, Goblins.MobGSpawner, 0, 2);
	}

	protected void generateBottomLeftCornerHouse(World world, Random rand, int xCoord, int yCoord, int zCoord)
	{
		generateCornerHouse(world, rand, xCoord, yCoord, zCoord);
		if (rand.nextInt(4) == 1)
		{
			world.setBlock(xCoord + 3, yCoord + 3, zCoord + 3, Blocks.web, 0, 2);
		}
		if (rand.nextInt(5) == 0)
		{
			world.setBlock(xCoord + 3, yCoord + 3, zCoord + 2, Blocks.web, 0, 2);
		}
		if (rand.nextInt(4) == 0)
		{
			world.setBlock(xCoord + 2, yCoord + 3, zCoord + 3, Blocks.web, 0, 2);
		}
		if (rand.nextInt(5) == 0)
		{
			world.setBlock(xCoord + 2, yCoord + 3, zCoord + 2, Blocks.web, 0, 2);
		}
		world.setBlock(xCoord + 1, yCoord + 3, zCoord + 4, Blocks.planks, 0, 2);
		world.setBlock(xCoord + 1, yCoord + 2, zCoord + 4, Blocks.air, 0, 2);
		world.setBlock(xCoord + 1, yCoord + 1, zCoord + 4, Blocks.air, 0, 2);
		world.setBlock(xCoord + 1, yCoord + 2, zCoord + 5, Blocks.torch, 0, 2);
		world.setBlock(xCoord + 0, yCoord + 2, zCoord + 4, Blocks.torch, 0, 2);
		world.setBlock(xCoord + 3, yCoord + 2, zCoord + 1, Blocks.torch, 0, 2);
		world.setBlock(xCoord + 4, yCoord + 2, zCoord + 2, Blocks.torch, 0, 2);
		world.setBlock(xCoord + 3, yCoord + 1, zCoord + 2, Blocks.fence, 0, 2);
		world.setBlock(xCoord + 3, yCoord + 2, zCoord + 2, Blocks.fence, 0, 2);
		world.setBlock(xCoord + 3, yCoord + 3, zCoord + 2, Blocks.fence, 0, 2);
		if (rand.nextInt(2) == 0)
		{
			world.setBlock(xCoord + 4, yCoord + 1, zCoord + 2, (Block) Blocks.chest, 0, 2);
			TileEntityChest tileentitychest = (TileEntityChest) world.getTileEntity(xCoord + 4, yCoord + 1, zCoord + 2);
			if (tileentitychest != null)
			{
				ChestGenHooks info = ChestGenHooks.getInfo("dungeonChest");
				WeightedRandomChestContent.generateChestContents(rand, GoblinsWorldGen.goblinStandardChest, (IInventory) tileentitychest, rand.nextInt(3) + 7);
			}
		}
		world.setBlock(xCoord + 2, yCoord, zCoord + 3, Goblins.MobGSpawner, 0, 2);
	}

	protected void generateTopRightCornerHouse(World world, Random rand, int xCoord, int yCoord, int zCoord)
	{
		generateCornerHouse(world, rand, xCoord, yCoord, zCoord);
		if (rand.nextInt(4) == 1)
		{
			world.setBlock(xCoord + 3, yCoord + 3, zCoord + 3, Blocks.web, 0, 2);
		}
		if (rand.nextInt(5) == 0)
		{
			world.setBlock(xCoord + 3, yCoord + 3, zCoord + 2, Blocks.web, 0, 2);
		}
		if (rand.nextInt(4) == 0)
		{
			world.setBlock(xCoord + 2, yCoord + 3, zCoord + 3, Blocks.web, 0, 2);
		}
		if (rand.nextInt(5) == 0)
		{
			world.setBlock(xCoord + 2, yCoord + 3, zCoord + 2, Blocks.web, 0, 2);
		}
		world.setBlock(xCoord + 4, yCoord + 3, zCoord + 1, Blocks.planks, 0, 2);
		world.setBlock(xCoord + 4, yCoord + 2, zCoord + 1, Blocks.air, 0, 2);
		world.setBlock(xCoord + 4, yCoord + 1, zCoord + 1, Blocks.air, 0, 2);
		world.setBlock(xCoord + 5, yCoord + 2, zCoord + 1, Blocks.torch, 0, 2);
		world.setBlock(xCoord + 4, yCoord + 2, zCoord + 0, Blocks.torch, 0, 2);
		world.setBlock(xCoord + 1, yCoord + 2, zCoord + 3, Blocks.torch, 0, 2);
		world.setBlock(xCoord + 2, yCoord + 2, zCoord + 4, Blocks.torch, 0, 2);
		world.setBlock(xCoord + 2, yCoord + 1, zCoord + 3, Blocks.fence, 0, 2);
		world.setBlock(xCoord + 2, yCoord + 2, zCoord + 3, Blocks.fence, 0, 2);
		world.setBlock(xCoord + 2, yCoord + 3, zCoord + 3, Blocks.fence, 0, 2);
		if (rand.nextInt(1) == 0)
		{
			world.setBlock(xCoord + 1, yCoord + 1, zCoord + 2, (Block) Blocks.chest, 0, 2);
			TileEntityChest tileentitychest = (TileEntityChest) world.getTileEntity(xCoord + 1, yCoord + 1, zCoord + 2);
			if (tileentitychest != null)
			{
				ChestGenHooks info = ChestGenHooks.getInfo("dungeonChest");
				WeightedRandomChestContent.generateChestContents(rand, GoblinsWorldGen.goblinStandardChest, (IInventory) tileentitychest, rand.nextInt(3) + 7);
			}
		}
		world.setBlock(xCoord + 3, yCoord, zCoord + 2, Goblins.MobGSpawner, 0, 2);
	}

	protected void generateBottomRightCornerHouse(World world, Random rand, int xCoord, int yCoord, int zCoord)
	{
		generateCornerHouse(world, rand, xCoord, yCoord, zCoord);
		if (rand.nextInt(4) == 1)
		{
			world.setBlock(xCoord + 3, yCoord + 3, zCoord + 3, Blocks.web, 0, 2);
		}
		if (rand.nextInt(5) == 0)
		{
			world.setBlock(xCoord + 3, yCoord + 3, zCoord + 2, Blocks.web, 0, 2);
		}
		if (rand.nextInt(4) == 0)
		{
			world.setBlock(xCoord + 2, yCoord + 3, zCoord + 3, Blocks.web, 0, 2);
		}
		if (rand.nextInt(5) == 0)
		{
			world.setBlock(xCoord + 2, yCoord + 3, zCoord + 2, Blocks.web, 0, 2);
		}
		world.setBlock(xCoord + 4, yCoord + 3, zCoord + 4, Blocks.planks, 0, 2);
		world.setBlock(xCoord + 4, yCoord + 2, zCoord + 4, Blocks.air, 0, 2);
		world.setBlock(xCoord + 4, yCoord + 1, zCoord + 4, Blocks.air, 0, 2);
		world.setBlock(xCoord + 5, yCoord + 2, zCoord + 4, Blocks.torch, 0, 2);
		world.setBlock(xCoord + 4, yCoord + 2, zCoord + 5, Blocks.torch, 0, 2);
		world.setBlock(xCoord + 2, yCoord + 2, zCoord + 1, Blocks.torch, 0, 2);
		world.setBlock(xCoord + 1, yCoord + 2, zCoord + 2, Blocks.torch, 0, 2);
		world.setBlock(xCoord + 2, yCoord + 1, zCoord + 2, Blocks.fence, 0, 2);
		world.setBlock(xCoord + 2, yCoord + 2, zCoord + 2, Blocks.fence, 0, 2);
		world.setBlock(xCoord + 2, yCoord + 3, zCoord + 2, Blocks.fence, 0, 2);
		if (rand.nextInt(1) == 0)
		{
			world.setBlock(xCoord + 1, yCoord + 1, zCoord + 3, (Block) Blocks.chest, 0, 2);
			TileEntityChest tileentitychest = (TileEntityChest) world.getTileEntity(xCoord + 1, yCoord + 1, zCoord + 3);
			if (tileentitychest != null)
			{
				ChestGenHooks info = ChestGenHooks.getInfo("dungeonChest");
				WeightedRandomChestContent.generateChestContents(rand, GoblinsWorldGen.goblinStandardChest, (IInventory) tileentitychest, rand.nextInt(3) + 7);
			}
		}
		world.setBlock(xCoord + 3, yCoord, zCoord + 3, Goblins.MobGSpawner, 0, 2);
	}

	protected void generateTopHouse(World world, Random rand, int xCoord, int yCoord, int zCoord)
	{
		for (int width1 = 0; width1 <= 6; ++width1)
		{
			for (int width2 = 0; width2 <= 5; ++width2)
			{
				for (int height = 0; height <= 4; ++height)
				{
					if (height == 0)
					{
						world.setBlock(xCoord + width1, yCoord - height, zCoord + width2, (Block) Blocks.grass, 0, 2);
					}
					else if (height > 0 && height < 3)
					{
						world.setBlock(xCoord + width1, yCoord - height, zCoord + width2, Blocks.dirt, 0, 2);
					}
					else
					{
						world.setBlock(xCoord + width1, yCoord - height, zCoord + width2, Blocks.stone, 0, 2);
					}
				}
			}
		}
		for (int width1 = 1; width1 <= 5; ++width1)
		{
			for (int width2 = 1; width2 <= 4; ++width2)
			{
				for (int height = 1; height <= 4; ++height)
				{
					world.setBlock(xCoord + width1, yCoord + height, zCoord + width2, Blocks.air, 0, 2);
				}
			}
		}
		for (int height2 = 1; height2 <= 2; ++height2)
		{
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 0, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 0, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 5, yCoord + height2, zCoord + 1, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 1, yCoord + height2, zCoord + 1, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 0, yCoord + height2, zCoord + 3, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 0, yCoord + height2, zCoord + 2, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 1, yCoord + height2, zCoord + 4, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 5, yCoord + height2, zCoord + 4, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 5, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 5, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 5, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 6, yCoord + height2, zCoord + 3, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 6, yCoord + height2, zCoord + 2, Blocks.planks, 0, 2);
		}
		for (int height2 = 3; height2 <= 3; ++height2)
		{
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 1, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 1, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 0, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 4, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 4, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 4, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 1, yCoord + height2, zCoord + 3, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 1, yCoord + height2, zCoord + 2, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 5, yCoord + height2, zCoord + 3, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 5, yCoord + height2, zCoord + 2, Blocks.planks, 0, 2);
		}
		for (int height2 = 4; height2 <= 4; ++height2)
		{
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 3, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 2, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 3, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 2, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 3, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 1, Blocks.planks, 0, 2);
		}
		for (int height2 = 5; height2 <= 5; ++height2)
		{
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 2, (Block) Blocks.wooden_slab, 0, 2);
		}
		for (int height2 = 0; height2 <= 0; ++height2)
		{
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 1, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 1, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 3, yCoord + 1, zCoord, Blocks.air, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 5, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 5, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 5, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 1, yCoord + height2, zCoord + 4, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 1, yCoord + height2, zCoord + 3, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 1, yCoord + height2, zCoord + 2, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 5, yCoord + height2, zCoord + 4, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 5, yCoord + height2, zCoord + 3, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 5, yCoord + height2, zCoord + 2, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 4, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 3, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 2, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 4, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 3, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 2, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 4, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 1, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 3, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 2, Blocks.cobblestone, 0, 2);
		}
		for (int height2 = 1; height2 <= 4; ++height2)
		{
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 2, Blocks.fence, 0, 2);
		}
		for (int height2 = 2; height2 <= 2; ++height2)
		{
			world.setBlock(xCoord + 2, yCoord + height2, zCoord - 1, Blocks.torch, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord - 1, Blocks.torch, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 4, Blocks.torch, 0, 2);
		}
		world.setBlock(xCoord + 3, yCoord, zCoord + 1, Goblins.MobGSpawner, 0, 2);
		world.setBlock(xCoord + 3, yCoord + 1, zCoord + 4, (Block) Blocks.chest, 0, 2);
		TileEntityChest tileentitychest = (TileEntityChest) world.getTileEntity(xCoord + 3, yCoord + 1, zCoord + 4);
		if (tileentitychest != null)
		{
			ChestGenHooks info = ChestGenHooks.getInfo("dungeonChest");
			WeightedRandomChestContent.generateChestContents(rand, GoblinsWorldGen.goblinStandardChest, (IInventory) tileentitychest, rand.nextInt(3) + 7);
		}
	}

	protected void generateRightMine(World world, Random rand, int xCoord, int yCoord, int zCoord)
	{
		for (int width1 = 0; width1 <= 7; ++width1)
		{
			for (int width2 = 0; width2 <= 9; ++width2)
			{
				for (int height = 0; height <= 4; ++height)
				{
					if (height == 0)
					{
						world.setBlock(xCoord + width1, yCoord - height, zCoord + width2, (Block) Blocks.grass, 0, 2);
					}
					else if (height > 0 && height < 3)
					{
						world.setBlock(xCoord + width1, yCoord - height, zCoord + width2, Blocks.dirt, 0, 2);
					}
					else
					{
						world.setBlock(xCoord + width1, yCoord - height, zCoord + width2, Blocks.stone, 0, 2);
					}
				}
			}
		}
		for (int width1 = -1; width1 <= 7; ++width1)
		{
			for (int width2 = 1; width2 <= 5; ++width2)
			{
				for (int height = 1; height <= 4; ++height)
				{
					world.setBlock(xCoord + width1, yCoord + height, zCoord + width2, Blocks.air, 0, 2);
				}
			}
		}
		for (int height2 = 1; height2 <= 2; ++height2)
		{
			world.setBlock(xCoord + 1, yCoord + height2, zCoord + 1, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 0, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 0, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 1, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 5, yCoord + height2, zCoord + 2, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 5, yCoord + height2, zCoord + 4, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 5, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 6, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 6, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 1, yCoord + height2, zCoord + 5, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 0, yCoord + height2, zCoord + 4, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 0, yCoord + height2, zCoord + 2, Blocks.planks, 0, 2);
		}
		for (int height2 = 3; height2 <= 3; ++height2)
		{
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 1, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 1, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 4, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 3, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2 + 1, zCoord + 3, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 2, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 5, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 5, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 1, yCoord + height2, zCoord + 4, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 1, yCoord + height2, zCoord + 2, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 0, yCoord + height2, zCoord + 3, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 5, yCoord + height2, zCoord + 3, Blocks.planks, 0, 2);
		}
		for (int height2 = 0; height2 <= 0; ++height2)
		{
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 1, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 1, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 4, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 3, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 2, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 5, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 5, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 1, yCoord + height2, zCoord + 4, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 1, yCoord + height2, zCoord + 2, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 0, yCoord + height2, zCoord + 3, Blocks.cobblestone, 0, 2);
		}
		for (int height2 = 4; height2 <= 4; ++height2)
		{
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 4, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2 + 1, zCoord + 3, (Block) Blocks.wooden_slab, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 2, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 4, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 2, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 1, yCoord + height2, zCoord + 3, Blocks.planks, 0, 2);
		}
		for (int height2 = 0; height2 <= 0; ++height2)
		{
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 4, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 3, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 2, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 4, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 2, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 1, yCoord + height2, zCoord + 3, Blocks.cobblestone, 0, 2);
		}
		world.setBlock(xCoord + 2, yCoord + 5, zCoord + 3, (Block) Blocks.wooden_slab, 0, 2);
		world.setBlock(xCoord + 2, yCoord, zCoord + 3, Blocks.cobblestone, 0, 2);
		for (int height2 = 1; height2 <= 4; ++height2)
		{
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 3, Blocks.fence, 0, 2);
		}
		world.setBlock(xCoord + 6, yCoord + 2, zCoord + 4, Blocks.torch, 0, 2);
		world.setBlock(xCoord + 6, yCoord + 2, zCoord + 2, Blocks.torch, 0, 2);
		world.setBlock(xCoord + 4, yCoord + 2, zCoord + 3, Blocks.torch, 0, 2);
		world.setBlock(xCoord + 4, yCoord, zCoord + 3, Goblins.MobGMSpawner, 0, 2);
		world.setBlock(xCoord + 2, yCoord + 1, zCoord + 5, (Block) Blocks.chest, 0, 2);
		TileEntityChest tileentitychest = (TileEntityChest) world.getTileEntity(xCoord + 2, yCoord + 1, zCoord + 5);
		if (tileentitychest != null)
		{
			ChestGenHooks info = ChestGenHooks.getInfo("dungeonChest");
			WeightedRandomChestContent.generateChestContents(rand, GoblinsWorldGen.goblinMinerChest, (IInventory) tileentitychest, rand.nextInt(3) + 7);
		}
		for (int i2 = -6; i2 <= -4; ++i2)
		{
			for (int j2 = -3; j2 <= 3; ++j2)
			{
				for (int k2 = 1; k2 <= 5; ++k2)
				{
					world.setBlock(xCoord + i2, yCoord + j2, zCoord + k2, Blocks.air, 0, 2);
				}
			}
		}
		for (int i2 = -3; i2 <= -1; ++i2)
		{
			for (int j2 = -3; j2 <= 3; ++j2)
			{
				for (int k2 = 2; k2 <= 4; ++k2)
				{
					world.setBlock(xCoord + i2, yCoord + j2, zCoord + k2, Blocks.air, 0, 2);
				}
			}
		}
		for (int k3 = 2; k3 <= 4; ++k3)
		{
			world.setBlock(xCoord - 1, yCoord - 1, zCoord + k3, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord - 2, yCoord - 2, zCoord + k3, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord - 3, yCoord - 3, zCoord + k3, Blocks.cobblestone, 0, 2);
		}
		world.setBlock(xCoord - 6, yCoord - 3, zCoord + 5, Blocks.stone, 0, 2);
		world.setBlock(xCoord - 5, yCoord - 3, zCoord + 1, Blocks.stone, 0, 2);
		world.setBlock(xCoord - 6, yCoord - 3, zCoord + 1, Blocks.stone, 0, 2);
		world.setBlock(xCoord - 6, yCoord - 2, zCoord + 1, Blocks.stone, 0, 2);
		for (int i2 = -3; i2 <= 0; ++i2)
		{
			world.setBlock(xCoord + i2, yCoord + 3, zCoord + 1, Blocks.air, 0, 2);
			world.setBlock(xCoord + i2, yCoord + 3, zCoord + 5, Blocks.air, 0, 2);
			world.setBlock(xCoord + i2, yCoord + 2, zCoord + 1, Blocks.air, 0, 2);
			world.setBlock(xCoord + i2, yCoord + 2, zCoord + 5, Blocks.air, 0, 2);
			world.setBlock(xCoord + i2, yCoord + 1, zCoord + 1, Blocks.fence, 0, 2);
			world.setBlock(xCoord + i2, yCoord + 1, zCoord + 5, Blocks.fence, 0, 2);
			world.setBlock(xCoord + i2, yCoord, zCoord + 1, (Block) Blocks.grass, 0, 2);
			world.setBlock(xCoord + i2, yCoord, zCoord + 5, (Block) Blocks.grass, 0, 2);
			for (int j2 = -3; j2 <= -1; ++j2)
			{
				world.setBlock(xCoord + i2, yCoord + j2, zCoord + 1, Blocks.dirt, 0, 2);
				world.setBlock(xCoord + i2, yCoord + j2, zCoord + 5, Blocks.dirt, 0, 2);
			}
		}
		for (int i2 = -7; i2 <= -3; ++i2)
		{
			world.setBlock(xCoord + i2, yCoord + 3, zCoord + 0, Blocks.air, 0, 2);
			world.setBlock(xCoord + i2, yCoord + 3, zCoord + 6, Blocks.air, 0, 2);
			world.setBlock(xCoord + i2, yCoord + 2, zCoord + 0, Blocks.air, 0, 2);
			world.setBlock(xCoord + i2, yCoord + 2, zCoord + 6, Blocks.air, 0, 2);
			world.setBlock(xCoord + i2, yCoord + 1, zCoord + 0, Blocks.fence, 0, 2);
			world.setBlock(xCoord + i2, yCoord + 1, zCoord + 6, Blocks.fence, 0, 2);
			world.setBlock(xCoord + i2, yCoord, zCoord + 0, (Block) Blocks.grass, 0, 2);
			world.setBlock(xCoord + i2, yCoord, zCoord + 6, (Block) Blocks.grass, 0, 2);
			for (int j2 = -3; j2 <= -1; ++j2)
			{
				world.setBlock(xCoord + i2, yCoord + j2, zCoord + 0, Blocks.dirt, 0, 2);
				world.setBlock(xCoord + i2, yCoord + j2, zCoord + 6, Blocks.dirt, 0, 2);
			}
		}
		for (int k3 = 1; k3 <= 5; ++k3)
		{
			world.setBlock(xCoord - 7, yCoord + 2, zCoord + k3, Blocks.air, 0, 2);
			world.setBlock(xCoord - 7, yCoord + 3, zCoord + k3, Blocks.air, 0, 2);
			world.setBlock(xCoord - 7, yCoord + 1, zCoord + k3, Blocks.fence, 0, 2);
			world.setBlock(xCoord - 7, yCoord, zCoord + k3, (Block) Blocks.grass, 0, 2);
		}
		startNewMineBranch(world, rand, xCoord - 7, yCoord, zCoord + 3);
	}

	protected void startNewMineBranch(World world, Random rand, int xCoord, int yCoord, int zCoord)
	{
		int diri = -1;
		int dirk = 0;
		for (int limit = rand.nextInt(20) + 20, j2 = 0; j2 <= limit + 3; ++j2)
		{
			int diri2 = diri * j2;
			int dirk2 = dirk * j2;
			if (!world.isAirBlock(xCoord + diri2, yCoord - 1 - j2, zCoord + dirk2) && !world.isAirBlock(xCoord + diri2, yCoord - 5 - j2, zCoord + dirk2))
			{
				world.setBlock(xCoord + diri2, yCoord - 1 - j2, zCoord + dirk2, Blocks.air, 0, 3);
				world.setBlock(xCoord + diri2, yCoord - 2 - j2, zCoord + dirk2, Blocks.air, 0, 3);
				world.setBlock(xCoord + diri2, yCoord - 3 - j2, zCoord + dirk2, Blocks.air, 0, 3);
				world.setBlock(xCoord + diri2, yCoord - 4 - j2, zCoord + dirk2, Blocks.stone_stairs, 0, 3);
				if (j2 % 6 == 0)
				{
					world.setBlock(xCoord - j2 + 1, yCoord - 3 - j2 + 1, zCoord, Blocks.torch, 0, 3);
				}
			}
			if (j2 == limit)
			{
				for (int j3 = -1; j3 <= 4; ++j3)
				{
					for (int i2 = -4; i2 <= 4; ++i2)
					{
						for (int k2 = -4; k2 <= 4; ++k2)
						{
							if (Math.sqrt(Math.abs(j3)) + Math.sqrt(Math.abs(k2)) + Math.sqrt(Math.abs(i2)) <= 5.0 && world.getBlock(xCoord + diri2 + i2, yCoord - j2 + j3 - 4, zCoord + dirk2 + k2) != Blocks.stone_stairs)
							{
								world.setBlock(xCoord + diri2 + i2, yCoord - j2 + j3 - 4, zCoord + dirk2 + k2, Blocks.air, 0, 3);
							}
							if (j3 == 1)
							{
								world.setBlock(xCoord + diri2 + 3, yCoord - j2 + j3 - 7, zCoord + dirk2, Goblins.MobGMSpawner, 0, 3);
								startNewMineTunnel(world, rand, xCoord + diri2 + 4, yCoord - j2 + j3 - 6, zCoord + dirk2, 0, 10);
								startNewMineTunnel(world, rand, xCoord + diri2 - 4, yCoord - j2 + j3 - 6, zCoord + dirk2, 0, 10);
								startNewMineTunnel(world, rand, xCoord + diri2, yCoord - j2 + j3 - 6, zCoord + dirk2 + 4, 0, 10);
								startNewMineTunnel(world, rand, xCoord + diri2, yCoord - j2 + j3 - 6, zCoord + dirk2 - 4, 0, 10);
							}
						}
					}
				}
			}
		}
	}

	protected void startNewMineTunnel(World world, Random rand, int xCoord, int yCoord, int zCoord, int direction, int chance)
	{
		int diri = 0;
		int dirk = 0;
		if (direction == 0)
		{
			diri = -1;
		}
		if (direction == 1)
		{
			diri = 1;
		}
		if (direction == 2)
		{
			dirk = -1;
		}
		if (direction == 3)
		{
			dirk = 1;
		}
		for (int limit = rand.nextInt(20) + 10, len = 0; len <= limit; ++len)
		{
			int diri2 = diri * len;
			int dirk2 = dirk * len;
			if (world.getBlock(xCoord + diri2, yCoord, zCoord + dirk) != Blocks.stone_stairs && world.getBlock(xCoord + diri2, yCoord + 1, zCoord + dirk) != Blocks.stone_stairs)
			{
				world.setBlock(xCoord + diri2, yCoord, zCoord + dirk2, Blocks.air, 0, 3);
				world.setBlock(xCoord + diri2, yCoord + 1, zCoord + dirk2, Blocks.air, 0, 3);
			}
			if (rand.nextInt(1000) < chance)
			{
				startNewMineTunnel(world, rand, xCoord + diri2, yCoord, zCoord + dirk2, rand.nextInt(4), chance - 2);
			}
		}
	}

	protected void createStairPiece(World world, Random rand, int xCoord, int yCoord, int zCoord, int direction)
	{
	}

	protected void generateRightHouse(World world, Random rand, int xCoord, int yCoord, int zCoord)
	{
		for (int width1 = 0; width1 <= 7; ++width1)
		{
			for (int width2 = 0; width2 <= 9; ++width2)
			{
				for (int height = 0; height <= 4; ++height)
				{
					if (height == 0)
					{
						world.setBlock(xCoord + width1, yCoord - height, zCoord + width2, (Block) Blocks.grass, 0, 2);
					}
					else if (height > 0 && height < 3)
					{
						world.setBlock(xCoord + width1, yCoord - height, zCoord + width2, Blocks.dirt, 0, 2);
					}
					else
					{
						world.setBlock(xCoord + width1, yCoord - height, zCoord + width2, Blocks.stone, 0, 2);
					}
				}
			}
		}
		for (int width1 = -1; width1 <= 7; ++width1)
		{
			for (int width2 = 1; width2 <= 5; ++width2)
			{
				for (int height = 1; height <= 4; ++height)
				{
					world.setBlock(xCoord + width1, yCoord + height, zCoord + width2, Blocks.air, 0, 2);
				}
			}
		}
		for (int height2 = 1; height2 <= 2; ++height2)
		{
			world.setBlock(xCoord + 1, yCoord + height2, zCoord + 1, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 0, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 0, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 1, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 5, yCoord + height2, zCoord + 2, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 5, yCoord + height2, zCoord + 4, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 5, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 6, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 6, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 1, yCoord + height2, zCoord + 5, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 0, yCoord + height2, zCoord + 3, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 0, yCoord + height2, zCoord + 4, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 0, yCoord + height2, zCoord + 2, Blocks.planks, 0, 2);
		}
		for (int height2 = 3; height2 <= 3; ++height2)
		{
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 1, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 1, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 4, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 2, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 5, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 5, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 1, yCoord + height2, zCoord + 4, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 1, yCoord + height2, zCoord + 2, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 5, yCoord + height2, zCoord + 3, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 1, yCoord + height2, zCoord + 3, Blocks.planks, 0, 2);
		}
		for (int height2 = 0; height2 <= 0; ++height2)
		{
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 1, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 1, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 4, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 3, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 2, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 5, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 5, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 1, yCoord + height2, zCoord + 4, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 1, yCoord + height2, zCoord + 2, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 1, yCoord + height2, zCoord + 3, Blocks.cobblestone, 0, 2);
		}
		for (int height2 = 4; height2 <= 4; ++height2)
		{
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 4, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 3, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 2, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 4, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 2, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 3, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 3, Blocks.planks, 0, 2);
		}
		for (int height2 = 0; height2 <= 0; ++height2)
		{
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 4, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 3, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 2, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 4, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 2, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 3, Blocks.cobblestone, 0, 2);
		}
		world.setBlock(xCoord + 3, yCoord + 5, zCoord + 3, (Block) Blocks.wooden_slab, 0, 2);
		world.setBlock(xCoord + 2, yCoord, zCoord + 3, Blocks.cobblestone, 0, 2);
		for (int height2 = 1; height2 <= 4; ++height2)
		{
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 3, Blocks.fence, 0, 2);
		}
		world.setBlock(xCoord + 6, yCoord + 2, zCoord + 4, Blocks.torch, 0, 2);
		world.setBlock(xCoord + 6, yCoord + 2, zCoord + 2, Blocks.torch, 0, 2);
		world.setBlock(xCoord + 1, yCoord + 2, zCoord + 3, Blocks.torch, 0, 2);
		world.setBlock(xCoord + 4, yCoord, zCoord + 3, Goblins.MobGSpawner, 0, 2);
		world.setBlock(xCoord + 5, yCoord + 1, zCoord + 3, Blocks.air, 0, 2);
		world.setBlock(xCoord + 1, yCoord + 1, zCoord + 3, (Block) Blocks.chest, 0, 2);
		TileEntityChest tileentitychest = (TileEntityChest) world.getTileEntity(xCoord + 1, yCoord + 1, zCoord + 3);
		if (tileentitychest != null)
		{
			ChestGenHooks info = ChestGenHooks.getInfo("dungeonChest");
			WeightedRandomChestContent.generateChestContents(rand, GoblinsWorldGen.goblinStandardChest, (IInventory) tileentitychest, rand.nextInt(3) + 7);
		}
	}

	protected void generateLeftWolfPen(World world, Random rand, int xCoord, int yCoord, int zCoord)
	{
		for (int width1 = -2; width1 <= 5; ++width1)
		{
			for (int width2 = 0; width2 <= 6; ++width2)
			{
				for (int height = 0; height <= 4; ++height)
				{
					if (height == 0)
					{
						world.setBlock(xCoord + width1, yCoord - height, zCoord + width2, (Block) Blocks.grass, 0, 2);
					}
					else if (height > 0 && height < 3)
					{
						world.setBlock(xCoord + width1, yCoord - height, zCoord + width2, Blocks.dirt, 0, 2);
					}
					else
					{
						world.setBlock(xCoord + width1, yCoord - height, zCoord + width2, Blocks.stone, 0, 2);
					}
				}
			}
		}
		for (int width1 = -2; width1 <= 6; ++width1)
		{
			for (int width2 = 1; width2 <= 5; ++width2)
			{
				for (int height = 1; height <= 4; ++height)
				{
					world.setBlock(xCoord + width1, yCoord + height, zCoord + width2, Blocks.air, 0, 2);
				}
			}
		}
		for (int height2 = 1; height2 <= 2; ++height2)
		{
			world.setBlock(xCoord + 1, yCoord + height2, zCoord + 1, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 0, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 0, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 1, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 5, yCoord + height2, zCoord + 2, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 5, yCoord + height2, zCoord + 4, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 5, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 6, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 6, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 1, yCoord + height2, zCoord + 5, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 0, yCoord + height2, zCoord + 4, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 0, yCoord + height2, zCoord + 2, Blocks.planks, 0, 2);
		}
		world.setBlock(xCoord + 5, yCoord + 1, zCoord + 3, Blocks.fence_gate, 1, 2);
		for (int height2 = 3; height2 <= 3; ++height2)
		{
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 1, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 1, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 4, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 3, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2 + 1, zCoord + 3, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 2, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 5, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 5, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 1, yCoord + height2, zCoord + 4, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 1, yCoord + height2, zCoord + 2, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 0, yCoord + height2, zCoord + 3, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 5, yCoord + height2, zCoord + 3, Blocks.planks, 0, 2);
		}
		for (int height2 = 0; height2 <= 0; ++height2)
		{
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 1, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 1, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 4, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 3, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 2, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 5, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 5, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 1, yCoord + height2, zCoord + 4, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 1, yCoord + height2, zCoord + 2, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 0, yCoord + height2, zCoord + 3, Blocks.cobblestone, 0, 2);
		}
		for (int height2 = 4; height2 <= 4; ++height2)
		{
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 4, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2 + 1, zCoord + 3, (Block) Blocks.wooden_slab, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 2, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 4, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 2, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 1, yCoord + height2, zCoord + 3, Blocks.planks, 0, 2);
		}
		for (int height2 = 0; height2 <= 0; ++height2)
		{
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 4, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 3, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 2, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 4, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 2, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 1, yCoord + height2, zCoord + 3, Blocks.cobblestone, 0, 2);
		}
		world.setBlock(xCoord + 2, yCoord + 5, zCoord + 3, (Block) Blocks.wooden_slab, 0, 2);
		world.setBlock(xCoord + 2, yCoord, zCoord + 3, Blocks.cobblestone, 0, 2);
		for (int height2 = 1; height2 <= 4; ++height2)
		{
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 3, Blocks.fence, 0, 2);
		}
		world.setBlock(xCoord - 1, yCoord + 2, zCoord + 4, Blocks.torch, 0, 2);
		world.setBlock(xCoord - 1, yCoord + 2, zCoord + 2, Blocks.torch, 0, 2);
		world.setBlock(xCoord + 4, yCoord + 2, zCoord + 3, Blocks.torch, 0, 2);
		world.setBlock(xCoord + 1, yCoord, zCoord + 3, Goblins.MobGRSpawner, 0, 2);
		if (rand.nextInt(2) == 0)
		{
			world.setBlock(xCoord + 3, yCoord + 1, zCoord + 5, (Block) Blocks.chest, 0, 2);
			TileEntityChest tileentitychest = (TileEntityChest) world.getTileEntity(xCoord + 3, yCoord + 1, zCoord + 5);
			if (tileentitychest != null)
			{
				ChestGenHooks info = ChestGenHooks.getInfo("dungeonChest");
				WeightedRandomChestContent.generateChestContents(rand, GoblinsWorldGen.goblinRiderChest, (IInventory) tileentitychest, rand.nextInt(3) + 7);
			}
		}
		for (int k2 = 0; k2 <= 2; ++k2)
		{
			for (int i2 = 0; i2 <= 4; ++i2)
			{
				for (int j2 = 1; j2 <= 3; ++j2)
				{
					world.setBlock(xCoord + i2 + 6, yCoord + j2, zCoord + k2 + 2, Blocks.air, 0, 2);
				}
			}
		}
		for (int k2 = 0; k2 <= 4; ++k2)
		{
			for (int i2 = 0; i2 <= 6; ++i2)
			{
				for (int j2 = 0; j2 <= 5; ++j2)
				{
					world.setBlock(xCoord + i2 + 5, yCoord - j2, zCoord + k2 + 1, Blocks.dirt, 0, 2);
				}
			}
		}
		for (int i3 = 0; i3 <= 5; ++i3)
		{
			world.setBlock(xCoord + i3 + 5, yCoord + 1, zCoord + 5, Blocks.fence, 0, 2);
			world.setBlock(xCoord + i3 + 5, yCoord, zCoord + 5, Blocks.cobblestone, 0, 2);
		}
		for (int i3 = 0; i3 <= 5; ++i3)
		{
			world.setBlock(xCoord + i3 + 5, yCoord + 1, zCoord + 1, Blocks.fence, 0, 2);
			world.setBlock(xCoord + i3 + 5, yCoord, zCoord + 1, Blocks.cobblestone, 0, 2);
		}
		for (int k2 = 0; k2 <= 3; ++k2)
		{
			world.setBlock(xCoord + 11, yCoord + 1, zCoord + k2 + 1, Blocks.fence, 0, 2);
			world.setBlock(xCoord + 11, yCoord, zCoord + k2 + 1, Blocks.cobblestone, 0, 2);
		}
		world.setBlock(xCoord + 11, yCoord + 1, zCoord + 5, Blocks.planks, 0, 2);
		world.setBlock(xCoord + 11, yCoord + 1, zCoord + 1, Blocks.planks, 0, 2);
		world.setBlock(xCoord + 11, yCoord + 2, zCoord + 5, Blocks.fence, 0, 2);
		world.setBlock(xCoord + 10, yCoord + 2, zCoord + 1, Blocks.fence, 0, 2);
		world.setBlock(xCoord + 10, yCoord + 2, zCoord + 5, Blocks.fence, 0, 2);
		world.setBlock(xCoord + 11, yCoord + 2, zCoord + 2, Blocks.fence, 0, 2);
		world.setBlock(xCoord + 11, yCoord + 2, zCoord + 4, Blocks.fence, 0, 2);
		world.setBlock(xCoord + 11, yCoord + 2, zCoord + 1, Blocks.fence, 0, 2);
		world.setBlock(xCoord + 11, yCoord + 3, zCoord + 5, Blocks.torch, 0, 2);
		world.setBlock(xCoord + 11, yCoord + 3, zCoord + 1, Blocks.torch, 0, 2);
		for (int a = 0; a <= 1; ++a)
		{
			int a2 = rand.nextInt(3);
			int a3 = rand.nextInt(2);
			EntityDirewolf direwolf = new EntityDirewolf(world);
			direwolf.setLocationAndAngles((double) (xCoord + 7 + a2), (double) (yCoord + 1), (double) (zCoord + 2 + a3), world.rand.nextFloat() * 360.0f, 0.0f);
			direwolf.setPosition((double) (xCoord + 7 + a2), (double) (yCoord + 1), (double) (zCoord + 2 + a3));
			world.spawnEntityInWorld((Entity) direwolf);
		}
	}

	protected void generateLeftHouse(World world, Random rand, int xCoord, int yCoord, int zCoord)
	{
		for (int width1 = -2; width1 <= 5; ++width1)
		{
			for (int width2 = -2; width2 <= 6; ++width2)
			{
				for (int height = 0; height <= 4; ++height)
				{
					if (height == 0)
					{
						world.setBlock(xCoord + width1, yCoord - height, zCoord + width2, (Block) Blocks.grass, 0, 2);
					}
					else if (height > 0 && height < 3)
					{
						world.setBlock(xCoord + width1, yCoord - height, zCoord + width2, Blocks.dirt, 0, 2);
					}
					else
					{
						world.setBlock(xCoord + width1, yCoord - height, zCoord + width2, Blocks.stone, 0, 2);
					}
				}
			}
		}
		for (int width1 = -2; width1 <= 6; ++width1)
		{
			for (int width2 = 1; width2 <= 5; ++width2)
			{
				for (int height = 1; height <= 4; ++height)
				{
					world.setBlock(xCoord + width1, yCoord + height, zCoord + width2, Blocks.air, 0, 2);
				}
			}
		}
		for (int height2 = 1; height2 <= 2; ++height2)
		{
			world.setBlock(xCoord + 1, yCoord + height2, zCoord + 1, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 0, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 0, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 1, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 5, yCoord + height2, zCoord + 2, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 5, yCoord + height2, zCoord + 3, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 5, yCoord + height2, zCoord + 4, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 5, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 6, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 6, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 1, yCoord + height2, zCoord + 5, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 0, yCoord + height2, zCoord + 4, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 0, yCoord + height2, zCoord + 2, Blocks.planks, 0, 2);
		}
		for (int height2 = 3; height2 <= 3; ++height2)
		{
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 1, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 1, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 4, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 3, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 2, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 5, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 5, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 1, yCoord + height2, zCoord + 4, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 1, yCoord + height2, zCoord + 2, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 0, yCoord + height2, zCoord + 3, Blocks.planks, 0, 2);
		}
		for (int height2 = 0; height2 <= 0; ++height2)
		{
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 1, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 1, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 4, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 3, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 4, yCoord + height2, zCoord + 2, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 5, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 5, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 1, yCoord + height2, zCoord + 4, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 1, yCoord + height2, zCoord + 2, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 0, yCoord + height2, zCoord + 3, Blocks.cobblestone, 0, 2);
		}
		for (int height2 = 4; height2 <= 4; ++height2)
		{
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 4, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 3, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 2, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 4, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 2, Blocks.planks, 0, 2);
			world.setBlock(xCoord + 1, yCoord + height2, zCoord + 3, Blocks.planks, 0, 2);
		}
		for (int height2 = 0; height2 <= 0; ++height2)
		{
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 4, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 3, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 3, yCoord + height2, zCoord + 2, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 4, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 2, Blocks.cobblestone, 0, 2);
			world.setBlock(xCoord + 1, yCoord + height2, zCoord + 3, Blocks.cobblestone, 0, 2);
		}
		world.setBlock(xCoord + 2, yCoord + 5, zCoord + 3, (Block) Blocks.wooden_slab, 0, 2);
		world.setBlock(xCoord + 2, yCoord, zCoord + 3, Blocks.cobblestone, 0, 2);
		for (int height2 = 1; height2 <= 4; ++height2)
		{
			world.setBlock(xCoord + 2, yCoord + height2, zCoord + 3, Blocks.fence, 0, 2);
		}
		world.setBlock(xCoord - 1, yCoord + 2, zCoord + 4, Blocks.torch, 0, 2);
		world.setBlock(xCoord - 1, yCoord + 2, zCoord + 2, Blocks.torch, 0, 2);
		world.setBlock(xCoord + 4, yCoord + 2, zCoord + 3, Blocks.torch, 0, 2);
		world.setBlock(xCoord + 1, yCoord, zCoord + 3, Goblins.MobGSpawner, 0, 2);
		world.setBlock(xCoord + 0, yCoord + 1, zCoord + 3, Blocks.air, 0, 2);
		if (rand.nextInt(2) == 0)
		{
			world.setBlock(xCoord + 4, yCoord + 1, zCoord + 3, (Block) Blocks.chest, 0, 2);
			TileEntityChest tileentitychest = (TileEntityChest) world.getTileEntity(xCoord + 4, yCoord + 1, zCoord + 3);
			if (tileentitychest != null)
			{
				ChestGenHooks info = ChestGenHooks.getInfo("dungeonChest");
				WeightedRandomChestContent.generateChestContents(rand, GoblinsWorldGen.goblinStandardChest, (IInventory) tileentitychest, rand.nextInt(3) + 7);
			}
		}
	}

	static
	{
		goblinStandardChest = new WeightedRandomChestContent[]
		{ new WeightedRandomChestContent(Items.bread, 0, 1, 1, 10), new WeightedRandomChestContent(Items.cooked_beef, 0, 1, 3, 10), new WeightedRandomChestContent(Items.gunpowder, 0, 1, 4, 10), new WeightedRandomChestContent(Items.string, 0, 1, 4, 10), new WeightedRandomChestContent(Items.bucket, 0, 1, 1, 10), new WeightedRandomChestContent(Items.golden_apple, 0, 1, 1, 1), new WeightedRandomChestContent(Goblins.bomb, 0, 1, 4, 10), new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 10),
				new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 5, 5), new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.log), 0, 4, 10, 10) };
		goblinRiderChest = new WeightedRandomChestContent[]
		{ new WeightedRandomChestContent(Items.bread, 0, 3, 6, 9), new WeightedRandomChestContent(Items.cooked_beef, 0, 3, 6, 9), new WeightedRandomChestContent(Items.cooked_porkchop, 0, 3, 6, 9), new WeightedRandomChestContent(Items.bucket, 0, 1, 1, 9), new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 9), new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 5, 4), new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.log), 0, 4, 10, 9), new WeightedRandomChestContent(Item
				.getItemFromBlock(Blocks.skull), 0, 1, 2, 9), new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 10), new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.wool), 0, 10, 20, 10) };
		goblinMinerChest = new WeightedRandomChestContent[]
		{ new WeightedRandomChestContent(Items.bread, 0, 1, 1, 10), new WeightedRandomChestContent(Items.cooked_beef, 0, 1, 3, 10), new WeightedRandomChestContent(Items.bucket, 0, 1, 1, 10), new WeightedRandomChestContent(Items.iron_ingot, 0, 3, 8, 10), new WeightedRandomChestContent(Items.redstone, 0, 3, 8, 10), new WeightedRandomChestContent(Items.gold_ingot, 0, 3, 8, 5), new WeightedRandomChestContent(Items.diamond, 0, 1, 1, 1), new WeightedRandomChestContent(Items.coal, 0, 7, 10, 5),
				new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.stone), 0, 15, 50, 10) };
	}
}
