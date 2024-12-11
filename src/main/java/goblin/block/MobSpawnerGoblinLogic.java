
package goblin.block;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import goblin.Goblins;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;

public abstract class MobSpawnerGoblinLogic {
	public int spawnDelay;
	private String entityTypeName;
	private char goblinTypeCharacterOfThisSpawner;
	private int goblinsLeft;
	private List potentialEntitySpawns;
	private WeightedRandomMinecart randomEntity;
	public double field_98287_c;
	public double field_98284_d;
	private int minSpawnDelay;
	private int maxSpawnDelay;
	private int spawnCount;
	private Entity cachedEntity;
	private int maxNearbyEntities;
	private int activatingRangeFromPlayer;
	private int spawnRange;
	final private static String __OBFID = "CL_00000129";

	public MobSpawnerGoblinLogic(char goblinTypeCharacterOfThisSpawner)
	{
		spawnDelay = 20;
		entityTypeName = "Pig";
		goblinsLeft = 10;
		minSpawnDelay = 400;
		maxSpawnDelay = 800;
		spawnCount = 2;
		maxNearbyEntities = 6;
		activatingRangeFromPlayer = 16;
		spawnRange = 1;
		this.goblinTypeCharacterOfThisSpawner = goblinTypeCharacterOfThisSpawner;
	}

	public String getEntityNameToSpawn()
	{
		if (goblinTypeCharacterOfThisSpawner == 'g')
		{
			int goblinPickerNumber = getSpawnerWorld().rand.nextInt(20);
			if (goblinPickerNumber == 0)
			{
				return "goblin.GoblinBomber";
			}
			if (goblinPickerNumber <= 13)
			{
				return "goblin.GoblinRanger";
			}
			if (goblinPickerNumber <= 18)
			{
				return "goblin.GoblinSoldier";
			}
			else
			{
				return "goblin.Goblin";
			}
		}
		else if (goblinTypeCharacterOfThisSpawner == 'm')
		{
				return "goblin.GoblinMiner";
		}
		else if (goblinTypeCharacterOfThisSpawner == 'r')
		{
				return "goblin.GoblinRider";
		}
		else
		{
			return "goblin.Goblin";
		}
	}

	public void setGoblinSign(char charGob)
	{
		goblinTypeCharacterOfThisSpawner = charGob;
	}

	public boolean isActivated()
	{
		return getSpawnerWorld().getClosestPlayer(getSpawnerX() + 0.5, getSpawnerY() + 0.5, getSpawnerZ() + 0.5, activatingRangeFromPlayer) != null;
	}

	public void updateSpawner()
	{
		if (isActivated())
		{
			if (goblinsLeft <= 0)
			{
				if (Goblins.spawnerDeath)
				{
					getSpawnerWorld().setBlock(getSpawnerX(), getSpawnerY(), getSpawnerZ(), Blocks.cobblestone, 0, 2);
				}
				else
				{
					goblinsLeft = 10; //restart the cycle
				}
			}
			if (getSpawnerWorld().isRemote)
			{
				double xCoord = getSpawnerX() + getSpawnerWorld().rand.nextFloat();
				double yCoord = getSpawnerY() + getSpawnerWorld().rand.nextFloat();
				double zCoord = getSpawnerZ() + getSpawnerWorld().rand.nextFloat();
				getSpawnerWorld().spawnParticle("smoke", xCoord, yCoord, zCoord, 0.0, 0.0, 0.0);
				getSpawnerWorld().spawnParticle("flame", xCoord, yCoord, zCoord, 0.0, 0.0, 0.0);
				if (spawnDelay > 0)
				{
					--spawnDelay;
				}
				field_98284_d = field_98287_c;
				field_98287_c = (field_98287_c + 1000.0f / (spawnDelay + 200.0f)) % 360.0;
			}
			else
			{
				if (spawnDelay == -1)
				{
					resetTimer();
				}
				if (spawnDelay > 0)
				{
					--spawnDelay;
					return;
				}
				boolean flag = false;
				if (goblinsLeft > 0)
				{
					for (int i = 0; i < spawnCount; ++i)
					{
						Entity entity = EntityList.createEntityByName(getEntityNameToSpawn(), getSpawnerWorld());
						if (entity == null)
						{
							// System.out.println("YA");
							return;
						}
						int j = getSpawnerWorld().getEntitiesWithinAABB(entity.getClass(), AxisAlignedBB.getBoundingBox(getSpawnerX(), getSpawnerY(), getSpawnerZ(), getSpawnerX() + 1, getSpawnerY() + 1, getSpawnerZ() + 1).expand(spawnRange * 2, 4.0, spawnRange * 2)).size();
						if (j >= maxNearbyEntities)
						{
							resetTimer();
							return;
						}
						double d3 = getSpawnerX() + (getSpawnerWorld().rand.nextDouble() - getSpawnerWorld().rand.nextDouble()) * spawnRange;
						double d4 = getSpawnerY() + 2;
						double d5 = getSpawnerZ() + (getSpawnerWorld().rand.nextDouble() - getSpawnerWorld().rand.nextDouble()) * spawnRange;
						EntityLiving entityLiving = (entity instanceof EntityLiving) ? (EntityLiving) entity : null;
						entity.setLocationAndAngles(d3, d4, d5, getSpawnerWorld().rand.nextFloat() * 360.0f, 0.0f);
						if (goblinTypeCharacterOfThisSpawner == 'r')
						{
							Entity entity2 = EntityList.createEntityByName("goblin.Direwolf", getSpawnerWorld());
							entity2.setLocationAndAngles(d3, d4, d5, getSpawnerWorld().rand.nextFloat() * 360.0f, 0.0f);
							spawnEntity(entity2);
						}
						spawnEntity(entity);
						--goblinsLeft;
						getSpawnerWorld().playAuxSFX(2004, getSpawnerX(), getSpawnerY(), getSpawnerZ(), 0);
						if (entityLiving != null)
						{
							entityLiving.spawnExplosionParticle();
						}
						flag = true;
					}
				}
				if (flag)
				{
					resetTimer();
				}
			}
		}
		else
		{
			goblinsLeft = 10;
		}
	}

	public Entity spawnEntity(Entity entity)
	{
		if (getRandomEntity() != null)
		{
			NBTTagCompound nbtTagCompound = new NBTTagCompound();
			entity.writeToNBTOptional(nbtTagCompound);
			Iterator iterator = getRandomEntity().field_98222_b.func_150296_c().iterator();

			while (iterator.hasNext())
			{
				String s = (String) iterator.next();
				NBTBase nbtbase = getRandomEntity().field_98222_b.getTag(s);
				nbtTagCompound.setTag(s, nbtbase.copy());
			}

			entity.readFromNBT(nbtTagCompound);

			if (entity.worldObj != null)
			{
				entity.worldObj.spawnEntityInWorld(entity);
			}

			NBTTagCompound nbtTagCompound2;

			for (Entity entity1 = entity; nbtTagCompound.hasKey("Riding", 10); nbtTagCompound = nbtTagCompound2)
			{
				nbtTagCompound2 = nbtTagCompound.getCompoundTag("Riding");
				Entity entity2 = EntityList.createEntityByName(nbtTagCompound2.getString("id"), entity.worldObj);

				if (entity2 != null)
				{
					NBTTagCompound nbtTagCompound1 = new NBTTagCompound();
					entity2.writeToNBTOptional(nbtTagCompound1);
					Iterator iterator1 = nbtTagCompound2.func_150296_c().iterator();

					while (iterator1.hasNext())
					{
						String s1 = (String) iterator1.next();
						NBTBase nbtbase1 = nbtTagCompound2.getTag(s1);
						nbtTagCompound1.setTag(s1, nbtbase1.copy());
					}

					entity2.readFromNBT(nbtTagCompound1);
					entity2.setLocationAndAngles(entity1.posX, entity1.posY, entity1.posZ, entity1.rotationYaw, entity1.rotationPitch);

					if (entity.worldObj != null)
					{
						entity.worldObj.spawnEntityInWorld(entity2);
					}

					entity1.mountEntity(entity2);
				}

				entity1 = entity2;
			}
		}
		else if (entity instanceof EntityLivingBase && entity.worldObj != null)
		{
			((EntityLiving) entity).onSpawnWithEgg((IEntityLivingData) null);
			getSpawnerWorld().spawnEntityInWorld(entity);
		}

		return entity;
	}

	private void resetTimer()
	{
		if (maxSpawnDelay <= minSpawnDelay)
		{
			spawnDelay = minSpawnDelay;
		}
		else
		{
			int i = maxSpawnDelay - minSpawnDelay;
			spawnDelay = minSpawnDelay + getSpawnerWorld().rand.nextInt(i);
		}
		if (potentialEntitySpawns != null && potentialEntitySpawns.size() > 0)
		{
			setRandomEntity((WeightedRandomMinecart) WeightedRandom.getRandomItem(getSpawnerWorld().rand, potentialEntitySpawns));
		}
		func_98267_a(1);
	}

	public void readFromNBT(NBTTagCompound nbtTagCompound)
	{
		entityTypeName = nbtTagCompound.getString("EntityId");
		spawnDelay = nbtTagCompound.getShort("Delay");
		if (nbtTagCompound.hasKey("SpawnPotentials", 9))
		{
			potentialEntitySpawns = new ArrayList();
			NBTTagList nbttaglist = nbtTagCompound.getTagList("SpawnPotentials", 10);
			for (int i = 0; i < nbttaglist.tagCount(); ++i)
			{
				potentialEntitySpawns.add(new WeightedRandomMinecart(nbttaglist.getCompoundTagAt(i)));
			}
		}
		else
		{
			potentialEntitySpawns = null;
		}
		if (nbtTagCompound.hasKey("SpawnData", 10))
		{
			setRandomEntity(new WeightedRandomMinecart(nbtTagCompound.getCompoundTag("SpawnData"), entityTypeName));
		}
		else
		{
			setRandomEntity(null);
		}
		if (nbtTagCompound.hasKey("MinSpawnDelay", 99))
		{
			minSpawnDelay = nbtTagCompound.getShort("MinSpawnDelay");
			maxSpawnDelay = nbtTagCompound.getShort("MaxSpawnDelay");
			spawnCount = nbtTagCompound.getShort("SpawnCount");
		}
		if (nbtTagCompound.hasKey("MaxNearbyEntities", 99))
		{
			maxNearbyEntities = nbtTagCompound.getShort("MaxNearbyEntities");
			activatingRangeFromPlayer = nbtTagCompound.getShort("RequiredPlayerRange");
		}
		if (nbtTagCompound.hasKey("SpawnRange", 99))
		{
			spawnRange = nbtTagCompound.getShort("SpawnRange");
		}
		if (getSpawnerWorld() != null && getSpawnerWorld().isRemote)
		{
			cachedEntity = null;
		}
	}

	public void writeToNBT(NBTTagCompound nbtTagCompound)
	{
		nbtTagCompound.setString("EntityId", getEntityNameToSpawn());
		nbtTagCompound.setShort("Delay", (short) spawnDelay);
		nbtTagCompound.setShort("MinSpawnDelay", (short) minSpawnDelay);
		nbtTagCompound.setShort("MaxSpawnDelay", (short) maxSpawnDelay);
		nbtTagCompound.setShort("SpawnCount", (short) spawnCount);
		nbtTagCompound.setShort("MaxNearbyEntities", (short) maxNearbyEntities);
		nbtTagCompound.setShort("RequiredPlayerRange", (short) activatingRangeFromPlayer);
		nbtTagCompound.setShort("SpawnRange", (short) spawnRange);

		if (getRandomEntity() != null)
		{
			nbtTagCompound.setTag("SpawnData", getRandomEntity().field_98222_b.copy());
		}

		if (getRandomEntity() != null || potentialEntitySpawns != null && potentialEntitySpawns.size() > 0)
		{
			NBTTagList nbttaglist = new NBTTagList();

			if (potentialEntitySpawns != null && potentialEntitySpawns.size() > 0)
			{
				Iterator iterator = potentialEntitySpawns.iterator();

				while (iterator.hasNext())
				{
					MobSpawnerBaseLogic.WeightedRandomMinecart weightedrandomminecart = (MobSpawnerBaseLogic.WeightedRandomMinecart) iterator.next();
					nbttaglist.appendTag(weightedrandomminecart.func_98220_a());
				}
			}
			else
			{
				nbttaglist.appendTag(getRandomEntity().func_98220_a());
			}

			nbtTagCompound.setTag("SpawnPotentials", nbttaglist);
		}
	}

	public boolean setDelayToMin(int par1)
	{
		if (par1 == 1 && getSpawnerWorld().isRemote)
		{
			spawnDelay = minSpawnDelay;
			return true;
		}
		return false;
	}

	@SideOnly(Side.CLIENT)
	public Entity getEntityToRender()
	{
		if (cachedEntity == null)
		{
			Entity entity = EntityList.createEntityByName(getEntityNameToSpawn(), (World) null);
			entity = spawnEntity(entity);
			cachedEntity = entity;
		}
		return cachedEntity;
	}

	public WeightedRandomMinecart getRandomEntity()
	{
		return randomEntity;
	}

	public void setRandomEntity(WeightedRandomMinecart weightedRandomMinecart)
	{
		randomEntity = weightedRandomMinecart;
	}

	public abstract void func_98267_a(int p0);

	public abstract World getSpawnerWorld();

	public abstract int getSpawnerX();

	public abstract int getSpawnerY();

	public abstract int getSpawnerZ();

	public class WeightedRandomMinecart extends WeightedRandom.Item {
		public NBTTagCompound field_98222_b;
		public String entityTypeName;
		final private static String __OBFID = "CL_00000130";

		public WeightedRandomMinecart(NBTTagCompound nbtTagCompound)
		{
			super(nbtTagCompound.getInteger("Weight"));
			NBTTagCompound nbtTagCompound1 = nbtTagCompound.getCompoundTag("Properties");
			String s = nbtTagCompound.getString("Type");
			if (s.equals("Minecart"))
			{
				if (nbtTagCompound1 != null)
				{
					switch (nbtTagCompound1.getInteger("Type"))
					{
					case 0:
					{
						s = "MinecartRideable";
						break;
					}
					case 1:
					{
						s = "MinecartChest";
						break;
					}
					case 2:
					{
						s = "MinecartFurnace";
						break;
					}
					}
				}
				else
				{
					s = "MinecartRideable";
				}
			}
			field_98222_b = nbtTagCompound1;
			entityTypeName = s;
		}

		public WeightedRandomMinecart(NBTTagCompound nbtTagCompound, String string)
		{
			super(1);
			if (string.equals("Minecart"))
			{
				if (nbtTagCompound != null)
				{
					switch (nbtTagCompound.getInteger("Type"))
					{
					case 0:
					{
						string = "MinecartRideable";
						break;
					}
					case 1:
					{
						string = "MinecartChest";
						break;
					}
					case 2:
					{
						string = "MinecartFurnace";
						break;
					}
					}
				}
				else
				{
					string = "MinecartRideable";
				}
			}
			field_98222_b = nbtTagCompound;
			entityTypeName = string;
		}

		public NBTTagCompound func_98220_a()
		{
			NBTTagCompound nbtTagCompound = new NBTTagCompound();
			nbtTagCompound.setTag("Properties", field_98222_b);
			nbtTagCompound.setString("Type", entityTypeName);
			nbtTagCompound.setInteger("Weight", itemWeight);
			return nbtTagCompound;
		}
	}
}
