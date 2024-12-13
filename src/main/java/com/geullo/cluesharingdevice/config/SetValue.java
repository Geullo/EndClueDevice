package com.geullo.cluesharingdevice.config;

import net.minecraft.client.Minecraft;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class SetValue {
    static InputStream inputStream;
    public static void SetValue(HashMap<String,Boolean> value){
        if (value.keySet().size()<=0){
            value.keySet().forEach(l->{
                try {
                    SetValue(l,value.get(l));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
    public static void SetValue(String valueID, boolean settings) throws IOException {
        SetValue(valueID,String.valueOf(settings));
    }
    public static void SetValue(String key, String value) throws IOException {
        try {
            String passwardProp = Minecraft.getMinecraft().mcDataDir + "/config/END/clues.properties";
            try {
                FileInputStream input = new FileInputStream(passwardProp);
                Properties prop = new Properties();
                prop.load(input);
                input.close();

                FileOutputStream output = new FileOutputStream(passwardProp);
                prop.setProperty(key, value);
                prop.store(output, null);
                output.close();

            }
            catch(IOException io) {
                io.printStackTrace();
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                assert false;
                if (inputStream!=null) {
                    inputStream.close();
                }
            }catch (NullPointerException e){e.printStackTrace();}
        }
    }
}
