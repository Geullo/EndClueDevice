package com.geullo.cluesharingdevice.UI.passward.doorlock;

import com.geullo.endpassward.Lock.DoorLock;
import net.minecraft.client.Minecraft;

import java.io.IOException;

public class A3BirthHanseol extends DoorLock {
    public A3BirthHanseol() {
        super("1226");
    }

    @Override
    public void onReward() throws IOException {
        super.onReward();
        if (Minecraft.getMinecraft().player!=null){
            Minecraft.getMinecraft().player.sendChatMessage("/csd unlock door 3");
            Minecraft.getMinecraft().player.sendChatMessage("/door setblock door_3");
        }
    }
}
