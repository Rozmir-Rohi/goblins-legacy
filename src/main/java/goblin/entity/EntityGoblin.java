
package goblin.entity;

import goblin.Goblins;
import net.minecraft.command.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.nbt.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.*;
import net.minecraft.init.*;

public class EntityGoblin extends EntityMob implements IGoblinEntityTextureBase {
	private static IEntitySelector attackEntitySelector;
	float moveSpeed;
	boolean isPanicked;
	private static ItemStack defaultHeldItem;

	public EntityGoblin(World world)
	{
		super(world);
		moveSpeed = 0.9f;
		setSize(0.6f, 1.4f);
		isPanicked = false;
		getNavigator().setBreakDoors(true);
		tasks.addTask(0, (EntityAIBase) new EntityAISwimming((EntityLiving) this));
		tasks.addTask(1, (EntityAIBase) new EntityAIBreakDoor((EntityLiving) this));
		tasks.addTask(2, (EntityAIBase) new EntityAIAttackOnCollide((EntityCreature) this, (Class) EntityPlayer.class, (double) moveSpeed, false));
		tasks.addTask(3, (EntityAIBase) new EntityAIAttackOnCollide((EntityCreature) this, (Class) EntityVillager.class, (double) moveSpeed, true));
		tasks.addTask(3, (EntityAIBase) new EntityAIAttackOnCollide((EntityCreature) this, (Class) EntityGolem.class, (double) moveSpeed, true));
		tasks.addTask(3, (EntityAIBase) new EntityAIAttackOnCollide((EntityCreature) this, (Class) EntityLiving.class, (double) moveSpeed, true));
		tasks.addTask(4, (EntityAIBase) new EntityAIMoveTowardsRestriction((EntityCreature) this, (double) moveSpeed));
		tasks.addTask(5, (EntityAIBase) new EntityAIMoveThroughVillage((EntityCreature) this, (double) moveSpeed, false));
		tasks.addTask(6, (EntityAIBase) new EntityAIWander((EntityCreature) this, (double) moveSpeed));
		tasks.addTask(7, (EntityAIBase) new EntityAIWatchClosest((EntityLiving) this, (Class) EntityPlayer.class, 8.0f));
		tasks.addTask(7, (EntityAIBase) new EntityAILookIdle((EntityLiving) this));
		targetTasks.addTask(1, (EntityAIBase) new EntityAIHurtByTarget((EntityCreature) this, false));
		targetTasks.addTask(2, (EntityAIBase) new EntityAINearestAttackableTarget((EntityCreature) this, (Class) EntityPlayer.class, 0, true));
		targetTasks.addTask(3, (EntityAIBase) new EntityAINearestAttackableTarget((EntityCreature) this, (Class) EntityVillager.class, 0, false));
		targetTasks.addTask(4, (EntityAIBase) new EntityAINearestAttackableTarget((EntityCreature) this, (Class) EntityPlayer.class, 0, false));
		targetTasks.addTask(5, (EntityAIBase) new EntityAINearestAttackableTarget((EntityCreature) this, (Class) EntityLiving.class, 0, false, false, EntityGoblin.attackEntitySelector));
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(13.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3);
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

	protected void dropFewItems(boolean flag, int i)
	{
		dropItem(Goblins.goblinFlesh, rand.nextInt(2));
		dropItem(Items.leather, rand.nextInt(2));
	}

	public int getMaxSpawnedInChunk()
	{
		return 20;
	}

	public boolean getCanSpawnHere()
	{
		return true;
	}

	public ItemStack getHeldItem()
	{
		return EntityGoblin.defaultHeldItem;
	}

	static
	{
		attackEntitySelector = (IEntitySelector) new EntityGoblinAttackFilter();
		defaultHeldItem = new ItemStack(Items.wooden_axe, 1);
	}

	@Override
	public ResourceLocation getEntityTexture()
	{
		return new ResourceLocation("goblin:textures/entity/Goblin.png");
	}
}
