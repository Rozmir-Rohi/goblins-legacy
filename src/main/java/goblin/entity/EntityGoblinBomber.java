
package goblin.entity;

import net.minecraft.item.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.ai.*;
import net.minecraft.nbt.*;
import net.minecraft.util.*;
import net.minecraft.init.*;
import goblin.Goblins;
import goblin.entity.projectile.EntityBomb;
import goblin.world.GoblinsExplosion;
import net.minecraft.entity.*;

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
		tasks.addTask(0, (EntityAIBase) new EntityAISwimming((EntityLiving) this));
		tasks.addTask(1, (EntityAIBase) new EntityAIBreakDoor((EntityLiving) this));
		tasks.addTask(4, (EntityAIBase) new EntityAIMoveTowardsRestriction((EntityCreature) this, (double) moveSpeed));
		tasks.addTask(5, (EntityAIBase) new EntityAIMoveThroughVillage((EntityCreature) this, (double) moveSpeed, false));
		tasks.addTask(7, (EntityAIBase) new EntityAIWatchClosest((EntityLiving) this, (Class) EntityPlayer.class, 8.0f));
		tasks.addTask(7, (EntityAIBase) new EntityAILookIdle((EntityLiving) this));
		targetTasks.addTask(1, (EntityAIBase) new EntityAIHurtByTarget((EntityCreature) this, false));
		targetTasks.addTask(2, (EntityAIBase) new EntityAINearestAttackableTarget((EntityCreature) this, (Class) EntityPlayer.class, 0, true));
		targetTasks.addTask(2, (EntityAIBase) new EntityAINearestAttackableTarget((EntityCreature) this, (Class) EntityVillager.class, 0, false));
		targetTasks.addTask(2, (EntityAIBase) new EntityAINearestAttackableTarget((EntityCreature) this, (Class) EntityGolem.class, 0, false));
		tasks.addTask(4, (EntityAIBase) new EntityAIArrowAttack((IRangedAttackMob) this, (double) moveSpeed, 60, 12.0f));
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(15.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.27);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0);
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

	public boolean isEntityInsideOpaqueBlock()
	{
		return false;
	}

	protected boolean isAIEnabled()
	{
		return true;
	}

	public float getEyeHeight()
	{
		return height * 0.06f;
	}

	protected boolean canDespawn()
	{
		return false;
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
		dropItem(Goblins.bomb, rand.nextInt(10) + 1);
	}

	public boolean getCanSpawnHere()
	{
		int xCoord = MathHelper.floor_double(posX);
		int yCoord = MathHelper.floor_double(boundingBox.minY) - 1;
		int zCoord = MathHelper.floor_double(posZ);
		return worldObj.getBlock(xCoord, yCoord, zCoord) == Blocks.grass || worldObj.getBlock(xCoord, yCoord, zCoord) == Blocks.sand || worldObj.getBlock(xCoord, yCoord, zCoord) == Blocks.gravel || worldObj.getBlock(xCoord, yCoord, zCoord) == Blocks.dirt || worldObj.getBlock(xCoord, yCoord, zCoord) == Goblins.MobGSpawner;
	}

	public ItemStack getHeldItem()
	{
		return EntityGoblinBomber.defaultHeldItem;
	}

	public void explode()
	{
		createExplosionG(null, posX, posY, posZ, 2.2f);
		setDead();
	}

	public GoblinsExplosion createExplosionG(Entity entity, double xCoord, double yCoord, double zCoord, float f)
	{
		return newExplosionG(entity, xCoord, yCoord, zCoord, f, false);
	}

	public GoblinsExplosion newExplosionG(Entity entity, double xCoord, double yCoord, double zCoord, float f, boolean flag)
	{
		GoblinsExplosion explosion = new GoblinsExplosion(worldObj, entity, xCoord, yCoord, zCoord, f);
		explosion.isFlaming = flag;
		explosion.doExplosionA();
		explosion.doExplosionB(true);
		return explosion;
	}

	public void attackEntityWithRangedAttack(EntityLivingBase entityLivingBase, float f)
	{
		EntityBomb entityBomb = new EntityBomb(worldObj, (EntityLivingBase) this, entityLivingBase, 1.6f, 12.0f);
		entityBomb.setPosition(posX, posY + 0.75, posZ);
		playSound("random.bow", 1.0f, 1.0f / (getRNG().nextFloat() * 0.4f + 0.8f));
		if (!worldObj.isRemote)
		{
			worldObj.spawnEntityInWorld((Entity) entityBomb);
		}
	}

	static
	{
		defaultHeldItem = new ItemStack(Goblins.bomb, 1);
	}
}
