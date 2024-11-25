package goblin.proxy;

import cpw.mods.fml.common.network.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;

public class CommonProxy implements IGuiHandler {
	public void registerEntityRenderers()
	{
		//Nothing is supposed to be here, renderers are handled through the client proxy.
	}

	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}

	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}
}
