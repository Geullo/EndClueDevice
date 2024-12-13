package com.geullo.cluesharingdevice.UI.passward.doorlock;

import com.geullo.endpassward.Lock.DoorLock;
import net.minecraft.client.Minecraft;

import java.io.IOException;

public class A8OfficeRoom  extends DoorLock {
    public A8OfficeRoom() {
        super("72839");
    }

    @Override
    public void onReward() throws IOException {
        super.onReward();
        if (Minecraft.getMinecraft().player!=null){
            Minecraft.getMinecraft().player.sendChatMessage("/csd unlock door 8");
            Minecraft.getMinecraft().player.sendChatMessage("/door setblock door_8");
        }
    }
}
