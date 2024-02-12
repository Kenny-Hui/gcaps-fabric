package com.lx862.mozccaps.mixin;

import com.lx862.mozccaps.AtamaInput;
import com.lx862.mozccaps.MainClient;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(method = "doAttack", at = @At("HEAD"), cancellable = true)
    public void interactBlock(CallbackInfoReturnable<Boolean> cir) {
        if(MainClient.capEquipped() && AtamaInput.inputEnabled()) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "handleBlockBreaking", at = @At("HEAD"), cancellable = true)
    public void breakBlock(boolean breaking, CallbackInfo ci) {
        if(MainClient.capEquipped() && AtamaInput.inputEnabled()) {
            ci.cancel();
        }
    }
}
