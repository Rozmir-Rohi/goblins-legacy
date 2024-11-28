
package goblin.entity.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityShuriken extends EntityArrow implements IProjectile {
	public EntityShuriken(World world)
	{
		super(world);
		renderDistanceWeight = 10.0;
		setSize(0.5f, 0.5f);
	}

	public EntityShuriken(World world, double x, double y, double z)
	{
		super(world);
		renderDistanceWeight = 10.0;
		setSize(0.5f, 0.5f);
		setPosition(x, y, z);
		yOffset = 0.0f;
	}

	public EntityShuriken(World world, EntityLivingBase entityLivingBaseThrower, EntityLivingBase entityLivingBaseTarget, float par4, float par5)
	{
		super(world);
		renderDistanceWeight = 10.0;
		shootingEntity = (Entity) entityLivingBaseThrower;
		if (entityLivingBaseThrower instanceof EntityPlayer)
		{
			canBePickedUp = 1;
		}
		posY = entityLivingBaseThrower.posY + entityLivingBaseThrower.getEyeHeight() - 0.10000000149011612;
		double d0 = entityLivingBaseTarget.posX - entityLivingBaseThrower.posX;
		double d2 = entityLivingBaseTarget.boundingBox.minY + entityLivingBaseTarget.height / 3.0f - posY;
		double d3 = entityLivingBaseTarget.posZ - entityLivingBaseThrower.posZ;
		double d4 = MathHelper.sqrt_double(d0 * d0 + d3 * d3);
		if (d4 >= 1.0E-7)
		{
			float f2 = (float) (Math.atan2(d3, d0) * 180.0 / Math.PI) - 90.0f;
			float f3 = (float) (-(Math.atan2(d2, d4) * 180.0 / Math.PI));
			double d5 = d0 / d4;
			double d6 = d3 / d4;
			setLocationAndAngles(entityLivingBaseThrower.posX + d5, posY, entityLivingBaseThrower.posZ + d6, f2, f3);
			yOffset = 0.0f;
			float f4 = (float) d4 * 0.2f;
			setThrowableHeading(d0, d2 + f4, d3, par4, par5);
		}
	}

	public EntityShuriken(World world, EntityLivingBase entityLivingBaseThrower, float par3)
	{
		super(world);
		renderDistanceWeight = 10.0;
		shootingEntity = (Entity) entityLivingBaseThrower;
		if (entityLivingBaseThrower instanceof EntityPlayer)
		{
			canBePickedUp = 1;
		}
		setSize(0.5f, 0.5f);
		setLocationAndAngles(entityLivingBaseThrower.posX, entityLivingBaseThrower.posY + entityLivingBaseThrower.getEyeHeight(), entityLivingBaseThrower.posZ, entityLivingBaseThrower.rotationYaw, entityLivingBaseThrower.rotationPitch);
		posX -= MathHelper.cos(rotationYaw / 180.0f * (float) Math.PI) * 0.16f;
		posY -= 0.10000000149011612;
		posZ -= MathHelper.sin(rotationYaw / 180.0f * (float) Math.PI) * 0.16f;
		setPosition(posX, posY, posZ);
		yOffset = 0.0f;
		motionX = -MathHelper.sin(rotationYaw / 180.0f * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0f * (float) Math.PI);
		motionZ = MathHelper.cos(rotationYaw / 180.0f * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0f * (float) Math.PI);
		motionY = -MathHelper.sin(rotationPitch / 180.0f * (float) Math.PI);
		setThrowableHeading(motionX, motionY, motionZ, par3 * 1.5f, 1.0f);
	}

	public void onCollideWithPlayer(EntityPlayer entityPlayer)
	{
	}
        
}
