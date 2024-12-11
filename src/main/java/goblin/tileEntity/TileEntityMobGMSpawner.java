
package goblin.tileEntity;

import goblin.Goblins;
import goblin.block.MobSpawnerGoblinLogic;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityMobGMSpawner extends TileEntity {
	private MobSpawnerGoblinLogic field_145882_a;
	final private static String __OBFID = "CL_00000360";

	public TileEntityMobGMSpawner()
	{
		field_145882_a = new MobSpawnerGoblinLogic('m') {
			final private static String __OBFID = "CL_00000361";

			@Override
			public void func_98267_a(int par1)
			{
				TileEntityMobGMSpawner.this.worldObj.addBlockEvent(TileEntityMobGMSpawner.this.xCoord, TileEntityMobGMSpawner.this.yCoord, TileEntityMobGMSpawner.this.zCoord, Goblins.MobGMSpawner, par1, 0);
			}

			@Override
			public World getSpawnerWorld()
			{
				return TileEntityMobGMSpawner.this.worldObj;
			}

			@Override
			public int getSpawnerX()
			{
				return TileEntityMobGMSpawner.this.xCoord;
			}

			@Override
			public int getSpawnerY()
			{
				return TileEntityMobGMSpawner.this.yCoord;
			}

			@Override
			public int getSpawnerZ()
			{
				return TileEntityMobGMSpawner.this.zCoord;
			}

			@Override
			public void setRandomEntity(WeightedRandomMinecart weightedRandomMinecart)
			{
				super.setRandomEntity(weightedRandomMinecart);
				if (getSpawnerWorld() != null)
				{
					getSpawnerWorld().markBlockForUpdate(TileEntityMobGMSpawner.this.xCoord, TileEntityMobGMSpawner.this.yCoord, TileEntityMobGMSpawner.this.zCoord);
				}
			}
		};
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		field_145882_a.readFromNBT(compound);
	}

	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		field_145882_a.writeToNBT(compound);
	}

	@Override
	public void updateEntity()
	{
		field_145882_a.updateSpawner();
		super.updateEntity();
	}

	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound nbtTagCompound = new NBTTagCompound();
		writeToNBT(nbtTagCompound);
		nbtTagCompound.removeTag("SpawnPotentials");
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbtTagCompound);
	}

	@Override
	public boolean receiveClientEvent(int id, int type)
	{
		return field_145882_a.setDelayToMin(id) || super.receiveClientEvent(id, type);
	}

	public MobSpawnerGoblinLogic func_145881_a()
	{
		return field_145882_a;
	}
}
