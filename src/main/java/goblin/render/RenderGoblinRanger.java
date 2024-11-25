
package goblin.render;

import org.lwjgl.opengl.GL11;

import goblin.entity.IGoblinEntityTextureBase;
import goblin.model.ModelGoblinRanger;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

public class RenderGoblinRanger extends RenderLiving {
	private static ResourceLocation texture;
	protected ModelGoblinRanger modelBipedMain;

	public RenderGoblinRanger(ModelGoblinRanger modelGoblinRanger, float size)
	{
		super((ModelBase) modelGoblinRanger, size);
		modelBipedMain = modelGoblinRanger;
		RenderGoblinRanger.NAME_TAG_RANGE = 0.0f;
		RenderGoblinRanger.NAME_TAG_RANGE_SNEAK = 0.0f;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return ((IGoblinEntityTextureBase) entity).getEntityTexture();
	}
	
	protected void renderCarrying(EntityLiving entityLiving, float par2)
	{
		float f1 = 1.0f;
		GL11.glColor3f(f1, f1, f1);
		super.renderEquippedItems((EntityLivingBase) entityLiving, par2);
		ItemStack itemStack = entityLiving.getHeldItem();
		ItemStack itemStack2 = entityLiving.func_130225_q(3);
		if (itemStack != null)
		{
			GL11.glPushMatrix();
			modelBipedMain.bipedRightArm.postRender(0.0625f);
			GL11.glTranslatef(-0.0625f, 0.4375f, 0.0625f);
			IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemStack, IItemRenderer.ItemRenderType.EQUIPPED);
			boolean is3D = customRenderer != null && customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemStack, IItemRenderer.ItemRendererHelper.BLOCK_3D);
			if (itemStack.getItem() == Items.bow)
			{
				float f2 = 0.625f;
				GL11.glTranslatef(0.0f, 0.125f, 0.3125f);
				GL11.glRotatef(-20.0f, 0.0f, 1.0f, 0.0f);
				GL11.glScalef(f2, -f2, f2);
				GL11.glRotatef(-100.0f, 1.0f, 0.0f, 0.0f);
				GL11.glRotatef(45.0f, 0.0f, 1.0f, 0.0f);
			}
			else if (itemStack.getItem().isFull3D())
			{
				float f2 = 0.625f;
				if (itemStack.getItem().shouldRotateAroundWhenRendering())
				{
					GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
					GL11.glTranslatef(0.0f, -0.125f, 0.0f);
				}
				func_82422_c();
				GL11.glScalef(f2, -f2, f2);
				GL11.glRotatef(-100.0f, 1.0f, 0.0f, 0.0f);
				GL11.glRotatef(45.0f, 0.0f, 1.0f, 0.0f);
			}
			else
			{
				float f2 = 0.375f;
				GL11.glTranslatef(0.25f, 0.1875f, -0.1875f);
				GL11.glScalef(f2, f2, f2);
				GL11.glRotatef(60.0f, 0.0f, 0.0f, 1.0f);
				GL11.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
				GL11.glRotatef(20.0f, 0.0f, 0.0f, 1.0f);
			}
			renderManager.itemRenderer.renderItem((EntityLivingBase) entityLiving, itemStack, 0);
			if (itemStack.getItem().requiresMultipleRenderPasses())
			{
				for (int x = 1; x < itemStack.getItem().getRenderPasses(itemStack.getItemDamage()); ++x)
				{
					renderManager.itemRenderer.renderItem((EntityLivingBase) entityLiving, itemStack, x);
				}
			}
			GL11.glPopMatrix();
		}
	}

	protected void func_82422_c()
	{
		GL11.glTranslatef(0.0f, 0.1875f, 0.0f);
	}

	protected void renderEquippedItems(EntityLivingBase entityLiving, float f)
	{
		renderCarrying((EntityLiving) entityLiving, f);
	}
}
