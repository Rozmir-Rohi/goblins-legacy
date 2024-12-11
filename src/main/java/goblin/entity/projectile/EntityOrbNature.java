
package goblin.entity.projectile;

import goblin.entity.IGoblinEntityTextureBase;
import goblin.world.gen.WorldGenGCactus;
import goblin.world.gen.WorldGenGTrees;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityOrbNature extends EntityThrowableOrb implements IGoblinEntityTextureBase {
	
	public EntityOrbNature(World world)
	{
		super(world);
	}
	
	
	public EntityOrbNature(World world, EntityLivingBase entityLivingBase)
	{
		super(world, entityLivingBase);
	}
	
	public EntityOrbNature(World world, double x, double y, double z)
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
					for (int ia2 = 0; ia2 < 10; ++ia2)
					{
						worldObj.spawnParticle("slime", posX, posY, posZ, 0.0, 0.0, 0.0);
					}
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
			for (int ia3 = 0; ia3 < 10; ++ia3)
			{
				worldObj.spawnParticle("slime", posX, posY, posZ, 0.0, 0.0, 0.0);
			}
			inGround = true;
			if (inTile != 0)
			{
				Block.getBlockById(inTile).onEntityCollidedWithBlock(worldObj, xTile, yTile, zTile, this);
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
