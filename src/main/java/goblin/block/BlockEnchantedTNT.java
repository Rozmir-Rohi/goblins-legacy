
package goblin.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import goblin.entity.item.EntityEnchantedTNTPrimed;
import net.minecraft.block.BlockTNT;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockEnchantedTNT extends BlockTNT {
	@SideOnly(Side.CLIENT)
	private IIcon side;
	@SideOnly(Side.CLIENT)
	private IIcon top;
	@SideOnly(Side.CLIENT)
	private IIcon bottom;

	public BlockEnchantedTNT()
	{
		super();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iIconRegister)
	{
		side = iIconRegister.registerIcon("goblin:ETNT");
		top = iIconRegister.registerIcon("goblin:ETNTTop");
		bottom= iIconRegister.registerIcon("goblin:ETNTBot");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        return p_149691_1_ == 0 ? bottom : (p_149691_1_ == 1 ? top : side);
    }

	@Override
	public void onBlockDestroyedByExplosion(World world, int xCoord, int yCoord, int zCoord, Explosion explosion)
    {
        if (!world.isRemote)
        {
            EntityEnchantedTNTPrimed entityTntPrimed = new EntityEnchantedTNTPrimed(world, xCoord + 0.5f, yCoord + 0.5f, zCoord + 0.5f, explosion.getExplosivePlacedBy());
            entityTntPrimed.fuse = world.rand.nextInt(entityTntPrimed.fuse / 4) + entityTntPrimed.fuse / 8;
            world.spawnEntityInWorld(entityTntPrimed);
        }
    }
	
	@Override
	public void func_150114_a(World world, int xCoord, int yCoord, int zCoord, int par5, EntityLivingBase entityLivingBase)
    {
        if (!world.isRemote)
        {
            if ((par5 & 1) == 1)
            {
                EntityEnchantedTNTPrimed entityTntPrimed = new EntityEnchantedTNTPrimed(world, xCoord + 0.5f, yCoord + 0.5f, zCoord + 0.5f, entityLivingBase);
                world.spawnEntityInWorld(entityTntPrimed);
                world.playSoundAtEntity(entityTntPrimed, "game.tnt.primed", 1.0F, 1.0F);
            }
        }
    }
}
