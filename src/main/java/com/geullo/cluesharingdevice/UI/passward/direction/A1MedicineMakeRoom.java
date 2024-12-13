package com.geullo.cluesharingdevice.UI.passward.direction;

import com.geullo.endpassward.Lock.DirectionLock;
import com.geullo.endpassward.configuration.GetOneValue;
import net.minecraft.client.Minecraft;

import java.io.IOException;

public class A1MedicineMakeRoom extends DirectionLock {
    public A1MedicineMakeRoom() {
        super(COORD.EAST.label+
                COORD.NORTH.label+COORD.NORTH.label+COORD.NORTH.label+
                COORD.WEST.label+
                COORD.SOUTH.label+COORD.SOUTH.label+COORD.SOUTH.label
        );
    }

    @Override
    public void onReward() throws IOException {
        if (Minecraft.getMinecraft().player!=null){
            Minecraft.getMinecraft().player.sendChatMessage("/csd unlock direction 1");
            Minecraft.getMinecraft().player.sendChatMessage("/door setblock direction_1");
        }
        super.onReward();
    }
}
