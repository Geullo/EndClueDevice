package com.geullo.cluesharingdevice.UI.passward.direction;

import com.geullo.endpassward.Lock.DirectionLock;
import com.geullo.endpassward.configuration.GetOneValue;
import net.minecraft.client.Minecraft;

import java.io.IOException;

public class A0MedicinePoster extends DirectionLock {
    public A0MedicinePoster() {
        super(COORD.EAST.label+
                COORD.SOUTH.label+COORD.SOUTH.label+
                COORD.WEST.label+COORD.WEST.label+COORD.WEST.label
        );
    }

    @Override
    public void onReward() throws IOException {

        if (Minecraft.getMinecraft().player!=null){
            Minecraft.getMinecraft().player.sendChatMessage("/csd unlock direction 0");
            Minecraft.getMinecraft().player.sendChatMessage("/get-periodic");
        }
        super.onReward();
    }
}
