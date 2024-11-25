
package goblin.block;

import net.minecraft.block.*;
import net.minecraft.util.*;
import cpw.mods.fml.relauncher.*;
import goblin.Goblins;
import net.minecraft.block.material.*;
import net.minecraft.client.renderer.texture.*;

public class BlockTotem extends Block {
	@SideOnly(Side.CLIENT)
	private IIcon side;
	@SideOnly(Side.CLIENT)
	private IIcon top;
	
	String stringName;
	
	public BlockTotem(String stringName)
	{
		super(Material.ground);
		setBlockName(stringName);
		this.stringName = stringName;
		this.setCreativeTab(Goblins.GOBLINS_CREATIVE_TAB);
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iIconRegister)
	{
		if (stringName == "totemR")
		{
			side = iIconRegister.registerIcon("goblin:TotemR");
			top = iIconRegister.registerIcon("goblin:TotemRT");
		}
		if (stringName == "totemG")
		{
			side = iIconRegister.registerIcon("goblin:TotemG");
			top = iIconRegister.registerIcon("goblin:TotemGT");
		}
		if (stringName == "totemB")
		{
			side = iIconRegister.registerIcon("goblin:TotemB");
			top = iIconRegister.registerIcon("goblin:TotemBT");
		}
		if (stringName == "totemY")
		{
			side = iIconRegister.registerIcon("goblin:TotemY");
			top = iIconRegister.registerIcon("goblin:TotemYT");
		}
	}

	public IIcon getIcon(int i, int j)
	{
		if (i == 1)
		{
			return top;
		}
		if (i == 0)
		{
			return top;
		}
		return side;
	}
}
