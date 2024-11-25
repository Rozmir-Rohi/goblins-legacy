
package goblin.entity;

import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.boss.*;
import net.minecraft.world.*;
import goblin.Goblins;
import goblin.achievements.GoblinsAchievements;
import goblin.entity.projectile.EntityGArcaneball;
import goblin.world.gen.WorldGenSlimePool;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraft.nbt.*;
import net.minecraft.init.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

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

	public boolean isEntityInsideOpaqueBlock()
	{
		return false;
	}

	protected boolean canDespawn()
	{
		return false;
	}

	protected void entityInit()
	{
		super.entityInit();
		dataWatcher.addObject(16, Byte.valueOf((byte) 0));
		spawnPosX = posX;
		spawnPosY = posY;
		spawnPosZ = posZ;
	}

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
		
		EntityLivingBase targetedEntityFake = (EntityLivingBase) worldObj.getClosestVulnerablePlayerToEntity((Entity) this, 100.0);
		
		if (
				(
						targetedEntityFake != null
						&& getDistanceToEntity((Entity) targetedEntityFake) < 6.0f
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
		
	}

	protected void attackEntity(Entity entityToAttack, float distanceToEntityToAttack)
	{
		if (distanceToEntityToAttack < 20.0f)
		{
			moveSpeed = 0.65;
		}
	}

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
				targetedEntity = (EntityLivingBase) worldObj.getClosestVulnerablePlayerToEntity((Entity) this, 100.0);
				if (targetedEntity != null)
				{
					aggroCooldown = 20;
				}
			}
			double d4 = 16.0;
			targetedEntity = (EntityLivingBase) worldObj.getClosestVulnerablePlayerToEntity((Entity) this, 100.0);
			if (targetedEntity != null && targetedEntity.getDistanceSqToEntity((Entity) this) < d4 * d4)
			{
				double d5 = targetedEntity.posX - posX;
				double d6 = targetedEntity.boundingBox.minY + targetedEntity.height / 2.0f - (posY + height / 2.0f);
				double d7 = targetedEntity.posZ - posZ;
				float n = -(float) Math.atan2(d5, d7) * 180.0f / 3.141593f;
				rotationYaw = n;
				renderYawOffset = n;
				if (canEntityBeSeen((Entity) targetedEntity))
				{
					double d8 = 8.0;
					if (teleportCounter == 140)
					{
						motionY = 0.8000000150804645;
						fallDistance = -25.0f;
					}
					if (teleportCounter == 150)
					{
						String s = "explode";
						for (int i = 0; i < 7; ++i)
						{
							double d9 = rand.nextGaussian() * 0.01;
							double d10 = rand.nextGaussian() * 0.01;
							double d11 = rand.nextGaussian() * 0.01;
							worldObj.spawnParticle(s, posX + rand.nextFloat() * width * 2.0f - width, posY + 0.5 + rand.nextFloat() * height, posZ + rand.nextFloat() * width * 2.0f - width, d9, d10, d11);
						}
						teleport((Entity) targetedEntity);
						teleportCounter = 0;
					}
					++teleportCounter;
					if (getDistanceToEntity((Entity) targetedEntity) > 6.0f)
					{
						if (attackCounter == 20)
						{
							String s = "smoke";
							for (int i = 0; i < 7; ++i)
							{
								double d9 = rand.nextGaussian() * 0.01;
								double d10 = rand.nextGaussian() * 0.01;
								double d11 = rand.nextGaussian() * 0.01;
								worldObj.spawnParticle(s, posX + rand.nextFloat() * width * 2.0f - width, posY + 0.5 + rand.nextFloat() * height, posZ + rand.nextFloat() * width * 2.0f - width, d9, d10, d11);
							}
						}
						++attackCounter;
						if (attackCounter == 30)
						{
							swingItem(); //swing arm
							worldObj.playSoundAtEntity((Entity) this, "mob.ghast.fireball", getSoundVolume(), (rand.nextFloat() - rand.nextFloat()) * 0.2f + 1.0f);
							EntityGArcaneball entityArcaneball = new EntityGArcaneball(worldObj, (EntityLiving) this, d5, d6, d7);
							double d12 = 1.0;
							Vec3 vec3d = getLook(1.0f);
							entityArcaneball.posX = posX + vec3d.xCoord * d12;
							entityArcaneball.posY = posY + height / 2.0f + 0.4;
							entityArcaneball.posZ = posZ + vec3d.zCoord * d12;
							worldObj.spawnEntityInWorld((Entity) entityArcaneball);
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
							String particleName = "smoke";
							for (int i = 0; i < 7; ++i)
							{
								double d9 = rand.nextGaussian() * 0.01;
								double d10 = rand.nextGaussian() * 0.01;
								double d11 = rand.nextGaussian() * 0.01;
								worldObj.spawnParticle(particleName, posX + rand.nextFloat() * width * 2.0f - width, posY + 0.5 + rand.nextFloat() * height, posZ + rand.nextFloat() * width * 2.0f - width, d9, d10, d11);
							}
						}
						++attackCounter;
						if (attackCounter == 30)
						{
							if (!worldObj.isRemote)
							{
								WorldGenSlimePool gen = new WorldGenSlimePool();
								gen.generate(worldObj, rand, (int) posX, (int) posY, (int) posZ);
							}
							teleport((Entity) targetedEntity);
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
					dataWatcher.updateObject(16, (Object) byte2);
				}
			}
		}
		super.updateEntityActionState();
	}

	public void teleport(Entity entity)
	{
		int i1;
		int j1;
		int k1;
		int l;
		int m;
		int k2;
		for (i1 = rand.nextInt(16) - 8, j1 = rand.nextInt(16) - 8, k1 = rand.nextInt(4), l = MathHelper.floor_double(entity.posX), m = MathHelper.floor_double(entity.posZ), k2 = MathHelper.floor_double(entity.boundingBox.minY); worldObj.getBlock(l + i1, k2 + k1, m + j1) != Blocks.air || worldObj.getBlock(l + i1, k2 + k1 + 1, m + j1) != Blocks.air; i1 = rand.nextInt(16) - 8, j1 = rand.nextInt(16) - 8, k1 = rand.nextInt(4))
		{
		}
		swingItem(); //swing arm
		worldObj.playSoundAtEntity((Entity) this, "mob.endermen.portal", 1f, 1f);
		setLocationAndAngles((double) (l + i1), (double) k2, (double) (m + j1), rotationYaw, rotationPitch);
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
	
	public void onDeath(DamageSource damageSource)
    {
        if (damageSource.getEntity() != null && damageSource.getEntity() instanceof EntityPlayer)
        {
          EntityPlayer player = (EntityPlayer)damageSource.getEntity();
          if (player != null) {player.addStat(GoblinsAchievements.kill_goblin_shaman, 1);} 
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
}
