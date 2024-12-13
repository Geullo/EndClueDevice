package com.geullo.cluesharingdevice.UI.passward.spin;

import com.geullo.endpassward.Lock.SpinLock;
import net.minecraft.client.Minecraft;

import java.io.IOException;

import static com.geullo.endpassward.configuration.GetOneValue.GetOneValue;

public class A0SecretRoom extends SpinLock {
    public A0SecretRoom() {
        super("9426");
    }

    @Override
    public void onReward() throws IOException {
        if (Minecraft.getMinecraft().player!=null){
            Minecraft.getMinecraft().player.sendChatMessage("/csd unlock spin 0");
            Minecraft.getMinecraft().player.sendChatMessage("/door setblock spin_0");
        }
        super.onReward();
    }
}
