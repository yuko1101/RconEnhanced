package io.github.yuko1101.rconenhanced.mixin;

import net.minecraft.server.rcon.RconClient;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RconClient.class)
public class MixinRconClient {
    @Unique
    private final byte[] rconenhanced$packetBuffer = new byte[65535];

    @Redirect(method = "run", at = @At(value = "FIELD", target = "Lnet/minecraft/server/rcon/RconClient;packetBuffer:[B", opcode = Opcodes.GETFIELD))
    private byte[] redirectPacketBuffer(RconClient rconClient) {
        return rconenhanced$packetBuffer;
    }

    @ModifyConstant(method = "run", constant = @Constant(intValue = 1460))
    private int modifyMaxPacketSize(int original) {
        return 65535;
    }
}
