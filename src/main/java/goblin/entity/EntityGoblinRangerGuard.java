
package goblin.entity;

import goblin.Goblins;
import goblin.achievements.GoblinsAchievements;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.EntityLiving;
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
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityGoblinRangerGuard extends EntityMob implements IRangedAttackMob, IGoblinEntityTextureBase {
	private static IEntitySelector attackEntitySelector;
	boolean isPanicked;
	boolean shot;
	EntityAIArrowAttack arrowAttack;
	private static ItemStack defaultHeldItem;

	public EntityGoblinRangerGuard(World world)
	{
		super(world);
		arrowAttack = new EntityAIArrowAttack(this, 0.0, 60, 20.0f);
		isPanicked = false;
		shot = false;
		setSize(0.6f, 1.4f);
		float moveSpeed = 0.8f;
		getNavigator().setBreakDoors(true);
		getNavigator().setBreakDoors(true);
		tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
		tasks.addTask(7, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityGolem.class, 0, false));
		targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, false, false, EntityGoblinRangerGuard.attackEntitySelector));
		tasks.addTask(4, arrowAttack);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(15.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.29);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0);
	}

	@Override
	protected void updateAITasks()
	{
		if (worldObj.getClosestPlayerToEntity(this, 2.0) != null || (getHealth() < 15.0 && !shot))
		{
			float moveSpeed = 0.8f;
			tasks.removeTask(arrowAttack);
			tasks.addTask(0, new EntityAISwimming(this));
			tasks.addTask(1, new EntityAIBreakDoor(this));
			tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, moveSpeed));
			tasks.addTask(5, new EntityAIMoveThroughVillage(this, moveSpeed, false));
			tasks.addTask(4, new EntityAIArrowAttack(this, moveSpeed, 60, 10.0f));
			shot = true;
		}
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
	protected boolean canDespawn()
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
		return height;
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
		dropItem(Items.arrow, rand.nextInt(2) + 1);
	}

	@Override
	public boolean getCanSpawnHere()
	{
		return true;
	}

	@Override
	public ItemStack getHeldItem()
	{
		return EntityGoblinRangerGuard.defaultHeldItem;
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase entityLivingBase, float f)
	{
		EntityArrow entityArrow = new EntityArrow(worldObj, this, entityLivingBase, 1.6f, 12.0f);
		playSound("random.bow", 1.0f, 1.0f / (getRNG().nextFloat() * 0.4f + 0.8f));
		worldObj.spawnEntityInWorld(entityArrow);
	}

	static
	{
		attackEntitySelector = new GoblinsLesserGoblinAttackFilter();
		defaultHeldItem = new ItemStack(Items.bow, 1);
	}
	
	@Override
	public void onDeath(DamageSource damageSource)
    {
        if (damageSource.getEntity() != null && damageSource.getEntity() instanceof EntityPlayer)
        {
          EntityPlayer player = (EntityPlayer)damageSource.getEntity();
          if (player != null) {player.addStat(GoblinsAchievements.kill_goblin_ranger, 1);} 
        } 
        super.onDeath(damageSource);
    }

	@Override
	public ResourceLocation getEntityTexture()
	{
		return new ResourceLocation("goblin:textures/entity/GoblinRanger.png");
	}
}
