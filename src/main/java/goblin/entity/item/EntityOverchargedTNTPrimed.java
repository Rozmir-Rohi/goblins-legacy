
package goblin.entity.item;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.*;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.world.*;
import net.minecraft.util.*;
import net.minecraft.nbt.*;

public class EntityOverchargedTNTPrimed extends EntityTNTPrimed implements IEntityAdditionalSpawnData{
	
	
	public EntityOverchargedTNTPrimed(World world)
	{
		super(world);
	}
	
	public EntityOverchargedTNTPrimed(World world, double xCoord, double yCoord_, double zCoord, EntityLivingBase entityLivingBase)
    {
        super(world, xCoord, yCoord_, zCoord, entityLivingBase);
    }
	
	@Override
	public void writeSpawnData(ByteBuf buffer)
	{	
	}
	
	@Override
	public void readSpawnData(ByteBuf additionalData)
	{
		fuse = 80;
	}
	
	@Override
	public void onUpdate()
    {
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
        motionY -= 0.03999999910593033D;
        moveEntity(motionX, motionY, motionZ);
        motionX *= 0.9800000190734863D;
        motionY *= 0.9800000190734863D;
        motionZ *= 0.9800000190734863D;

        if (onGround)
        {
            motionX *= 0.699999988079071D;
            motionZ *= 0.699999988079071D;
            motionY *= -0.5D;
        }

        if (fuse-- <= 0)
        {
            setDead();

            if (!worldObj.isRemote)
            {
                explode();
            }
        }
        else
        {
            worldObj.spawnParticle("smoke", posX, posY + 0.5D, posZ, 0.0D, 0.0D, 0.0D);
        }
    }
	
	private void explode()
    {
        float f = 20.0F;
        worldObj.createExplosion(this, posX, posY, posZ, f, true);
    }
}
