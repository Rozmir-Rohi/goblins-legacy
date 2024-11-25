
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

public class EntityOrbForce extends EntityThrowableOrb implements IGoblinEntityTextureBase {
	
	public EntityOrbForce(World world)
	{
		super(world);
	}
	
	public EntityOrbForce(World world, EntityLivingBase entityLivingBase, float speedMultiplier)
	{
		super(world, entityLivingBase, speedMultiplier);
	}
	
	@Override
	protected void onImpact(MovingObjectPosition movingObjectPosition)
	{
		if (movingObjectPosition.entityHit != null)
		{
			float motion = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
			int motionTimesDamage = MathHelper.ceiling_double_int(motion * getDamage());
			if (getIsCritical())
			{
				motionTimesDamage += rand.nextInt(motionTimesDamage / 2 + 2);
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
					if (getKnockbackStrength() > 0)
					{
						float f6 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
						if (f6 > 0.0f)
						{
							movingObjectPosition.entityHit.addVelocity(motionX * getKnockbackStrength() * 0.6000000238418579 / f6, 0.1, motionZ * getKnockbackStrength() * 0.6000000238418579 / f6);
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
			setIsCritical(false);
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
