package com.lx862.mozccaps.render;

import com.lx862.mozccaps.MainClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class HudOverlayRenderer {
    public static final int PADDING = 4;
    public static final int TEXT_FIELD_HEIGHT = 12;

    public static void draw(DrawContext drawContext, RenderTickCounter delta) {
        MinecraftClient minecraft = MinecraftClient.getInstance();
        CapArmorRenderer.updateCapPressedAnimation(minecraft.getRenderTickCounter().getLastFrameDuration() / 4);

        if(!minecraft.options.hudHidden && MainClient.capEquipped() && MainClient.getAtamaInput().inputEnabled() && minecraft.player != null) {
            String selectedChar = MainClient.getAtamaInput().getSelection(minecraft.player.getHeadYaw());
            drawSelectedChar(selectedChar, minecraft, drawContext);
            drawTextField(selectedChar, minecraft, drawContext);
        }
    }

    private static void drawSelectedChar(String selectedChar, MinecraftClient minecraft, DrawContext drawContext) {
        MatrixStack matrices = drawContext.getMatrices();
        double halfTextWidth = minecraft.textRenderer.getWidth(selectedChar) / 2.0;
        double halfFontHeight = minecraft.textRenderer.fontHeight / 2.0;

        double halfScreenWidth = minecraft.getWindow().getScaledWidth() / 2.0;
        double halfScreenHeight = minecraft.getWindow().getScaledHeight() / 2.0;

        float typeAnimation = (float) CapArmorRenderer.getTypeAnimationProgress(minecraft.player.getGameProfile().getName(), 1.0);
        float textScale = 1.5f + (typeAnimation * 0.5f);

        matrices.push();
        matrices.translate(halfScreenWidth - halfTextWidth, halfScreenHeight - halfFontHeight, 0);
        matrices.translate(halfTextWidth, halfFontHeight, 0);
        matrices.scale(textScale, textScale, textScale);
        matrices.translate(-halfTextWidth, -halfFontHeight, 0);
        drawContext.drawTextWithShadow(minecraft.textRenderer, Text.literal(selectedChar), 0, 0, 0xFFFFFFFF);
        matrices.pop();
    }

    private static void drawTextField(String selectedChar, MinecraftClient minecraft, DrawContext drawContext) {
        int width = minecraft.getWindow().getScaledWidth();
        int height = minecraft.getWindow().getScaledHeight();
        int textFieldY = height - 34;

        // Background
        drawContext.fill(0, textFieldY, width, textFieldY + TEXT_FIELD_HEIGHT, 0x99000000);

        int sentenceWidth = minecraft.textRenderer.getWidth(MainClient.getAtamaInput().getInputted());
        int textY = textFieldY + (TEXT_FIELD_HEIGHT / 2) - (minecraft.textRenderer.fontHeight / 2);

        // Detail
        drawText(drawContext, minecraft, Text.translatable("hud.mozc_caps.left_click", Text.translatable(minecraft.options.attackKey.getBoundKeyTranslationKey()).getString()), 0, textY - PADDING - minecraft.textRenderer.fontHeight * 3, 0xFFFFFFFF);
        drawText(drawContext, minecraft, Text.translatable("hud.mozc_caps.middle_click", Text.translatable(minecraft.options.pickItemKey.getBoundKeyTranslationKey()).getString()), 0, textY - PADDING - minecraft.textRenderer.fontHeight * 2, 0xFFFFFFFF);
        drawText(drawContext, minecraft, Text.translatable("hud.mozc_caps.right_click", Text.translatable(minecraft.options.useKey.getBoundKeyTranslationKey()).getString()), 0, textY - PADDING - minecraft.textRenderer.fontHeight, 0xFFFFFFFF);
        drawTextRightAligned(drawContext, minecraft, Text.translatable("hud.mozc_caps.layout", MainClient.getAtamaInput().getLayoutName()), 0, textY - PADDING - minecraft.textRenderer.fontHeight, 0xFFFFFFFF);

        // Text
        drawText(drawContext, minecraft, Text.literal(MainClient.getAtamaInput().getInputted()), 0, textY, 0xFFFFFFFF);
        drawText(drawContext, minecraft, Text.literal(selectedChar), sentenceWidth, textY, 0xFFAAAAAA);
    }

    private static void drawText(DrawContext drawContext, MinecraftClient minecraft, Text string, int x, int y, int color) {
        drawContext.drawTextWithShadow(minecraft.textRenderer, string, PADDING + x, y, color);
    }

    private static void drawTextRightAligned(DrawContext drawContext, MinecraftClient minecraft, Text string, int x, int y, int color) {
        drawContext.drawTextWithShadow(minecraft.textRenderer, string, minecraft.getWindow().getScaledWidth() - PADDING - PADDING - x - minecraft.textRenderer.getWidth(string), y, color);
    }
}
