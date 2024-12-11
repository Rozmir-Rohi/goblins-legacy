
package goblin.entity;

import goblin.Goblins;
import goblin.achievements.GoblinsAchievements;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityGoblinSoldier extends GoblinsOldAIBase implements IGoblinEntityTextureBase {
	private static ItemStack defaultHeldItem;

	public EntityGoblinSoldier(World world)
	{
		super(world);
		setSize(0.6f, 1.4f);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(17.0);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0);
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	@Override
	protected boolean isAIEnabled()
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
		dropItem(Goblins.goblinFlesh, rand.nextInt(2));
		dropItem(Items.leather, rand.nextInt(2) + 1);
	}

	@Override
	protected void attackEntity(Entity entityToAttack, float distanceToEntityToAttack)
	{
		if (
				onGround
				&& distanceToEntityToAttack >= 2.0
			)
		{
			if (distanceToEntityToAttack > 3.0)
			{
				moveTowardEntity(entityToAttack, false); //walk towards entity to attack
			}
			else
			{
				moveTowardEntity(entityToAttack, true); //charge
			}
		}
		else if (attackTime <= 0 && distanceToEntityToAttack < 2.0f && entityToAttack.boundingBox.maxY > boundingBox.minY && entityToAttack.boundingBox.minY < boundingBox.maxY)
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
		
		if (shouldCharge)
		{
			motionX = xDistance / xzSquareRootDistance * 0.4 * 1.000000011920929 + motionX * 0.20000000298023224;
			motionZ = zDistance / xzSquareRootDistance * 0.4 * 1.000000011920929 + motionZ * 0.20000000298023224;
			
			motionY = 0.4000000241984645;
		}
		else
		{
			motionX = xDistance / xzSquareRootDistance * 0.1;
			motionZ = zDistance / xzSquareRootDistance * 0.1;
		}
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
		return EntityGoblinSoldier.defaultHeldItem;
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
          if (player != null) {player.addStat(GoblinsAchievements.kill_goblin_soldier, 1);} 
        } 
        super.onDeath(damageSource);
    }

	@Override
	public ResourceLocation getEntityTexture()
	{
		return new ResourceLocation("goblin:textures/entity/GoblinSoldier.png");
	}
}
