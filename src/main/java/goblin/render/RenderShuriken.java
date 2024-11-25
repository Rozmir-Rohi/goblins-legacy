
package goblin.render;

import org.lwjgl.opengl.GL11;

import goblin.entity.projectile.EntityShuriken;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class RenderShuriken extends Render {
	public static ResourceLocation texture;

	public void render(EntityShuriken entityShuriken, double par2, double par4, double par6, float par8, float par9)
	{
		bindEntityTexture((Entity) entityShuriken);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) par2, (float) par4, (float) par6);
		GL11.glRotatef(entityShuriken.prevRotationYaw + (entityShuriken.rotationYaw - entityShuriken.prevRotationYaw) * par9 - 90.0f, 0.0f, 1.0f, 0.0f);
		GL11.glRotatef(entityShuriken.prevRotationPitch + (entityShuriken.rotationPitch - entityShuriken.prevRotationPitch) * par9, 0.0f, 0.0f, 1.0f);
		Tessellator tessellator = Tessellator.instance;
		byte b0 = 0;
		float f2 = 0.0f;
		float f3 = 0.5f;
		float f4 = (0 + b0 * 10) / 32.0f;
		float f5 = (5 + b0 * 10) / 32.0f;
		float f6 = 0.0f;
		float f7 = 0.15625f;
		float f8 = (5 + b0 * 10) / 32.0f;
		float f9 = (10 + b0 * 10) / 32.0f;
		float f10 = 0.05625f;
		GL11.glEnable(32826);
		float f11 = entityShuriken.arrowShake - par9;
		if (f11 > 0.0f)
		{
			float f12 = -MathHelper.sin(f11 * 3.0f) * f11;
			GL11.glRotatef(f12, 0.0f, 0.0f, 1.0f);
		}
		GL11.glRotatef(45.0f, 1.0f, 0.0f, 0.0f);
		GL11.glScalef(f10, f10, f10);
		GL11.glTranslatef(-4.0f, 0.0f, 0.0f);
		GL11.glNormal3f(f10, 0.0f, 0.0f);
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(-7.0, -2.0, -2.0, (double) f6, (double) f8);
		tessellator.addVertexWithUV(-7.0, -2.0, 2.0, (double) f7, (double) f8);
		tessellator.addVertexWithUV(-7.0, 2.0, 2.0, (double) f7, (double) f9);
		tessellator.addVertexWithUV(-7.0, 2.0, -2.0, (double) f6, (double) f9);
		tessellator.draw();
		GL11.glNormal3f(-f10, 0.0f, 0.0f);
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(-7.0, 2.0, -2.0, (double) f6, (double) f8);
		tessellator.addVertexWithUV(-7.0, 2.0, 2.0, (double) f7, (double) f8);
		tessellator.addVertexWithUV(-7.0, -2.0, 2.0, (double) f7, (double) f9);
		tessellator.addVertexWithUV(-7.0, -2.0, -2.0, (double) f6, (double) f9);
		tessellator.draw();
		for (int i = 0; i < 4; ++i)
		{
			GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
			GL11.glNormal3f(0.0f, 0.0f, f10);
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(-8.0, -2.0, 0.0, (double) f2, (double) f4);
			tessellator.addVertexWithUV(8.0, -2.0, 0.0, (double) f3, (double) f4);
			tessellator.addVertexWithUV(8.0, 2.0, 0.0, (double) f3, (double) f5);
			tessellator.addVertexWithUV(-8.0, 2.0, 0.0, (double) f2, (double) f5);
			tessellator.draw();
		}
		GL11.glDisable(32826);
		GL11.glPopMatrix();
	}

	public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
	{
		render((EntityShuriken) entity, d, d1, d2, f, f1);
	}

	protected ResourceLocation getEntityTexture(EntityShuriken entityShuriken)
	{
		return RenderShuriken.texture;
	}

	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return getEntityTexture((EntityShuriken) entity);
	}

	protected ResourceLocation func_110779_a(EntityShuriken entityShuriken)
	{
		return RenderShuriken.texture;
	}

	static
	{
		texture = new ResourceLocation("goblin:textures/entity/shuriken.png");
	}
}
