
package goblin.proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import goblin.entity.EntityDirewolf;
import goblin.entity.EntityGoblin;
import goblin.entity.EntityGoblinBomber;
import goblin.entity.EntityGoblinLord;
import goblin.entity.EntityGoblinMage;
import goblin.entity.EntityGoblinMiner;
import goblin.entity.EntityGoblinNinja;
import goblin.entity.EntityGoblinRanger;
import goblin.entity.EntityGoblinRangerGuard;
import goblin.entity.EntityGoblinRider;
import goblin.entity.EntityGoblinSoldier;
import goblin.entity.item.EntityEnchantedTNTPrimed;
import goblin.entity.item.EntityOverchargedTNTPrimed;
import goblin.entity.projectile.EntityArcaneball;
import goblin.entity.projectile.EntityBomb;
import goblin.entity.projectile.EntityLightball;
import goblin.entity.projectile.EntityOrbExplosive;
import goblin.entity.projectile.EntityOrbForce;
import goblin.entity.projectile.EntityOrbLightning;
import goblin.entity.projectile.EntityOrbNature;
import goblin.entity.projectile.EntityShuriken;
import goblin.model.ModelDirewolf;
import goblin.model.ModelGoblin;
import goblin.model.ModelGoblinBomber;
import goblin.model.ModelGoblinMage;
import goblin.model.ModelGoblinRanger;
import goblin.render.RenderDirewolf;
import goblin.render.RenderEnchantedTNTPrimed;
import goblin.render.RenderGoblinBiped;
import goblin.render.RenderGoblinBomber;
import goblin.render.RenderGoblinMage;
import goblin.render.RenderGoblinRanger;
import goblin.render.RenderOrb;
import goblin.render.RenderOverchargedTNTPrimed;
import goblin.render.RenderShuriken;

public class ClientProxy extends CommonProxy {
	/**
	 * Register Renderers
	 */
	@Override
	public void registerEntityRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityShuriken.class, new RenderShuriken());
		RenderingRegistry.registerEntityRenderingHandler(EntityOrbLightning.class, new RenderOrb());
		RenderingRegistry.registerEntityRenderingHandler(EntityOrbNature.class, new RenderOrb());
		RenderingRegistry.registerEntityRenderingHandler(EntityOrbExplosive.class, new RenderOrb());
		RenderingRegistry.registerEntityRenderingHandler(EntityOrbForce.class, new RenderOrb());
		RenderingRegistry.registerEntityRenderingHandler(EntityEnchantedTNTPrimed.class, new RenderEnchantedTNTPrimed());
		RenderingRegistry.registerEntityRenderingHandler(EntityOverchargedTNTPrimed.class, new RenderOverchargedTNTPrimed());
		RenderingRegistry.registerEntityRenderingHandler(EntityArcaneball.class, new RenderOrb());
		RenderingRegistry.registerEntityRenderingHandler(EntityLightball.class, new RenderOrb());
		RenderingRegistry.registerEntityRenderingHandler(EntityBomb.class, new RenderOrb());
		RenderingRegistry.registerEntityRenderingHandler(EntityGoblinBomber.class, new RenderGoblinBomber(new ModelGoblinBomber(), 0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityGoblinRanger.class, new RenderGoblinRanger(new ModelGoblinRanger(), 0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityGoblinRangerGuard.class, new RenderGoblinRanger(new ModelGoblinRanger(), 0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityGoblinRider.class, new RenderGoblinBiped(new ModelGoblin(), 0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityGoblinMiner.class, new RenderGoblinBiped(new ModelGoblin(), 0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityGoblinLord.class, new RenderGoblinBiped(new ModelGoblin(), 0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityGoblin.class, new RenderGoblinBiped(new ModelGoblin(), 0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityGoblinSoldier.class, new RenderGoblinBiped(new ModelGoblin(), 0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityGoblinMage.class, new RenderGoblinMage(new ModelGoblinMage(), 0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityGoblinNinja.class, new RenderGoblinBiped(new ModelGoblin(), 0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityDirewolf.class, new RenderDirewolf(new ModelDirewolf(), 0.5f));
	}
}
