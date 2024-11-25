
package goblin.entity;

import net.minecraft.command.*;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.*;

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
