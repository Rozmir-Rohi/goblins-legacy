
package goblin.render;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import goblin.entity.IGoblinEntityTextureBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderOrb extends Render {

	public void render(Entity Entity, double d, double d1, double d2, float f, float f1)
	{
		bindEntityTexture((Entity) Entity);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) d, (float) d1, (float) d2);
		GL11.glEnable(32826);
		float f2 = 1.0f;
		GL11.glScalef(f2 / 1.0f, f2 / 1.0f, f2 / 1.0f);
		Tessellator tessellator = Tessellator.instance;
		float f3 = 0.0f;
		float f4 = 1.0f;
		float f5 = 0.0f;
		float f6 = 1.0f;
		float f7 = 1.0f;
		float f8 = 0.5f;
		float f9 = 0.25f;
		GL11.glRotatef(180.0f - renderManager.playerViewY, 0.0f, 1.0f, 0.0f);
		GL11.glRotatef(-renderManager.playerViewX, 1.0f, 0.0f, 0.0f);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0f, 1.0f, 0.0f);
		tessellator.addVertexWithUV((double) (0.0f - f8), (double) (0.0f - f9), 0.0, (double) f3, (double) f6);
		tessellator.addVertexWithUV((double) (f7 - f8), (double) (0.0f - f9), 0.0, (double) f4, (double) f6);
		tessellator.addVertexWithUV((double) (f7 - f8), (double) (1.0f - f9), 0.0, (double) f4, (double) f5);
		tessellator.addVertexWithUV((double) (0.0f - f8), (double) (1.0f - f9), 0.0, (double) f3, (double) f5);
		tessellator.draw();
		GL11.glDisable(32826);
		GL11.glPopMatrix();
	}

	public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
	{
		render((Entity) entity, d, d1, d2, f, f1);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return ((IGoblinEntityTextureBase) entity).getEntityTexture();
	}
}
