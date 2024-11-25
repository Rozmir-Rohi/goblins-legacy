package goblin;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;


public class GoblinsCreativeTab extends CreativeTabs {

	public GoblinsCreativeTab(int positionOfThisCreativeWithinCreativeTabArray, String creativeTabName)
	{
		super(positionOfThisCreativeWithinCreativeTabArray, creativeTabName);
		// TODO Auto-generated constructor stub
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem()
	{
		return Goblins.icon_goblin;
	}

}
