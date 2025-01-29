package com.lx862.mozccaps.mixin;

import com.lx862.mozccaps.MainClient;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Shadow protected abstract void openChatScreen(String text);

    @Inject(method = "doAttack", at = @At("HEAD"), cancellable = true)
    public void interactBlock(CallbackInfoReturnable<Boolean> cir) {
        if(MainClient.capEquipped() && MainClient.getAtamaInput().inputEnabled()) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "handleBlockBreaking", at = @At("HEAD"), cancellable = true)
    public void breakBlock(boolean breaking, CallbackInfo ci) {
        if(MainClient.capEquipped() && MainClient.getAtamaInput().inputEnabled()) {
            ci.cancel();
        }
    }

    @ModifyArg(method = "handleInputEvents", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;openChatScreen(Ljava/lang/String;)V"))
    public String moveCapsContentToChat(String text) {
        if(MainClient.capEquipped() && MainClient.getAtamaInput().inputEnabled()) {
            return MainClient.getAtamaInput().getInputted();
        } else {
            return text;
        }
    }
}
