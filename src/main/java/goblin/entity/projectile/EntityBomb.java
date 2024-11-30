
package goblin.entity.projectile;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import goblin.entity.IGoblinEntityTextureBase;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class EntityBomb extends EntityThrowable implements IGoblinEntityTextureBase {

	private int richocettCount;

	private boolean inGround;
	private boolean hasExploded;
	public int fuse = 20;
	private int ticksInAir;
	private double damage = 1;
	private int knockbackStrength;

	public EntityBomb(World world)
	{
		super(world);
		hasExploded = false;
		setSize(0.5f, 0.5f);
		noClip = false;
	}

	public EntityBomb(World world, double x, double y, double z)
	{
		super(world, x, y, z);

	}
	
	 public EntityBomb(World world, EntityLivingBase entityLivingBaseShooter)
    {
		 super(world, entityLivingBaseShooter);
    }

	
	//fix this constructer
	public EntityBomb(World world, EntityLivingBase entityLivingBase, EntityLivingBase entityLivingBase2, float par4, float par5)
	{
		super(world);
	}
	
	@Override
	public boolean canBeCollidedWith()
    {
        return true;
    }
	
	@Override
	public boolean canBePushed()
    {
        return canBeCollidedWith();
    }
	

	protected void entityInit()
	{
		dataWatcher.addObject(16, Byte.valueOf((byte) 0));
	}

	@Override
	public void onUpdate()
	{
		if (richocettCount < 2) //freezes the projectile on the surface after it has ricocheted the max amount
		{
			super.onUpdate();
		}
		
		if (inGround)
		{
			--fuse;
			if (fuse <= 0)
			{
				explode();
			}
		}
	}

	@Override
	public void onImpact(MovingObjectPosition movingObjectPosition)
	{
		if (movingObjectPosition.entityHit != null) //hit an entity
		{
			float motionSquared = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
			int motionTimesDamage = MathHelper.ceiling_double_int(motionSquared * damage);
			DamageSource damageSource = null;
			
			
			if (movingObjectPosition.entityHit != null)
	        {
	            movingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), motionTimesDamage);
	        }
			
			ricochet();
		}
		else //hit a block
		{
			if (richocettCount < 2)
			{
				ricochet();
				inGround = true;
			}
		}
	}

	private void ricochet()
	{
		motionX *= -0.10000000149011612;
		motionY *= -0.10000000149011612;
		motionZ *= -0.10000000149011612;
		rotationYaw += 180.0f;
		prevRotationYaw += 180.0f;
		ticksInAir = 0;
		
		richocettCount++;
	}

	public void writeEntityToNBT(NBTTagCompound nbtTagCompound)
	{

		nbtTagCompound.setByte("richocettCount", (byte) richocettCount);

		nbtTagCompound.setByte("inGround", (byte) (byte) (inGround ? 1 : 0));
	}

	public void readEntityFromNBT(NBTTagCompound nbtTagCompound)
	{
		richocettCount = (nbtTagCompound.getByte("richocettCount") & 0xFF);

		inGround = (nbtTagCompound.getByte("inGround") == 1);
	}

	protected boolean canTriggerWalking()
	{
		return false;
	}

	@SideOnly(Side.CLIENT)
	public float getShadowSize()
	{
		return 0.0f;
	}

	public boolean canAttackWithItem()
	{
		return false;
	}
	public void explode()
	{
		if (!hasExploded)
		{
			hasExploded = true;
			createExplosionG(null, posX, posY, posZ, 1.2f);
			setDead();
		}
	}

	public void createExplosionG(Entity entity, double x, double y, double z, float size)
	{
		worldObj.createExplosion(entity, x, y, z, size, false);
	}

	@Override
	public ResourceLocation getEntityTexture()
	{
		return new ResourceLocation("goblin:textures/items/Bomb.png");
	}
}
