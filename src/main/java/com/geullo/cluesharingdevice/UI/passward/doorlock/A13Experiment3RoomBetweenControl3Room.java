package com.geullo.cluesharingdevice.UI.passward.doorlock;

import com.geullo.endpassward.Lock.DoorLock;
import net.minecraft.client.Minecraft;

import java.io.IOException;

public class A13Experiment3RoomBetweenControl3Room extends DoorLock {
    public A13Experiment3RoomBetweenControl3Room() {
        super("1119");
    }
    @Override
    public void onReward() throws IOException {
        super.onReward();
        if (Minecraft.getMinecraft().player!=null){
            Minecraft.getMinecraft().player.sendChatMessage("/csd unlock door 13");
            Minecraft.getMinecraft().player.sendChatMessage("/문열림");
        }
    }
}
