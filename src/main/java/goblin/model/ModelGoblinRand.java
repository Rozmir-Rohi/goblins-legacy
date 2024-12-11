
package goblin.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelGoblinRand extends ModelBase {
	public ModelRenderer quiver;
	public ModelRenderer bipedHead;
	public ModelRenderer bipedBody;
	public ModelRenderer bipedRightArm;
	public ModelRenderer bipedLeftArm;
	public ModelRenderer bipedRightLeg;
	public ModelRenderer bipedLeftLeg;
	public ModelRenderer ear1;
	public ModelRenderer ear2;
	public ModelRenderer nose1;
	public ModelRenderer nose2;
	public ModelRenderer bomb1;
	public ModelRenderer bomb2;
	public ModelRenderer bag;

	public ModelGoblinRand()
	{
		(bipedHead = new ModelRenderer(this, 0, 0)).addBox(-2.0f, -5.0f, -2.0f, 5, 5, 4, 0.0f);
		bipedHead.setRotationPoint(0.0f, 9.0f, -1.0f);
		bipedHead.rotateAngleX = 0.0f;
		bipedHead.rotateAngleY = 0.0f;
		bipedHead.rotateAngleZ = 0.0f;
		bipedHead.mirror = false;
		(bipedBody = new ModelRenderer(this, 0, 9)).addBox(-3.0f, 0.0f, -1.0f, 7, 9, 2, 0.0f);
		bipedBody.setRotationPoint(0.0f, 9.0f, -1.0f);
		bipedBody.rotateAngleX = 0.0f;
		bipedBody.rotateAngleY = 0.0f;
		bipedBody.rotateAngleZ = 0.0f;
		bipedBody.mirror = false;
		(bipedRightArm = new ModelRenderer(this, 0, 20)).addBox(-2.0f, 0.0f, -1.0f, 2, 7, 2, 0.0f);
		bipedRightArm.setRotationPoint(-3.0f, 9.0f, -1.0f);
		bipedRightArm.rotateAngleX = 0.0f;
		bipedRightArm.rotateAngleY = 0.0f;
		bipedRightArm.rotateAngleZ = 0.0f;
		bipedRightArm.mirror = false;
		(bipedLeftArm = new ModelRenderer(this, 0, 20)).addBox(0.0f, 0.0f, -1.0f, 2, 7, 2, 0.0f);
		bipedLeftArm.setRotationPoint(4.0f, 9.0f, -1.0f);
		bipedLeftArm.rotateAngleX = 0.0f;
		bipedLeftArm.rotateAngleY = 0.0f;
		bipedLeftArm.rotateAngleZ = 0.0f;
		bipedLeftArm.mirror = false;
		(bipedRightLeg = new ModelRenderer(this, 16, 20)).addBox(-1.0f, 0.0f, -1.0f, 3, 6, 2, 0.0f);
		bipedRightLeg.setRotationPoint(-2.0f, 18.0f, -1.0f);
		bipedRightLeg.rotateAngleX = 0.0f;
		bipedRightLeg.rotateAngleY = 0.0f;
		bipedRightLeg.rotateAngleZ = 0.0f;
		bipedRightLeg.mirror = false;
		(bipedLeftLeg = new ModelRenderer(this, 16, 20)).addBox(-2.0f, 0.0f, -1.0f, 3, 6, 2, 0.0f);
		bipedLeftLeg.setRotationPoint(3.0f, 18.0f, -1.0f);
		bipedLeftLeg.rotateAngleX = 0.0f;
		bipedLeftLeg.rotateAngleY = 0.0f;
		bipedLeftLeg.rotateAngleZ = 0.0f;
		bipedLeftLeg.mirror = true;
		(ear1 = new ModelRenderer(this, 18, 8)).addBox(3.0f, -7.0f, 0.0f, 1, 4, 1, 0.0f);
		ear1.setRotationPoint(0.0f, 9.0f, -1.0f);
		ear1.rotateAngleX = 0.0f;
		ear1.rotateAngleY = 0.0f;
		ear1.rotateAngleZ = 0.0f;
		ear1.mirror = false;
		(ear2 = new ModelRenderer(this, 18, 8)).addBox(-3.0f, -7.0f, 0.0f, 1, 4, 1, 0.0f);
		ear2.setRotationPoint(0.0f, 9.0f, -1.0f);
		ear2.rotateAngleX = 0.0f;
		ear2.rotateAngleY = 0.0f;
		ear2.rotateAngleZ = 0.0f;
		ear2.mirror = false;
		(nose1 = new ModelRenderer(this, 8, 24)).addBox(0.0f, -3.0f, -3.0f, 1, 1, 1, 0.0f);
		nose1.setRotationPoint(0.0f, 9.0f, -1.0f);
		nose1.rotateAngleX = 0.0f;
		nose1.rotateAngleY = 0.0f;
		nose1.rotateAngleZ = 0.0f;
		nose1.mirror = false;
		(nose2 = new ModelRenderer(this, 8, 20)).addBox(0.0f, -2.0f, -5.0f, 1, 1, 3, 0.0f);
		nose2.setRotationPoint(0.0f, 9.0f, -1.0f);
		nose2.rotateAngleX = 0.0f;
		nose2.rotateAngleY = 0.0f;
		nose2.rotateAngleZ = 0.0f;
		nose2.mirror = false;
		(quiver = new ModelRenderer(this, 26, 23)).addBox(-1.0f, 0.0f, 1.0f, 3, 7, 2, 0.0f);
		quiver.setRotationPoint(0.0f, 10.0f, -1.0f);
		quiver.rotateAngleX = 0.0f;
		quiver.rotateAngleY = 0.0f;
		quiver.rotateAngleZ = 0.0f;
		quiver.mirror = false;
		(bag = new ModelRenderer(this, 23, 0)).addBox(-1.0f, 0.0f, 1.0f, 7, 8, 6);
		bag.setRotationPoint(-2.0f, 9.0f, -2.0f);
		bag.rotateAngleX = 0.10472f;
		bag.rotateAngleY = 0.0f;
		bag.rotateAngleZ = 0.0f;
		bag.mirror = false;
		(bomb2 = new ModelRenderer(this, 18, 17)).addBox(-2.0f, -1.0f, 4.0f, 2, 1, 2);
		bomb2.setRotationPoint(0.0f, 9.0f, -2.0f);
		bomb2.rotateAngleX = 0.10472f;
		bomb2.rotateAngleY = 0.0f;
		bomb2.rotateAngleZ = 0.0f;
		bomb2.mirror = false;
		(bomb1 = new ModelRenderer(this, 18, 17)).addBox(1.0f, -1.0f, 3.0f, 2, 1, 2);
		bomb1.setRotationPoint(0.0f, 9.0f, -2.0f);
		bomb1.rotateAngleX = 0.10472f;
		bomb1.rotateAngleY = 0.0f;
		bomb1.rotateAngleZ = 0.0f;
		bomb1.mirror = false;
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		setRotationAngles(f, f1, f2, f3, f4, f5);
		bipedHead.render(f5);
		bipedBody.render(f5);
		bipedRightArm.render(f5);
		bipedLeftArm.render(f5);
		bipedRightLeg.render(f5);
		bipedLeftLeg.render(f5);
		ear1.render(f5);
		ear2.render(f5);
		nose1.render(f5);
		nose2.render(f5);
		quiver.render(f5);
		bomb1.render(f5);
		bomb2.render(f5);
		bag.render(f5);
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{
		bipedHead.rotateAngleY = f3 / 57.29578f;
		bipedHead.rotateAngleX = f4 / 57.29578f;
		ear1.rotateAngleY = bipedHead.rotateAngleY;
		ear1.rotateAngleX = bipedHead.rotateAngleX;
		ear2.rotateAngleY = bipedHead.rotateAngleY;
		ear2.rotateAngleX = bipedHead.rotateAngleX;
		nose1.rotateAngleY = bipedHead.rotateAngleY;
		nose1.rotateAngleX = bipedHead.rotateAngleX;
		nose2.rotateAngleY = bipedHead.rotateAngleY;
		nose2.rotateAngleX = bipedHead.rotateAngleX;
		bipedRightArm.rotateAngleX = MathHelper.cos(f * 0.6662f + 3.141593f) * 2.0f * f1 * 0.5f;
		bipedLeftArm.rotateAngleX = MathHelper.cos(f * 0.6662f) * 2.0f * f1 * 0.5f;
		bipedRightArm.rotateAngleZ = 0.0f;
		bipedLeftArm.rotateAngleZ = 0.0f;
		bipedRightLeg.rotateAngleX = MathHelper.cos(f * 0.6662f) * 1.4f * f1;
		bipedLeftLeg.rotateAngleX = MathHelper.cos(f * 0.6662f + 3.141593f) * 1.4f * f1;
		bipedRightLeg.rotateAngleY = 0.0f;
		bipedLeftLeg.rotateAngleY = 0.0f;
		if (isRiding)
		{
			ModelRenderer bipedRightArm = this.bipedRightArm;
			bipedRightArm.rotateAngleX -= 0.6283185f;
			ModelRenderer bipedLeftArm = this.bipedLeftArm;
			bipedLeftArm.rotateAngleX -= 0.6283185f;
			bipedRightLeg.rotateAngleX = -1.256637f;
			bipedLeftLeg.rotateAngleX = -1.256637f;
			bipedRightLeg.rotateAngleY = 0.3141593f;
			bipedLeftLeg.rotateAngleY = -0.3141593f;
		}
		bipedRightArm.rotateAngleY = 0.0f;
		bipedLeftArm.rotateAngleY = 0.0f;
		if (onGround > -9990.0f)
		{
			float f6 = onGround;
			bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(f6) * 3.141593f * 2.0f) * 0.2f;
			bomb1.rotateAngleY = bipedBody.rotateAngleY;
			bomb1.rotateAngleX = bipedBody.rotateAngleX;
			bomb2.rotateAngleY = bipedBody.rotateAngleY;
			bomb2.rotateAngleX = bipedBody.rotateAngleX;
			bag.rotateAngleY = bipedBody.rotateAngleY;
			bag.rotateAngleX = bipedBody.rotateAngleX;
			quiver.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(f6) * 3.141593f * 2.0f) * 0.2f;
			ModelRenderer bipedRightArm2 = bipedRightArm;
			bipedRightArm2.rotateAngleY += bipedBody.rotateAngleY;
			ModelRenderer bipedLeftArm2 = bipedLeftArm;
			bipedLeftArm2.rotateAngleY += bipedBody.rotateAngleY;
			ModelRenderer bipedLeftArm3 = bipedLeftArm;
			bipedLeftArm3.rotateAngleX += bipedBody.rotateAngleY;
			f6 = 1.0f - onGround;
			f6 *= f6;
			f6 *= f6;
			f6 = 1.0f - f6;
			float f7 = MathHelper.sin(f6 * 3.141593f);
			float f8 = MathHelper.sin(onGround * 3.141593f) * -(bipedHead.rotateAngleX - 0.7f) * 0.75f;
			ModelRenderer bipedRightArm3 = bipedRightArm;
			bipedRightArm3.rotateAngleX -= (float) (f7 * 1.2 + f8);
			ModelRenderer bipedRightArm4 = bipedRightArm;
			bipedRightArm4.rotateAngleY += bipedBody.rotateAngleY * 2.0f;
			bipedRightArm.rotateAngleZ = MathHelper.sin(onGround * 3.141593f) * -0.4f;
		}
		ModelRenderer bipedRightArm5 = bipedRightArm;
		bipedRightArm5.rotateAngleZ += MathHelper.cos(f2 * 0.09f) * 0.05f + 0.05f;
		ModelRenderer bipedLeftArm4 = bipedLeftArm;
		bipedLeftArm4.rotateAngleZ -= MathHelper.cos(f2 * 0.09f) * 0.05f + 0.05f;
		ModelRenderer bipedRightArm6 = bipedRightArm;
		bipedRightArm6.rotateAngleX += MathHelper.sin(f2 * 0.067f) * 0.05f;
		ModelRenderer bipedLeftArm5 = bipedLeftArm;
		bipedLeftArm5.rotateAngleX -= MathHelper.sin(f2 * 0.067f) * 0.05f;
	}
}
