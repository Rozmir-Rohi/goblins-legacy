
package goblin.entity;

import goblin.Goblins;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityGoblinSoldier extends EntityGoblinsOldAiBase implements IGoblinEntityTextureBase {
	private static ItemStack defaultHeldItem;

	public EntityGoblinSoldier(World world)
	{
		super(world);
		setSize(0.6f, 1.4f);
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(17.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.65);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0);
	}

	protected boolean canDespawn()
	{
		return false;
	}

	protected boolean isAIEnabled()
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
		dropItem(Items.leather, rand.nextInt(2) + 1);
	}

	protected void attackEntity(Entity entityToAttack, float distanceToEntityToAttack)
	{
		if (onGround && distanceToEntityToAttack >= 2.0 && distanceToEntityToAttack < 3.0 && rand.nextInt(3) == 0)
		{
			moveTowardEntity(entityToAttack, true);
		}
		else if (attackTime <= 0 && distanceToEntityToAttack < 2.0f && entityToAttack.boundingBox.maxY > boundingBox.minY && entityToAttack.boundingBox.minY < boundingBox.maxY)
		{
			attackTime = 20;
			GoblinsEntityTools.goblinsCustomAttackEntityAsMob(this, entityToAttack);
		}
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
		return EntityGoblinSoldier.defaultHeldItem;
	}

	static
	{
		defaultHeldItem = new ItemStack(Items.stone_sword, 1);
	}

	@Override
	public ResourceLocation getEntityTexture()
	{
		return new ResourceLocation("goblin:textures/entity/GoblinSoldier.png");
	}
}
