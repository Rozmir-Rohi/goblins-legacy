
package goblin.model;

import goblin.entity.EntityDirewolf;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class ModelDirewolf extends ModelBase {
	public ModelRenderer wolfHeadMain;
	public ModelRenderer wolfBody;
	public ModelRenderer wolfLeg1;
	public ModelRenderer wolfLeg2;
	public ModelRenderer wolfLeg3;
	public ModelRenderer wolfLeg4;
	ModelRenderer wolfRightEar;
	ModelRenderer wolfLeftEar;
	ModelRenderer wolfSnout;
	ModelRenderer wolfTail;
	ModelRenderer wolfMane;

	public ModelDirewolf()
	{
		float f = 0.0f;
		float f2 = 13.5f;
		(wolfHeadMain = new ModelRenderer((ModelBase) this, 0, 0)).addBox(-3.0f, -3.0f, -2.0f, 6, 6, 4, f);
		wolfHeadMain.setRotationPoint(-1.0f, f2, -7.0f);
		(wolfBody = new ModelRenderer((ModelBase) this, 18, 14)).addBox(-4.0f, -2.0f, -3.0f, 6, 9, 6, f);
		wolfBody.setRotationPoint(0.0f, 14.0f, 2.0f);
		(wolfMane = new ModelRenderer((ModelBase) this, 21, 0)).addBox(-4.0f, -3.0f, -3.0f, 8, 6, 7, f);
		wolfMane.setRotationPoint(-1.0f, 14.0f, 2.0f);
		(wolfLeg1 = new ModelRenderer((ModelBase) this, 0, 18)).addBox(-1.0f, 0.0f, -1.0f, 2, 8, 2, f);
		wolfLeg1.setRotationPoint(-2.5f, 16.0f, 7.0f);
		(wolfLeg2 = new ModelRenderer((ModelBase) this, 0, 18)).addBox(-1.0f, 0.0f, -1.0f, 2, 8, 2, f);
		wolfLeg2.setRotationPoint(0.5f, 16.0f, 7.0f);
		(wolfLeg3 = new ModelRenderer((ModelBase) this, 0, 18)).addBox(-1.0f, 0.0f, -1.0f, 2, 8, 2, f);
		wolfLeg3.setRotationPoint(-2.5f, 16.0f, -4.0f);
		(wolfLeg4 = new ModelRenderer((ModelBase) this, 0, 18)).addBox(-1.0f, 0.0f, -1.0f, 2, 8, 2, f);
		wolfLeg4.setRotationPoint(0.5f, 16.0f, -4.0f);
		(wolfTail = new ModelRenderer((ModelBase) this, 9, 18)).addBox(-1.0f, 0.0f, -1.0f, 2, 8, 2, f);
		wolfTail.setRotationPoint(-1.0f, 12.0f, 8.0f);
		(wolfRightEar = new ModelRenderer((ModelBase) this, 16, 14)).addBox(-3.0f, -5.0f, 0.0f, 2, 2, 1, f);
		wolfRightEar.setRotationPoint(-1.0f, f2, -7.0f);
		(wolfLeftEar = new ModelRenderer((ModelBase) this, 16, 14)).addBox(1.0f, -5.0f, 0.0f, 2, 2, 1, f);
		wolfLeftEar.setRotationPoint(-1.0f, f2, -7.0f);
		(wolfSnout = new ModelRenderer((ModelBase) this, 0, 10)).addBox(-2.0f, 0.0f, -5.0f, 3, 3, 4, f);
		wolfSnout.setRotationPoint(-0.5f, f2, -7.0f);
	}

	public void render(Entity entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		super.render(entity, par2, par3, par4, par5, par6, par7);
		setRotationAngles(par2, par3, par4, par5, par6, par7, entity);
		wolfHeadMain.renderWithRotation(par7);
		wolfSnout.renderWithRotation(par7);
		wolfRightEar.renderWithRotation(par7);
		wolfLeftEar.renderWithRotation(par7);
		wolfBody.render(par7);
		wolfLeg1.render(par7);
		wolfLeg2.render(par7);
		wolfLeg3.render(par7);
		wolfLeg4.render(par7);
		wolfTail.renderWithRotation(par7);
		wolfMane.render(par7);
	}

	public void setLivingAnimations(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4)
	{
		EntityDirewolf entitywolf = (EntityDirewolf) par1EntityLivingBase;
		wolfTail.rotateAngleY = MathHelper.cos(par2 * 0.6662f) * 1.4f * par3;
		wolfBody.setRotationPoint(0.0f, 14.0f, 2.0f);
		wolfBody.rotateAngleX = 1.5707964f;
		wolfMane.setRotationPoint(-1.0f, 14.0f, -3.0f);
		wolfMane.rotateAngleX = wolfBody.rotateAngleX;
		wolfTail.setRotationPoint(-1.0f, 12.0f, 8.0f);
		wolfLeg1.setRotationPoint(-2.5f, 16.0f, 7.0f);
		wolfLeg2.setRotationPoint(0.5f, 16.0f, 7.0f);
		wolfLeg3.setRotationPoint(-2.5f, 16.0f, -4.0f);
		wolfLeg4.setRotationPoint(0.5f, 16.0f, -4.0f);
		wolfLeg1.rotateAngleX = MathHelper.cos(par2 * 0.6662f) * 1.4f * par3;
		wolfLeg2.rotateAngleX = MathHelper.cos(par2 * 0.6662f + (float) Math.PI) * 1.4f * par3;
		wolfLeg3.rotateAngleX = MathHelper.cos(par2 * 0.6662f + (float) Math.PI) * 1.4f * par3;
		wolfLeg4.rotateAngleX = MathHelper.cos(par2 * 0.6662f) * 1.4f * par3;
	}

	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
	{
		super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
		wolfHeadMain.rotateAngleX = par5 / 57.295776f;
		wolfHeadMain.rotateAngleY = par4 / 57.295776f;
		wolfLeftEar.rotateAngleX = par5 / 57.295776f;
		wolfLeftEar.rotateAngleY = par4 / 57.295776f;
		wolfRightEar.rotateAngleX = par5 / 57.295776f;
		wolfRightEar.rotateAngleY = par4 / 57.295776f;
		wolfSnout.rotateAngleX = par5 / 57.295776f;
		wolfSnout.rotateAngleY = par4 / 57.295776f;
		wolfTail.rotateAngleX = 45.0f;
	}
}
