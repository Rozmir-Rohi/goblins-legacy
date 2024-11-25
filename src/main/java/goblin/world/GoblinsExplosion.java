
package goblin.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class GoblinsExplosion extends Explosion {
	public boolean isFlaming;
	private int field_77289_h;
	private Random explosionRNG;
	private World worldObj;
	public double explosionX;
	public double explosionY;
	public double explosionZ;
	public Entity exploder;
	public float explosionSize;
	public List affectedBlockPositions;
	private Map field_77288_k;

	public GoblinsExplosion(World world, Entity entity, double xCoord, double yCoord, double zCoord, float par9)
	{
		super(world, entity, xCoord, yCoord, zCoord, par9);
		isFlaming = false;
		field_77289_h = 16;
		explosionRNG = new Random();
		affectedBlockPositions = new ArrayList();
		field_77288_k = new HashMap();
		worldObj = world;
		exploder = entity;
		explosionSize = par9;
		explosionX = xCoord;
		explosionY = yCoord;
		explosionZ = zCoord;
	}

	public void doExplosionA()
	{
		float var1 = explosionSize;
		HashSet var2 = new HashSet();
		affectedBlockPositions.addAll(var2);
		explosionSize *= 2.0f;
		int var3 = MathHelper.floor_double(explosionX - explosionSize - 1.0);
		int var4 = MathHelper.floor_double(explosionX + explosionSize + 1.0);
		int var5 = MathHelper.floor_double(explosionY - explosionSize - 1.0);
		int var6 = MathHelper.floor_double(explosionY + explosionSize + 1.0);
		int var7 = MathHelper.floor_double(explosionZ - explosionSize - 1.0);
		int var8 = MathHelper.floor_double(explosionZ + explosionSize + 1.0);
		List var9 = worldObj.getEntitiesWithinAABBExcludingEntity(exploder, AxisAlignedBB.getBoundingBox((double) var3, (double) var5, (double) var7, (double) var4, (double) var6, (double) var8));
		Vec3 var10 = Vec3.createVectorHelper(explosionX, explosionY, explosionZ);
		for (int var11 = 0; var11 < var9.size(); ++var11)
		{
			Entity var12 = (Entity) var9.get(var11);
			double var13 = var12.getDistance(explosionX, explosionY, explosionZ) / explosionSize;
			if (var13 <= 1.0)
			{
				double var14 = var12.posX - explosionX;
				double var15 = var12.posY + var12.getEyeHeight() - explosionY;
				double var16 = var12.posZ - explosionZ;
				double var17 = MathHelper.sqrt_double(var14 * var14 + var15 * var15 + var16 * var16);
				if (var17 != 0.0)
				{
					var14 /= var17;
					var15 /= var17;
					var16 /= var17;
					double var18 = worldObj.getBlockDensity(var10, var12.boundingBox);
					double var19 = (1.0 - var13) * var18;
					var12.attackEntityFrom(DamageSource.setExplosionSource((Explosion) this), (float) (int) ((var19 * var19 + var19) / 4.0 * 8.0 * explosionSize + 1.0));
					Entity entity = var12;
					entity.motionX += var14 * var19;
					Entity entity2 = var12;
					entity2.motionY += var15 * var19;
					Entity entity3 = var12;
					entity3.motionZ += var16 * var19;
					if (var12 instanceof EntityPlayer)
					{
						field_77288_k.put(var12, Vec3.createVectorHelper(var14 * var19, var15 * var19, var16 * var19));
					}
				}
			}
		}
		explosionSize = var1;
	}

	public void doExplosionB(boolean flag)
	{
		worldObj.playSoundEffect(explosionX, explosionY, explosionZ, "random.explode", 4.0f, (1.0f + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.2f) * 0.7f);
		worldObj.spawnParticle("hugeexplosion", explosionX, explosionY, explosionZ, 0.0, 0.0, 0.0);

		if (explosionSize >= 2.0F && isSmoking)
		{
			worldObj.spawnParticle("hugeexplosion", explosionX, explosionY, explosionZ, 1.0D, 0.0D, 0.0D);
		}
		else
		{
			worldObj.spawnParticle("largeexplode", explosionX, explosionY, explosionZ, 1.0D, 0.0D, 0.0D);
		}

		Iterator iterator;
		ChunkPosition chunkposition;
		int i;
		int j;
		int k;
		Block block;

		if (isSmoking)
		{
			iterator = affectedBlockPositions.iterator();

			while (iterator.hasNext())
			{
				chunkposition = (ChunkPosition) iterator.next();
				i = chunkposition.chunkPosX;
				j = chunkposition.chunkPosY;
				k = chunkposition.chunkPosZ;
				block = worldObj.getBlock(i, j, k);

				if (flag)
				{
					double d0 = (double) ((float) i + worldObj.rand.nextFloat());
					double d1 = (double) ((float) j + worldObj.rand.nextFloat());
					double d2 = (double) ((float) k + worldObj.rand.nextFloat());
					double d3 = d0 - explosionX;
					double d4 = d1 - explosionY;
					double d5 = d2 - explosionZ;
					double d6 = (double) MathHelper.sqrt_double(d3 * d3 + d4 * d4 + d5 * d5);
					d3 /= d6;
					d4 /= d6;
					d5 /= d6;
					double d7 = 0.5D / (d6 / (double) explosionSize + 0.1D);
					d7 *= (double) (worldObj.rand.nextFloat() * worldObj.rand.nextFloat() + 0.3F);
					d3 *= d7;
					d4 *= d7;
					d5 *= d7;
					worldObj.spawnParticle("explode", (d0 + explosionX * 1.0D) / 2.0D, (d1 + explosionY * 1.0D) / 2.0D, (d2 + explosionZ * 1.0D) / 2.0D, d3, d4, d5);
					worldObj.spawnParticle("smoke", d0, d1, d2, d3, d4, d5);
				}

				if (block.getMaterial() != Material.air)
				{
					if (block.canDropFromExplosion(this))
					{
						block.dropBlockAsItemWithChance(worldObj, i, j, k, worldObj.getBlockMetadata(i, j, k), 1.0F / explosionSize, 0);
					}

					block.onBlockExploded(worldObj, i, j, k, this);
				}
			}
		}

		if (isFlaming)
		{
			iterator = affectedBlockPositions.iterator();

			while (iterator.hasNext())
			{
				chunkposition = (ChunkPosition) iterator.next();
				i = chunkposition.chunkPosX;
				j = chunkposition.chunkPosY;
				k = chunkposition.chunkPosZ;
				block = worldObj.getBlock(i, j, k);
				Block block1 = worldObj.getBlock(i, j - 1, k);

				if (block.getMaterial() == Material.air && block1.func_149730_j() && explosionRNG.nextInt(3) == 0)
				{
					worldObj.setBlock(i, j, k, Blocks.fire);
				}
			}
		}
	}

	public Map func_77277_b()
	{
		return field_77288_k;
	}
}
