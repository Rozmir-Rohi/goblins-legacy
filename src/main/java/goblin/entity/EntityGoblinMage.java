
package goblin.entity;

import goblin.Goblins;
import goblin.achievements.GoblinsAchievements;
import goblin.entity.projectile.EntityArcaneball;
import goblin.world.gen.WorldGenSlimePool;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityGoblinMage extends EntityMob implements IMob, IBossDisplayData {
	double moveSpeed;
	private double spawnPosX;
	private double spawnPosY;
	private double spawnPosZ;
	double a1;
	double a2;
	double a3;
	public boolean spellChooser;
	private int teleportCounter;
	public int courseChangeCooldown;
	public double waypointX;
	public double waypointY;
	public double waypointZ;
	private int spellType;
	public EntityLivingBase targetedEntity;
	private int aggroCooldown;
	public int prevAttackCounter;
	public int attackCounter;
	private boolean isTeleporting;

	public EntityGoblinMage(World world)
	{
		super(world);
		moveSpeed = 0.0;
		isImmuneToFire = true;
		setSize(0.6f, 1.4f);
		teleportCounter = 0;
		spellChooser = false;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(70.0);
		if (!worldObj.isRemote && worldObj.difficultySetting == EnumDifficulty.PEACEFUL)
		{
			getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0);
		}
		else
		{
			getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(moveSpeed);
		}
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0);
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
	protected void entityInit()
	{
		super.entityInit();
		dataWatcher.addObject(16, Byte.valueOf((byte) 0));
		spawnPosX = posX;
		spawnPosY = posY;
		spawnPosZ = posZ;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		if (!worldObj.isRemote && worldObj.difficultySetting == EnumDifficulty.PEACEFUL)
		{
			moveSpeed = 0.0;
		}
		else
		{
			moveSpeed = 0.65;
		}
		
		EntityLivingBase targetedEntityFake = worldObj.getClosestVulnerablePlayerToEntity(this, 100.0);
		
		if (
				(
						targetedEntityFake != null
						&& getDistanceToEntity(targetedEntityFake) < 6.0f
				)
				|| teleportCounter >= 130
			)
		{
			isTeleporting = true;
		}
		else
		{
			isTeleporting = false;
		}
			
		if (isTeleporting && swingProgressInt == 1) //completed arms swing
		{
			generateParticles("explode");
		}
		
	}

	@Override
	protected void attackEntity(Entity entityToAttack, float distanceToEntityToAttack)
	{
		if (distanceToEntityToAttack < 20.0f)
		{
			moveSpeed = 0.65;
		}
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource damageSource, float damageTaken)
    {
		if (
				(	
						damageSource.isProjectile()
						&& GoblinsEntityTools.isDamageSourceEntityFromGoblinsMod(damageSource)
						
				)
				|| (damageSource.isExplosion() && damageTaken <= 50)
			)
		{
			return false; //prevents infighting among Goblins
		}
		return super.attackEntityFrom(damageSource, damageTaken);
    }
	

	@Override
	protected void updateEntityActionState()
	{
		if (worldObj.difficultySetting != EnumDifficulty.PEACEFUL)
		{
			if (!spellChooser)
			{
				spellType = rand.nextInt(4);
				spellChooser = true;
			}
			if (targetedEntity != null && targetedEntity.isDead)
			{
				targetedEntity = null;
			}
			if (targetedEntity == null || aggroCooldown-- <= 0)
			{
				targetedEntity = worldObj.getClosestVulnerablePlayerToEntity(this, 100.0);
				if (targetedEntity != null)
				{
					aggroCooldown = 20;
				}
			}
			double d4 = 16.0;
			targetedEntity = worldObj.getClosestVulnerablePlayerToEntity(this, 100.0);
			if (targetedEntity != null && targetedEntity.getDistanceSqToEntity(this) < d4 * d4)
			{
				double xDistance = targetedEntity.posX - posX;
				double yDistance = targetedEntity.boundingBox.minY + targetedEntity.height / 2.0f - (posY + height / 2.0f);
				double zDistance = targetedEntity.posZ - posZ;
				float n = -(float) Math.atan2(xDistance, zDistance) * 180.0f / 3.141593f;
				rotationYaw = n;
				renderYawOffset = n;
				if (canEntityBeSeen(targetedEntity))
				{
					double d8 = 8.0;
					if (teleportCounter == 140)
					{
						motionY = 0.8000000150804645;
						fallDistance = -25.0f;
					}
					if (teleportCounter == 150)
					{
						teleportToRandomAreaNearby(targetedEntity);
						teleportCounter = 0;
					}
					++teleportCounter;
					if (getDistanceToEntity(targetedEntity) > 6.0f)
					{
						if (attackCounter == 20)
						{
							generateParticles("smoke");
						}
						++attackCounter;
						if (attackCounter == 30)
						{
							swingItem(); //swing arm
							worldObj.playSoundAtEntity(this, "mob.ghast.fireball", getSoundVolume(), (rand.nextFloat() - rand.nextFloat()) * 0.2f + 1.0f);
							EntityArcaneball entityArcaneball = new EntityArcaneball(worldObj, this);
							double horizontalMultiplier = 1.0;
							Vec3 vec3d = getLook(1.0f);
							entityArcaneball.posX = posX + vec3d.xCoord * horizontalMultiplier;
							entityArcaneball.posY = posY + height / 2.0f + 0.4;
							entityArcaneball.posZ = posZ + vec3d.zCoord * horizontalMultiplier;
							worldObj.spawnEntityInWorld(entityArcaneball);
							attackCounter = 0;
							spellChooser = false;
						}
					}
					else
					{
						if (attackCounter == 15)
						{
							a1 = targetedEntity.posX;
							a2 = targetedEntity.posY;
							a3 = targetedEntity.posZ;
						}
						if (attackCounter == 20)
						{
							generateParticles("smoke");
						}
						++attackCounter;
						if (attackCounter == 30)
						{
							if (!worldObj.isRemote)
							{
								WorldGenSlimePool worldGenSlimePool = new WorldGenSlimePool();
								worldGenSlimePool.generate(worldObj, rand, (int) posX, (int) posY, (int) posZ);
							}
							teleportToRandomAreaNearby(targetedEntity);
							spellChooser = false;
							attackCounter = 0;
						}
					}
				}
				else if (attackCounter > 0)
				{
					--attackCounter;
				}
			}
			else
			{
				float n2 = -(float) Math.atan2(motionX, motionZ) * 180.0f / 3.141593f;
				rotationYaw = n2;
				renderYawOffset = n2;
				if (attackCounter > 0)
				{
					--attackCounter;
				}
			}
			if (!worldObj.isRemote)
			{
				byte byte0 = dataWatcher.getWatchableObjectByte(16);
				byte byte2 = (byte) ((attackCounter > 10) ? 1 : 0);
				if (byte0 != byte2)
				{
					dataWatcher.updateObject(16, byte2);
				}
			}
		}
		super.updateEntityActionState();
	}

	private void generateParticles(String  particleName)
	{
		for (int i = 0; i < 7; ++i)
		{
			double xOffset = rand.nextGaussian() * 0.01;
			double yOffset = rand.nextGaussian() * 0.01;
			double zOffset = rand.nextGaussian() * 0.01;
			worldObj.spawnParticle(particleName, posX + rand.nextFloat() * width * 2.0f - width, posY + 0.5 + rand.nextFloat() * height, posZ + rand.nextFloat() * width * 2.0f - width, xOffset, yOffset, zOffset);
		}
	}

	public void teleportToRandomAreaNearby(Entity entity)
	{
		int xOffset;
		int zOffset;
		int k1;
		int x;
		int z;
		int y;
		for (xOffset = rand.nextInt(16) - 8, zOffset = rand.nextInt(16) - 8, k1 = rand.nextInt(4), x = MathHelper.floor_double(entity.posX), z = MathHelper.floor_double(entity.posZ), y = MathHelper.floor_double(entity.boundingBox.minY); worldObj.getBlock(x + xOffset, y + k1, z + zOffset) != Blocks.air || worldObj.getBlock(x + xOffset, y + k1 + 1, z + zOffset) != Blocks.air; xOffset = rand.nextInt(16) - 8, zOffset = rand.nextInt(16) - 8, k1 = rand.nextInt(4))
		{
		}
		swingItem(); //swing arm
		worldObj.playSoundAtEntity(this, "mob.endermen.portal", 1.0f, 1.0f);
		setLocationAndAngles(x + xOffset, y, z + zOffset, rotationYaw, rotationPitch);
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
		
		for (int amountToDrop = rand.nextInt(3), index = 0; index < amountToDrop; ++index)
		{
			Item powder = Goblins.powderB;
			
			int powderTypePicker = rand.nextInt(4);
			
			switch(powderTypePicker)
			{
				case 0:
					powder = Goblins.powderB;
					break;
				case 1:
					powder = Goblins.powderG;
					break;
				case 2:
					powder = Goblins.powderR;
					break;
				case 3:
					powder = Goblins.powderY;
					break;
			}
			
			dropItem(powder, 1);
		}
		
		dropItem(Items.golden_apple, 1);
	}
	
	@Override
	public ItemStack getHeldItem()
	{
		if (isTeleporting)
		{
			return new ItemStack(Goblins.staffTeleport);
		}
		else
		{
			return new ItemStack(Goblins.staffArcane);
		}
	}
	
	@Override
	public void onDeath(DamageSource damageSource)
    {
        if (damageSource.getEntity() != null && damageSource.getEntity() instanceof EntityPlayer)
        {
          EntityPlayer player = (EntityPlayer)damageSource.getEntity();
          if (player != null) {player.addStat(GoblinsAchievements.kill_goblin_shaman, 1);} 
        } 
        super.onDeath(damageSource);
    }

	@Override
	public boolean getCanSpawnHere()
	{
		int xCoord = MathHelper.floor_double(posX);
		int yCoord = MathHelper.floor_double(boundingBox.minY);
		int zCoord = MathHelper.floor_double(posZ);
		return getBlockPathWeight(xCoord, yCoord, zCoord) >= 0.0f;
	}
}
