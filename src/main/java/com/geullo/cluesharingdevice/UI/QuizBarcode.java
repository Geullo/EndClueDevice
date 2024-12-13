package com.geullo.cluesharingdevice.UI;


import com.geullo.endpassward.Render.Render;
import com.geullo.endpassward.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

public class QuizBarcode extends ShowClueImage{
    protected float[] barcodePos = new float[3],barcodeSize = new float[2];
    protected float gapX;
    protected boolean imageClicked = false;

    public QuizBarcode(String id) {
        super("quizs/quiz_"+id, null);
    }

    @Override
    public void firstset() {
        super.firstset();
        if (barcodePos[0]>=0&&!imageClicked&&barcodePos[1]==0) {
            barcodeSize[0] = width / 1.963f;
            barcodeSize[1] = height / 1.518f;
            barcodePos[0] = (width - barcodeSize[0]) / 2;
            barcodePos[1] =(height - barcodeSize[1]) / 2;
        }
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float f) {
        super.drawScreen(mouseX, mouseY, f);
        if (imageClicked){
            float nx = mouseX-gapX;
            this.barcodePos[2] = barcodePos[0];
            if ((nx>=0&&nx+barcodeSize[0]<=width)) {
                this.barcodePos[0] = mouseX - gapX;
            }else if (nx<=0.001f){
                this.barcodePos[0] = 0;
            }else if (nx+barcodeSize[0]>=width){
                this.barcodePos[0] = width-barcodeSize[0];
            }
        }
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("cluesharingdevice", "menu/clues/quizs/barcode.png"));
        Render.setColor(0xffffffff);
        Render.drawTexturedRect(barcodePos[0], barcodePos[1], barcodeSize[0], barcodeSize[1]);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        if (imageClicked){
            imageClicked=false;
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if (mouseButton==0){
            if (Utils.iconClicked(mouseX,mouseY,barcodePos[0],barcodePos[1],barcodeSize[0],barcodeSize[1])){
                gapX = mouseX - barcodePos[0];
                imageClicked = true;
            }
        }
    }


}
