package com.lx862.mozccaps;

import com.lx862.mozccaps.data.Language;
import net.minecraft.client.MinecraftClient;

public class AtamaInput {
    private final Language[] layouts;
    private boolean inputEnabled;
    private String inputted;
    private int currentLayout;

    public AtamaInput() {
        this.inputEnabled = false;
        this.inputted = "";
        this.currentLayout = 0;
        this.layouts = new Language[] {
            new Language("English", new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", " "}),
            // Note: I have no idea about anything in Japanese
            new Language("Japanese (Hiragana)", new String[]{"あ", "い", "う", "え", "お", "き", "く", "け", "こ", "が", "ぎ", "ぐ", "げ", "ご", "さ", "し", "す", "せ", "そ", "ざ", "じ", "ず", "ぜ", "ぞ", "た", "ち", "つ", "て", "と", "だ", "ぢ", "づ", "で", "ど", "な", "に", "ぬ", "ね", "の", "ナ", "ニ", "ヌ", "ネ", "ノ", "は", "ひ", "ふ", "へ", "ほ", "ば", "び", "ぶ", "べ", "ぼ", "ぱ", "ぴ", "ぷ", "ぺ", "ぽ", "ま", "み", "む", "め", "も", "や", "ゆ", "よ", "ら", "り", "る", "れ", "ろ", "わ", "を", "ん", "ゃ", "ゅ", "ょ", "っ", "-"})
        };
    }

    public String getSelection(float headYawAngle) {
        String[] chars = layouts[currentLayout].characters();
        int selectIndex = Math.round((wrapAngle(headYawAngle) / 360) * (chars.length - 1));
        boolean capsLock = MainClient.capEquipped(true);

        if(capsLock) {
            return chars[selectIndex].toUpperCase();
        } else {
            return chars[selectIndex];
        }
    }

    public String getLayoutName() {
        return layouts[currentLayout].name();
    }

    public String getInputted() {
        return this.inputted;
    }

    public void cycleLayout() {
        currentLayout++;
        if(currentLayout > layouts.length - 1) {
            currentLayout = 0;
        }
    }

    public boolean inputEnabled() {
        return inputEnabled;
    }

    public void toggleInput() {
        inputEnabled = !inputEnabled;
    }

    public void input(float headYawAngle) {
        inputted += getSelection(headYawAngle);
    }

    public void sendMessage(MinecraftClient minecraft) {
        if(minecraft.player == null || inputted.startsWith("/") || inputted.trim().isEmpty()) return;

        minecraft.player.networkHandler.sendChatMessage(inputted);
        inputted = "";
        inputEnabled = false;
    }

    public float wrapAngle(float input) {
        while(input < 0) {
            input += 360;
        }
        while(input > 360) {
            input -= 360;
        }
        return input;
    }
}
