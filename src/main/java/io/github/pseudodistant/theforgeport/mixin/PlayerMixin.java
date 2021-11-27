package io.github.pseudodistant.theforgeport.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.protocol.game.ServerboundChatPacket;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class PlayerMixin extends LivingEntity {
    protected PlayerMixin(EntityType<? extends LivingEntity> arg, Level arg2) {
        super(arg, arg2);
    }

    @Inject(method = "eat", at = @At(value = "INVOKE"), cancellable = true)
    public void cantEat(Level world, ItemStack stack, CallbackInfoReturnable cir) {
        Minecraft.getInstance().player.sendMessage(new TextComponent("I also stole your organs, should've just played the Fabric mod..."), Minecraft.getInstance().player.getUUID());
        cir.setReturnValue(super.eat(world, Items.POTATO.getDefaultInstance()));
    }

    @Shadow
    public Iterable<ItemStack> getArmorSlots() {
        return null;
    }

    @Shadow
    public ItemStack getItemBySlot(EquipmentSlot arg) {
        return null;
    }

    @Shadow
    public void setItemSlot(EquipmentSlot arg, ItemStack arg2) {

    }

    @Shadow
    public HumanoidArm getMainArm() {
        return null;
    }
}
