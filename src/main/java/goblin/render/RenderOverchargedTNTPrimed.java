package goblin.render;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import goblin.Goblins;
import goblin.entity.item.EntityOverchargedTNTPrimed;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderOverchargedTNTPrimed extends Render
{
    private RenderBlocks blockRenderer = new RenderBlocks();

    public RenderOverchargedTNTPrimed()
    {
        shadowSize = 0.5F;
    }


    public void doRender(EntityOverchargedTNTPrimed entityOverchargedTNTPrimed, double x, double y, double z, float rotationYaw, float rotationPitch)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x, (float)y, (float)z);
        float f2;

        if ((float)entityOverchargedTNTPrimed.fuse - rotationPitch + 1.0F < 10.0F)
        {
            f2 = 1.0F - ((float)entityOverchargedTNTPrimed.fuse - rotationPitch + 1.0F) / 10.0F;

            if (f2 < 0.0F)
            {
                f2 = 0.0F;
            }

            if (f2 > 1.0F)
            {
                f2 = 1.0F;
            }

            f2 *= f2;
            f2 *= f2;
            float f3 = 1.0F + f2 * 0.3F;
            GL11.glScalef(f3, f3, f3);
        }

        f2 = (1.0F - ((float) entityOverchargedTNTPrimed.fuse - rotationPitch + 1.0F) / 100.0F) * 0.8F;
        bindEntityTexture(entityOverchargedTNTPrimed);
        blockRenderer.renderBlockAsItem(Goblins.MTNT, 0, entityOverchargedTNTPrimed.getBrightness(rotationPitch));

        if (entityOverchargedTNTPrimed.fuse / 5 % 2 == 0)
        {
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_DST_ALPHA);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, f2);
            blockRenderer.renderBlockAsItem(Goblins.MTNT, 0, 1.0F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
        }

        GL11.glPopMatrix();
    }


    protected ResourceLocation getEntityTexture(EntityOverchargedTNTPrimed entityOverchargedTNTPrimed)
    {
        return TextureMap.locationBlocksTexture;
    }

    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return getEntityTexture((EntityOverchargedTNTPrimed) entity);
    }

    public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float rotationPitch)
    {
        doRender((EntityOverchargedTNTPrimed) entity, x, y, z, rotationYaw, rotationPitch);
    }
}