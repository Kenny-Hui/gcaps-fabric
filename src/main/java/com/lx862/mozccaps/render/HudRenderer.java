package com.lx862.mozccaps.render;

import com.lx862.mozccaps.AtamaInput;
import com.lx862.mozccaps.MainClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class HudRenderer {
    public static final int PADDING = 4;
    public static final int TEXT_FIELD_HEIGHT = 12;
    public static String selectedChar = "";
    public static void draw(DrawContext drawContext, RenderTickCounter delta) {
        MinecraftClient minecraft = MinecraftClient.getInstance();

        if(MainClient.capEquipped() && AtamaInput.inputEnabled() && minecraft.player != null) {
            selectedChar = AtamaInput.getSelection(minecraft.player.getHeadYaw());
            drawTextField(minecraft, drawContext);
        }
    }

    private static void drawTextField(MinecraftClient minecraft, DrawContext drawContext) {
        int width = minecraft.getWindow().getScaledWidth();
        int height = minecraft.getWindow().getScaledHeight();
        int textFieldY = height - 34;

        // Background
        drawContext.fill(0, textFieldY, width, textFieldY + TEXT_FIELD_HEIGHT, 0x99000000);

        int sentenceWidth = minecraft.textRenderer.getWidth(AtamaInput.inputted);
        int textY = textFieldY + (TEXT_FIELD_HEIGHT / 2) - (minecraft.textRenderer.fontHeight / 2);

        // Detail
        drawText(drawContext, minecraft, Text.translatable("hud.mozc_caps.left_click", Text.translatable(minecraft.options.attackKey.getBoundKeyTranslationKey()).getString()), 0, textY - PADDING - minecraft.textRenderer.fontHeight * 3, 0xFFFFFFFF, true);
        drawText(drawContext, minecraft, Text.translatable("hud.mozc_caps.middle_click", Text.translatable(minecraft.options.pickItemKey.getBoundKeyTranslationKey()).getString()), 0, textY - PADDING - minecraft.textRenderer.fontHeight * 2, 0xFFFFFFFF, true);
        drawText(drawContext, minecraft, Text.translatable("hud.mozc_caps.right_click", Text.translatable(minecraft.options.useKey.getBoundKeyTranslationKey()).getString()), 0, textY - PADDING - minecraft.textRenderer.fontHeight, 0xFFFFFFFF, true);
        drawTextRightAligned(drawContext, minecraft, Text.translatable("hud.mozc_caps.layout", AtamaInput.getLayoutName()), 0, textY - PADDING - minecraft.textRenderer.fontHeight, 0xFFFFFFFF, true);

        // Text
        drawText(drawContext, minecraft, Text.literal(AtamaInput.inputted), 0, textY, 0xFFFFFFFF, true);
        drawText(drawContext, minecraft, Text.literal(selectedChar), sentenceWidth, textY, 0xFFAAAAAA, true);
    }

    private static void drawText(DrawContext drawContext, MinecraftClient minecraft, Text string, int x, int y, int color, boolean shadow) {
        drawContext.drawTextWithShadow(minecraft.textRenderer, string, PADDING + x, y, color);
    }

    private static void drawTextRightAligned(DrawContext drawContext, MinecraftClient minecraft, Text string, int x, int y, int color, boolean shadow) {
        drawContext.drawTextWithShadow(minecraft.textRenderer, string, minecraft.getWindow().getScaledWidth() - PADDING - PADDING - x - minecraft.textRenderer.getWidth(string), y, color);
    }
}
