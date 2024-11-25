
package goblin.entity.projectile;

import net.minecraft.entity.*;
import cpw.mods.fml.relauncher.*;
import goblin.Goblins;
import goblin.entity.IGoblinEntityTextureBase;
import goblin.world.gen.WorldGenGCactus;
import goblin.world.gen.WorldGenGTrees;
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

public class EntityOrbNature extends EntityThrowableOrb implements IGoblinEntityTextureBase {
	
	public EntityOrbNature(World world)
	{
		super(world);
	}
	
	
	public EntityOrbNature(World world, EntityLivingBase entityLivingBase, float speedMultiplier)
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
					for (int ia2 = 0; ia2 < 10; ++ia2)
					{
						worldObj.spawnParticle("slime", posX, posY, posZ, 0.0, 0.0, 0.0);
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
			for (int ia3 = 0; ia3 < 10; ++ia3)
			{
				worldObj.spawnParticle("slime", posX, posY, posZ, 0.0, 0.0, 0.0);
			}
			inGround = true;
			setIsCritical(false);
			if (inTile != 0)
			{
				Block.getBlockById(inTile).onEntityCollidedWithBlock(worldObj, xTile, yTile, zTile, (Entity) this);
			}
			if (!worldObj.isRemote)
			{
				int loop = 0;
				double posX1 = posX;
				double posZ1 = posZ;
				while (true)
				{
					WorldGenGCactus cactus = new WorldGenGCactus();
					WorldGenGTrees tree = new WorldGenGTrees(true);
					if (loop <= 2)
					{
						if (tree.generate(worldObj, rand, (int) posX1, worldObj.getHeightValue((int) posX1, (int) posZ1), (int) posZ1))
						{
							setDead();
							break;
						}
						posX1 += rand.nextInt(2);
						if (tree.generate(worldObj, rand, (int) posX1, worldObj.getHeightValue((int) posX1, (int) posZ1), (int) posZ1))
						{
							setDead();
							break;
						}
						posZ1 += rand.nextInt(2);
						++loop;
					}
					else
					{
						if (cactus.generate(worldObj, rand, (int) posX, worldObj.getHeightValue((int) posX, (int) posZ), (int) posZ))
						{
							setDead();
							break;
						}
						continue;
					}
				}
				setDead();
			}
		}
	}
	
	@Override
	protected String getTrailParticleStringName()
	{
		return "happyVillager";
	}

	@Override
	public ResourceLocation getEntityTexture()
	{
		return new ResourceLocation("goblin:textures/items/orbG.png");
	}
}
