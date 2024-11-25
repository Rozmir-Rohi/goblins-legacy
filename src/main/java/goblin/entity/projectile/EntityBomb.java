
package goblin.entity.projectile;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import goblin.Goblins;
import goblin.entity.IGoblinEntityTextureBase;
import goblin.world.GoblinsExplosion;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
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
	public int fuse;
	public int canBePickedUp;
	public int arrowShake;
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
		fuse = 20;
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
		fuse = 20;
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
		fuse = 20;
		damage = 1.0;
		renderDistanceWeight = 10.0;
		shootingEntity = (Entity) entityLivingBase;
		if (entityLivingBase instanceof EntityPlayer)
		{
			canBePickedUp = 1;
		}
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
		fuse = 20;
		damage = 1.0;
		renderDistanceWeight = 10.0;
		shootingEntity = (Entity) entityLivingBase;
		if (entityLivingBase instanceof EntityPlayer)
		{
			canBePickedUp = 1;
		}
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
		if (arrowShake > 0)
		{
			--arrowShake;
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
			List list = worldObj.getEntitiesWithinAABBExcludingEntity((Entity) this, boundingBox.addCoord(motionX, motionY, motionZ).expand(1.0, 1.0, 1.0));
			double d0 = 0.0;
			for (int l = 0; l < list.size(); ++l)
			{
				Entity entity2 = (Entity) list.get(l);
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
				if (movingObjectPosition.entityHit != null)
				{
					float f3 = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
					int i2 = MathHelper.ceiling_double_int(f3 * damage);
					if (getIsCritical())
					{
						i2 += rand.nextInt(i2 / 2 + 2);
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
					if (movingObjectPosition.entityHit.attackEntityFrom(damageSource, (float) i2))
					{
						if (movingObjectPosition.entityHit instanceof EntityLivingBase)
						{
							EntityLivingBase entityLivingBase = (EntityLivingBase) movingObjectPosition.entityHit;
							if (knockbackStrength > 0)
							{
								float f4 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
								if (f4 > 0.0f)
								{
									movingObjectPosition.entityHit.addVelocity(motionX * knockbackStrength * 0.6000000238418579 / f4, 0.1, motionZ * knockbackStrength * 0.6000000238418579 / f4);
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
						motionX *= -0.10000000149011612;
						motionY *= -0.10000000149011612;
						motionZ *= -0.10000000149011612;
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
					Block block = worldObj.getBlock(xTile, yTile + 1, zTile);
					if (movingObjectPosition.sideHit == 1 && (worldObj.getBlock(xTile, yTile + 1, zTile) == Blocks.air || block.isReplaceable((IBlockAccess) worldObj, xTile, yTile + 1, zTile)))
					{
						inTile = Block.getIdFromBlock(worldObj.getBlock(xTile, yTile, zTile));
						inData = worldObj.getBlockMetadata(xTile, yTile, zTile);
						motionX = (float) (movingObjectPosition.hitVec.xCoord - posX);
						motionY = (float) (movingObjectPosition.hitVec.yCoord - posY);
						motionZ = (float) (movingObjectPosition.hitVec.zCoord - posZ);
						float f3 = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
						posX -= motionX / f3 * 0.05000000074505806;
						posY -= motionY / f3 * 0.05000000074505806;
						posZ -= motionZ / f3 * 0.05000000074505806;
						
						inGround = true;
						arrowShake = 7;
						setIsCritical(false);
						if (inTile != 0)
						{
							Block.getBlockById(inTile).onEntityCollidedWithBlock(worldObj, xTile, yTile, zTile, (Entity) this);
						}
					}
					else
					{
						motionX *= -0.10000000149011612;
						motionY *= -0.10000000149011612;
						motionZ *= -0.10000000149011612;
						rotationYaw += 180.0f;
						prevRotationYaw += 180.0f;
						ticksInAir = 0;
					}
				}
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
			float f5 = 0.79f;
			float f2 = 0.15f;
			if (isInWater())
			{
				for (int j2 = 0; j2 < 4; ++j2)
				{
					float f4 = 0.25f;
					worldObj.spawnParticle("bubble", posX - motionX * f4, posY - motionY * f4, posZ - motionZ * f4, motionX, motionY, motionZ);
				}
				f5 = 0.4f;
			}
			motionX *= f5;
			motionY *= f5;
			motionZ *= f5;
			motionY -= f2;
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

	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		par1NBTTagCompound.setShort("xTile", (short) xTile);
		par1NBTTagCompound.setShort("yTile", (short) yTile);
		par1NBTTagCompound.setShort("zTile", (short) zTile);
		par1NBTTagCompound.setByte("inTile", (byte) inTile);
		par1NBTTagCompound.setByte("inData", (byte) inData);
		par1NBTTagCompound.setByte("shake", (byte) arrowShake);
		par1NBTTagCompound.setByte("inGround", (byte) (byte) (inGround ? 1 : 0));
		par1NBTTagCompound.setByte("pickup", (byte) canBePickedUp);
		par1NBTTagCompound.setDouble("damage", damage);
	}

	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		xTile = par1NBTTagCompound.getShort("xTile");
		yTile = par1NBTTagCompound.getShort("yTile");
		zTile = par1NBTTagCompound.getShort("zTile");
		inTile = (par1NBTTagCompound.getByte("inTile") & 0xFF);
		inData = (par1NBTTagCompound.getByte("inData") & 0xFF);
		arrowShake = (par1NBTTagCompound.getByte("shake") & 0xFF);
		inGround = (par1NBTTagCompound.getByte("inGround") == 1);
		if (par1NBTTagCompound.hasKey("damage"))
		{
			damage = par1NBTTagCompound.getDouble("damage");
		}
		if (par1NBTTagCompound.hasKey("pickup"))
		{
			canBePickedUp = par1NBTTagCompound.getByte("pickup");
		}
		else if (par1NBTTagCompound.hasKey("player"))
		{
			canBePickedUp = (par1NBTTagCompound.getBoolean("player") ? 1 : 0);
		}
	}

	public void onCollideWithPlayer(EntityPlayer entityPlayer)
	{
		if (!worldObj.isRemote && inGround && arrowShake <= 0)
		{
			boolean flag = canBePickedUp == 1 || (canBePickedUp == 2 && entityPlayer.capabilities.isCreativeMode);
			if (canBePickedUp == 1 && !entityPlayer.inventory.addItemStackToInventory(new ItemStack(Goblins.bomb, 1)))
			{
				flag = false;
			}
			if (flag)
			{
				playSound("random.pop", 0.2f, ((rand.nextFloat() - rand.nextFloat()) * 0.7f + 1.0f) * 2.0f);
				entityPlayer.onItemPickup((Entity) this, 1);
				setDead();
			}
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

	public void setDamage(double par1)
	{
		damage = par1;
	}

	public double getDamage()
	{
		return damage;
	}

	public void setKnockbackStrength(int par1)
	{
		knockbackStrength = par1;
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

	public void explode()
	{
		if (!exploded)
		{
			exploded = true;
			createExplosionG(null, posX, posY, posZ, 4.2f);
			setDead();
		}
	}

	public GoblinsExplosion createExplosionG(Entity entity, double d, double d1, double d2, float f)
	{
		return newExplosionG(entity, d, d1, d2, f, false);
	}

	public GoblinsExplosion newExplosionG(Entity entity, double d, double d1, double d2, float f, boolean flag)
	{
		GoblinsExplosion explosion = new GoblinsExplosion(worldObj, entity, d, d1, d2, f);
		explosion.isFlaming = flag;
		explosion.doExplosionA();
		explosion.doExplosionB(true);
		return explosion;
	}

	@Override
	public ResourceLocation getEntityTexture()
	{
		return new ResourceLocation("goblin:textures/items/Bomb.png");
	}
}
