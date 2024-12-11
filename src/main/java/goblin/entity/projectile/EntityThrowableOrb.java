
package goblin.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityThrowableOrb extends EntityThrowable implements IProjectile {

	public EntityThrowableOrb(World world)
	{
		super(world);
	}

	public EntityThrowableOrb(World world, double x, double y, double z)
	{
		super(world, x, y, z);

	}

	public EntityThrowableOrb(World world, EntityLivingBase entityLivingBaseShooter)
	{
		super(world, entityLivingBaseShooter);
	}
	
	protected String getTrailParticleStringName()
	{
		return "";
	}
	
	protected int getTrailParticleSpawnDelay()
	{
		return 1;
	}
	
	@Override
	protected void onImpact(MovingObjectPosition movingObjectPosition)
	{
	}
	
	@Override
	protected float getGravityVelocity()
    {
        return 0.05F;
    }
	
	protected static int getDamage()
	{
		return 1;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		
		if (
				getTrailParticleStringName().length() > 0 //not empty string
				&& rand.nextInt(getTrailParticleSpawnDelay()) == 0
				
			) 
		{	//creates trail particles
			
			worldObj.spawnParticle(getTrailParticleStringName(), posX, posY, posZ, 0.0, 0.0, 0.0);
		}
	}

	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}

	@Override
	public boolean canAttackWithItem()
	{
		return false;
	}
}