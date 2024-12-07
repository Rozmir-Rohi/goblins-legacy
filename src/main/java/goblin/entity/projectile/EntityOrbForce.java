
package goblin.entity.projectile;

import goblin.entity.IGoblinEntityTextureBase;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityOrbForce extends EntityThrowableOrb implements IGoblinEntityTextureBase {
	
	public EntityOrbForce(World world)
	{
		super(world);
	}
	
	public EntityOrbForce(World world, EntityLivingBase entityLivingBase)
	{
		super(world, entityLivingBase);
	}
	
	public EntityOrbForce(World world, double x, double y, double z)
	{
		super(world, x, y, z);
	}

	@Override
	protected void onImpact(MovingObjectPosition movingObjectPosition)
	{
		if (movingObjectPosition.entityHit != null)
		{
			float motion = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
			int motionTimesDamage = MathHelper.ceiling_double_int(motion * getDamage());
			
			DamageSource damageSource = null;
			if (getThrower() == null)
			{
				damageSource = DamageSource.causeThrownDamage((Entity) this, (Entity) this);
			}
			else
			{
				damageSource = DamageSource.causeThrownDamage((Entity) this, getThrower());
			}
			if (isBurning() && !(movingObjectPosition.entityHit instanceof EntityEnderman))
			{
				movingObjectPosition.entityHit.setFire(5);
			}
			if (movingObjectPosition.entityHit.attackEntityFrom(damageSource, (float) motionTimesDamage))
			{
				if (movingObjectPosition.entityHit instanceof EntityLivingBase)
				{
					EntityLivingBase entityLivingBase = (EntityLivingBase) movingObjectPosition.entityHit;
					for (int ia2 = 0; ia2 < 20; ++ia2)
					{
						worldObj.spawnParticle("splash", posX, posY, posZ, 0.0, 0.0, 0.0);
					}
					double d3;
					double d4;
					for (d3 = entityLivingBase.posX - posX, d4 = entityLivingBase.posZ - posZ; d3 * d3 + d4 * d4 < 1.0E-4; d3 = (Math.random() - Math.random()) * 0.01, d3 = (Math.random() - Math.random()) * 0.01)
					{
					}
					float f4 = MathHelper.sqrt_double(d3 * d3 + d4 * d4);
					float f5 = 2.0f;
					entityLivingBase.motionX /= 2.0;
					EntityLivingBase entityLivingBase2 = entityLivingBase;
					entityLivingBase2.motionY /= 2.0;
					EntityLivingBase entityLivingBase3 = entityLivingBase;
					entityLivingBase3.motionZ /= 2.0;
					EntityLivingBase entityLivingBase4 = entityLivingBase;
					entityLivingBase4.motionX += d3 / f4 * f5;
					EntityLivingBase entityLivingBase5 = entityLivingBase;
					entityLivingBase5.motionY += f5;
					EntityLivingBase entityLivingBase6 = entityLivingBase;
					entityLivingBase6.motionZ += d4 / f4 * f5;
					if (entityLivingBase.motionY > 1.0000000059604646)
					{
						entityLivingBase.motionY = 1.0000000059604646;
					}

					if (getThrower() != null && getThrower() instanceof EntityLivingBase)
					{
						EnchantmentHelper.func_151384_a(entityLivingBase, getThrower());
						EnchantmentHelper.func_151385_b((EntityLivingBase) getThrower(), (Entity) entityLivingBase);
					}
					if (getThrower() != null && movingObjectPosition.entityHit != getThrower() && movingObjectPosition.entityHit instanceof EntityPlayer && getThrower() instanceof EntityPlayerMP)
					{
						((EntityPlayerMP) getThrower()).playerNetServerHandler.sendPacket((Packet) new S2BPacketChangeGameState(6, 0.0f));
					}
				}
				if (!(movingObjectPosition.entityHit instanceof EntityEnderman))
				{
					setDead();
				}
			}
			else
			{
				motionX *= -0.10000000149011612;
				motionY *= -0.10000000149011612;
				motionZ *= -0.10000000149011612;
				rotationYaw += 180.0f;
				prevRotationYaw += 180.0f;
			}
		}
		else
		{
			int xTile = movingObjectPosition.blockX;
			int yTile = movingObjectPosition.blockY;
			int zTile = movingObjectPosition.blockZ;
			int inTile = Block.getIdFromBlock(worldObj.getBlock(xTile, yTile, zTile));
			int inData = worldObj.getBlockMetadata(xTile, yTile, zTile);
			motionX = (float) (movingObjectPosition.hitVec.xCoord - posX);
			motionY = (float) (movingObjectPosition.hitVec.yCoord - posY);
			motionZ = (float) (movingObjectPosition.hitVec.zCoord - posZ);
			float motion = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
			posX -= motionX / motion * 0.05000000074505806;
			posY -= motionY / motion * 0.05000000074505806;
			posZ -= motionZ / motion * 0.05000000074505806;
			inGround = true;
			for (int ia3 = 0; ia3 < 20; ++ia3)
			{
				worldObj.spawnParticle("splash", posX, posY, posZ, 0.0, 0.0, 0.0);
			}
			setDead();
			if (inTile != 0)
			{
				Block.getBlockById(inTile).onEntityCollidedWithBlock(worldObj, xTile, yTile, zTile, (Entity) this);
			}
		}
	}
	
	@Override
	protected String getTrailParticleStringName()
	{
		return "splash";
	}
	
	@Override
	protected int getTrailParticleSpawnDelay()
	{
		return 2;
	}

	@Override
	public ResourceLocation getEntityTexture()
	{
		return new ResourceLocation("goblin:textures/items/orbB.png");
	}
}
