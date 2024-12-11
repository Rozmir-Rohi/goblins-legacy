
package goblin.entity;

import goblin.Goblins;
import goblin.achievements.GoblinsAchievements;
import goblin.entity.projectile.EntityShuriken;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityGoblinNinja extends GoblinsOldAIBase implements IGoblinEntityTextureBase {
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

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(18.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.7f);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0);
	}
	
	@Override
	protected boolean isAIEnabled()
	{
		return false;
	}

	@Override
	protected void attackEntity(Entity entityToAttack, float distanceToEntityToAttack)
	{
		if (
				distanceToEntityToAttack < DISTANCE_THAT_IS_CONSIDERED_AS_CLOSE
			)
		{
			if (
					onGround
					&& distanceToEntityToAttack >= 2.0
					&& distanceToEntityToAttack < 3.0
					&& rand.nextInt(3) == 0
				)
			{	//move toward entityToAttack
				moveTowardEntity(entityToAttack, true);
			}
			else if (
						(attackTime <= 0 || attackTime > 20)
						&& distanceToEntityToAttack < 2.0f
						&& entityToAttack.boundingBox.maxY > boundingBox.minY
						&& entityToAttack.boundingBox.minY < boundingBox.maxY
					)
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
					EntityShuriken entityShuriken = new EntityShuriken(worldObj, this, 1.0f);
					double yDistance = entityToAttack.posY + entityToAttack.getEyeHeight() - 0.699999988079071 - entityShuriken.posY;
					float xzSquareRootDistance = MathHelper.sqrt_double(xDistance * xDistance + zDistance * zDistance) * 0.2f;
					worldObj.playSoundAtEntity(this, "random.bow", 1.0f, 1.0f / (rand.nextFloat() * 0.4f + 0.8f));
					worldObj.spawnEntityInWorld(entityShuriken);
					entityShuriken.setThrowableHeading(xDistance, yDistance + xzSquareRootDistance, zDistance, 1.6f, 12.0f);
					attackTime = 50;
				}
				rotationYaw = (float) (Math.atan2(zDistance, xDistance) * 180.0 /Math.PI) - 90.0f;
				hasAttacked = true;
			}
		}
	}
	
	@Override
	public void onUpdate()
	{	
		Entity entityTarget = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);
	    if (
	    		entityTarget == null
	    		&& rand.nextInt(25) == 0 //give a slight delay here because the findPlayerToAttack() function is exhaustive
	    	)
	    {
	    	entityTarget = findPlayerToAttack(); //look for non-player entites nearby as target
	    }
	    
	    if (entityTarget != null)
	    {
	    
		    float distanceToEntityTarget = getDistanceToEntity(entityTarget);
		    
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
	
	
	private void getTeleport()
	{
		int i1 = rand.nextInt(16) + 10;
		int j1 = rand.nextInt(16) + 10;
		int k = MathHelper.floor_double(posX);
		int l = MathHelper.floor_double(posZ);
		int m = MathHelper.floor_double(boundingBox.minY);
		setLocationAndAngles(k + i1 + 0.5f, m, l + j1 + 0.5f, rotationYaw, rotationPitch);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbtTagCompound)
	{
		super.writeEntityToNBT(nbtTagCompound);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbtTagCompound)
	{
		super.readEntityFromNBT(nbtTagCompound);
	}

	@Override
	protected String getLivingSound()
	{
		return "goblin:goblin.idle";
	}

	@Override
	protected String getHurtSound()
	{
		return "goblin:goblin.hurt";
	}

	@Override
	protected String getDeathSound()
	{
		return "goblin:goblin.dead";
	}

	@Override
	protected float getSoundVolume()
	{
		return 0.4f;
	}

	@Override
	public int getMaxSpawnedInChunk()
	{
		return 1;
	}

	@Override
	protected void dropFewItems(boolean flag, int i)
	{
		dropItem(Goblins.shuriken, rand.nextInt(5) + 4);
		
		int katanaDropChance = rand.nextInt(10);
		if (katanaDropChance == 1)
		{
			dropItem(Goblins.swordKatana, 1);
		}
	}

	@Override
	public boolean getCanSpawnHere()
	{
		return true;
	}
	
	@Override
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
	public void onDeath(DamageSource damageSource)
    {
        if (damageSource.getEntity() != null && damageSource.getEntity() instanceof EntityPlayer)
        {
          EntityPlayer player = (EntityPlayer)damageSource.getEntity();
          if (player != null) {player.addStat(GoblinsAchievements.kill_goblin_rider, 1);} 
        } 
        super.onDeath(damageSource);
    }
	
	@Override
	public ResourceLocation getEntityTexture()
	{
		return new ResourceLocation("goblin:textures/entity/GoblinNinja.png");
	}
}
