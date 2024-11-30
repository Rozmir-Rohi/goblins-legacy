
package goblin.entity;

import goblin.Goblins;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;

public class GoblinsLesserGoblinsAttackFilter implements IEntitySelector {
	public boolean isEntityApplicable(Entity entity)
	{
		return
			(	//attack the entities below
				entity instanceof EntityLiving
				&& (
						((EntityLiving) entity).getCreatureAttribute() == EnumCreatureAttribute.ARTHROPOD
						|| ((EntityLiving) entity).getCreatureAttribute() == EnumCreatureAttribute.UNDEAD
						|| entity instanceof EntityVillager
						|| entity instanceof EntityAnimal	
						|| (
								Goblins.isWitcheryLoaded
								&& EntityList.getEntityString(entity).equals("witchery.villageguard")
							)
					)
			);
	}
}
