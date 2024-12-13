package com.geullo.cluesharingdevice.UI.Device;

import com.geullo.cluesharingdevice.Render;
import com.geullo.cluesharingdevice.UI.ShowClueImage;
import com.geullo.cluesharingdevice.config.Get;
import com.geullo.endpassward.util.Utils;
import javafx.scene.control.ListView;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.*;


public class CGallery extends GuiScreen {
    private GuiScreen parentScreen = null;

    private ArrayList<String> s = new ArrayList<>();
    private int[] BgSize=new int[2],BgPos=new int[2],previousPos=new int[2],previousSize=new int[2],
                  imagesPosX = new int[10],imagesPosY = new int[10],imagesSize = new int[2];
    private double alpha = 0.00000f;
    public CGallery(GuiScreen parent) {
        if (parent==null) {
            System.out.println("Geullo : Parent Screen is nullable");mc.displayGuiScreen(parent);
            return;
        }
        this.parentScreen = parent;
    }

    @Override
    public void initGui() {
        try {
            firstSet();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateScreen() {
        try {
            firstSet();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void firstSet() throws IOException {
        if (width/1.35!=BgSize[0]||height/1.25!=BgSize[1]) {
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

            previousSize[0] = (int) (((BgSize[0] / 4) / 4) / 4.5);

            previousSize[1] = (int) ((BgSize[0] / 4) / 4 / 2.5);

            previousPos[0] = (int) (BgPos[0] + ((BgSize[0] / 7) / 2.83));
            previousPos[1] = (int) (BgPos[1] + ((BgSize[1] / 3) / 3.15));

            HashMap<String, String> va = Get.GetValues("LIST");
            s.clear();
            if (s.isEmpty()) {
                for (int i = 0;i<va.size();i++){
                    if (va.containsKey("LIST_" + i)) {
                        s.add(va.get("LIST_" + i).toLowerCase());
                    }
                }
            }
            Collections.reverse(s);
            if (s.size() > 0) {
                imagesSize[0] = (int) ((BgSize[0] / 2.55) / 2.213);
                imagesSize[1] = (int) ((BgSize[1] / 1.56) / 2);
                for (int i = 0; i < s.size(); i++) {
                    if (i == 0) {
                        imagesPosX[i] = (int) (BgPos[0] + ((BgSize[0] / 5.75) / 3.9));
                        imagesPosY[i] = (int) (BgPos[1] + ((BgSize[1] / 1.93) / 2.2));
                    } else if (i % 5 == 0) {
                        imagesPosX[i] = imagesPosX[0];
                        imagesPosY[i] = (int) ((imagesPosY[i - 1] + imagesSize[1]) + ((imagesSize[1] / 5) / 4.43));
                    } else {
                        imagesPosX[i] = (int) ((imagesPosX[i - 1] + imagesSize[0]) + ((imagesSize[0] / 5) / 4.43));
                        imagesPosY[i] = imagesPosY[i - 1];
                    }
                }
            }
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

            Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("cluesharingdevice", "menu/background/image.png"));
            Render.setColor(0xffffffff);
            Render.drawTexturedRect(BgPos[0], BgPos[1], BgSize[0], BgSize[1]);

            Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("cluesharingdevice", "menu/icon/previous.png"));
            Render.setColor(0xffffffff);
            Render.drawTexturedRect(previousPos[0], previousPos[1], previousSize[0], previousSize[1]);
            if (Utils.iconClicked(mouseX,mouseY,previousPos[0], previousPos[1], previousSize[0], previousSize[1])){
                Render.setColor(0x33000000);
                Render.drawTexturedRect(previousPos[0], previousPos[1], previousSize[0], previousSize[1]);
            }
            if (s.size()>0) {
                for (int i = 0; i < s.size(); i++) {
                    Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("cluesharingdevice", "menu/clues/preview/"+s.get(i)+".png"));
                    Render.setColor(0xffffffff);
                    Render.drawTexturedRect(imagesPosX[i], imagesPosY[i], imagesSize[0], imagesSize[1]);
                    if (Utils.iconClicked(mouseX,mouseY,imagesPosX[i], imagesPosY[i], imagesSize[0], imagesSize[1])){
                        Render.setColor(0x33000000);
                        Render.drawTexturedRect(imagesPosX[i], imagesPosY[i], imagesSize[0], imagesSize[1]);
                    }
                }
            }

            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GlStateManager.popMatrix();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) {
        if (keyCode == Keyboard.KEY_ESCAPE) {
            mc.displayGuiScreen(parentScreen);
            GlStateManager.disableFog();
            GlStateManager.disableLighting();
        }
    }
    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (mouseButton == 0) {
            if (Utils.iconClicked(mouseX,mouseY,previousPos[0],previousPos[1],previousSize[0],previousSize[1])){
                mc.displayGuiScreen(parentScreen);
                GlStateManager.disableFog();
                GlStateManager.disableLighting();
            }else {
                for (int i = 0; i < s.size(); i++) {
                    if (Utils.iconClicked(mouseX, mouseY, imagesPosX[i], imagesPosY[i], imagesSize[0], imagesSize[1])) {
                        GlStateManager.disableFog();
                        GlStateManager.disableLighting();
                        mc.displayGuiScreen(new ShowClueImage(s.get(i).toLowerCase(),this));
                        return;
                    }
                }
            }
        }
    }

}
