package com.lx862.mozccaps.armor;

import net.minecraft.client.model.*;

public class ChinModel {

    /* Code generated from Blockbench */
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(42, 10).cuboid(-7.0F, -13.0F, -1.5F, 0.0F, 13.0F, 3.0F, new Dilation(0.0F))
                .uv(42, 10).cuboid(7.0F, -13.0F, -1.5F, 0.0F, 13.0F, 3.0F, new Dilation(0.0F))
                .uv(-3, 50).cuboid(-7.0F, 0.1F, -1.5F, 14.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
        return TexturedModelData.of(modelData, 48, 96);
    }

    public static ModelPart getMainModel() {
        return getTexturedModelData().createModel().getChild("bb_main");
    }
}
