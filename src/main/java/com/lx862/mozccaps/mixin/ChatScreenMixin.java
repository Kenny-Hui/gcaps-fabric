package com.lx862.mozccaps.mixin;

import com.lx862.mozccaps.MainClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ChatScreen.class)
public class ChatScreenMixin extends Screen {

    @Shadow protected TextFieldWidget chatField;

    protected ChatScreenMixin(Text title) {
        super(title);
    }

    @Override
    public void close() {
        super.close();
        if(!chatField.getText().startsWith("/")) { // We probably don't want to record command
            MainClient.getAtamaInput().setInputted(chatField.getText());
        }
    }
}
