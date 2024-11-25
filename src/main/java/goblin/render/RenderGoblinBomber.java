
package goblin.render;

import org.lwjgl.opengl.GL11;

import goblin.entity.EntityGoblinBomber;
import goblin.model.ModelGoblinBomber;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderGoblinBomber extends RenderLiving {
	private static ResourceLocation texture;
	protected ModelGoblinBomber modelBipedMain;

	public RenderGoblinBomber(ModelBase modelBase, float par2)
	{
		super(modelBase, par2);
	}

	protected ResourceLocation func_110832_a(EntityGoblinBomber entityGoblinBomber)
	{
		return RenderGoblinBomber.texture;
	}

	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return func_110832_a((EntityGoblinBomber) entity);
	}

	protected void renderEquippedItems(EntityLiving entityLiving, float f)
	{
		super.renderEquippedItems((EntityLivingBase) entityLiving, f);
		ItemStack itemStack = entityLiving.getHeldItem();
		if (itemStack != null)
		{
			GL11.glPushMatrix();
			modelBipedMain.bipedRightArm.postRender(0.0625f);
			GL11.glTranslatef(-0.0625f, 0.4375f, 0.0625f);
			if (Item.getIdFromItem(itemStack.getItem()) < 256 && RenderBlocks.renderItemIn3d(Block.getBlockById(Item.getIdFromItem(itemStack.getItem())).getRenderType()))
			{
				float f2 = 0.5f;
				GL11.glTranslatef(0.0f, 0.1875f, -0.3125f);
				f2 *= 0.75f;
				GL11.glRotatef(20.0f, 1.0f, 0.0f, 0.0f);
				GL11.glRotatef(45.0f, 0.0f, 1.0f, 0.0f);
				GL11.glScalef(f2, -f2, f2);
			}
			else if (itemStack.getItem() == Items.bow)
			{
				float f3 = 0.625f;
				GL11.glTranslatef(0.0f, 0.125f, 0.3125f);
				GL11.glRotatef(-20.0f, 0.0f, 1.0f, 0.0f);
				GL11.glScalef(f3, -f3, f3);
				GL11.glRotatef(-100.0f, 1.0f, 0.0f, 0.0f);
				GL11.glRotatef(45.0f, 0.0f, 1.0f, 0.0f);
			}
			else if (itemStack.getItem().isFull3D())
			{
				float f4 = 0.625f;
				GL11.glTranslatef(0.0f, 0.1875f, 0.0f);
				GL11.glScalef(f4, -f4, f4);
				GL11.glRotatef(-100.0f, 1.0f, 0.0f, 0.0f);
				GL11.glRotatef(45.0f, 0.0f, 1.0f, 0.0f);
			}
			else
			{
				float f5 = 0.375f;
				GL11.glTranslatef(0.25f, 0.1875f, -0.1875f);
				GL11.glScalef(f5, f5, f5);
				GL11.glRotatef(60.0f, 0.0f, 0.0f, 1.0f);
				GL11.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
				GL11.glRotatef(20.0f, 0.0f, 0.0f, 1.0f);
			}
			renderManager.itemRenderer.renderItem((EntityLivingBase) entityLiving, itemStack, 0);
			if (itemStack.getItem() == Items.potionitem)
			{
				renderManager.itemRenderer.renderItem((EntityLivingBase) entityLiving, itemStack, 1);
			}
			GL11.glPopMatrix();
		}
	}

	static
	{
		texture = new ResourceLocation("goblin:textures/entity/GoblinBomber.png");
	}
}
