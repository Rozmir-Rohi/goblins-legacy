
package goblin.entity;

import goblin.Goblins;
import goblin.achievements.GoblinsAchievements;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityGoblinLord extends EntityMob implements IGoblinEntityTextureBase {
	double moveSpeed;
	private static ItemStack defaultHeldItem;
	public float armAngle;

	public EntityGoblinLord(World world)
	{
		super(world);
		moveSpeed = 0.8;
		armAngle = 0.0f;
		setSize(0.6f, 1.4f);
		isImmuneToFire = true;
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(70.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(7.0);
	}

	public void writeEntityToNBT(NBTTagCompound nbtTagCompound)
	{
		super.writeEntityToNBT(nbtTagCompound);
	}

	public void readEntityFromNBT(NBTTagCompound nbtTagCompound)
	{
		super.readEntityFromNBT(nbtTagCompound);
	}

	protected void attackEntity(Entity entityToAttack, float distanceToEntityToAttack)
	{
		if (onGround && distanceToEntityToAttack >= 5.5 && distanceToEntityToAttack < 6.5)
		{
			double xDistance = entityToAttack.posX - posX;
			double zDistance = entityToAttack.posZ - posZ;
			float xzSquareRootDistance = MathHelper.sqrt_double(xDistance * xDistance + zDistance * zDistance);
			motionX = xDistance / xzSquareRootDistance * 0.8 * 1.000000011920929 + motionX * 0.40000000298023225;
			motionZ = zDistance / xzSquareRootDistance * 0.8 * 1.000000011920929 + motionZ * 0.40000000298023225;
			motionY = 0.8000000150804645;
			fallDistance = -25.0f;
			playSound("goblin:goblinlord.charge", getSoundVolume(), getSoundPitch());
		}
		else if (onGround && distanceToEntityToAttack >= 2.0 && distanceToEntityToAttack < 3.0 && rand.nextInt(3) == 0)
		{
			double xDistance = entityToAttack.posX - posX;
			double zDistance = entityToAttack.posZ - posZ;
			float xzSquareRootDistance = MathHelper.sqrt_double(xDistance * xDistance + zDistance * zDistance);
			motionX = xDistance / xzSquareRootDistance * 0.3 * 1.000000011920929 + motionX * 0.40000000298023225;
			motionZ = zDistance / xzSquareRootDistance * 0.3 * 1.000000011920929 + motionZ * 0.40000000298023225;
			motionY = 0.3000000150804645;
		}
		else if (attackTime <= 0 && distanceToEntityToAttack < 2.0f && entityToAttack.boundingBox.maxY > boundingBox.minY && entityToAttack.boundingBox.minY < boundingBox.maxY)
		{
			attackTime = 20;
			GoblinsEntityTools.goblinsCustomAttackEntityAsMob(this, entityToAttack);
		}
	}
	

	protected boolean canDespawn()
	{
		return false;
	}

	protected String getLivingSound()
	{
		return "goblin:goblinlord.idle";
	}

	protected String getHurtSound()
	{
		return "goblin:goblinlord.hurt";
	}

	protected String getDeathSound()
	{
		return "goblin:goblinlord.dead";
	}

	protected float getSoundVolume()
	{
		return 0.5f;
	}

	protected Item getDropItem()
	{
		return Items.wooden_axe;
	}

	protected void dropFewItems(boolean flag, int i)
	{
		dropItem(Goblins.goblinFlesh, rand.nextInt(2));
		
		dropItem(Items.golden_apple, 1);
	}

	public void knockBack(Entity entity, float par2, double par3, double par5)
	{
		if (rand.nextDouble() >= getEntityAttribute(SharedMonsterAttributes.knockbackResistance).getAttributeValue())
		{
			isAirBorne = true;
			float f1 = MathHelper.sqrt_double(par3 * par3 + par5 * par5);
			if (rand.nextInt(3) == 0)
			{
				f1 = 1.0f;
			}
			float f2 = 0.4f;
			motionX /= 2.0;
			motionY /= 2.0;
			motionZ /= 2.0;
			motionX -= par3 / f1 * f2;
			motionY += f2;
			motionZ -= par5 / f1 * f2;
			if (motionY > 0.4000000059604645)
			{
				motionY = 0.4000000059604645;
			}
		}
	}

	public int getMaxSpawnedInChunk()
	{
		return 1;
	}
	
	public void onDeath(DamageSource damageSource)
    {
        if (damageSource.getEntity() != null && damageSource.getEntity() instanceof EntityPlayer)
        {
          EntityPlayer player = (EntityPlayer)damageSource.getEntity();
          if (player != null) {player.addStat(GoblinsAchievements.kill_goblin_lord, 1);} 
        } 
        super.onDeath(damageSource);
    }

	public boolean getCanSpawnHere()
	{
		int xCoord = MathHelper.floor_double(posX);
		int yCoord = MathHelper.floor_double(boundingBox.minY);
		int zCoord = MathHelper.floor_double(posZ);
		return getBlockPathWeight(xCoord, yCoord, zCoord) >= 0.0f;
	}

	public ItemStack getHeldItem()
	{
		return EntityGoblinLord.defaultHeldItem;
	}

	static
	{
		defaultHeldItem = new ItemStack(Items.iron_sword, 1);
	}

	@Override
	public ResourceLocation getEntityTexture()
	{
		return new ResourceLocation("goblin:textures/entity/GoblinLord.png");
	}
}
