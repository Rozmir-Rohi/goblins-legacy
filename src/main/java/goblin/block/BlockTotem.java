
package goblin.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import goblin.Goblins;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

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

	@Override
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

	@Override
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
