
package goblin.entity;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.passive.EntityAnimal;

public class EntityGoblinAttackFilter implements IEntitySelector {
	public boolean isEntityApplicable(Entity entity)
	{
		return (
					entity instanceof EntityLiving
						&& (
								((EntityLiving) entity).getCreatureAttribute() == EnumCreatureAttribute.ARTHROPOD
								|| ((EntityLiving) entity).getCreatureAttribute() == EnumCreatureAttribute.UNDEAD
							)
				)
				|| entity instanceof EntityAnimal;
	}
}
