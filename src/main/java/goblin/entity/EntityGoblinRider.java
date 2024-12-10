
package goblin.entity;

import goblin.Goblins;
import goblin.achievements.GoblinsAchievements;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityGoblinRider extends EntityMob implements IGoblinEntityTextureBase {
	private static IEntitySelector attackEntitySelector;
	boolean isPanicked;
	private static ItemStack defaultHeldItem;

	public EntityGoblinRider(World world)
	{
		super(world);
		isPanicked = false;
		setSize(0.6f, 1.4f);
		float moveSpeed = 0.9f;
		getNavigator().setBreakDoors(true);
		tasks.addTask(0, (EntityAIBase) new EntityAISwimming((EntityLiving) this));
		tasks.addTask(1, (EntityAIBase) new EntityAIBreakDoor((EntityLiving) this));
		tasks.addTask(2, (EntityAIBase) new EntityAIAttackOnCollide((EntityCreature) this, (Class) EntityPlayer.class, (double) moveSpeed, false));
		tasks.addTask(3, (EntityAIBase) new EntityAIAttackOnCollide((EntityCreature) this, (Class) EntityVillager.class, (double) moveSpeed, true));
		tasks.addTask(3, (EntityAIBase) new EntityAIAttackOnCollide((EntityCreature) this, (Class) EntityLiving.class, (double) moveSpeed, true));
		tasks.addTask(4, (EntityAIBase) new EntityAIMoveTowardsRestriction((EntityCreature) this, (double) moveSpeed));
		tasks.addTask(5, (EntityAIBase) new EntityAIMoveThroughVillage((EntityCreature) this, (double) moveSpeed, false));
		tasks.addTask(6, (EntityAIBase) new EntityAIWander((EntityCreature) this, (double) moveSpeed));
		tasks.addTask(7, (EntityAIBase) new EntityAIWatchClosest((EntityLiving) this, (Class) EntityPlayer.class, 8.0f));
		tasks.addTask(7, (EntityAIBase) new EntityAILookIdle((EntityLiving) this));
		targetTasks.addTask(1, (EntityAIBase) new EntityAIHurtByTarget((EntityCreature) this, false));
		targetTasks.addTask(2, (EntityAIBase) new EntityAINearestAttackableTarget((EntityCreature) this, (Class) EntityPlayer.class, 0, true));
		targetTasks.addTask(2, (EntityAIBase) new EntityAINearestAttackableTarget((EntityCreature) this, (Class) EntityVillager.class, 0, false));
		targetTasks.addTask(4, (EntityAIBase) new EntityAINearestAttackableTarget((EntityCreature) this, (Class) EntityLiving.class, 0, false, false, EntityGoblinRider.attackEntitySelector));
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(15.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.9);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0);
	}

	protected void updateAITasks()
	{
		if (getHealth() < 3.0f && !isPanicked)
		{
			worldObj.playSoundAtEntity((Entity) this, "goblin:goblin.fear", 0.4f, 1.0f);
			tasks.addTask(1, (EntityAIBase) new EntityAIPanic((EntityCreature) this, 1.0));
			isPanicked = true;
		}
		super.updateAITasks();
	}
	
	protected boolean isAIEnabled()
	{
		return true;
	}

	public void onUpdate()
	{
		super.onUpdate();
		if (ridingEntity == null)
		{
			for (int i = 0; i < worldObj.loadedEntityList.size(); ++i)
			{
				Entity entity = (Entity) worldObj.loadedEntityList.get(i);
				if ((entity instanceof EntityDirewolf || entity.riddenByEntity == null) && entity instanceof EntityDirewolf && entity.riddenByEntity == null)
				{
					double d = entity.getDistance(posX, posY, posZ);
					EntityDirewolf entitywolf = (EntityDirewolf) entity;
					if (d < 1.5 && entity.riddenByEntity == null)
					{
						mountEntity(entity);
					}
				}
			}
		}
		else
		{
			rotationYaw = ridingEntity.rotationYaw;
		}
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

	protected void dropFewItems(boolean flag, int i)
	{
		dropItem(Goblins.goblinFlesh, rand.nextInt(2));
		dropItem(Items.leather, rand.nextInt(2));
	}

	public boolean isEntityInsideOpaqueBlock()
	{
		return ridingEntity == null && super.isEntityInsideOpaqueBlock();
	}

	public boolean attackEntityFrom(DamageSource damageSource, float i)
	{
		Entity entityThatAttackedThisEntity = damageSource.getEntity();
		if (!super.attackEntityFrom(damageSource, i))
		{
			return false;
		}
		
		if (
				damageSource.isProjectile()
				&& GoblinsEntityTools.isDamageSourceEntityFromGoblinsMod(damageSource)
			)
		{
			return false; //prevents infighting among Goblins
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

	public double getYOffset()
	{
		if (ridingEntity instanceof EntityDirewolf)
		{
			return yOffset - 0.08f;
		}
		return yOffset;
	}

	public int getMaxSpawnedInChunk()
	{
		return 20;
	}

	public void updateRiderPosition()
	{
		riddenByEntity.setPosition(posX, posY + getMountedYOffset() + riddenByEntity.getYOffset(), posZ);
	}

	public boolean getCanSpawnHere()
	{
		return true;
	}

	public ItemStack getHeldItem()
	{
		return EntityGoblinRider.defaultHeldItem;
	}

	static
	{
		attackEntitySelector = (IEntitySelector) new GoblinsLesserGoblinAttackFilter();
		defaultHeldItem = new ItemStack(Items.stone_sword, 1);
	}
	
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
		return new ResourceLocation("goblin:textures/entity/GoblinRider.png");
	}
}
