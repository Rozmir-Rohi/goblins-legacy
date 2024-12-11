
package goblin.entity;

import goblin.Goblins;
import goblin.achievements.GoblinsAchievements;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityGoblinRider extends GoblinsOldAIBase implements IGoblinEntityTextureBase {
	private static IEntitySelector attackEntitySelector;
	boolean isPanicked;
	private static ItemStack defaultHeldItem;

	public EntityGoblinRider(World world)
	{
		super(world);
		setSize(0.6f, 1.4f);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(15.0);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0);
	}
	
	@Override
	protected void attackEntity(Entity entityToAttack, float distanceToEntityToAttack)
	{
		if (
				onGround
				&& distanceToEntityToAttack >= 2.0
			)
		{
			moveTowardEntity(entityToAttack, false);
		}

		if (
				attackTime <= 0
				&& distanceToEntityToAttack < 2.0f
				&& entityToAttack.boundingBox.maxY > boundingBox.minY
				&& entityToAttack.boundingBox.minY < boundingBox.maxY
			)
		{
			attackTime = 20;
			GoblinsEntityTools.goblinsCustomAttackEntityAsMob(this, entityToAttack);
		}
	}
	
	@Override
	protected void moveTowardEntity(Entity entityToAttack, boolean shouldCharge)
	{
		double xDistance = entityToAttack.posX - posX;
		double zDistance = entityToAttack.posZ - posZ;
		float xzSquareRootDistance = MathHelper.sqrt_double(xDistance * xDistance + zDistance * zDistance);
		motionX = xDistance / xzSquareRootDistance * 0.15;
		motionZ = zDistance / xzSquareRootDistance * 0.15;
		
		if (shouldCharge)
		{
			motionY = 0.4000000241984645;
		}
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		if (ridingEntity == null)
		{
			for (int i = 0; i < worldObj.loadedEntityList.size(); ++i)
			{
				Entity entityNearby = (Entity) worldObj.loadedEntityList.get(i);
				if (
						entityNearby instanceof EntityDirewolf
						&& entityNearby.riddenByEntity == null
					)
				{
					double distanceToDirewolf = entityNearby.getDistance(posX, posY, posZ);
					EntityDirewolf entityDirewolf = (EntityDirewolf) entityNearby;
					if (distanceToDirewolf < 1.5)
					{
						mountEntity(entityNearby);
					}
				}
			}
		}
		else
		{
			rotationYaw = ridingEntity.rotationYaw;
		}
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
		dropItem(Goblins.goblinFlesh, rand.nextInt(2));
		dropItem(Items.leather, rand.nextInt(2));
	}

	@Override
	public boolean isEntityInsideOpaqueBlock()
	{
		return ridingEntity == null && super.isEntityInsideOpaqueBlock();
	}

	@Override
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

	@Override
	public double getYOffset()
	{
		if (ridingEntity instanceof EntityDirewolf)
		{
			return yOffset - 0.08f;
		}
		return yOffset;
	}

	@Override
	public int getMaxSpawnedInChunk()
	{
		return 20;
	}

	@Override
	public void updateRiderPosition()
	{
		riddenByEntity.setPosition(posX, posY + getMountedYOffset() + riddenByEntity.getYOffset(), posZ);
	}

	@Override
	public boolean getCanSpawnHere()
	{
		return true;
	}

	@Override
	public ItemStack getHeldItem()
	{
		return EntityGoblinRider.defaultHeldItem;
	}

	static
	{
		defaultHeldItem = new ItemStack(Items.stone_sword, 1);
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
		return new ResourceLocation("goblin:textures/entity/GoblinRider.png");
	}
}
