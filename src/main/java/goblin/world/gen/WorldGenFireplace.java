
package goblin.world.gen;

import java.util.Random;

import goblin.Goblins;
import goblin.entity.EntityGoblin;
import goblin.entity.EntityGoblinBomber;
import goblin.entity.EntityGoblinRanger;
import goblin.entity.EntityGoblinSoldier;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenFireplace extends WorldGenerator {
	int houseLoc1;
	int houseLoc2;

	public boolean generate(World world, Random rand, int xCoord, int yCoord, int zCoord)
	{
		if (world.getBlock(xCoord, yCoord, zCoord) == Blocks.grass && world.getBlock(xCoord + 6, yCoord, zCoord + 6) == Blocks.grass && world.getBlock(xCoord + 6, yCoord, zCoord) == Blocks.grass && world.getBlock(xCoord, yCoord, zCoord + 6) == Blocks.grass)
		{
			for (int width1 = -1; width1 <= 6; ++width1)
			{
				for (int width2 = -1; width2 <= 6; ++width2)
				{
					for (int height = 1; height <= 10; ++height)
					{
						if (width1 >= 1 && width1 <= 5 && width2 >= 1 && width2 <= 5 && world.getBlock(xCoord + width1, yCoord + height, zCoord + width2) == Blocks.log)
						{
							world.setBlock(xCoord + width1, yCoord + height, zCoord + width2, Blocks.air, 0, 2);
						}
						if (world.getBlock(xCoord + width1, yCoord + height, zCoord + width2) == Blocks.leaves || world.getBlock(xCoord + width1, yCoord + height, zCoord + width2) == Blocks.tallgrass || world.getBlock(xCoord + width1, yCoord + height, zCoord + width2) == Blocks.vine)
						{
							world.setBlock(xCoord + width1, yCoord + height, zCoord + width2, Blocks.air, 0, 2);
						}
					}
				}
			}
			world.setBlock(xCoord + 3, yCoord + 1, zCoord + 2, (Block) Blocks.double_stone_slab, 0, 2);
			world.setBlock(xCoord + 2, yCoord + 1, zCoord + 3, (Block) Blocks.double_stone_slab, 0, 2);
			world.setBlock(xCoord + 3, yCoord + 1, zCoord + 3, Blocks.iron_bars, 0, 2);
			world.setBlock(xCoord + 3, yCoord, zCoord + 3, (Block) Blocks.fire, 0, 2);
			world.setBlock(xCoord + 3, yCoord - 1, zCoord + 3, Blocks.netherrack, 0, 2);
			world.setBlock(xCoord + 4, yCoord + 1, zCoord + 3, (Block) Blocks.double_stone_slab, 0, 2);
			world.setBlock(xCoord + 3, yCoord + 1, zCoord + 4, (Block) Blocks.double_stone_slab, 0, 2);
			int a = rand.nextInt(4);
			switch (a)
			{
			case 0:
			{
				world.setBlock(xCoord + 3, yCoord + 1, zCoord + 0, (Block) Blocks.chest, 0, 2);
				TileEntityChest tileentitychest = (TileEntityChest) world.getTileEntity(xCoord + 3, yCoord + 1, zCoord + 0);
				for (int r1 = 0; r1 <= 4; ++r1)
				{
					ItemStack itemStack = pickCheckLootItem(rand);
					if (itemStack != null)
					{
						tileentitychest.setInventorySlotContents(rand.nextInt(tileentitychest.getSizeInventory()), itemStack);
					}
				}
				break;
			}
			case 1:
			{
				world.setBlock(xCoord + 0, yCoord + 1, zCoord + 3, (Block) Blocks.chest, 0, 2);
				TileEntityChest tileentitychest2 = (TileEntityChest) world.getTileEntity(xCoord + 0, yCoord + 1, zCoord + 3);
				for (int r2 = 0; r2 <= 4; ++r2)
				{
					ItemStack itemStack2 = pickCheckLootItem(rand);
					if (itemStack2 != null)
					{
						tileentitychest2.setInventorySlotContents(rand.nextInt(tileentitychest2.getSizeInventory()), itemStack2);
					}
				}
				break;
			}
			case 2:
			{
				world.setBlock(xCoord + 6, yCoord + 1, zCoord + 3, (Block) Blocks.chest, 0, 2);
				TileEntityChest tileentitychest3 = (TileEntityChest) world.getTileEntity(xCoord + 6, yCoord + 1, zCoord + 3);
				for (int r3 = 0; r3 <= 4; ++r3)
				{
					ItemStack itemStack3 = pickCheckLootItem(rand);
					if (itemStack3 != null)
					{
						tileentitychest3.setInventorySlotContents(rand.nextInt(tileentitychest3.getSizeInventory()), itemStack3);
					}
				}
				break;
			}
			case 3:
			{
				world.setBlock(xCoord + 3, yCoord + 1, zCoord + 6, (Block) Blocks.chest, 0, 2);
				TileEntityChest tileentitychest4 = (TileEntityChest) world.getTileEntity(xCoord + 3, yCoord + 1, zCoord + 6);
				for (int r4 = 0; r4 <= 4; ++r4)
				{
					ItemStack itemStack4 = pickCheckLootItem(rand);
					if (itemStack4 != null)
					{
						tileentitychest4.setInventorySlotContents(rand.nextInt(tileentitychest4.getSizeInventory()), itemStack4);
					}
				}
				break;
			}
			}
			for (int b = rand.nextInt(3) + 3; b >= 0; --b)
			{
				int goblinPick = rand.nextInt(20);
				if (goblinPick >= 0 && goblinPick <= 6)
				{
					EntityGoblin goblin = new EntityGoblin(world);
					int c = -1 + rand.nextInt(9);
					int c2 = -1 + rand.nextInt(9);
					goblin.setLocationAndAngles((double) (xCoord + c), (double) (yCoord + 1), (double) (zCoord + c2), world.rand.nextFloat() * 360.0f, 0.0f);
					goblin.setPosition((double) (xCoord + c), (double) (yCoord + 1), (double) (zCoord + c2));
					world.spawnEntityInWorld((Entity) goblin);
				}
				if (goblinPick >= 7 && goblinPick <= 12)
				{
					EntityGoblinRanger goblin2 = new EntityGoblinRanger(world);
					int c = -1 + rand.nextInt(9);
					int c2 = -1 + rand.nextInt(9);
					goblin2.setLocationAndAngles((double) (xCoord + c), (double) (yCoord + 1), (double) (zCoord + c2), world.rand.nextFloat() * 360.0f, 0.0f);
					goblin2.setPosition((double) (xCoord + c), (double) (yCoord + 1), (double) (zCoord + c2));
					world.spawnEntityInWorld((Entity) goblin2);
				}
				if (goblinPick >= 13 && goblinPick <= 18)
				{
					EntityGoblinSoldier goblin3 = new EntityGoblinSoldier(world);
					int c = -1 + rand.nextInt(9);
					int c2 = -1 + rand.nextInt(9);
					goblin3.setLocationAndAngles((double) (xCoord + c), (double) (yCoord + 1), (double) (zCoord + c2), world.rand.nextFloat() * 360.0f, 0.0f);
					goblin3.setPosition((double) (xCoord + c), (double) (yCoord + 1), (double) (zCoord + c2));
					world.spawnEntityInWorld((Entity) goblin3);
				}
				if (goblinPick == 19)
				{
					EntityGoblinBomber goblin4 = new EntityGoblinBomber(world);
					int c = -1 + rand.nextInt(9);
					int c2 = -1 + rand.nextInt(9);
					goblin4.setLocationAndAngles((double) (xCoord + c), (double) (yCoord + 1), (double) (zCoord + c2), world.rand.nextFloat() * 360.0f, 0.0f);
					goblin4.setPosition((double) (xCoord + c), (double) (yCoord + 1), (double) (zCoord + c2));
					world.spawnEntityInWorld((Entity) goblin4);
				}
			}
			return true;
		}
		return false;
	}

	private ItemStack pickCheckLootItem(Random random)
	{
		int i = random.nextInt(7);
		if (i == 0)
		{
			return new ItemStack(Items.apple, random.nextInt(2) + 1);
		}
		if (i == 1)
		{
			return new ItemStack(Item.getItemFromBlock(Blocks.log), 8);
		}
		if (i == 2)
		{
			return new ItemStack(Items.bread, random.nextInt(2) + 1);
		}
		if (i == 3)
		{
			return new ItemStack(Items.cooked_beef, random.nextInt(2) + 1);
		}
		if (i == 4)
		{
			return new ItemStack(Items.iron_ingot, random.nextInt(2) + 2);
		}
		if (i == 5 && random.nextInt(20) == 1)
		{
			return new ItemStack(Goblins.powderR);
		}
		if (i == 6)
		{
			return new ItemStack(Items.coal, random.nextInt(3) + 5);
		}
		return null;
	}

	private ItemStack pickCheckLootItemRider(Random random)
	{
		int i = random.nextInt(6);
		if (i == 0)
		{
			return new ItemStack(Items.apple, random.nextInt(3) + 1);
		}
		if (i == 1)
		{
			return new ItemStack(Items.gold_ingot, random.nextInt(2) + 1);
		}
		if (i == 2)
		{
			return new ItemStack(Items.saddle);
		}
		if (i == 3)
		{
			return new ItemStack(Items.cooked_beef, random.nextInt(3) + 1);
		}
		if (i == 4)
		{
			return new ItemStack(Items.iron_ingot, random.nextInt(3) + 2);
		}
		if (i == 5 && random.nextInt(10) == 1)
		{
			return new ItemStack(Goblins.powderG);
		}
		return null;
	}

	private ItemStack pickCheckLootItemMiner(Random random)
	{
		int i = random.nextInt(6);
		if (i == 0)
		{
			return new ItemStack(Items.flint, random.nextInt(7) + 1);
		}
		if (i == 1)
		{
			return new ItemStack(Items.gold_ingot, random.nextInt(2) + 1);
		}
		if (i == 2)
		{
			return new ItemStack(Items.coal, random.nextInt(8) + 5);
		}
		if (i == 3)
		{
			return new ItemStack(Items.cooked_beef, random.nextInt(2) + 1);
		}
		if (i == 4)
		{
			return new ItemStack(Items.iron_ingot, random.nextInt(5) + 5);
		}
		if (i == 5 && random.nextInt(10) == 1)
		{
			return new ItemStack(Goblins.powderY);
		}
		return null;
	}
}
