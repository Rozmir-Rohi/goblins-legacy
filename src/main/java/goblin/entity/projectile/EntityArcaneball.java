
package goblin.entity.projectile;

import net.minecraft.entity.player.*;
import net.minecraft.init.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import net.minecraft.block.*;
import net.minecraft.util.*;
import java.util.*;

import goblin.entity.IGoblinEntityTextureBase;
import net.minecraft.nbt.*;

public class EntityArcaneball extends Entity implements IGoblinEntityTextureBase {
	private int xTile;
	private int yTile;
	private int zTile;
	private int inTile;
	private int inData;
	private boolean inGround;
	public boolean doesArrowBelongToPlayer;
	public int arrowShake;
	public Entity shootingEntity;
	private int ticksInGround;
	private int ticksInAir;
	public boolean arrowCritical;

	public EntityArcaneball(World world)
	{
		super(world);
		xTile = -1;
		yTile = -1;
		zTile = -1;
		inTile = 0;
		inData = 0;
		inGround = false;
		doesArrowBelongToPlayer = false;
		arrowShake = 0;
		ticksInAir = 0;
		arrowCritical = false;
		setSize(0.5f, 0.5f);
	}

	public EntityArcaneball(World world, double d, double d1, double d2)
	{
		super(world);
		xTile = -1;
		yTile = -1;
		zTile = -1;
		inTile = 0;
		inData = 0;
		inGround = false;
		doesArrowBelongToPlayer = false;
		arrowShake = 0;
		ticksInAir = 0;
		arrowCritical = false;
		setSize(0.5f, 0.5f);
		setPosition(d, d1, d2);
		yOffset = 0.0f;
	}

	public EntityArcaneball(World world, EntityLivingBase entityLivingBase, float f)
	{
		super(world);
		xTile = -1;
		yTile = -1;
		zTile = -1;
		inTile = 0;
		inData = 0;
		inGround = false;
		doesArrowBelongToPlayer = false;
		arrowShake = 0;
		ticksInAir = 0;
		arrowCritical = false;
		shootingEntity = (Entity) entityLivingBase;
		doesArrowBelongToPlayer = (entityLivingBase instanceof EntityPlayer);
		setSize(0.5f, 0.5f);
		setLocationAndAngles(entityLivingBase.posX, entityLivingBase.posY + entityLivingBase.getEyeHeight(), entityLivingBase.posZ, entityLivingBase.rotationYaw, entityLivingBase.rotationPitch);
		posX -= MathHelper.cos(rotationYaw / 180.0f * 3.141593f) * 0.16f;
		posY -= 0.10000000149011612;
		posZ -= MathHelper.sin(rotationYaw / 180.0f * 3.141593f) * 0.16f;
		setPosition(posX, posY, posZ);
		yOffset = 0.0f;
		motionX = -MathHelper.sin(rotationYaw / 180.0f * 3.141593f) * MathHelper.cos(rotationPitch / 180.0f * 3.141593f);
		motionZ = MathHelper.cos(rotationYaw / 180.0f * 3.141593f) * MathHelper.cos(rotationPitch / 180.0f * 3.141593f);
		motionY = -MathHelper.sin(rotationPitch / 180.0f * 3.141593f);
		setArrowHeading(motionX, motionY, motionZ, f * 1.5f, 1.0f);
	}

	protected void entityInit()
	{
	}

	public void setArrowHeading(double xCoord, double yCoord, double zCoord, float f, float f1)
	{
		float f2 = MathHelper.sqrt_double(xCoord * xCoord + yCoord * yCoord + zCoord * zCoord);
		xCoord /= f2;
		yCoord /= f2;
		zCoord /= f2;
		xCoord += rand.nextGaussian() * 0.007499999832361937 * f1;
		yCoord += rand.nextGaussian() * 0.007499999832361937 * f1;
		zCoord += rand.nextGaussian() * 0.007499999832361937 * f1;
		xCoord *= f;
		yCoord *= f;
		zCoord *= f;
		motionX = xCoord;
		motionY = yCoord;
		motionZ = zCoord;
		float f3 = MathHelper.sqrt_double(xCoord * xCoord + zCoord * zCoord);
		float n = (float) (Math.atan2(xCoord, zCoord) * 180.0 /Math.PI);
		rotationYaw = n;
		prevRotationYaw = n;
		float n2 = (float) (Math.atan2(yCoord, f3) * 180.0 /Math.PI);
		rotationPitch = n2;
		prevRotationPitch = n2;
		ticksInGround = 0;
	}

	public void setVelocity(double d, double d1, double d2)
	{
		motionX = d;
		motionY = d1;
		motionZ = d2;
		if (prevRotationPitch == 0.0f && prevRotationYaw == 0.0f)
		{
			float f = MathHelper.sqrt_double(d * d + d2 * d2);
			float n = (float) (Math.atan2(d, d2) * 180.0 /Math.PI);
			rotationYaw = n;
			prevRotationYaw = n;
			float n2 = (float) (Math.atan2(d1, f) * 180.0 /Math.PI);
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
		if (prevRotationPitch == 0.0f && prevRotationYaw == 0.0f)
		{
			float f = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
			float n = (float) (Math.atan2(motionX, motionZ) * 180.0 /Math.PI);
			rotationYaw = n;
			prevRotationYaw = n;
			float n2 = (float) (Math.atan2(motionY, f) * 180.0 /Math.PI);
			rotationPitch = n2;
			prevRotationPitch = n2;
		}
		Block i = worldObj.getBlock(xTile, yTile, zTile);
		if (i != Blocks.air)
		{
			i.setBlockBoundsBasedOnState((IBlockAccess) worldObj, xTile, yTile, zTile);
			AxisAlignedBB axisalignedbb = i.getCollisionBoundingBoxFromPool(worldObj, xTile, yTile, zTile);
			if (axisalignedbb != null && axisalignedbb.isVecInside(Vec3.createVectorHelper(posX, posY, posZ)))
			{
				inGround = true;
			}
		}
		if (arrowShake > 0)
		{
			--arrowShake;
		}
		if (inGround)
		{
			setDead();
			return;
		}
		++ticksInAir;
		Vec3 vec3d = Vec3.createVectorHelper(posX, posY, posZ);
		Vec3 vec3d2 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
		MovingObjectPosition movingObjectPosition = worldObj.func_147447_a(vec3d2, vec3d, false, true, false);
		vec3d = Vec3.createVectorHelper(posX, posY, posZ);
		vec3d2 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
		if (movingObjectPosition != null)
		{
			vec3d2 = Vec3.createVectorHelper(movingObjectPosition.hitVec.xCoord, movingObjectPosition.hitVec.yCoord, movingObjectPosition.hitVec.zCoord);
		}
		Entity entity = null;
		List list = worldObj.getEntitiesWithinAABBExcludingEntity((Entity) this, boundingBox.addCoord(motionX, motionY, motionZ).expand(1.0, 1.0, 1.0));
		double d = 0.0;
		for (int l = 0; l < list.size(); ++l)
		{
			Entity entity2 = (Entity) list.get(l);
			if (entity2.canBeCollidedWith())
			{
				if (entity2 != shootingEntity || ticksInAir >= 5)
				{
					float f2 = 0.3f;
					AxisAlignedBB axisalignedbb2 = entity2.boundingBox.expand((double) f2, (double) f2, (double) f2);
					MovingObjectPosition movingObjectPosition2 = axisalignedbb2.calculateIntercept(vec3d, vec3d2);
					if (movingObjectPosition2 != null)
					{
						double d2 = vec3d.distanceTo(movingObjectPosition2.hitVec);
						if (d2 < d || d == 0.0)
						{
							entity = entity2;
							d = d2;
						}
					}
				}
			}
		}
		if (entity != null)
		{
			movingObjectPosition = new MovingObjectPosition(entity);
		}
		if (movingObjectPosition != null)
		{
			if (movingObjectPosition.entityHit != null)
			{
				float f3 = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
				int j1 = (int) Math.ceil(f3 * 2.0);
				if (arrowCritical)
				{
					j1 += rand.nextInt(j1 / 2 + 2);
				}
				DamageSource damageSource = null;
				if (shootingEntity == null)
				{
					damageSource = DamageSource.causeThrownDamage((Entity) this, (Entity) this);
				}
				else
				{
					damageSource = DamageSource.causeThrownDamage((Entity) this, shootingEntity);
				}
				if (movingObjectPosition.entityHit.attackEntityFrom(damageSource, (float) j1))
				{
					if (movingObjectPosition.entityHit instanceof EntityLiving && !worldObj.isRemote)
					{
						EntityLiving var25 = (EntityLiving) movingObjectPosition.entityHit;
						var25.setArrowCountInEntity(var25.getArrowCountInEntity() + 1);
					}
					setDead();
				}
				else
				{
					rotationYaw += 180.0f;
					prevRotationYaw += 180.0f;
					ticksInAir = 0;
				}
			}
			else
			{
				xTile = movingObjectPosition.blockX;
				yTile = movingObjectPosition.blockY;
				zTile = movingObjectPosition.blockZ;
				inTile = Block.getIdFromBlock(worldObj.getBlock(xTile, yTile, zTile));
				inData = worldObj.getBlockMetadata(xTile, yTile, zTile);
				motionX = (float) (movingObjectPosition.hitVec.xCoord - posX);
				motionY = (float) (movingObjectPosition.hitVec.yCoord - posY);
				motionZ = (float) (movingObjectPosition.hitVec.zCoord - posZ);
				float f4 = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
				worldObj.playSoundAtEntity((Entity) this, "mob.ghast.fireball", 1.0f, 1.2f / (rand.nextFloat() * 0.2f + 0.9f));
				inGround = true;
				arrowShake = 7;
			}
		}
		posX += motionX;
		posY += motionY;
		posZ += motionZ;
		float f5 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
		rotationYaw = (float) (Math.atan2(motionX, motionZ) * 180.0 /Math.PI);
		rotationPitch = (float) (Math.atan2(motionY, f5) * 180.0 /Math.PI);
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
		float f6 = 0.99f;
		float f7 = 0.05f;
		if (isInWater())
		{
			for (int k1 = 0; k1 < 4; ++k1)
			{
				float f8 = 0.25f;
				worldObj.spawnParticle("bubble", posX - motionX * f8, posY - motionY * f8, posZ - motionZ * f8, motionX, motionY, motionZ);
			}
			f6 = 0.8f;
		}
		setPosition(posX, posY, posZ);
	}

	public void writeEntityToNBT(NBTTagCompound nbtTagCompound)
	{
		nbtTagCompound.setShort("xTile", (short) xTile);
		nbtTagCompound.setShort("yTile", (short) yTile);
		nbtTagCompound.setShort("zTile", (short) zTile);
		nbtTagCompound.setByte("inTile", (byte) inTile);
		nbtTagCompound.setByte("inData", (byte) inData);
		nbtTagCompound.setByte("shake", (byte) arrowShake);
		nbtTagCompound.setByte("inGround", (byte) (byte) (inGround ? 1 : 0));
		nbtTagCompound.setBoolean("player", doesArrowBelongToPlayer);
	}

	public void readEntityFromNBT(NBTTagCompound nbtTagCompound)
	{
		xTile = nbtTagCompound.getShort("xTile");
		yTile = nbtTagCompound.getShort("yTile");
		zTile = nbtTagCompound.getShort("zTile");
		inTile = (nbtTagCompound.getByte("inTile") & 0xFF);
		inData = (nbtTagCompound.getByte("inData") & 0xFF);
		arrowShake = (nbtTagCompound.getByte("shake") & 0xFF);
		inGround = (nbtTagCompound.getByte("inGround") == 1);
		doesArrowBelongToPlayer = nbtTagCompound.getBoolean("player");
	}

	public void onCollideWithPlayer(EntityPlayer entityPlayer)
	{
		if (worldObj.isRemote)
		{
			return;
		}
		if (inGround)
		{
			setDead();
		}
	}

	public float getShadowSize()
	{
		return 0.0f;
	}

	@Override
	public ResourceLocation getEntityTexture()
	{
		return new ResourceLocation("goblin:textures/entity/Arcaneball.png");
	}
}
