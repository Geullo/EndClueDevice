package com.geullo.cluesharingdevice.UI.passward.direction;

import com.geullo.endpassward.Lock.DirectionLock;
import net.minecraft.client.Minecraft;

import java.io.IOException;

public class A3EscapeDoor extends DirectionLock {
    public A3EscapeDoor() {
        super(
                COORD.NORTH.label+COORD.NORTH.label+COORD.NORTH.label
                        +COORD.WEST.label+COORD.WEST.label
                        +COORD.SOUTH.label
                        +COORD.EAST.label+COORD.EAST.label
                );
    }

    @Override
    public void onReward() throws IOException {
        if (Minecraft.getMinecraft().player!=null){
            Minecraft.getMinecraft().player.sendChatMessage("/csd unlock direction 3");
            Minecraft.getMinecraft().player.sendChatMessage("/door setblock direction_3");
        }
        super.onReward();
    }
}
