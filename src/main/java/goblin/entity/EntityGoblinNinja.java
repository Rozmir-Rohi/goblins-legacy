
package goblin.entity;

import goblin.Goblins;
import goblin.entity.projectile.EntityShuriken;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityGoblinNinja extends EntityGreaterGoblinOldAiBase implements IGoblinEntityTextureBase {
	int hide;
	boolean hidden;
	
	boolean isCloseToEnemy = true;
	
	static final float DISTANCE_THAT_IS_CONSIDERED_AS_CLOSE = 5f;
	
	static final float DISTANCE_THAT_IS_CONSIDERED_AS_TOO_FAR = 10f;

	public EntityGoblinNinja(World world)
	{
		super(world);
		hidden = false;
		setSize(0.6f, 0.8f);
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(18.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.7f);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0);
	}
	
	protected boolean isAIEnabled()
	{
		return false;
	}

	protected void attackEntity(Entity entityToAttack, float distanceToEntityToAttack)
	{
		if (
				distanceToEntityToAttack < DISTANCE_THAT_IS_CONSIDERED_AS_CLOSE
			)
		{
			if (onGround && distanceToEntityToAttack >= 2.0 && distanceToEntityToAttack < 3.0 && rand.nextInt(3) == 0)
			{	//move toward entityToAttack
				moveTowardEntity(entityToAttack, true);
			}
			else if ((attackTime <= 0 || attackTime > 20) && distanceToEntityToAttack < 2.0f && entityToAttack.boundingBox.maxY > boundingBox.minY && entityToAttack.boundingBox.minY < boundingBox.maxY)
			{
				attackTime = 20;
				GoblinsEntityTools.goblinsCustomAttackEntityAsMob(this, entityToAttack);
			}
		}
		else
		{	
			if (distanceToEntityToAttack > DISTANCE_THAT_IS_CONSIDERED_AS_TOO_FAR)
			{
				moveTowardEntity(entityToAttack, false);
			}
			
			else
			{
				//attack with shuriken
				double xDistance = entityToAttack.posX - posX;
				double zDistance = entityToAttack.posZ - posZ;
				
				if (attackTime == 0)
				{
					EntityShuriken entityShuriken = new EntityShuriken(worldObj, (EntityLivingBase) this, 1.0f);
					double yDistance = entityToAttack.posY + entityToAttack.getEyeHeight() - 0.699999988079071 - entityShuriken.posY;
					float xzSquareRootDistance = MathHelper.sqrt_double(xDistance * xDistance + zDistance * zDistance) * 0.2f;
					worldObj.playSoundAtEntity((Entity) this, "random.bow", 1.0f, 1.0f / (rand.nextFloat() * 0.4f + 0.8f));
					worldObj.spawnEntityInWorld((Entity) entityShuriken);
					entityShuriken.setThrowableHeading(xDistance, yDistance + xzSquareRootDistance, zDistance, 1.6f, 12.0f);
					attackTime = 50;
				}
				rotationYaw = (float) (Math.atan2(zDistance, xDistance) * 180.0 /Math.PI) - 90.0f;
				hasAttacked = true;
			}
		}
	}
	
	public void onUpdate()
	{	
		Entity entityTarget = worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 16.0D);
	    if (
	    		entityTarget == null
	    		&& rand.nextInt(25) == 0 //give a slight delay here because the findPlayerToAttack() function is exhaustive
	    	)
	    {
	    	entityTarget = findPlayerToAttack(); //look for non-player entites nearby as target
	    }
	    
	    if (entityTarget != null)
	    {
	    
		    float distanceToEntityTarget = getDistanceToEntity((Entity) entityTarget);
		    
		    if (distanceToEntityTarget < DISTANCE_THAT_IS_CONSIDERED_AS_CLOSE)
			{
				isCloseToEnemy = true;
			}
		    else
		    {
		    	isCloseToEnemy = false;
		    }
	    }
	    super.onUpdate();
	}
	
	public boolean attackEntityFrom(DamageSource damageSource, float damageTaken)
    {
		if (
				damageSource.isProjectile()
				&& (damageSource.getEntity() instanceof EntityGoblinNinja)
			)
		{
			return false; //prevents infighting among Goblin Ninjas
		}
		return super.attackEntityFrom(damageSource, damageTaken);
    }
	
	
	private void getTeleport()
	{
		int i1 = rand.nextInt(16) + 10;
		int j1 = rand.nextInt(16) + 10;
		int k = MathHelper.floor_double(posX);
		int l = MathHelper.floor_double(posZ);
		int m = MathHelper.floor_double(boundingBox.minY);
		setLocationAndAngles((double) (k + i1 + 0.5f), (double) m, (double) (l + j1 + 0.5f), rotationYaw, rotationPitch);
	}

	public void writeEntityToNBT(NBTTagCompound nbtTagCompound)
	{
		super.writeEntityToNBT(nbtTagCompound);
	}

	public void readEntityFromNBT(NBTTagCompound nbtTagCompound)
	{
		super.readEntityFromNBT(nbtTagCompound);
	}

	protected String getLivingSound()
	{
		return "goblin:goblin.idle";
	}

	protected String getHurtSound()
	{
		return "goblin:goblin.hurt";
	}

	protected String getDeathSound()
	{
		return "goblin:goblin.dead";
	}

	protected float getSoundVolume()
	{
		return 0.4f;
	}

	public int getMaxSpawnedInChunk()
	{
		return 1;
	}

	protected void dropFewItems(boolean flag, int i)
	{
		dropItem(Goblins.shuriken, rand.nextInt(5) + 4);
		
		int katanaDropChance = rand.nextInt(10);
		if (katanaDropChance == 1)
		{
			dropItem(Goblins.swordKatana, 1);
		}
	}

	public boolean getCanSpawnHere()
	{
		return true;
	}
	
	public ItemStack getHeldItem()
	{
		if (isCloseToEnemy)
		{
			return new ItemStack(Goblins.swordKatana, 1);
		}
		
		else
		{
			return new ItemStack(Goblins.shuriken, 1);
		}
	}
	
	@Override
	public ResourceLocation getEntityTexture()
	{
		return new ResourceLocation("goblin:textures/entity/GoblinNinja.png");
	}
}
