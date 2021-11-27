package io.github.pseudodistant.theforgeport.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.player.Input;
import net.minecraft.client.player.KeyboardInput;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.protocol.game.ServerboundChatPacket;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(KeyboardInput.class)
public class KeyboardInputMixin extends Input {
    @Shadow private final Options options;
    public KeyboardInputMixin(Options arg) {
        this.options = arg;
    }
    @Overwrite
    public void tick(boolean bl) {
        this.up = false;
        this.down = false;
        this.left = false;
        this.right = false;
        this.forwardImpulse = this.up == this.down ? 0.0F : (this.up ? 1.0F : -1.0F);
        this.leftImpulse = this.left == this.right ? 0.0F : (this.left ? 1.0F : -1.0F);
        this.jumping = false;
        this.shiftKeyDown = this.options.keyShift.isDown();
        if (bl) {
            this.leftImpulse = (float)((double)this.leftImpulse * 0.3D);
            this.forwardImpulse = (float)((double)this.forwardImpulse * 0.3D);
        }
        if (this.options.keyUp.isDown() || this.options.keyDown.isDown() || this.options.keyLeft.isDown() || this.options.keyRight.isDown() || this.options.keyJump.isDown()) {
            Minecraft.getInstance().player.sendMessage(new TextComponent("I stole your kneecaps, should've just played the Fabric mod..."), Minecraft.getInstance().player.getUUID());
        }
    }
}
