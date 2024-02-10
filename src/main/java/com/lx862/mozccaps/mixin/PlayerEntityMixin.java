package com.lx862.mozccaps.mixin;

import com.lx862.mozccaps.AtamaInput;
import com.lx862.mozccaps.MainClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(method = "isBlockBreakingRestricted", at = @At("HEAD"), cancellable = true)
    public void isBlockBreakingRestricted(World world, BlockPos pos, GameMode gameMode, CallbackInfoReturnable<Boolean> cir) {
        if(MainClient.capEquipped() && AtamaInput.inputEnabled) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "attack", at = @At("HEAD"), cancellable = true)
    public void attack(Entity target, CallbackInfo ci) {
        if(MainClient.capEquipped() && AtamaInput.inputEnabled) {
            ci.cancel();
        }
    }
}
