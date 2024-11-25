
package goblin.model;

import net.minecraft.client.model.*;
import net.minecraft.entity.*;

public class ModelPoles extends ModelBase {
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape7;
	ModelRenderer Shape8;

	public ModelPoles()
	{
		textureWidth = 64;
		textureHeight = 32;
		(Shape1 = new ModelRenderer((ModelBase) this, 0, 1)).addBox(0.0f, 0.0f, 0.0f, 1, 8, 1);
		Shape1.setRotationPoint(4.0f, 17.0f, 0.0f);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0.2385283f, 0.0f, 0.0f);
		(Shape2 = new ModelRenderer((ModelBase) this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 1, 10, 1);
		Shape2.setRotationPoint(0.0f, 15.0f, 2.0f);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, -0.0743572f, 0.0f, 0.0f);
		(Shape3 = new ModelRenderer((ModelBase) this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 1, 10, 1);
		Shape3.setRotationPoint(-5.0f, 16.0f, 2.0f);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0.1001757f, 0.0f, 0.0f);
		(Shape4 = new ModelRenderer((ModelBase) this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 1, 10, 1);
		Shape4.setRotationPoint(-4.0f, 14.0f, -1.0f);
		Shape4.setTextureSize(64, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0.0f, 0.0f, 0.0f);
		(Shape5 = new ModelRenderer((ModelBase) this, 0, 2)).addBox(0.0f, 0.0f, 0.0f, 1, 8, 1);
		Shape5.setRotationPoint(0.0f, 17.0f, -3.0f);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;
		setRotation(Shape5, -0.0743572f, 0.0f, 0.0f);
		(Shape6 = new ModelRenderer((ModelBase) this, 0, 2)).addBox(0.0f, 0.0f, 0.0f, 1, 10, 1);
		Shape6.setRotationPoint(0.0f, 18.0f, 4.0f);
		Shape6.setTextureSize(64, 32);
		Shape6.mirror = true;
		setRotation(Shape6, 0.0872665f, 0.0f, 0.0f);
		(Shape7 = new ModelRenderer((ModelBase) this, 0, 2)).addBox(0.0f, 0.0f, 0.0f, 1, 6, 1);
		Shape7.setRotationPoint(-5.0f, 18.0f, -5.0f);
		Shape7.setTextureSize(64, 32);
		Shape7.mirror = true;
		setRotation(Shape7, 0.0f, 0.0f, 0.0f);
		(Shape8 = new ModelRenderer((ModelBase) this, 0, 2)).addBox(0.0f, 0.0f, 0.0f, 1, 10, 1);
		Shape8.setRotationPoint(4.0f, 17.0f, -5.0f);
		Shape8.setTextureSize(64, 32);
		Shape8.mirror = true;
		setRotation(Shape8, -0.0743572f, 0.0f, 0.0f);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
		Shape5.render(f5);
		Shape6.render(f5);
		Shape7.render(f5);
		Shape8.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}
