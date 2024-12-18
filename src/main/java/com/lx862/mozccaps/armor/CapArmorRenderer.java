package com.lx862.mozccaps.armor;

import com.lx862.mozccaps.MainClient;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.model.*;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.joml.Quaternionf;

public class CapArmorRenderer implements ArmorRenderer {
    private static final Identifier TEXTURE_ID = Identifier.of("mozc_caps:textures/armor/mozc_caps.png");
    private static final ModelPart capModel = CapModel.getMainModel();
    private static final ModelPart chinModel = ChinModel.getMainModel();
    private static final float CAP_TILT = -0.2F;
    private final boolean hasStrap;

    public CapArmorRenderer(boolean hasStrap) {
        this.hasStrap = hasStrap;
    }

    private void renderCap(MatrixStack matrices, VertexConsumer vertexConsumer, BipedEntityModel<BipedEntityRenderState> contextModel, double pressedAmount, int light) {
        Quaternionf rotation = new Quaternionf();
        rotation.rotateX(CAP_TILT);

        matrices.push();
        matrices.multiply(rotation);
        capModel.setTransform(contextModel.hat.getTransform());
        matrices.translate(0, -0.1F, -0.07F); //Small offset to make things look right
        matrices.scale(0.6F, 0.6F, 0.6F);
        matrices.translate(0, 0.1F * pressedAmount, 0);
        capModel.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
        matrices.pop();
    }

    private void renderStrap(MatrixStack matrices, VertexConsumer vertexConsumer, BipedEntityModel<BipedEntityRenderState> contextModel, int light) {
        matrices.push();
        matrices.scale(0.6F, 0.6F, 0.6F);
        chinModel.setTransform(contextModel.head.getTransform());
        chinModel.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
        matrices.pop();
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumerProvider, ItemStack itemStack, BipedEntityRenderState bipedEntityRenderState, EquipmentSlot equipmentSlot, int light, BipedEntityModel<BipedEntityRenderState> contextModel) {
        if(bipedEntityRenderState instanceof PlayerEntityRenderState) {
            VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutout(TEXTURE_ID));

            double animationProgress = 0;
            if(bipedEntityRenderState instanceof  PlayerEntityRenderState) {
                animationProgress = MainClient.keyPressedList.getOrDefault(((PlayerEntityRenderState)bipedEntityRenderState).name, 0.0);
            }
            double pressedAmount = animationProgress > 0.5 ? (1 - animationProgress) : (animationProgress);

            renderCap(matrices, vertexConsumer, contextModel, pressedAmount, light);

            if(hasStrap) {
                renderStrap(matrices, vertexConsumer, contextModel, light);
            }
        }
    }
}
