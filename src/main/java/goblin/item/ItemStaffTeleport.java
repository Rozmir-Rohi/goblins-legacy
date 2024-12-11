
package goblin.item;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemStaffTeleport extends GoblinsItem {
	public int timer;

	public ItemStaffTeleport(String stringName)
	{
		super(stringName);
		maxStackSize = 1;
		setMaxDamage(40);
		timer = 0;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
	{
		if (timer == 0)
		{
			int maxDistanceForTeleport = 15;
			
			if (//if player is stuck in a wall try to find a random safe place to teleport
					world.getBlock(Math.round((float) entityPlayer.posX), Math.round((float) entityPlayer.posY + 1), Math.round((float) entityPlayer.posZ)) != Blocks.air
					&& world.getBlock(Math.round((float) entityPlayer.posX), Math.round((float) entityPlayer.posY + 1), Math.round((float) entityPlayer.posZ)) != Blocks.air
				)
			{
				int x = Math.round((float) entityPlayer.posX);
				int y = Math.round((float) entityPlayer.posY);
				int z = Math.round((float) entityPlayer.posZ);
				
				int loopCount = 0;
				Random rand = new Random();
				while (
						loopCount < 100
						&& isAreaNotSafeForTeleport(world, x, y, z)
					)
				{
					x += rand.nextInt(maxDistanceForTeleport+maxDistanceForTeleport) - maxDistanceForTeleport;
					y += rand.nextInt(maxDistanceForTeleport+maxDistanceForTeleport) - maxDistanceForTeleport;
					z += rand.nextInt(maxDistanceForTeleport+maxDistanceForTeleport) - maxDistanceForTeleport;
					loopCount++;
				}
				
				if (isAreaNotSafeForTeleport(world, x, y, z))
				{
					return itemStack; //don't teleport
				}
				
				else
				{
					executeTeleportAndDamageItem(itemStack, world, entityPlayer, x, y, z, true);
					return itemStack;
				}
			}
			
			else
			{//try to teleport to block that player is looking at
				Vec3 lookVec = entityPlayer.getLookVec();
				Vec3 positionVec = Vec3.createVectorHelper(entityPlayer.posX, entityPlayer.posY + entityPlayer.getEyeHeight(), entityPlayer.posZ);

				Vec3 finalVec = positionVec.addVector(lookVec.xCoord * maxDistanceForTeleport, lookVec.yCoord * maxDistanceForTeleport, lookVec.zCoord * maxDistanceForTeleport);
				
				MovingObjectPosition movingObjectPosition = world.rayTraceBlocks(positionVec, finalVec);
				if (movingObjectPosition != null && movingObjectPosition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
				{	
					int x = Math.round(movingObjectPosition.blockX);
					int y = Math.round(movingObjectPosition.blockY);
					int z = Math.round(movingObjectPosition.blockZ);
					
					if (
							world.getBlock(x, y, z) != Blocks.air
							&& world.getBlock(x, y - 1, z) != Blocks.air //block underneath is a solid block
						)
					{
						if (world.getBlock(x, y + 1, z) == Blocks.air)
						{ //stop players legs stuck in a block
							y++;
						}
							
						if (isAreaNotSafeForTeleport(world, x, y, z)) //final check for unsafe teleports
						{
							return itemStack; //don't teleport
						}
					}
					
					executeTeleportAndDamageItem(itemStack, world, entityPlayer, x, y, z, false);
				}
			}
		}
		return itemStack;
	}

	private boolean isAreaNotSafeForTeleport(World world, int x, int y, int z)
	{
		return
		(
			world.getBlock(x, y, z) != Blocks.air // block at players legs is solid
			|| world.getBlock(x, y + 1, z) != Blocks.air //block at players head is solid
			|| world.getBlock(x, y - 1, z) == Blocks.air //block below is air
			|| world.getBlock(x, y - 1, z) instanceof BlockLiquid //block below is a liquid
		);
	}

	private void executeTeleportAndDamageItem(ItemStack itemStack, World world, EntityPlayer entityPlayer, int x, int y, int z, boolean shouldBreakInstantly)
	{
		entityPlayer.swingItem();
		entityPlayer.fallDistance = 0f;
		
		entityPlayer.setPositionAndUpdate(x, y, z);
		
		generateParticles(entityPlayer);

		if (!world.isRemote)
		{
			world.playSoundAtEntity(entityPlayer, "mob.endermen.portal", 1f, 1f);
			timer = 20;
			
			if (shouldBreakInstantly)
			{
				setDamage(itemStack, itemStack.getMaxDamage() + 1);
			}
			else
			{
				itemStack.damageItem(1, entityPlayer);
			}
		}
	}
	
	private void generateParticles(EntityPlayer entityPlayer)
	{
		String particleName = "explode";
		
		Random rand = new Random();
		
		for (int i = 0; i < 7; ++i)
		{
			double xOffset = rand.nextGaussian() * 0.1;
			double yOffset = rand.nextGaussian() * 0.1;
			double zOffset = rand.nextGaussian() * 0.1;
			entityPlayer.worldObj.spawnParticle
					(
							particleName,
							entityPlayer.posX + rand.nextFloat() * entityPlayer.width * 2.0f - entityPlayer.width,
							entityPlayer.posY,
							entityPlayer.posZ + rand.nextFloat() * entityPlayer.width * 2.0f - entityPlayer.width,
							xOffset,
							yOffset,
							zOffset
					);
		}
	}

	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int i, boolean flag)
	{
		if (timer > 0)
		{
			--timer;
		}
	}
}
