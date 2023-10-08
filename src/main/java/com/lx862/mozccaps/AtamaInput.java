package com.lx862.mozccaps;

import com.lx862.mozccaps.data.Language;
import net.minecraft.client.MinecraftClient;

import java.util.List;

public class AtamaInput {
    public static int currentLayout = 0;
    public static String inputted = "";
    public static boolean inputEnabled = false;
    // TODO: Add chin strapped variants for CAPS LOCK!
    public static boolean capsLock = false;
    public static final List<Language> layouts = List.of(
            new Language("English", new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", " "}),
            // Note: I have no idea about anything in Japanese
            new Language("Japanese (Hiragana)", new String[]{"あ", "い", "う", "え", "お", "き", "く", "け", "こ", "が", "ぎ", "ぐ", "げ", "ご", "さ", "し", "す", "せ", "そ", "ざ", "じ", "ず", "ぜ", "ぞ", "た", "ち", "つ", "て", "と", "だ", "ぢ", "づ", "で", "ど", "な", "に", "ぬ", "ね", "の", "ナ", "ニ", "ヌ", "ネ", "ノ", "は", "ひ", "ふ", "へ", "ほ", "ば", "び", "ぶ", "べ", "ぼ", "ぱ", "ぴ", "ぷ", "ぺ", "ぽ", "ま", "み", "む", "め", "も", "や", "ゆ", "よ", "ら", "り", "る", "れ", "ろ", "わ", "を", "ん", "ゃ", "ゅ", "ょ", "っ", "-"})
    );

    public static String getSelection(float headYawAngle) {
        String[] chars = layouts.get(currentLayout).characters;
        int selectIndex = Math.round((wrapAngle(headYawAngle) / 360) * (chars.length - 1));

        if(capsLock) {
            return chars[selectIndex].toUpperCase();
        } else {
            return chars[selectIndex];
        }
    }

    public static String getLayoutName() {
        return layouts.get(currentLayout).name;
    }

    public static void cycleLayout() {
        currentLayout++;
        if(currentLayout > layouts.size() - 1) {
            currentLayout = 0;
        }
    }

    public static void input(float headYawAngle) {
        inputted += getSelection(headYawAngle);
    }

    public static void sendMessage(MinecraftClient minecraft) {
        if(inputted.startsWith("/") || inputted.trim().isEmpty()) return;

        minecraft.player.networkHandler.sendChatMessage(inputted);
        inputted = "";
        AtamaInput.inputEnabled = false;
    }

    public static float wrapAngle(float input) {
        while(input < 0) {
            input += 360;
        }
        while(input > 360) {
            input -= 360;
        }
        return input;
    }
}
