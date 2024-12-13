package com.geullo.cluesharingdevice.UI.Device;

import com.geullo.cluesharingdevice.Render;
import com.geullo.cluesharingdevice.Events.RightClickItem;
import com.geullo.endpassward.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

public class ALoginMenu extends GuiScreen {
    private boolean fingerprintClicked = false;
    private int tick =0;
    public int[] BgSize=new int[2],BgPos=new int[2],fingerprintPos=new int[2],fingerprintSize=new int[2],
            hourMinutePosX=new int[5],hourMinutePosY=new int[1],numberSize=new int[2],
            monthDayPosX=new int[4],monthDayPosY=new int[4];

    public ALoginMenu(){}

    @Override
    public void initGui() {
        firstSet();
    }

    @Override
    public void updateScreen() {
        firstSet();
    }
    private void firstSet() {
        if ((width / 1.35) != BgSize[0] || (height / 1.25) != BgSize[1]) {
            BgSize[0] = (int) (width / 1.35);
            BgSize[1] = (int) (height / 1.25);
            BgPos[0] = (width - BgSize[0]) / 2;
            BgPos[1] = (height - BgSize[1]) / 2;
            if (BgPos[0] <= -1) {
                BgPos[0] = 0;
            }
            if (BgPos[1] <= -1) {
                BgPos[1] = 0;
            }

            fingerprintSize[0] = (int) ((BgSize[0] / 8.74) / 1.386);
            fingerprintSize[1] = (int) ((BgSize[1] / 7.45) / 1.2);
            fingerprintPos[0] = (int) (((BgSize[0] - fingerprintSize[0]) / 2) + BgPos[0]);
            fingerprintPos[1] = (int) (BgPos[1] + (BgSize[1] / 1.25));

            numberSize[0] = fingerprintSize[0];
            numberSize[1] = fingerprintSize[0];

            //시계 설정
            hourMinutePosX[0] = (int) (BgPos[0] + ((BgSize[0] / 2) - (numberSize[0] * 2))); // c
            hourMinutePosX[1] = hourMinutePosX[0] + numberSize[0]; // c

            hourMinutePosY[0] = (int) (BgPos[1] + (BgSize[0] / 1.25));
            monthDayPosX[0] = hourMinutePosX[0]; // c
            monthDayPosY[0] = (int) (BgPos[1] + (BgSize[1] / 3.35));
        }
    }
    private void loginMenu(int mouseX, int mouseY){
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("cluesharingdevice", "menu/icon/fingerprint.png"));
        if (fingerprintClicked){
            Render.setColor(0xff76c966);
            tick++;
        }else {
            if (Utils.iconClicked(mouseX,mouseY,fingerprintPos[0],fingerprintPos[1],fingerprintSize[0],fingerprintSize[1]))
            {
                Render.setColor(0xff949393);
            }
            else
            {
                Render.setColor(0xff525252);
            }
        }
        Render.drawTexturedRect(fingerprintPos[0],fingerprintPos[1],fingerprintSize[0],fingerprintSize[1]);
        if (tick==46){
            GlStateManager.disableFog();
            GlStateManager.disableLighting();
            RightClickItem.getInstance().logined = true;
            mc.displayGuiScreen(new BDeviceMainMenu());
        }
    }
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.disableFog();
        GlStateManager.disableLighting();
        try {
            drawDefaultBackground();
            GlStateManager.pushMatrix();
            GlStateManager.loadIdentity();
            GlStateManager.translate(0, 0, -2000.0);

            Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("cluesharingdevice", "menu/background/login.png"));

            Render.setColor(0xffffffff);
            Render.drawTexturedRect(BgPos[0],BgPos[1],BgSize[0],BgSize[1]);


            loginMenu(mouseX,mouseY);

            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GlStateManager.popMatrix();
        }catch (NullPointerException e) {e.printStackTrace();}
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        if (keyCode == Keyboard.KEY_ESCAPE){
            mc.displayGuiScreen(null);
            GlStateManager.disableFog();
            GlStateManager.disableLighting();
        }
    }
    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (mouseButton == 0) {
            if (Utils.iconClicked(mouseX,mouseY,fingerprintPos[0],fingerprintPos[1],fingerprintSize[0],fingerprintSize[1])) {
                if (fingerprintClicked) return;
                fingerprintClicked = true;
            }
        }
    }
}
