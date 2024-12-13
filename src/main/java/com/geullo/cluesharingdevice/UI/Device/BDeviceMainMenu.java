package com.geullo.cluesharingdevice.UI.Device;

import com.geullo.cluesharingdevice.Render;
import com.geullo.cluesharingdevice.config.GetValues;
import com.geullo.endpassward.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

public class BDeviceMainMenu extends GuiScreen {


    private int[] BgSize=new int[2],BgPos=new int[2],fingerprintPos=new int[2],fingerprintSize=new int[2],
            latestCoordinatesPos=new int[2],latestCoordinatesSize = new int[2],datePos = new int[2] , dateSize = new int[2],
            iconPosX = new int[2],iconPosY=new int[2],iconSize = new int[2];

    public BDeviceMainMenu(){}

    @Override
    public void initGui() {
        firstSet();
    }

    @Override
    public void updateScreen() {
        firstSet();
    }
    private void firstSet(){
        if ((width/1.35)!=BgSize[0]||(height/1.25)!=BgSize[1]) {
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


            //시계 설정
            dateSize[0] = (int) (BgSize[0] / 3.2);
            dateSize[1] = (int) (BgSize[1] / 3.75);
            datePos[0] = (int) (BgPos[0] + ((BgPos[0] / 5.73) / 1.6));
            datePos[1] = (int) (BgPos[1] + (BgPos[1] / 1.54));

            iconSize[0] = (int) (dateSize[0] / 4.5);
            iconSize[1] = (int) (dateSize[1] / 1.8);


            latestCoordinatesSize[0] = (int) (dateSize[0] - ((dateSize[0] / 2) / 1.93));
            latestCoordinatesSize[1] = (int) (BgSize[1] / 3.04);

            latestCoordinatesPos[0] = (int) (datePos[0] + ((dateSize[0] - latestCoordinatesSize[0]) / 2));
            latestCoordinatesPos[1] = (datePos[1] + dateSize[1]) - (iconSize[1] / 5);
//        +(iconSize[0]/4)
            iconPosX[0] = (int) ((datePos[0] + (dateSize[0] - ((dateSize[0] / 4) / 2.2))));
            iconPosY[0] = (int) (datePos[1] + dateSize[1] / 4.4);


            iconPosX[1] = (iconPosX[0] + iconSize[0]) + (iconSize[0] / 4);
            iconPosY[1] = iconPosY[0];

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

            Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("cluesharingdevice", "menu/background/default.png"));
            Render.setColor(0xffffffff);
            Render.drawTexturedRect(BgPos[0], BgPos[1], BgSize[0], BgSize[1]);

            Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("cluesharingdevice", "menu/icon/main_date.png"));
            Render.setColor(0xff000000);
            Render.drawTexturedRect(datePos[0], datePos[1], dateSize[0], dateSize[1]);

            if (!Utils.iconClicked(mouseX,mouseY,iconPosX[0], iconPosY[0], iconSize[0], iconSize[1])) {
                Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("cluesharingdevice", "menu/icon/gallery.png"));
            }else{
                Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("cluesharingdevice", "menu/icon/gallery_over.png"));
            }
            Render.setColor(0xffffffff);
            Render.drawTexturedRect(iconPosX[0], iconPosY[0], iconSize[0], iconSize[1]);

            Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("cluesharingdevice", "menu/icon/camera.png"));
            Render.setColor(0xffffffff);
            Render.drawTexturedRect(iconPosX[1], iconPosY[1], iconSize[0], iconSize[1]);

            if (!checkAllFoundCLUE()) {
                Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("cluesharingdevice", "menu/icon/latest_clue.png"));
            }else {
                Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("cluesharingdevice", "menu/icon/hint.png"));
            }
            Render.setColor(0xffffffff);
            Render.drawTexturedRect(latestCoordinatesPos[0], latestCoordinatesPos[1], latestCoordinatesSize[0], latestCoordinatesSize[1]);


            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GlStateManager.popMatrix();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
    public boolean checkAllFoundCLUE(){
        try {
            int k = 8;
            int l=0;
            boolean[] t = GetValues.GetValues("CLUE");
            for (int i=0;i<t.length;i++){
                if (t[i]){
                    l++;
                }
            }
            if (k == l) return true;
            else return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
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
            if (Utils.iconClicked(mouseX,mouseY,iconPosX[0],iconPosY[0],iconSize[0],iconSize[1])){
                GlStateManager.disableFog();
                GlStateManager.disableLighting();
                mc.displayGuiScreen(new CGallery(this));
            }
        }
    }
}
