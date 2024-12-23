package goblin.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;

public class GoblinsEntityTools
{	
	public static boolean isDamageSourceEntityFromGoblinsMod(DamageSource damageSource)
	{
		return
			(
					isEntityFromGoblinsMod(damageSource.getEntity())
			);
	}
	
	public static boolean isEntityFromGoblinsMod(Entity entity)
	{
		return
			(
					entity != null
					&& entity instanceof EntityMob
					&& EntityList.getEntityString(entity).contains("goblin.")
			);
	}
	
	public static boolean goblinsCustomAttackEntityAsMob(EntityLivingBase attacker, Entity entityToAttack)
    {
        float f = (float)attacker.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
        int i = 0;

        if (entityToAttack instanceof EntityLivingBase)
        {
            f += EnchantmentHelper.getEnchantmentModifierLiving(attacker, (EntityLivingBase)entityToAttack);
            i += EnchantmentHelper.getKnockbackModifier(attacker, (EntityLivingBase)entityToAttack);
        }

        boolean flag = entityToAttack.attackEntityFrom(DamageSource.causeMobDamage(attacker), f);

        if (flag)
        {
        	attacker.swingItem(); //swing arm
        	
            if (i > 0)
            {
                entityToAttack.addVelocity(-MathHelper.sin(attacker.rotationYaw * (float)Math.PI / 180.0F) * i * 0.5F, 0.1D, MathHelper.cos(attacker.rotationYaw * (float)Math.PI / 180.0F) * i * 0.5F);
                attacker.motionX *= 0.6D;
                attacker.motionZ *= 0.6D;
            }

            int j = EnchantmentHelper.getFireAspectModifier(attacker);

            if (j > 0)
            {
                entityToAttack.setFire(j * 4);
            }

            if (entityToAttack instanceof EntityLivingBase)
            {
                EnchantmentHelper.func_151384_a((EntityLivingBase)entityToAttack, attacker);
            }

            EnchantmentHelper.func_151385_b(attacker, entityToAttack);
        }

        return flag;
    }
}
