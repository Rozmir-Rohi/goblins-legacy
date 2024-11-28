
package goblin.entity.projectile;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import goblin.entity.IGoblinEntityTextureBase;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class EntityBomb extends Entity implements IProjectile, IGoblinEntityTextureBase {
	private int xTile;
	private int yTile;
	private int zTile;
	private int inTile;
	private int inData;
	private boolean inGround;
	private boolean exploded;
	public int fuse = 20;
	public Entity shootingEntity;
	private int ticksInGround;
	private int ticksInAir;
	private double damage;
	private int knockbackStrength;

	public EntityBomb(World world)
	{
		super(world);
		xTile = -1;
		yTile = -1;
		zTile = -1;
		exploded = false;
		damage = 1.0;
		renderDistanceWeight = 10.0;
		setSize(0.5f, 0.5f);
	}

	public EntityBomb(World world, double par2, double par4, double par6)
	{
		super(world);
		xTile = -1;
		yTile = -1;
		zTile = -1;
		exploded = false;
		damage = 1.0;
		renderDistanceWeight = 10.0;
		setSize(0.5f, 0.5f);
		setPosition(par2, par4, par6);
		yOffset = 0.0f;
	}

	public EntityBomb(World world, EntityLivingBase entityLivingBase, EntityLivingBase entityLivingBase2, float par4, float par5)
	{
		super(world);
		xTile = -1;
		yTile = -1;
		zTile = -1;
		exploded = false;
		damage = 1.0;
		renderDistanceWeight = 10.0;
		shootingEntity = (Entity) entityLivingBase;

		posY = entityLivingBase.posY + entityLivingBase.getEyeHeight() - 0.10000000149011612;
		double d0 = entityLivingBase2.posX - entityLivingBase.posX;
		double d2 = entityLivingBase2.boundingBox.minY + entityLivingBase2.height / 3.0f - posY;
		double d3 = entityLivingBase2.posZ - entityLivingBase.posZ;
		double d4 = MathHelper.sqrt_double(d0 * d0 + d3 * d3);
		if (d4 >= 1.0E-7)
		{
			float f2 = (float) (Math.atan2(d3, d0) * 180.0 / Math.PI) - 90.0f;
			float f3 = (float) (-(Math.atan2(d2, d4) * 180.0 / Math.PI));
			double d5 = d0 / d4;
			double d6 = d3 / d4;
			setLocationAndAngles(entityLivingBase.posX + d5, posY, entityLivingBase.posZ + d6, f2, f3);
			yOffset = 0.0f;
			float f4 = (float) d4 * 0.2f;
			setThrowableHeading(d0, d2 + f4, d3, par4, par5);
		}
	}

	public EntityBomb(World world, EntityLivingBase entityLivingBase, float par3)
	{
		super(world);
		xTile = -1;
		yTile = -1;
		zTile = -1;
		exploded = false;
		damage = 1.0;
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
		setThrowableHeading(motionX, motionY, motionZ, par3 * 1.5f, 1.0f);
	}

	protected void entityInit()
	{
		dataWatcher.addObject(16, Byte.valueOf((byte) 0));
	}

	public void setThrowableHeading(double par1, double par3, double par5, float par7, float par8)
	{
		float f2 = MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5);
		par1 /= f2;
		par3 /= f2;
		par5 /= f2;
		par1 += rand.nextGaussian() * (rand.nextBoolean() ? -1 : 1) * 0.007499999832361937 * par8;
		par3 += rand.nextGaussian() * (rand.nextBoolean() ? -1 : 1) * 0.007499999832361937 * par8;
		par5 += rand.nextGaussian() * (rand.nextBoolean() ? -1 : 1) * 0.007499999832361937 * par8;
		par1 *= par7;
		par3 *= par7;
		par5 *= par7;
		motionX = par1;
		motionY = par3;
		motionZ = par5;
		float f3 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
		float n = (float) (Math.atan2(par1, par5) * 180.0 / Math.PI);
		rotationYaw = n;
		prevRotationYaw = n;
		float n2 = (float) (Math.atan2(par3, f3) * 180.0 / Math.PI);
		rotationPitch = n2;
		prevRotationPitch = n2;
		ticksInGround = 0;
	}

	@SideOnly(Side.CLIENT)
	public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9)
	{
		setPosition(par1, par3, par5);
		setRotation(par7, par8);
	}

	@SideOnly(Side.CLIENT)
	public void setVelocity(double par1, double par3, double par5)
	{
		motionX = par1;
		motionY = par3;
		motionZ = par5;
		if (prevRotationPitch == 0.0f && prevRotationYaw == 0.0f)
		{
			float f = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
			float n = (float) (Math.atan2(par1, par5) * 180.0 / Math.PI);
			rotationYaw = n;
			prevRotationYaw = n;
			float n2 = (float) (Math.atan2(par3, f) * 180.0 / Math.PI);
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
			float n = (float) (Math.atan2(motionX, motionZ) * 180.0 / Math.PI);
			rotationYaw = n;
			prevRotationYaw = n;
			float n2 = (float) (Math.atan2(motionY, f) * 180.0 / Math.PI);
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
		if (inGround)
		{
			Block j = worldObj.getBlock(xTile, yTile, zTile);
			int k = worldObj.getBlockMetadata(xTile, yTile, zTile);
			if (j == Block.getBlockById(inTile) && k == inData)
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
			List listOfNearbyEntities = worldObj.getEntitiesWithinAABBExcludingEntity((Entity) this, boundingBox.addCoord(motionX, motionY, motionZ).expand(1.0, 1.0, 1.0));
			double impactDidtance = 0.0;
			for (int index = 0; index < listOfNearbyEntities.size(); ++index)
			{
				Entity closestEntity = (Entity) listOfNearbyEntities.get(index);
				if (closestEntity.canBeCollidedWith() && (closestEntity != shootingEntity || ticksInAir >= 5))
				{
					float boundingBoxExpansionAmount = 0.3f;
					AxisAlignedBB axisalignedbb2 = closestEntity.boundingBox.expand((double) boundingBoxExpansionAmount, (double) boundingBoxExpansionAmount, (double) boundingBoxExpansionAmount);
					MovingObjectPosition movingObjectPosition2 = axisalignedbb2.calculateIntercept(vec3, vec4);
					if (movingObjectPosition2 != null)
					{
						double distanceToClosestEntity = vec3.distanceTo(movingObjectPosition2.hitVec);
						if (distanceToClosestEntity < impactDidtance || impactDidtance == 0.0)
						{
							entity = closestEntity;
							impactDidtance = distanceToClosestEntity;
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
			float horizontalDeceleration = 0.79f;
			float verticalDeceleration = 0.15f;
			if (isInWater())
			{
				for (int j2 = 0; j2 < 4; ++j2)
				{
					float f4 = 0.25f;
					worldObj.spawnParticle("bubble", posX - motionX * f4, posY - motionY * f4, posZ - motionZ * f4, motionX, motionY, motionZ);
				}
				horizontalDeceleration = 0.4f;
			}
			motionX *= horizontalDeceleration;
			motionY *= horizontalDeceleration;
			motionZ *= horizontalDeceleration;
			motionY -= verticalDeceleration;
			setPosition(posX, posY, posZ);
			func_145775_I();
		}
		if (inGround)
		{
			--fuse;
			if (fuse <= 0)
			{
				explode();
			}
		}
	}

	private void onImpact(MovingObjectPosition movingObjectPosition)
	{
		if (movingObjectPosition.entityHit != null) //hit an entity
		{
			float motionSquared = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
			int motionTimesDamage = MathHelper.ceiling_double_int(motionSquared * damage);
			DamageSource damageSource = null;
			if (shootingEntity == null)
			{
				damageSource = DamageSource.causeThrownDamage((Entity) this, (Entity) this);
			}
			else
			{
				damageSource = DamageSource.causeThrownDamage((Entity) this, shootingEntity);
			}
			if (movingObjectPosition.entityHit.attackEntityFrom(damageSource, (float) motionTimesDamage))
			{
				if (movingObjectPosition.entityHit instanceof EntityLivingBase)
				{
					EntityLivingBase entityLivingBase = (EntityLivingBase) movingObjectPosition.entityHit;
					if (knockbackStrength > 0)
					{
						float horizontalMotion = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
						if (horizontalMotion > 0.0f)
						{
							movingObjectPosition.entityHit.addVelocity(motionX * knockbackStrength * 0.6000000238418579 / horizontalMotion, 0.1, motionZ * knockbackStrength * 0.6000000238418579 / horizontalMotion);
						}
					}
					if (shootingEntity != null && shootingEntity instanceof EntityLivingBase)
					{
						EnchantmentHelper.func_151384_a(entityLivingBase, shootingEntity);
						EnchantmentHelper.func_151385_b((EntityLivingBase) shootingEntity, (Entity) entityLivingBase);
					}
					if (shootingEntity != null && movingObjectPosition.entityHit != shootingEntity && movingObjectPosition.entityHit instanceof EntityPlayer && shootingEntity instanceof EntityPlayerMP)
					{
						((EntityPlayerMP) shootingEntity).playerNetServerHandler.sendPacket((Packet) new S2BPacketChangeGameState(6, 0.0f));
					}
				}
				
			}
			else
			{
				ricochet();
			}
		}
		else //hit a block
		{
			xTile = movingObjectPosition.blockX;
			yTile = movingObjectPosition.blockY;
			zTile = movingObjectPosition.blockZ;
			Block block = worldObj.getBlock(xTile, yTile + 1, zTile);
			if (
					movingObjectPosition.sideHit == 1
					&& (
							worldObj.getBlock(xTile, yTile + 1, zTile) == Blocks.air //block above is air
							|| block.isReplaceable((IBlockAccess) worldObj, xTile, yTile + 1, zTile)
						)
				)
			{
				inTile = Block.getIdFromBlock(worldObj.getBlock(xTile, yTile, zTile));
				inData = worldObj.getBlockMetadata(xTile, yTile, zTile);
				
				motionX = (float) (movingObjectPosition.hitVec.xCoord - posX);
				motionY = (float) (movingObjectPosition.hitVec.yCoord - posY);
				motionZ = (float) (movingObjectPosition.hitVec.zCoord - posZ);
				
				float motionSquared = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
				
				posX -= motionX / motionSquared * 0.05000000074505806;
				posY -= motionY / motionSquared * 0.05000000074505806;
				posZ -= motionZ / motionSquared * 0.05000000074505806;
				
				inGround = true;
				if (inTile != 0)
				{
					Block.getBlockById(inTile).onEntityCollidedWithBlock(worldObj, xTile, yTile, zTile, (Entity) this);
				}
			}
			else
			{
				ricochet();
			}
		}
	}

	private void ricochet()
	{
		motionX *= -0.10000000149011612;
		motionY *= -0.10000000149011612;
		motionZ *= -0.10000000149011612;
		rotationYaw += 180.0f;
		prevRotationYaw += 180.0f;
		ticksInAir = 0;
	}

	public void writeEntityToNBT(NBTTagCompound nbtTagCompound)
	{
		nbtTagCompound.setShort("xTile", (short) xTile);
		nbtTagCompound.setShort("yTile", (short) yTile);
		nbtTagCompound.setShort("zTile", (short) zTile);
		nbtTagCompound.setByte("inTile", (byte) inTile);
		nbtTagCompound.setByte("inData", (byte) inData);
		nbtTagCompound.setByte("inGround", (byte) (byte) (inGround ? 1 : 0));
		
		nbtTagCompound.setDouble("damage", damage);
	}

	public void readEntityFromNBT(NBTTagCompound nbtTagCompound)
	{
		xTile = nbtTagCompound.getShort("xTile");
		yTile = nbtTagCompound.getShort("yTile");
		zTile = nbtTagCompound.getShort("zTile");
		inTile = (nbtTagCompound.getByte("inTile") & 0xFF);
		inData = (nbtTagCompound.getByte("inData") & 0xFF);
		inGround = (nbtTagCompound.getByte("inGround") == 1);
		if (nbtTagCompound.hasKey("damage"))
		{
			damage = nbtTagCompound.getDouble("damage");
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

	public boolean canAttackWithItem()
	{
		return false;
	}
	public void explode()
	{
		if (!exploded)
		{
			exploded = true;
			createExplosionG(null, posX, posY, posZ, 1.2f);
			setDead();
		}
	}

	public void createExplosionG(Entity entity, double x, double y, double z, float size)
	{
		worldObj.createExplosion(entity, x, y, z, size, false);
	}

	@Override
	public ResourceLocation getEntityTexture()
	{
		return new ResourceLocation("goblin:textures/items/Bomb.png");
	}
}
