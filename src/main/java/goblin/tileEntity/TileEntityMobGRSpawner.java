
package goblin.tileEntity;

import net.minecraft.tileentity.*;
import net.minecraft.world.*;
import goblin.Goblins;
import goblin.block.MobSpawnerGoblinLogic;
import net.minecraft.nbt.*;
import net.minecraft.network.*;
import net.minecraft.network.play.server.*;

public class TileEntityMobGRSpawner extends TileEntity {
	private MobSpawnerGoblinLogic field_145882_a;
	final private static String __OBFID = "CL_00000360";

	public TileEntityMobGRSpawner()
	{
		field_145882_a = new MobSpawnerGoblinLogic('r') {
			final private static String __OBFID = "CL_00000361";

			@Override
			public void func_98267_a(int par1)
			{
				TileEntityMobGRSpawner.this.worldObj.addBlockEvent(TileEntityMobGRSpawner.this.xCoord, TileEntityMobGRSpawner.this.yCoord, TileEntityMobGRSpawner.this.zCoord, Goblins.MobGRSpawner, par1, 0);
			}

			@Override
			public World getSpawnerWorld()
			{
				return TileEntityMobGRSpawner.this.worldObj;
			}

			@Override
			public int getSpawnerX()
			{
				return TileEntityMobGRSpawner.this.xCoord;
			}

			@Override
			public int getSpawnerY()
			{
				return TileEntityMobGRSpawner.this.yCoord;
			}

			@Override
			public int getSpawnerZ()
			{
				return TileEntityMobGRSpawner.this.zCoord;
			}

			@Override
			public void setRandomEntity(WeightedRandomMinecart par1WeightedRandomMinecart)
			{
				super.setRandomEntity(par1WeightedRandomMinecart);
				if (getSpawnerWorld() != null)
				{
					getSpawnerWorld().markBlockForUpdate(TileEntityMobGRSpawner.this.xCoord, TileEntityMobGRSpawner.this.yCoord, TileEntityMobGRSpawner.this.zCoord);
				}
			}
		};
	}

	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		field_145882_a.readFromNBT(compound);
	}

	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		field_145882_a.writeToNBT(compound);
	}

	public void updateEntity()
	{
		field_145882_a.updateSpawner();
		super.updateEntity();
	}

	public Packet getDescriptionPacket()
	{
		NBTTagCompound nbtTagCompound = new NBTTagCompound();
		writeToNBT(nbtTagCompound);
		nbtTagCompound.removeTag("SpawnPotentials");
		return (Packet) new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbtTagCompound);
	}

	public boolean receiveClientEvent(int id, int type)
	{
		return field_145882_a.setDelayToMin(id) || super.receiveClientEvent(id, type);
	}

	public MobSpawnerGoblinLogic func_145881_a()
	{
		return field_145882_a;
	}
}
