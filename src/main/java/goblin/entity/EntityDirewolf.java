
package goblin.entity;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityDirewolf extends EntityMob {
	private static IEntitySelector attackEntitySelector;

	public EntityDirewolf(World world)
	{
		super(world);
		setSize(0.8f, 0.8f);
		float moveSpeed = 1.1f;
		getNavigator().setBreakDoors(true);
		tasks.addTask(0, (EntityAIBase) new EntityAISwimming((EntityLiving) this));
		tasks.addTask(2, (EntityAIBase) new EntityAIAttackOnCollide((EntityCreature) this, (Class) EntityPlayer.class, (double) moveSpeed, false));
		tasks.addTask(3, (EntityAIBase) new EntityAIAttackOnCollide((EntityCreature) this, (Class) EntityVillager.class, (double) moveSpeed, true));
		tasks.addTask(3, (EntityAIBase) new EntityAIAttackOnCollide((EntityCreature) this, (Class) EntityLiving.class, (double) moveSpeed, true));
		tasks.addTask(4, (EntityAIBase) new EntityAIMoveTowardsRestriction((EntityCreature) this, (double) moveSpeed));
		tasks.addTask(6, (EntityAIBase) new EntityAIWander((EntityCreature) this, (double) moveSpeed));
		tasks.addTask(7, (EntityAIBase) new EntityAIWatchClosest((EntityLiving) this, (Class) EntityPlayer.class, 8.0f));
		tasks.addTask(7, (EntityAIBase) new EntityAILookIdle((EntityLiving) this));
		targetTasks.addTask(1, (EntityAIBase) new EntityAIHurtByTarget((EntityCreature) this, false));
		targetTasks.addTask(2, (EntityAIBase) new EntityAINearestAttackableTarget((EntityCreature) this, (Class) EntityPlayer.class, 0, true));
		targetTasks.addTask(2, (EntityAIBase) new EntityAINearestAttackableTarget((EntityCreature) this, (Class) EntityVillager.class, 0, false));
		targetTasks.addTask(4, (EntityAIBase) new EntityAINearestAttackableTarget((EntityCreature) this, (Class) EntityLiving.class, 0, false, false, EntityDirewolf.attackEntitySelector));
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(16.0);
		if (!worldObj.isRemote && worldObj.difficultySetting == EnumDifficulty.PEACEFUL)
		{
			getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0);
		}
		else
		{
			getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.65);
		}
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0);
	}

	protected boolean canDespawn()
	{
		return false;
	}

	protected void attackEntity(Entity entityToAttack, float distanceToEntityToAttack)
	{
		if (!worldObj.isRemote && worldObj.difficultySetting != EnumDifficulty.PEACEFUL)
		{
			if (distanceToEntityToAttack > 2.0f && distanceToEntityToAttack < 6.0f && rand.nextInt(10) == 0)
			{
				if (onGround)
				{
					double xDistance = entityToAttack.posX - posX;
					double zDistance = entityToAttack.posZ - posZ;
					float xzSquareRootDistance = MathHelper.sqrt_double(xDistance * xDistance + zDistance * zDistance);
					motionX = xDistance / xzSquareRootDistance * 0.5 * 0.800000011920929 + motionX * 0.20000000298023224;
					motionZ = zDistance / xzSquareRootDistance * 0.5 * 0.800000011920929 + motionZ * 0.20000000298023224;
					motionY = 0.20000000596046447;
				}
			}
			else if (distanceToEntityToAttack < 1.5 && entityToAttack.boundingBox.maxY > boundingBox.minY && entityToAttack.boundingBox.minY < boundingBox.maxY)
			{
				attackTime = 20;
				attackEntityAsMob(entityToAttack);
			}
		}
	}

	public boolean getCanSpawnHere()
	{
		int xCoord = MathHelper.floor_double(posX);
		int yCoord = MathHelper.floor_double(boundingBox.minY);
		int zCoord = MathHelper.floor_double(posZ);
		return worldObj.getBlock(xCoord, yCoord - 1, zCoord) == Blocks.grass && worldObj.getFullBlockLightValue(xCoord, yCoord, zCoord) > 8 && super.getCanSpawnHere();
	}

	public float setTailRotation()
	{
		return 0.6283185f;
	}

	public boolean isEntityInsideOpaqueBlock()
	{
		return riddenByEntity == null && super.isEntityInsideOpaqueBlock();
	}

	public boolean attackEntityFrom(DamageSource damageSource, int i)
	{
		Entity entityThatAttackedThisEntity = damageSource.getEntity();
		if (!super.attackEntityFrom(damageSource, (float) i))
		{
			return false;
		}
		if (riddenByEntity == entityThatAttackedThisEntity || ridingEntity == entityThatAttackedThisEntity)
		{
			entityToAttack = null;
			return false;
		}
		if (entityThatAttackedThisEntity != this && worldObj.difficultySetting != EnumDifficulty.PEACEFUL)
		{
			entityToAttack = entityThatAttackedThisEntity;
		}
		return true;
	}

	protected String getLivingSound()
	{
		return "mob.wolf.growl";
	}

	protected String getHurtSound()
	{
		return "mob.wolf.hurt";
	}

	protected String getDeathSound()
	{
		return "mob.wolf.death";
	}

	protected int getDropItemId()
	{
		return -1;
	}

	static
	{
		attackEntitySelector = (IEntitySelector) new GoblinsLesserGoblinsAttackFilter();
	}
}
