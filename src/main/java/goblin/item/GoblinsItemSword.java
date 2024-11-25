
package goblin.item;

import goblin.Goblins;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class GoblinsItemSword extends ItemSword {
	private float field_150934_a;
	private Item.ToolMaterial repairMaterial;
	final private static String __OBFID = "CL_00000072";
	private int weaponDamage;

	public GoblinsItemSword(String stringName, Item.ToolMaterial enumToolMaterial)
	{
		super(enumToolMaterial);
		
		setUnlocalizedName(stringName);
		
		repairMaterial = enumToolMaterial;
		
		setCreativeTab(Goblins.GOBLINS_CREATIVE_TAB);
	}

	public void registerIcons(IIconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon("goblin" + getUnlocalizedName().replaceFirst("item.", ":"));
	}

	public boolean hitEntity(ItemStack itemStack, EntityLivingBase entityLiving, EntityLivingBase entityLiving1)
	{
		if (repairMaterial == Goblins.EXPLOSIVE_MATERIAL) //flame blade only
		{
			entityLiving.setFire(3);
		}
		
		itemStack.damageItem(1, entityLiving1);
		
		return true;
	}
}
