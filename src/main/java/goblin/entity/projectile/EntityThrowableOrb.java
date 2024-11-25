
package goblin.entity.projectile;

import net.minecraft.entity.*;
import cpw.mods.fml.relauncher.*;
import goblin.Goblins;
import goblin.entity.IGoblinEntityTextureBase;
import net.minecraft.init.*;
import net.minecraft.world.*;
import net.minecraft.block.*;
import net.minecraft.entity.monster.*;
import net.minecraft.enchantment.*;
import net.minecraft.entity.player.*;
import net.minecraft.network.play.server.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import java.util.*;
import net.minecraft.nbt.*;
import net.minecraft.item.*;

public class EntityThrowableOrb extends Entity implements IProjectile {
	protected int xTile;
	protected int yTile;
	protected int zTile;
	protected int inTile;
	protected int inData;
	protected boolean inGround;
	public Entity shootingEntity;
	private int ticksInGround;
	protected int ticksInAir;
	private double damage;
	private int knockbackStrength;

	public EntityThrowableOrb(World world)
	{
		super(world);
		xTile = -1;
		yTile = -1;
		zTile = -1;
		damage = 2.0;
		renderDistanceWeight = 10.0;
		setSize(0.5f, 0.5f);
	}

	public EntityThrowableOrb(World world, double x, double y, double z)
	{
		super(world);
		xTile = -1;
		yTile = -1;
		zTile = -1;
		damage = 2.0;
		renderDistanceWeight = 10.0;
		setSize(0.5f, 0.5f);
		setPosition(x, y, z);
		yOffset = 0.0f;
	}

	public EntityThrowableOrb(World world, EntityLivingBase entityLivingBaseThrower, EntityLivingBase entityLivingBaseTarget, float par4, float par5)
	{
		super(world);
		xTile = -1;
		yTile = -1;
		zTile = -1;
		damage = 2.0;
		renderDistanceWeight = 10.0;
		shootingEntity = (Entity) entityLivingBaseThrower;
		posY = entityLivingBaseThrower.posY + entityLivingBaseThrower.getEyeHeight() - 0.10000000149011612;
		double d0 = entityLivingBaseTarget.posX - entityLivingBaseThrower.posX;
		double d2 = entityLivingBaseTarget.boundingBox.minY + entityLivingBaseTarget.height / 3.0f - posY;
		double d3 = entityLivingBaseTarget.posZ - entityLivingBaseThrower.posZ;
		double d4 = MathHelper.sqrt_double(d0 * d0 + d3 * d3);
		if (d4 >= 1.0E-7)
		{
			float f2 = (float) (Math.atan2(d3, d0) * 180.0 / Math.PI) - 90.0f;
			float f3 = (float) (-(Math.atan2(d2, d4) * 180.0 / Math.PI));
			double d5 = d0 / d4;
			double d6 = d3 / d4;
			setLocationAndAngles(entityLivingBaseThrower.posX + d5, posY, entityLivingBaseThrower.posZ + d6, f2, f3);
			yOffset = 0.0f;
			float f4 = (float) d4 * 0.2f;
			setThrowableHeading(d0, d2 + f4, d3, par4, par5);
		}
	}

	public EntityThrowableOrb(World world, EntityLivingBase entityLivingBase, float speedMultiplier)
	{
		super(world);
		xTile = -1;
		yTile = -1;
		zTile = -1;
		damage = 2.0;
		renderDistanceWeight = 10.0;
		shootingEntity = (Entity) entityLivingBase;
		setSize(0.5f, 0.5f);
		setLocationAndAngles(entityLivingBase.posX, entityLivingBase.posY + entityLivingBase.getEyeHeight(), entityLivingBase.posZ, entityLivingBase.rotationYaw, entityLivingBase.rotationPitch);
		posX -= MathHelper.cos(rotationYaw / 180.0f * 3.1415927f) * 0.16f;
		posY -= 0.10000000149011612;
		posZ -= MathHelper.sin(rotationYaw / 180.0f * 3.1415927f) * 0.16f;
		setPosition(posX, posY, posZ);
		yOffset = 0.0f;
		motionX = -MathHelper.sin(rotationYaw / 180.0f * 3.1415927f) * MathHelper.cos(rotationPitch / 180.0f * 3.1415927f);
		motionZ = MathHelper.cos(rotationYaw / 180.0f * 3.1415927f) * MathHelper.cos(rotationPitch / 180.0f * 3.1415927f);
		motionY = -MathHelper.sin(rotationPitch / 180.0f * 3.1415927f);
		setThrowableHeading(motionX, motionY, motionZ, speedMultiplier * 1.5f, 1.0f);
	}
	
	protected String getTrailParticleStringName()
	{
		return "";
	}
	
	protected int getTrailParticleSpawnDelay()
	{
		return 1;
	}

	protected void entityInit()
	{
		dataWatcher.addObject(16, Byte.valueOf((byte) 0));
	}

	public void setThrowableHeading(double x, double y, double z, float multiplier, float offset)
	{
		float f2 = MathHelper.sqrt_double(x * x + y * y + z * z);
		x /= f2;
		y /= f2;
		z /= f2;
		x += rand.nextGaussian() * (rand.nextBoolean() ? -1 : 1) * 0.007499999832361937 * offset;
		y += rand.nextGaussian() * (rand.nextBoolean() ? -1 : 1) * 0.007499999832361937 * offset;
		z += rand.nextGaussian() * (rand.nextBoolean() ? -1 : 1) * 0.007499999832361937 * offset;
		x *= multiplier;
		y *= multiplier;
		z *= multiplier;
		motionX = x;
		motionY = y;
		motionZ = z;
		float f3 = MathHelper.sqrt_double(x * x + z * z);
		float n = (float) (Math.atan2(x, z) * 180.0 / Math.PI);
		rotationYaw = n;
		prevRotationYaw = n;
		float n2 = (float) (Math.atan2(y, f3) * 180.0 / Math.PI);
		rotationPitch = n2;
		prevRotationPitch = n2;
		ticksInGround = 0;
	}

	@SideOnly(Side.CLIENT)
	public void setPositionAndRotation2(double x, double y, double z, float rotationYaw, float rotationPitch, int par9)
	{
		setPosition(x, y, z);
		setRotation(rotationYaw, rotationPitch);
	}

	@SideOnly(Side.CLIENT)
	public void setVelocity(double xMotionToSet, double yMotionToSet, double zMotionToSet)
	{
		motionX = xMotionToSet;
		motionY = yMotionToSet;
		motionZ = zMotionToSet;
		if (prevRotationPitch == 0.0f && prevRotationYaw == 0.0f)
		{
			float f = MathHelper.sqrt_double(xMotionToSet * xMotionToSet + zMotionToSet * zMotionToSet);
			float n = (float) (Math.atan2(xMotionToSet, zMotionToSet) * 180.0 / Math.PI);
			rotationYaw = n;
			prevRotationYaw = n;
			float n2 = (float) (Math.atan2(yMotionToSet, f) * 180.0 / Math.PI);
			rotationPitch = n2;
			prevRotationPitch = n2;
			prevRotationPitch = rotationPitch;
			prevRotationYaw = rotationYaw;
			setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
			ticksInGround = 0;
		}
	}

	public void onUpdate()
	{
		super.onUpdate();
		if (getTrailParticleStringName().length() > 0) //not empty string
		{
			for (int index = 0; index < getTrailParticleSpawnDelay(); ++index)
			{
				worldObj.spawnParticle(getTrailParticleStringName(), posX, posY, posZ, 0.0, 0.0, 0.0);
			}
		}
		if (prevRotationPitch == 0.0f && prevRotationYaw == 0.0f)
		{
			float f = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
			float n = (float) (Math.atan2(motionX, motionZ) * 180.0 / Math.PI);
			rotationYaw = n;
			prevRotationYaw = n;
			float n2 = (float) (Math.atan2(motionY, f) * 180.0 / Math.PI);
			rotationPitch = n2;
			prevRotationPitch = n2;
		}
		Block block = worldObj.getBlock(xTile, yTile, zTile);
		if (block != Blocks.air)
		{
			block.setBlockBoundsBasedOnState((IBlockAccess) worldObj, xTile, yTile, zTile);
			AxisAlignedBB axisalignedbb = block.getCollisionBoundingBoxFromPool(worldObj, xTile, yTile, zTile);
			if (axisalignedbb != null && axisalignedbb.isVecInside(Vec3.createVectorHelper(posX, posY, posZ)))
			{
				inGround = true;
			}
		}
		if (inGround)
		{
			Block block1 = worldObj.getBlock(xTile, yTile, zTile);
			int block1Metadata = worldObj.getBlockMetadata(xTile, yTile, zTile);
			if (Block.getIdFromBlock(block1) == inTile && block1Metadata == inData)
			{
				++ticksInGround;
				if (ticksInGround == 1200)
				{
					setDead();
				}
			}
			else
			{
				inGround = false;
				motionX *= rand.nextFloat() * 0.2f;
				motionY *= rand.nextFloat() * 0.2f;
				motionZ *= rand.nextFloat() * 0.2f;
				ticksInGround = 0;
				ticksInAir = 0;
			}
		}
		else
		{
			++ticksInAir;
			Vec3 vec3 = Vec3.createVectorHelper(posX, posY, posZ);
			Vec3 vec4 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
			MovingObjectPosition movingObjectPosition = worldObj.func_147447_a(vec4, vec3, false, true, false);
			vec3 = Vec3.createVectorHelper(posX, posY, posZ);
			vec4 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
			if (movingObjectPosition != null)
			{
				vec4 = Vec3.createVectorHelper(movingObjectPosition.hitVec.xCoord, movingObjectPosition.hitVec.yCoord, movingObjectPosition.hitVec.zCoord);
			}
			Entity entity = null;
			List listOfEntitiesNearby = worldObj.getEntitiesWithinAABBExcludingEntity((Entity) this, boundingBox.addCoord(motionX, motionY, motionZ).expand(1.0, 1.0, 1.0));
			double d0 = 0.0;
			for (int index = 0; index < listOfEntitiesNearby.size(); ++index)
			{
				Entity entity2 = (Entity) listOfEntitiesNearby.get(index);
				if (entity2.canBeCollidedWith() && (entity2 != shootingEntity || ticksInAir >= 5))
				{
					float f2 = 0.3f;
					AxisAlignedBB axisalignedbb2 = entity2.boundingBox.expand((double) f2, (double) f2, (double) f2);
					MovingObjectPosition movingObjectPosition2 = axisalignedbb2.calculateIntercept(vec3, vec4);
					if (movingObjectPosition2 != null)
					{
						double d2 = vec3.distanceTo(movingObjectPosition2.hitVec);
						if (d2 < d0 || d0 == 0.0)
						{
							entity = entity2;
							d0 = d2;
						}
					}
				}
			}
			if (entity != null)
			{
				movingObjectPosition = new MovingObjectPosition(entity);
			}
			if (movingObjectPosition != null && movingObjectPosition.entityHit != null && movingObjectPosition.entityHit instanceof EntityPlayer)
			{
				EntityPlayer entityPlayer = (EntityPlayer) movingObjectPosition.entityHit;
				if (entityPlayer.capabilities.disableDamage || (shootingEntity instanceof EntityPlayer && !((EntityPlayer) shootingEntity).canAttackPlayer(entityPlayer)))
				{
					movingObjectPosition = null;
				}
			}
			if (movingObjectPosition != null)
			{
				onImpact(movingObjectPosition);
			}
			if (getIsCritical())
			{
				for (int l = 0; l < 4; ++l)
				{
					worldObj.spawnParticle("crit", posX + motionX * l / 4.0, posY + motionY * l / 4.0, posZ + motionZ * l / 4.0, -motionX, -motionY + 0.2, -motionZ);
				}
			}
			posX += motionX;
			posY += motionY;
			posZ += motionZ;
			float f3 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
			rotationYaw = (float) (Math.atan2(motionX, motionZ) * 180.0 / Math.PI);
			rotationPitch = (float) (Math.atan2(motionY, f3) * 180.0 / Math.PI);
			while (rotationPitch - prevRotationPitch < -180.0f)
			{
				prevRotationPitch -= 360.0f;
			}
			while (rotationPitch - prevRotationPitch >= 180.0f)
			{
				prevRotationPitch += 360.0f;
			}
			while (rotationYaw - prevRotationYaw < -180.0f)
			{
				prevRotationYaw -= 360.0f;
			}
			while (rotationYaw - prevRotationYaw >= 180.0f)
			{
				prevRotationYaw += 360.0f;
			}
			rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 0.2f;
			rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2f;
			float f7 = 0.99f;
			float f2 = 0.05f;
			if (isInWater())
			{
				for (int j2 = 0; j2 < 4; ++j2)
				{
					float f6 = 0.25f;
					worldObj.spawnParticle("bubble", posX - motionX * f6, posY - motionY * f6, posZ - motionZ * f6, motionX, motionY, motionZ);
				}
				f7 = 0.8f;
			}
			motionX *= f7;
			motionY *= f7;
			motionZ *= f7;
			motionY -= f2;
			setPosition(posX, posY, posZ);
			func_145775_I();
		}
	}

	protected void onImpact(MovingObjectPosition movingObjectPosition)
	{
	}

	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		par1NBTTagCompound.setShort("xTile", (short) xTile);
		par1NBTTagCompound.setShort("yTile", (short) yTile);
		par1NBTTagCompound.setShort("zTile", (short) zTile);
		par1NBTTagCompound.setByte("inTile", (byte) inTile);
		par1NBTTagCompound.setByte("inData", (byte) inData);
		par1NBTTagCompound.setByte("inGround", (byte) (byte) (inGround ? 1 : 0));
		par1NBTTagCompound.setDouble("damage", damage);
	}

	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		xTile = par1NBTTagCompound.getShort("xTile");
		yTile = par1NBTTagCompound.getShort("yTile");
		zTile = par1NBTTagCompound.getShort("zTile");
		inTile = (par1NBTTagCompound.getByte("inTile") & 0xFF);
		inData = (par1NBTTagCompound.getByte("inData") & 0xFF);
		inGround = (par1NBTTagCompound.getByte("inGround") == 1);
		if (par1NBTTagCompound.hasKey("damage"))
		{
			damage = par1NBTTagCompound.getDouble("damage");
		}
	}

	protected boolean canTriggerWalking()
	{
		return false;
	}

	@SideOnly(Side.CLIENT)
	public float getShadowSize()
	{
		return 0.0f;
	}

	public double getDamage()
	{
		return damage;
	}

	protected double getKnockbackStrength()
	{
		return knockbackStrength;
	}

	public boolean canAttackWithItem()
	{
		return false;
	}

	public void setIsCritical(boolean par1)
	{
		byte b0 = dataWatcher.getWatchableObjectByte(16);
		if (par1)
		{
			dataWatcher.updateObject(16, Byte.valueOf((byte) (b0 | 1)));
		}
		else
		{
			dataWatcher.updateObject(16, Byte.valueOf((byte) (b0 & -2)));
		}
	}

	public boolean getIsCritical()
	{
		byte b0 = dataWatcher.getWatchableObjectByte(16);
		return (b0 & 1) != 0;
	}
}