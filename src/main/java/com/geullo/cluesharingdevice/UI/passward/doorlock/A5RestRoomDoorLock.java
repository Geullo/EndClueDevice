package com.geullo.cluesharingdevice.UI.passward.doorlock;

import com.geullo.endpassward.Lock.DoorLock;
import net.minecraft.client.Minecraft;

import java.io.IOException;

//휴게실
public class A5RestRoomDoorLock extends DoorLock {
    public A5RestRoomDoorLock() {
        super("3627");
    }

    @Override
    public void onReward() throws IOException {
        super.onReward();
        if (Minecraft.getMinecraft().player!=null){
            Minecraft.getMinecraft().player.sendChatMessage("/csd unlock door 5");
            Minecraft.getMinecraft().player.sendChatMessage("/door setblock door_5");
        }
    }
}
