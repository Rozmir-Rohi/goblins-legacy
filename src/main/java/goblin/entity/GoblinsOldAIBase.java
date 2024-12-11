package goblin.entity;

import java.util.List;

import goblin.Goblins;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class GoblinsOldAIBase extends EntityMob {

	public GoblinsOldAIBase(World world)
	{
		super(world);
	}
	
	@Override
	protected Entity findPlayerToAttack()
    {
        return findTargetEntityToAttackForThisGoblin();
    }
	
	
	protected Entity findTargetEntityToAttackForThisGoblin()
    {
		double distanceToLookForTargetsIn = 16.0D;
		
        EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, distanceToLookForTargetsIn);
        
        if (entityPlayer != null && canEntityBeSeen(entityPlayer))
        {
        	return entityPlayer;
        }
        if (entityPlayer == null)
        {
        	return getClosestEntityLiving(distanceToLookForTargetsIn);
        }
        return null;
    }
	
	@Override
	public boolean attackEntityFrom(DamageSource damageSource, float damageTaken)
    {
		if (
				damageSource.isProjectile()
				&& GoblinsEntityTools.isDamageSourceEntityFromGoblinsMod(damageSource)

			)
		{
			return false; //prevents infighting among Goblins
		}
		return super.attackEntityFrom(damageSource, damageTaken);
    }
	
	
	protected EntityLivingBase getClosestEntityLiving(double distance)
    {
        double currentMinimumDistance = -1D;

        EntityLivingBase entityLiving = null;

        List entitiesNearbyList = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(distance, distance, distance));

        int iterationLength = entitiesNearbyList.size();

        if (iterationLength > 0)
        {
	        for (int index = 0; index < iterationLength; index++)
	        {
	            Entity entityNearby = (Entity) entitiesNearbyList.get(index);

	            if (!isEntityApplicable(entityNearby))
	            {
	                continue;
	            }

	            double overallDistanceSquared = entityNearby.getDistanceSq(this.posX, this.posY, this.posZ);

	            if (((distance < 0.0D) || (overallDistanceSquared < (distance * distance))) && ((currentMinimumDistance == -1D) || (overallDistanceSquared < currentMinimumDistance)) && ((EntityLivingBase) entityNearby).canEntityBeSeen(this))
	            {
	                currentMinimumDistance = overallDistanceSquared;
	                entityLiving = (EntityLivingBase) entityNearby;
	            }
	        }
        }

        return entityLiving;
    }
	
	protected void moveTowardEntity(Entity entityToAttack, boolean shouldCharge)
	{
		double xDistance = entityToAttack.posX - posX;
		double zDistance = entityToAttack.posZ - posZ;
		float xzSquareRootDistance = MathHelper.sqrt_double(xDistance * xDistance + zDistance * zDistance);
		motionX = xDistance / xzSquareRootDistance * 0.4 * 1.000000011920929 + motionX * 0.20000000298023224;
		motionZ = zDistance / xzSquareRootDistance * 0.4 * 1.000000011920929 + motionZ * 0.20000000298023224;
		
		if (shouldCharge)
		{
			motionY = 0.4000000241984645;
		}
	}
	
	
	public boolean isEntityApplicable(Entity entity)
	{
		return
			(	//attack the entities below
				entity instanceof EntityVillager
				|| (
						Goblins.isWitcheryLoaded
						&& entity instanceof EntityLiving
						&& EntityList.getEntityString(entity).equals("witchery.villageguard")
					)
			);
	}

}
