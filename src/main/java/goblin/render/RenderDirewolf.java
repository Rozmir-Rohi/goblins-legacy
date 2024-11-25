
package goblin.render;

import net.minecraft.client.renderer.entity.*;
import net.minecraft.util.*;
import goblin.entity.EntityDirewolf;
import net.minecraft.client.model.*;
import net.minecraft.entity.*;

public class RenderDirewolf extends RenderLiving {
	private static ResourceLocation texture;

	public RenderDirewolf(ModelBase modelBase, float par2)
	{
		super(modelBase, par2);
	}

	protected ResourceLocation func_110832_a(EntityDirewolf entityDirewolf)
	{
		return RenderDirewolf.texture;
	}

	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return func_110832_a((EntityDirewolf) entity);
	}

	static
	{
		texture = new ResourceLocation("goblin:textures/entity/Direwolf.png");
	}
}
