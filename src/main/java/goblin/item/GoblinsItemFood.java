
package goblin.item;

import goblin.Goblins;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemFood;

public class GoblinsItemFood extends ItemFood {
	private int healAmount;
	private float saturationModifier;
	private boolean isWolfsFavoriteMeat;
	private boolean alwaysEdible;
	private int potionId;
	private int potionDuration;
	private int potionAmplifier;
	private float potionEffectProbability;
	private int healNum;

	public GoblinsItemFood(String stringName, int healAmount, float saturationModifier, boolean isWolfsFavouriteFood)
	{
		super(healAmount, saturationModifier, isWolfsFavouriteFood);
		setUnlocalizedName(stringName);
		
		alwaysEdible = true;
		this.healAmount = healAmount;
		this.isWolfsFavoriteMeat = isWolfsFavouriteFood;
		this.saturationModifier = saturationModifier;
		setAlwaysEdible();
		
		setCreativeTab(Goblins.GOBLINS_CREATIVE_TAB);
	}

	public void registerIcons(IIconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon("goblin" + getUnlocalizedName().replaceFirst("item.", ":"));
	}
}
