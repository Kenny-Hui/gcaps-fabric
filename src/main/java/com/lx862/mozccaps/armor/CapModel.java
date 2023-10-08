package com.lx862.mozccaps.armor;

import net.minecraft.client.model.*;

public class CapModel {

    /* Code generated from Blockbench */
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData Front_r1 = bb_main.addChild("Front_r1", ModelPartBuilder.create().uv(26, 42).cuboid(1.0F, -3.5F, 0.0F, 2.0F, 7.0F, 0.0F, new Dilation(0.0F))
                .uv(22, 42).cuboid(-1.0F, -3.5F, 0.0F, 2.0F, 7.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(4.5017F, -5.5573F, -13.5F, -1.5708F, 0.0F, 0.3491F));

        ModelPartData Front_r2 = bb_main.addChild("Front_r2", ModelPartBuilder.create().uv(18, 42).cuboid(1.0F, -3.5F, 0.0F, 2.0F, 7.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.6076F, -6.4203F, -13.5F, -1.5708F, 0.0F, 0.1745F));

        ModelPartData Front_r3 = bb_main.addChild("Front_r3", ModelPartBuilder.create().uv(14, 42).cuboid(-1.0F, -3.5F, 0.0F, 2.0F, 7.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.5934F, -6.2902F, -13.5F, -1.5708F, 0.0F, 0.0436F));

        ModelPartData Front_r4 = bb_main.addChild("Front_r4", ModelPartBuilder.create().uv(10, 42).cuboid(4.1F, -3.5F, -0.8F, 2.0F, 7.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-6.3491F, -4.7445F, -13.5F, -1.5708F, 0.0F, -0.1309F));

        ModelPartData Front_r5 = bb_main.addChild("Front_r5", ModelPartBuilder.create().uv(6, 42).cuboid(2.1F, -3.5F, -0.8F, 2.0F, 7.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-6.2182F, -4.4044F, -13.5F, -1.5708F, 0.0F, -0.2182F));

        ModelPartData Front_r6 = bb_main.addChild("Front_r6", ModelPartBuilder.create().uv(0, 42).cuboid(-1.9F, -3.5F, -0.8F, 3.0F, 7.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-5.0F, -4.45F, -13.5F, -1.5708F, 0.0F, -0.4363F));

        ModelPartData cube_r1 = bb_main.addChild("cube_r1", ModelPartBuilder.create().uv(-4, 20).cuboid(-2.0F, 0.0F, -3.5F, 4.0F, 0.0F, 7.0F, new Dilation(0.0F))
                .uv(-4, 13).cuboid(-2.0F, 0.0F, 3.5F, 4.0F, 0.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(-1.7248F, -15.1885F, -4.2F, 0.0F, 0.0F, 0.0698F));

        ModelPartData cube_r2 = bb_main.addChild("cube_r2", ModelPartBuilder.create().uv(0, 20).cuboid(-2.0F, 0.0F, -3.5F, 4.0F, 0.0F, 7.0F, new Dilation(0.0F))
                .uv(0, 13).cuboid(-2.0F, 0.0F, 3.5F, 4.0F, 0.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(2.2656F, -15.1885F, -4.2F, 0.0F, 0.0F, -0.0698F));

        ModelPartData cube_r3 = bb_main.addChild("cube_r3", ModelPartBuilder.create().uv(4, 20).cuboid(2.0F, 0.0F, -3.5F, 3.0F, 0.0F, 7.0F, new Dilation(0.0F))
                .uv(4, 13).cuboid(2.0F, 0.0F, 3.5F, 3.0F, 0.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(2.2912F, -14.9807F, -4.2F, 0.0F, 0.0F, -0.1745F));

        ModelPartData cube_r4 = bb_main.addChild("cube_r4", ModelPartBuilder.create().uv(-7, 20).cuboid(-3.1F, 0.7F, -3.2F, 3.0F, 0.0F, 7.0F, new Dilation(0.0F))
                .uv(-7, 13).cuboid(-3.1F, 0.7F, 3.8F, 3.0F, 0.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(-3.5F, -16.0F, -4.5F, 0.0F, 0.0F, 0.1745F));

        ModelPartData Left_r1 = bb_main.addChild("Left_r1", ModelPartBuilder.create().uv(0, 62).cuboid(-11.5F, -5.8745F, 0.0F, 23.0F, 12.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(8.6425F, -10.3255F, 0.5F, 0.288F, 1.5708F, 0.0F));

        ModelPartData Right_r1 = bb_main.addChild("Right_r1", ModelPartBuilder.create().uv(0, 62).cuboid(-20.0F, -6.8F, -6.85F, 23.0F, 12.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, -7.6F, -8.0F, -0.288F, 1.5708F, 0.0F));

        ModelPartData Back_r1 = bb_main.addChild("Back_r1", ModelPartBuilder.create().uv(6, 0).cuboid(-8.0F, -1.0F, 16.25F, 21.0F, 13.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, -7.6F, -8.0F, 0.48F, 0.0F, 0.0F));

        ModelPartData Front_r7 = bb_main.addChild("Front_r7", ModelPartBuilder.create().uv(0, 84).cuboid(-8.0F, -8.2F, -1.85F, 21.0F, 12.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, -7.6F, -8.0F, -0.288F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 48, 96);
    }

    public static ModelPart getMainModel() {
        return getTexturedModelData().createModel().getChild("bb_main");
    }
}
