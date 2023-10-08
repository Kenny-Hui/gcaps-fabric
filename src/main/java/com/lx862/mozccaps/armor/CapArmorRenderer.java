package com.lx862.mozccaps.armor;

import com.lx862.mozccaps.MainClient;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.model.*;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.joml.Quaternionf;

public class CapArmorRenderer implements ArmorRenderer {
    private static final Identifier TEXTURE_ID = new Identifier("mozc_caps:textures/armor/mozc_caps.png");
    private static final ModelPart model = CapModel.getMainModel();
    private static final float CAP_TILT = -0.2F;

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, ItemStack stack, LivingEntity entity, EquipmentSlot slot, int light, BipedEntityModel<LivingEntity> contextModel) {
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutout(TEXTURE_ID));
        double animationProgress = MainClient.keyPressedList.getOrDefault(entity.getUuid(), 0.0);
        double pressedAmount = animationProgress > 0.5 ? (1 - animationProgress) : (animationProgress);

        Quaternionf rotation = new Quaternionf();
        rotation.rotateX(CAP_TILT);

        matrices.push();
        matrices.multiply(rotation);
        matrices.translate(0, -0.1F, -0.07F); //Small offset to make things look right
        matrices.scale(0.6F, 0.6F, 0.6F);
        matrices.translate(0, 0.1F * pressedAmount, 0);
        model.setTransform(contextModel.hat.getTransform());
        model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
        matrices.pop();
    }
}
