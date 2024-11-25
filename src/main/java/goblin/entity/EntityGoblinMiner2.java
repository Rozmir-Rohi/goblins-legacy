
package goblin.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityGoblinMiner2 extends EntityMob implements IGoblinEntityTextureBase {
	private static ItemStack defaultHeldItem;

	public EntityGoblinMiner2(World world)
	{
		super(world);
		setSize(0.6f, 1.4f);
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(14.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.7);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0);
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
		if (rand.nextInt(2) == 0)
		{
			dropItem(Items.coal, rand.nextInt(3) + 1);
		}
		if (rand.nextInt(10) == 0)
		{
			dropItem(Items.iron_ingot, 1);
		}
	}

	public boolean isEntityInsideOpaqueBlock()
	{
		return false;
	}

	public int getMaxSpawnedInChunk()
	{
		return 20;
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
		return EntityGoblinMiner2.defaultHeldItem;
	}

	static
	{
		defaultHeldItem = new ItemStack(Items.stone_pickaxe, 1);
	}

	@Override
	public ResourceLocation getEntityTexture()
	{
		return new ResourceLocation("goblin:textures/entity/GoblinMiner.png");
	}
}
