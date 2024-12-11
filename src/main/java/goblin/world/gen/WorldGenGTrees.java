
package goblin.world.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenGTrees extends WorldGenerator {
	private int field_48202_a;
	private boolean field_48200_b;
	private int field_48201_c;
	private int field_48199_d;

	public WorldGenGTrees(boolean par1)
	{
		this(par1, 4, 0, 0, false);
	}

	public WorldGenGTrees(boolean par1, int par2, int par3, int par4, boolean par5)
	{
		super(par1);
		field_48202_a = par2;
		field_48201_c = par3;
		field_48199_d = par4;
		field_48200_b = par5;
	}

	@Override
	public boolean generate(World world, Random random, int xCoord, int yCoord, int zCoord)
	{
		int i = random.nextInt(3) + field_48202_a;
		boolean flag = true;
		if (yCoord < 1 || yCoord + i + 1 > 256)
		{
			return false;
		}
		for (int j = yCoord; j <= yCoord + 1 + i; ++j)
		{
			byte byte0 = 1;
			if (j == yCoord)
			{
				byte0 = 0;
			}
			if (j >= yCoord + 1 + i - 2)
			{
				byte0 = 2;
			}
			for (int l = xCoord - byte0; l <= xCoord + byte0 && flag; ++l)
			{
				for (int j2 = zCoord - byte0; j2 <= zCoord + byte0 && flag; ++j2)
				{
					if (j >= 0 && j < 256)
					{
						Block j3 = world.getBlock(l, j, j2);
						if (j3 != Blocks.air && j3 != Blocks.leaves && j3 != Blocks.grass && j3 != Blocks.dirt && j3 != Blocks.log)
						{
							flag = false;
						}
					}
					else
					{
						flag = false;
					}
				}
			}
		}
		if (!flag)
		{
			return false;
		}
		Block k = world.getBlock(xCoord, yCoord - 1, zCoord);
		if ((k != Blocks.grass && k != Blocks.dirt) || yCoord >= 256 - i - 1)
		{
			return false;
		}
		world.setBlock(xCoord, yCoord - 1, zCoord, Blocks.dirt, 0, 2);
		byte byte2 = 3;
		int i2 = 0;
		for (int k2 = yCoord - byte2 + i; k2 <= yCoord + i; ++k2)
		{
			int k3 = k2 - (yCoord + i);
			for (int j4 = i2 + 1 - k3 / 2, l2 = xCoord - j4; l2 <= xCoord + j4; ++l2)
			{
				int j5 = l2 - xCoord;
				for (int l3 = zCoord - j4; l3 <= zCoord + j4; ++l3)
				{
					int i3 = l3 - zCoord;
					if ((Math.abs(j5) != j4 || Math.abs(i3) != j4 || (random.nextInt(2) != 0 && k3 != 0)) && !world.getBlock(l2, k2, l3).isOpaqueCube())
					{
						world.setBlock(l2, k2, l3, Blocks.leaves);
						world.setBlockMetadataWithNotify(l2, k2, l3, field_48199_d, 3);
					}
				}
			}
		}
		for (int l4 = 0; l4 < i; ++l4)
		{
			Block l5 = world.getBlock(xCoord, yCoord + l4, zCoord);
			if (l5 == Blocks.air || l5 == Blocks.leaves)
			{
				world.setBlock(xCoord, yCoord + l4, zCoord, Blocks.log);
				world.setBlockMetadataWithNotify(xCoord, yCoord + l4, zCoord, field_48201_c, 3);
				if (field_48200_b)
				{
					if (l4 > 0)
					{
						if (random.nextInt(3) > 0 && world.isAirBlock(xCoord - 1, yCoord + l4, zCoord))
						{
							world.setBlock(xCoord - 1, yCoord + l4, zCoord, Blocks.vine);
							world.setBlockMetadataWithNotify(xCoord - 1, yCoord + l4, zCoord, 8, 3);
						}
						if (random.nextInt(3) > 0 && world.isAirBlock(xCoord + 1, yCoord + l4, zCoord))
						{
							world.setBlock(xCoord + 1, yCoord + l4, zCoord, Blocks.vine);
							world.setBlockMetadataWithNotify(xCoord + 1, yCoord + l4, zCoord, 2, 3);
						}
						if (random.nextInt(3) > 0 && world.isAirBlock(xCoord, yCoord + l4, zCoord - 1))
						{
							world.setBlock(xCoord, yCoord + l4, zCoord - 1, Blocks.vine);
							world.setBlockMetadataWithNotify(xCoord, yCoord + l4, zCoord - 1, 1, 3);
						}
						if (random.nextInt(3) > 0 && world.isAirBlock(xCoord, yCoord + l4, zCoord + 1))
						{
							world.setBlock(xCoord, yCoord + l4, zCoord + 1, Blocks.vine);
							world.setBlockMetadataWithNotify(xCoord, yCoord + l4, zCoord + 1, 4, 3);
						}
					}
				}
			}
		}
		if (field_48200_b)
		{
			for (int i4 = yCoord - 3 + i; i4 <= yCoord + i; ++i4)
			{
				int i5 = i4 - (yCoord + i);
				for (int k4 = 2 - i5 / 2, i6 = xCoord - k4; i6 <= xCoord + k4; ++i6)
				{
					for (int k5 = zCoord - k4; k5 <= zCoord + k4; ++k5)
					{
						if (world.getBlock(i6, i4, k5) == Blocks.leaves)
						{
							if (random.nextInt(4) == 0 && world.getBlock(i6 - 1, i4, k5) == Blocks.air)
							{
								func_48198_a(world, i6 - 1, i4, k5, 8);
							}
							if (random.nextInt(4) == 0 && world.getBlock(i6 + 1, i4, k5) == Blocks.air)
							{
								func_48198_a(world, i6 + 1, i4, k5, 2);
							}
							if (random.nextInt(4) == 0 && world.getBlock(i6, i4, k5 - 1) == Blocks.air)
							{
								func_48198_a(world, i6, i4, k5 - 1, 1);
							}
							if (random.nextInt(4) == 0 && world.getBlock(i6, i4, k5 + 1) == Blocks.air)
							{
								func_48198_a(world, i6, i4, k5 + 1, 4);
							}
						}
					}
				}
			}
		}
		return true;
	}

	private void func_48198_a(World world, int xCoord, int yCoord, int zCoord, int par5)
	{
		world.setBlock(xCoord, yCoord, zCoord, Blocks.vine, par5, 2);
		for (int i = 4; world.getBlock(xCoord, --yCoord, zCoord) == Blocks.air && i > 0; --i)
		{
			world.setBlock(xCoord, yCoord, zCoord, Blocks.vine, par5, 2);
		}
	}
}
