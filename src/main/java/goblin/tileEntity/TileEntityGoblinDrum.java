
package goblin.tileEntity;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityGoblinDrum extends TileEntity {
	public byte note;
	public boolean previousRedstoneState;

	public TileEntityGoblinDrum()
	{
		note = 0;
		previousRedstoneState = false;
	}

	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setByte("note", note);
	}

	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readFromNBT(par1NBTTagCompound);
		note = par1NBTTagCompound.getByte("note");
		if (note < 0)
		{
			note = 0;
		}
		if (note > 24)
		{
			note = 24;
		}
	}

	public void changePitch()
	{
		note = (byte) ((note + 1) % 25);
		markDirty();
	}

	public void triggerNote(World world, int xCoord, int yCoord, int zCoord)
	{
		Material material = world.getBlock(xCoord, yCoord - 1, zCoord).getMaterial();
		byte byte0 = 1;
		world.addBlockEvent(xCoord, yCoord, zCoord, Blocks.noteblock, (int) byte0, (int) note);
	}
}
