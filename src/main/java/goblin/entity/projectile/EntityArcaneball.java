
package goblin.entity.projectile;

import goblin.entity.IGoblinEntityTextureBase;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityArcaneball extends EntityThrowableOrb implements IGoblinEntityTextureBase {
	
	private final static int MAXIMUM_SECONDS_FOR_EXISTANCE = 10;
	
	public EntityArcaneball(World world)
	{
		super(world);
	}

	public EntityArcaneball(World world, double x, double y, double z)
	{
		super(world, x, y, z);
	}

	public EntityArcaneball(World world, EntityLivingBase entityLivingBase)
	{
		super(world, entityLivingBase);
	}
	
	@Override
	protected float getGravityVelocity()
    {
        return 0;
    }
	
	@Override
	public void onUpdate()
    {
        if (
        		!worldObj.isRemote
        		&& (
        				getThrower() != null
        				&& getThrower().isDead
        				|| !worldObj.blockExists((int) posX, (int) posY, (int) posZ)
        				|| inGround
        				|| ticksExisted >= 20 * MAXIMUM_SECONDS_FOR_EXISTANCE
        			)
        	)
        {
            setDead();
        }
        else
        {
            super.onUpdate();  
        }
    }
	
	@Override
	protected String getTrailParticleStringName()
	{
		return "smoke";
	}
	
	@Override
	protected void onImpact(MovingObjectPosition movingObjectPosition)
	{
		if (movingObjectPosition != null)
		{
			if (movingObjectPosition.entityHit != null)
			{
				int damageToDeal = 6;
				
				DamageSource damageSource = null;
				if (getThrower() == null)
				{
					damageSource = DamageSource.causeThrownDamage(this, this);
				}
				else
				{
					damageSource = DamageSource.causeThrownDamage(this, getThrower());
				}
				if (movingObjectPosition.entityHit.attackEntityFrom(damageSource, damageToDeal))
				{
					setDead();
				}
				else
				{
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
				float f4 = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
				worldObj.playSoundAtEntity(this, "mob.ghast.fireball", 1.0f, 1.2f / (rand.nextFloat() * 0.2f + 0.9f));
				inGround = true;
				setDead();
			}
		}
	}

	@Override
	public ResourceLocation getEntityTexture()
	{
		return new ResourceLocation("goblin:textures/entity/Arcaneball.png");
	}
}
