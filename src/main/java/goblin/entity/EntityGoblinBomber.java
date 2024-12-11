
package goblin.entity;

import goblin.Goblins;
import goblin.achievements.GoblinsAchievements;
import goblin.entity.projectile.EntityBomb;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityGoblinBomber extends EntityMob implements IRangedAttackMob {
	boolean isPanicked;
	private static ItemStack defaultHeldItem;

	public EntityGoblinBomber(World world)
	{
		super(world);
		isPanicked = false;
		setSize(0.6f, 0.8f);
		float moveSpeed = 0.8f;
		getNavigator().setBreakDoors(true);
		getNavigator().setBreakDoors(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIBreakDoor(this));
		tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, moveSpeed));
		tasks.addTask(5, new EntityAIMoveThroughVillage(this, moveSpeed, false));
		tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
		tasks.addTask(7, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityGolem.class, 0, false));
		tasks.addTask(4, new EntityAIArrowAttack(this, moveSpeed, 60, 12.0f));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(15.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.27);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0);
	}

	@Override
	protected void updateAITasks()
	{
		if (getHealth() < 3.0f && !isPanicked)
		{
			worldObj.playSoundAtEntity(this, "goblin:goblin.fear", 0.4f, 1.0f);
			tasks.addTask(1, new EntityAIPanic(this, 1.0));
			isPanicked = true;
		}
		super.updateAITasks();
	}

	@Override
	public boolean isEntityInsideOpaqueBlock()
	{
		return false;
	}

	@Override
	protected boolean isAIEnabled()
	{
		return true;
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

	@Override
	public float getEyeHeight()
	{
		return height * 0.06f;
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
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
		return 10;
	}

	@Override
	protected void dropFewItems(boolean flag, int i)
	{
		dropItem(Goblins.goblinFlesh, rand.nextInt(2));
		dropItem(Goblins.bomb, rand.nextInt(10) + 1);
	}

	@Override
	public boolean getCanSpawnHere()
	{
		int xCoord = MathHelper.floor_double(posX);
		int yCoord = MathHelper.floor_double(boundingBox.minY) - 1;
		int zCoord = MathHelper.floor_double(posZ);
		return worldObj.getBlock(xCoord, yCoord, zCoord) == Blocks.grass || worldObj.getBlock(xCoord, yCoord, zCoord) == Blocks.sand || worldObj.getBlock(xCoord, yCoord, zCoord) == Blocks.gravel || worldObj.getBlock(xCoord, yCoord, zCoord) == Blocks.dirt || worldObj.getBlock(xCoord, yCoord, zCoord) == Goblins.MobGSpawner;
	}

	@Override
	public ItemStack getHeldItem()
	{
		return EntityGoblinBomber.defaultHeldItem;
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase entityLivingBaseTarget, float f)
	{
		EntityBomb entityBomb = new EntityBomb(worldObj, this);
		
		double x = entityLivingBaseTarget.posX - this.posX;
        double y = entityLivingBaseTarget.posY + entityLivingBaseTarget.getEyeHeight() - 1.100000023841858D - entityBomb.posY;
        double z = entityLivingBaseTarget.posZ - this.posZ;
        float yOffset = MathHelper.sqrt_double(x * x + z * z) * 0.2F;
        
        entityBomb.setThrowableHeading(x, y + yOffset, z, 1.6F, 12.0F);
	
		entityBomb.setPosition(posX, posY + 0.75, posZ);
		playSound("random.bow", 1.0f, 1.0f / (getRNG().nextFloat() * 0.4f + 0.8f));
		if (!worldObj.isRemote)
		{
			worldObj.spawnEntityInWorld(entityBomb);
		}
	}

	static
	{
		defaultHeldItem = new ItemStack(Goblins.bomb, 1);
	}
	
	@Override
	public void onDeath(DamageSource damageSource)
    {
        if (damageSource.getEntity() != null && damageSource.getEntity() instanceof EntityPlayer)
        {
          EntityPlayer player = (EntityPlayer)damageSource.getEntity();
          if (player != null) {player.addStat(GoblinsAchievements.kill_goblin_bomber, 1);} 
        } 
        super.onDeath(damageSource);
    }
}
