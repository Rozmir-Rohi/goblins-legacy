
package goblin.entity.projectile;

import goblin.entity.IGoblinEntityTextureBase;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
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
	
	public EntityOrbLightning(World world, EntityLivingBase entityLivingBase)
	{
		super(world, entityLivingBase);
	}
	
	public EntityOrbLightning(World world, double x, double y, double z)
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
				damageSource = DamageSource.causeThrownDamage(this, this);
			}
			else
			{
				damageSource = DamageSource.causeThrownDamage(this, getThrower());
			}
			if (isBurning() && !(movingObjectPosition.entityHit instanceof EntityEnderman))
			{
				movingObjectPosition.entityHit.setFire(5);
			}
			if (movingObjectPosition.entityHit.attackEntityFrom(damageSource, motionTimesDamage))
			{
				if (movingObjectPosition.entityHit instanceof EntityLivingBase)
				{
					EntityLivingBase entityLivingBase = (EntityLivingBase) movingObjectPosition.entityHit;
					if (getThrower() != null && getThrower() instanceof EntityLivingBase)
					{
						EnchantmentHelper.func_151384_a(entityLivingBase, getThrower());
						EnchantmentHelper.func_151385_b(getThrower(), entityLivingBase);
					}
					if (getThrower() != null && movingObjectPosition.entityHit != getThrower() && movingObjectPosition.entityHit instanceof EntityPlayer && getThrower() instanceof EntityPlayerMP)
					{
						((EntityPlayerMP) getThrower()).playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(6, 0.0f));
					}
				}
				worldObj.spawnEntityInWorld(new EntityLightningBolt(worldObj, posX, posY, posZ));
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
			
			if (inTile != 0)
			{
				Block.getBlockById(inTile).onEntityCollidedWithBlock(worldObj, xTile, yTile, zTile, this);
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
