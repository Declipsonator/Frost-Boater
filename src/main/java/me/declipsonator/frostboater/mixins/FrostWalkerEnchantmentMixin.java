package me.declipsonator.frostboater.mixins;

import me.declipsonator.frostboater.FrostBoater;
import net.minecraft.block.Block;
import net.minecraft.enchantment.FrostWalkerEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FrostWalkerEnchantment.class)
public class FrostWalkerEnchantmentMixin {
    private static boolean boat = false;
    @Redirect(method = "freezeWater", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;isOnGround()Z"))
    private static boolean isOnGround(LivingEntity entity) {
        boat = entity.hasVehicle() && entity.getVehicle().getType() == EntityType.BOAT;
        return entity.isOnGround() || (entity.hasVehicle() && entity.getVehicle().getType() == EntityType.BOAT && entity.getVehicle().isOnGround());
    }

    @ModifyVariable(method = "freezeWater", at = @At("HEAD"), index = 2, argsOnly = true)
    private static BlockPos boatOrNoBoat(BlockPos value, LivingEntity entity) {
        Entity e =  !entity.hasVehicle() ? entity : entity.getVehicle().getType() == EntityType.BOAT ?  entity.getVehicle() : entity;
        return e.getBlockPos();
    }

    @Redirect(method = "freezeWater", at = @At(value = "INVOKE", target = "Ljava/lang/Math;min(II)I"))
    private static int min(int a, int b) {
        return Math.min(a, boat ? b * 2 : b);
    }

}
