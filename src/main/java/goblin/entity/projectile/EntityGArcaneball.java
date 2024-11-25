
package goblin.entity.projectile;

import java.util.List;

import goblin.entity.IGoblinEntityTextureBase;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityGArcaneball extends Entity implements IGoblinEntityTextureBase {
	public EntityLiving shootingEntity;
	private int field_9402_e;
	private int field_9401_f;
	private int field_9400_g;
	private int field_9399_h;
	private boolean field_9398_i;
	public int field_9406_a;
	public EntityLiving field_9397_j;
	private int field_9396_k;
	private int field_9395_l;
	public double field_9405_b;
	public double field_9404_c;
	public double field_9403_d;

	public EntityGArcaneball(World world)
	{
		super(world);
		field_9402_e = -1;
		field_9401_f = -1;
		field_9400_g = -1;
		field_9399_h = 0;
		field_9398_i = false;
		field_9406_a = 0;
		field_9395_l = 0;
		setSize(1.0f, 1.0f);
	}

	protected void entityInit()
	{
	}

	public boolean isInRangeToRenderDist(double d)
	{
		double d2 = boundingBox.getAverageEdgeLength() * 4.0;
		d2 *= 64.0;
		return d < d2 * d2;
	}

	public EntityGArcaneball(World world, double d, double d1, double d2, double d3, double d4, double d5)
	{
		super(world);
		field_9402_e = -1;
		field_9401_f = -1;
		field_9400_g = -1;
		field_9399_h = 0;
		field_9398_i = false;
		field_9406_a = 0;
		field_9395_l = 0;
		setSize(1.0f, 1.0f);
		setLocationAndAngles(d, d1, d2, rotationYaw, rotationPitch);
		setPosition(d, d1, d2);
		double d6 = MathHelper.sqrt_double(d3 * d3 + d4 * d4 + d5 * d5);
		field_9405_b = d3 / d6 * 0.1;
		field_9404_c = d4 / d6 * 0.1;
		field_9403_d = d5 / d6 * 0.1;
	}

	public EntityGArcaneball(World world, EntityLiving entityLiving, double d, double d1, double d2)
	{
		super(world);
		field_9402_e = -1;
		field_9401_f = -1;
		field_9400_g = -1;
		field_9399_h = 0;
		field_9398_i = false;
		field_9406_a = 0;
		field_9395_l = 0;
		field_9397_j = entityLiving;
		setSize(1.0f, 1.0f);
		setLocationAndAngles(entityLiving.posX, entityLiving.posY, entityLiving.posZ, entityLiving.rotationYaw, entityLiving.rotationPitch);
		setPosition(posX, posY, posZ);
		yOffset = 0.0f;
		double motionX = 0.0;
		this.motionZ = motionX;
		this.motionY = motionX;
		this.motionX = motionX;
		d += rand.nextGaussian() * 0.4;
		d1 += rand.nextGaussian() * 0.4;
		d2 += rand.nextGaussian() * 0.4;
		double d3 = MathHelper.sqrt_double(d * d + d1 * d1 + d2 * d2);
		field_9405_b = d / d3 * 0.1;
		field_9404_c = d1 / d3 * 0.1;
		field_9403_d = d2 / d3 * 0.1;
	}

	public void onUpdate()
	{
		super.onUpdate();
		if (field_9406_a > 0)
		{
			--field_9406_a;
		}
		if (field_9398_i)
		{
			Block i = worldObj.getBlock(field_9402_e, field_9401_f, field_9400_g);
			if (Block.getIdFromBlock(i) == field_9399_h)
			{
				++field_9396_k;
				if (field_9396_k == 1200)
				{
					setDead();
				}
				return;
			}
			field_9398_i = false;
			motionX *= rand.nextFloat() * 0.2f;
			motionY *= rand.nextFloat() * 0.2f;
			motionZ *= rand.nextFloat() * 0.2f;
			field_9396_k = 0;
			field_9395_l = 0;
		}
		else
		{
			++field_9395_l;
		}
		Vec3 vec3d = Vec3.createVectorHelper(posX, posY, posZ);
		Vec3 vec3d2 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
		MovingObjectPosition movingObjectPosition = worldObj.rayTraceBlocks(vec3d, vec3d2);
		vec3d = Vec3.createVectorHelper(posX, posY, posZ);
		vec3d2 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
		if (movingObjectPosition != null)
		{
			vec3d2 = Vec3.createVectorHelper(movingObjectPosition.hitVec.xCoord, movingObjectPosition.hitVec.yCoord, movingObjectPosition.hitVec.zCoord);
		}
		Entity entity = null;
		List list = worldObj.getEntitiesWithinAABBExcludingEntity((Entity) this, boundingBox.addCoord(motionX, motionY, motionZ).expand(1.0, 1.0, 1.0));
		double d = 0.0;
		for (int j = 0; j < list.size(); ++j)
		{
			Entity entity2 = (Entity) list.get(j);
			if (entity2.canBeCollidedWith())
			{
				if (entity2 != field_9397_j || field_9395_l >= 25)
				{
					float f2 = 0.3f;
					AxisAlignedBB axisalignedbb = entity2.boundingBox.expand((double) f2, (double) f2, (double) f2);
					MovingObjectPosition movingObjectPosition2 = axisalignedbb.calculateIntercept(vec3d, vec3d2);
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
			if (!worldObj.isRemote && movingObjectPosition.entityHit != null)
			{
				DamageSource damageSource = null;
				if (shootingEntity == null)
				{
					damageSource = DamageSource.causeThrownDamage((Entity) this, (Entity) this);
				}
				else
				{
					damageSource = DamageSource.causeThrownDamage((Entity) this, (Entity) shootingEntity);
				}
				if (!movingObjectPosition.entityHit.attackEntityFrom(damageSource, 6.0f))
				{
				}
			}
			setDead();
		}
		posX += motionX;
		posY += motionY;
		posZ += motionZ;
		float f3 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
		rotationYaw = (float) (Math.atan2(motionX, motionZ) * 180.0 /Math.PI);
		rotationPitch = (float) (Math.atan2(motionY, f3) * 180.0 /Math.PI);
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
		float f4 = 0.95f;
		if (isInWater())
		{
			for (int k = 0; k < 4; ++k)
			{
				float f5 = 0.25f;
				worldObj.spawnParticle("bubble", posX - motionX * f5, posY - motionY * f5, posZ - motionZ * f5, motionX, motionY, motionZ);
			}
			f4 = 0.8f;
		}
		motionX += field_9405_b;
		motionY += field_9404_c;
		motionZ += field_9403_d;
		motionX *= f4;
		motionY *= f4;
		motionZ *= f4;
		worldObj.spawnParticle("smoke", posX, posY + 0.5, posZ, 0.0, 0.0, 0.0);
		setPosition(posX, posY, posZ);
	}

	public void writeEntityToNBT(NBTTagCompound nbtTagCompound)
	{
		nbtTagCompound.setShort("xTile", (short) field_9402_e);
		nbtTagCompound.setShort("yTile", (short) field_9401_f);
		nbtTagCompound.setShort("zTile", (short) field_9400_g);
		nbtTagCompound.setByte("inTile", (byte) field_9399_h);
		nbtTagCompound.setByte("shake", (byte) field_9406_a);
		nbtTagCompound.setByte("inGround", (byte) (byte) (field_9398_i ? 1 : 0));
	}

	public void readEntityFromNBT(NBTTagCompound nbtTagCompound)
	{
		field_9402_e = nbtTagCompound.getShort("xTile");
		field_9401_f = nbtTagCompound.getShort("yTile");
		field_9400_g = nbtTagCompound.getShort("zTile");
		field_9399_h = (nbtTagCompound.getByte("inTile") & 0xFF);
		field_9406_a = (nbtTagCompound.getByte("shake") & 0xFF);
		field_9398_i = (nbtTagCompound.getByte("inGround") == 1);
	}

	public boolean canBeCollidedWith()
	{
		return true;
	}

	public float getCollisionBorderSize()
	{
		return 1.0f;
	}

	public boolean attackEntityFrom(Entity entity, int i)
	{
		setBeenAttacked();
		if (entity != null)
		{
			Vec3 vec3d = entity.getLookVec();
			if (vec3d != null)
			{
				motionX = vec3d.xCoord;
				motionY = vec3d.yCoord;
				motionZ = vec3d.zCoord;
				field_9405_b = motionX * 0.1;
				field_9404_c = motionY * 0.1;
				field_9403_d = motionZ * 0.1;
			}
			return true;
		}
		return false;
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
