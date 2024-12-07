
package goblin.entity.projectile;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
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

	protected boolean canTriggerWalking()
	{
		return false;
	}

	public boolean canAttackWithItem()
	{
		return false;
	}
}