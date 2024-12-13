package com.geullo.cluesharingdevice.UI.passward.spin;

import com.geullo.endpassward.Lock.SpinLock;
import net.minecraft.client.Minecraft;

import java.io.IOException;

import static com.geullo.endpassward.configuration.GetOneValue.GetOneValue;

public class A1SpinRestRoomKey extends SpinLock {
    public A1SpinRestRoomKey() {
        super("9701");
    }

    @Override
    public void onReward() throws IOException {
        if (Minecraft.getMinecraft().player!=null){
            Minecraft.getMinecraft().player.sendChatMessage("/csd unlock spin 1");
            Minecraft.getMinecraft().player.sendChatMessage("/chest open");
        }
        super.onReward();
    }
}
