package com.lx862.mozccaps;

import com.lx862.mozccaps.data.Language;
import net.minecraft.client.MinecraftClient;

public class AtamaInput {
    private static boolean inputEnabled = false;
    public static int currentLayout = 0;
    public static String inputted = "";
    public static final Language[] layouts = {
            new Language("English", new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", " "}),
            // Note: I have no idea about anything in Japanese
            new Language("Japanese (Hiragana)", new String[]{"あ", "い", "う", "え", "お", "き", "く", "け", "こ", "が", "ぎ", "ぐ", "げ", "ご", "さ", "し", "す", "せ", "そ", "ざ", "じ", "ず", "ぜ", "ぞ", "た", "ち", "つ", "て", "と", "だ", "ぢ", "づ", "で", "ど", "な", "に", "ぬ", "ね", "の", "ナ", "ニ", "ヌ", "ネ", "ノ", "は", "ひ", "ふ", "へ", "ほ", "ば", "び", "ぶ", "べ", "ぼ", "ぱ", "ぴ", "ぷ", "ぺ", "ぽ", "ま", "み", "む", "め", "も", "や", "ゆ", "よ", "ら", "り", "る", "れ", "ろ", "わ", "を", "ん", "ゃ", "ゅ", "ょ", "っ", "-"})
    };

    public static String getSelection(float headYawAngle) {
        String[] chars = layouts[currentLayout].characters;
        int selectIndex = Math.round((wrapAngle(headYawAngle) / 360) * (chars.length - 1));
        boolean capsLock = MainClient.capEquipped(true);

        if(capsLock) {
            return chars[selectIndex].toUpperCase();
        } else {
            return chars[selectIndex];
        }
    }

    public static String getLayoutName() {
        return layouts[currentLayout].name;
    }

    public static void cycleLayout() {
        currentLayout++;
        if(currentLayout > layouts.length - 1) {
            currentLayout = 0;
        }
    }

    public static boolean inputEnabled() {
        return inputEnabled;
    }

    public static void toggleInput() {
        inputEnabled = !inputEnabled;
    }

    public static void input(float headYawAngle) {
        inputted += getSelection(headYawAngle);
    }

    public static void sendMessage(MinecraftClient minecraft) {
        if(minecraft.player == null || inputted.startsWith("/") || inputted.trim().isEmpty()) return;

        minecraft.player.networkHandler.sendChatMessage(inputted);
        inputted = "";
        inputEnabled = false;
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
