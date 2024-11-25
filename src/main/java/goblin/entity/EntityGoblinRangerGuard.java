
package goblin.entity;

import goblin.Goblins;
import net.minecraft.command.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.ai.*;
import net.minecraft.nbt.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.*;
import net.minecraft.init.*;
import net.minecraft.entity.*;
import net.minecraft.entity.projectile.*;

public class EntityGoblinRangerGuard extends EntityMob implements IRangedAttackMob, IGoblinEntityTextureBase {
	private static IEntitySelector attackEntitySelector;
	boolean isPanicked;
	boolean shot;
	EntityAIArrowAttack arrowAttack;
	private static ItemStack defaultHeldItem;

	public EntityGoblinRangerGuard(World world)
	{
		super(world);
		arrowAttack = new EntityAIArrowAttack((IRangedAttackMob) this, 0.0, 60, 20.0f);
		isPanicked = false;
		shot = false;
		setSize(0.6f, 1.4f);
		float moveSpeed = 0.8f;
		getNavigator().setBreakDoors(true);
		getNavigator().setBreakDoors(true);
		tasks.addTask(7, (EntityAIBase) new EntityAIWatchClosest((EntityLiving) this, (Class) EntityPlayer.class, 8.0f));
		tasks.addTask(7, (EntityAIBase) new EntityAILookIdle((EntityLiving) this));
		targetTasks.addTask(1, (EntityAIBase) new EntityAIHurtByTarget((EntityCreature) this, false));
		targetTasks.addTask(2, (EntityAIBase) new EntityAINearestAttackableTarget((EntityCreature) this, (Class) EntityPlayer.class, 0, true));
		targetTasks.addTask(2, (EntityAIBase) new EntityAINearestAttackableTarget((EntityCreature) this, (Class) EntityVillager.class, 0, false));
		targetTasks.addTask(2, (EntityAIBase) new EntityAINearestAttackableTarget((EntityCreature) this, (Class) EntityGolem.class, 0, false));
		targetTasks.addTask(4, (EntityAIBase) new EntityAINearestAttackableTarget((EntityCreature) this, (Class) EntityLiving.class, 0, false, false, EntityGoblinRangerGuard.attackEntitySelector));
		tasks.addTask(4, (EntityAIBase) arrowAttack);
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(15.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.29);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0);
	}

	protected void updateAITasks()
	{
		if (worldObj.getClosestPlayerToEntity((Entity) this, 2.0) != null || (getHealth() < 15.0 && !shot))
		{
			float moveSpeed = 0.8f;
			tasks.removeTask((EntityAIBase) arrowAttack);
			tasks.addTask(0, (EntityAIBase) new EntityAISwimming((EntityLiving) this));
			tasks.addTask(1, (EntityAIBase) new EntityAIBreakDoor((EntityLiving) this));
			tasks.addTask(4, (EntityAIBase) new EntityAIMoveTowardsRestriction((EntityCreature) this, (double) moveSpeed));
			tasks.addTask(5, (EntityAIBase) new EntityAIMoveThroughVillage((EntityCreature) this, (double) moveSpeed, false));
			tasks.addTask(4, (EntityAIBase) new EntityAIArrowAttack((IRangedAttackMob) this, (double) moveSpeed, 60, 10.0f));
			shot = true;
		}
		if (getHealth() < 3.0f && !isPanicked)
		{
			worldObj.playSoundAtEntity((Entity) this, "goblin:goblin.fear", 0.4f, 1.0f);
			tasks.addTask(1, (EntityAIBase) new EntityAIPanic((EntityCreature) this, 1.0));
			isPanicked = true;
		}
		super.updateAITasks();
	}

	public boolean isEntityInsideOpaqueBlock()
	{
		return false;
	}

	protected boolean canDespawn()
	{
		return false;
	}

	protected boolean isAIEnabled()
	{
		return true;
	}

	public float getEyeHeight()
	{
		return height;
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
		return 10;
	}

	protected void dropFewItems(boolean flag, int i)
	{
		dropItem(Goblins.goblinFlesh, rand.nextInt(2));
		dropItem(Items.arrow, rand.nextInt(2) + 1);
	}

	public boolean getCanSpawnHere()
	{
		return true;
	}

	public ItemStack getHeldItem()
	{
		return EntityGoblinRangerGuard.defaultHeldItem;
	}

	public void attackEntityWithRangedAttack(EntityLivingBase entityLivingBase, float f)
	{
		EntityArrow entityArrow = new EntityArrow(worldObj, (EntityLivingBase) this, entityLivingBase, 1.6f, 12.0f);
		playSound("random.bow", 1.0f, 1.0f / (getRNG().nextFloat() * 0.4f + 0.8f));
		worldObj.spawnEntityInWorld((Entity) entityArrow);
	}

	static
	{
		attackEntitySelector = (IEntitySelector) new EntityGoblinAttackFilter();
		defaultHeldItem = new ItemStack((Item) Items.bow, 1);
	}

	@Override
	public ResourceLocation getEntityTexture()
	{
		return new ResourceLocation("goblin:textures/entity/GoblinRanger.png");
	}
}
