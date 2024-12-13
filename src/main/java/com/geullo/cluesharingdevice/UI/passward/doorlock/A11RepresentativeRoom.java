package com.geullo.cluesharingdevice.UI.passward.doorlock;

import com.geullo.endpassward.Lock.DoorLock;
import net.minecraft.client.Minecraft;

import java.io.IOException;

public class A11RepresentativeRoom extends DoorLock {
    public A11RepresentativeRoom() {
        super("40000");
    }
    @Override
    public void onReward() throws IOException {
        super.onReward();
        if (Minecraft.getMinecraft().player!=null){
            Minecraft.getMinecraft().player.sendChatMessage("/csd unlock door 11");
            Minecraft.getMinecraft().player.sendChatMessage("/door setblock door_11");
        }
    }
}
