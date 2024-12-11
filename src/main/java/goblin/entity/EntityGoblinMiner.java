
package goblin.entity;

import goblin.achievements.GoblinsAchievements;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
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
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityGoblinMiner extends EntityMob implements IGoblinEntityTextureBase {
	private static IEntitySelector attackEntitySelector;
	boolean isPanicked;
	private static ItemStack defaultHeldItem;

	public EntityGoblinMiner(World world)
	{
		super(world);
		isPanicked = false;
		setSize(0.6f, 0.8f);
		float moveSpeed = 0.7f;
		getNavigator().setBreakDoors(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIBreakDoor(this));
		tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, moveSpeed, false));
		tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityVillager.class, moveSpeed, true));
		tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityGolem.class, moveSpeed, true));
		tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityLiving.class, moveSpeed, true));
		tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, moveSpeed));
		tasks.addTask(5, new EntityAIMoveThroughVillage(this, moveSpeed, false));
		tasks.addTask(6, new EntityAIWander(this, moveSpeed));
		tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
		tasks.addTask(7, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, false));
		targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, false));
		targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, false, false, EntityGoblinMiner.attackEntitySelector));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(13.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.29);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0);
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
	protected void dropFewItems(boolean flag, int i)
	{
		if (rand.nextInt(2) == 0)
		{
			dropItem(Items.coal, rand.nextInt(3) + 1);
		}
		if (rand.nextInt(5) == 0)
		{
			dropItem(Items.iron_ingot, 1);
		}
	}

	@Override
	public int getMaxSpawnedInChunk()
	{
		return 20;
	}

	
	@Override
	public boolean getCanSpawnHere()
	{
		int xCoord = MathHelper.floor_double(posX);
		int yCoord = MathHelper.floor_double(boundingBox.minY);
		int zCoord = MathHelper.floor_double(posZ);
		
	    return (
	    			worldObj.checkNoEntityCollision(boundingBox)
	    			&& worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty()
	    			&& !worldObj.isAnyLiquid(boundingBox)
	    			&& isValidLightLevel()
	    			&& (
	    					posY < 60.0D
	    					|| !(worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord)) //underground
	    				)
	    		);
	  }

	@Override
	public ItemStack getHeldItem()
	{
		return EntityGoblinMiner.defaultHeldItem;
	}

	static
	{
		attackEntitySelector = new GoblinsLesserGoblinAttackFilter();
		defaultHeldItem = new ItemStack(Items.stone_pickaxe, 1);
	}
	
	@Override
	public void onDeath(DamageSource damageSource)
    {
        if (damageSource.getEntity() != null && damageSource.getEntity() instanceof EntityPlayer)
        {
          EntityPlayer player = (EntityPlayer)damageSource.getEntity();
          if (player != null) {player.addStat(GoblinsAchievements.kill_goblin_miner, 1);} 
        } 
        super.onDeath(damageSource);
    }

	@Override
	public ResourceLocation getEntityTexture()
	{
		return new ResourceLocation("goblin:textures/entity/GoblinMiner.png");
	}
}
