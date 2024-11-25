
package goblin.entity.projectile;

import goblin.entity.IGoblinEntityTextureBase;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
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

public class EntityOrbLightning extends EntityThrowableOrb implements IGoblinEntityTextureBase {
	
	public EntityOrbLightning(World world)
	{
		super(world);
	}
	
	public EntityOrbLightning(World world, EntityLivingBase entityLivingBase, float speedMultiplier)
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
				worldObj.spawnEntityInWorld((Entity) new EntityLightningBolt(worldObj, posX, posY, posZ));
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
			setIsCritical(false);
			if (inTile != 0)
			{
				Block.getBlockById(inTile).onEntityCollidedWithBlock(worldObj, xTile, yTile, zTile, (Entity) this);
			}
			setDead();
		}
	}
	
	@Override
	protected String getTrailParticleStringName()
	{
		return "crit";
	}

	@Override
	public ResourceLocation getEntityTexture()
	{
		return new ResourceLocation("goblin:textures/items/orbY.png");
	}
}
