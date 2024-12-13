package com.geullo.cluesharingdevice;

import com.geullo.cluesharingdevice.UI.Device.BDeviceMainMenu;
import com.geullo.cluesharingdevice.UI.QuizBarcode;
import com.geullo.cluesharingdevice.UI.ShowClueImage;
import com.geullo.cluesharingdevice.UI.passward.direction.A1MedicineMakeRoom;
import com.geullo.cluesharingdevice.UI.passward.direction.A2ThirdExperimentRoom;
import com.geullo.cluesharingdevice.UI.passward.direction.A3EscapeDoor;
import com.geullo.cluesharingdevice.UI.passward.doorlock.*;
import com.geullo.cluesharingdevice.UI.passward.spin.A3ExperienceRoom3;
import com.geullo.cluesharingdevice.config.*;
import com.geullo.cluesharingdevice.UI.*;
import com.geullo.cluesharingdevice.UI.passward.direction.A0MedicinePoster;
import com.geullo.cluesharingdevice.UI.passward.spin.A2ExperienceRoom2;
import com.geullo.cluesharingdevice.UI.passward.spin.A0SecretRoom;
import com.geullo.cluesharingdevice.UI.passward.spin.A1SpinRestRoomKey;
import com.geullo.cluesharingdevice.util.Sound;
import com.geullo.endpassward.END.Background.Event;
import com.geullo.endpassward.END.SoundEffect;
import com.geullo.endpassward.Lock.*;
import com.geullo.endpassward.configuration.GetOneValue;
import com.geullo.endpassward.configuration.ResetPWConfig;
import com.geullo.endpassward.configuration.SetValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.*;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Csd {
    public Minecraft mc = null;

    private static Csd instance;

    /**<body><pre><p style="color:#FFFFFF";>
     * Direction Lock Instance List
     * </p></pre></body>
     * */
    private A0MedicinePoster direction0;
    private A1MedicineMakeRoom direction1;
    private A2ThirdExperimentRoom direction2;
    private A3EscapeDoor direction3;

    /**<body><pre><p style="color:#FFFFFF";>
     * Door Lock Instance List
     * </p></pre></body>
     * */
    protected A0Birthd7297 door0;
    protected A1Birth3castle door1;
    protected A2BirthDaju door2;
    protected A3BirthHanseol door3;
    protected A4MedicineCleanRoom door4;
    protected A5RestRoomDoorLock door5;
    protected A6ExperimentDoorlock door6;
    protected A7ControlRoom door7;
    protected A8OfficeRoom door8;
    protected A9MedicineExperimentRoomDoorLock door9;
    protected A10CleanRoom door10;
    protected A11RepresentativeRoom door11;
    protected A12RepresentSpecificInfoRoom door12;
    protected A13Experiment3RoomBetweenControl3Room door13;

    /**<body><pre><p style="color:#FFFFFF";>
     * Spin Lock Instance List
     * </p></pre></body>
     * */
    private A0SecretRoom spin0;
    private A1SpinRestRoomKey spin1;
    private A2ExperienceRoom2 spin2;
    private A3ExperienceRoom3 spin3;

    private String defaultPw = "1234";


    public static Csd getInstance(){
        if(instance == null){
            instance = new Csd();
        }
        return instance;
    }
    private Csd() {
        mc = Minecraft.getMinecraft();
    }

    //Packet Handle
    public void handleMessage(PacketMessage message) throws Exception {
        String recognitionCode = message.data.substring(0, 7);
        int t = 0;
        if (recognitionCode.equals("cuealar")){
            String playerName = message.data.substring(7, 9);
            MinecraftForge.EVENT_BUS.register(new Alarm(playerName));
        }
        else if (recognitionCode.equals("cuevset")){ //cuevsetTd7T0
            boolean TOrF = "T".equals(message.data.substring(7, 8));
            String p = message.data.substring(8,10), id = ""+Integer.parseInt(message.data.substring(11));
            if (!"".equals(id)&&Integer.parseInt(id)<8) {
                openClue(id,TOrF,p,"T".equals(message.data.substring(10,11)));
            }
            return;
        }
        else if (recognitionCode.equals("musipla")) {
            ISound sound = PositionedSoundRecord.getRecord(SoundEffect.WRONG, 1.0f, 0.5f);
            switch (message.data.substring(7)) {
                case "spin":
                    sound = Sound.getSound(SoundEffect.SPIN,SoundCategory.PLAYERS);
                    break;
                case "door_lock_fail":
                    sound = Sound.getSound(SoundEffect.DOOR_LOCK_FAIL,SoundCategory.PLAYERS);
                    break;
                case "door_lock_open":
                    sound = Sound.getSound(SoundEffect.DOOR_LOCK_OPEN,SoundCategory.PLAYERS);
                    break;
                case "door_lock_pad_click":
                    sound = Sound.getSound(SoundEffect.DOOR_LOCK_PAD_CLICK,SoundCategory.PLAYERS);
                    break;
                case "correct":
                    sound = Sound.getSound(SoundEffect.CORRECT,SoundCategory.PLAYERS);
                    break;
                case "wrong":
                    sound = Sound.getSound(SoundEffect.WRONG,SoundCategory.PLAYERS);
                    break;
                case "computer_2":
                    sound = Sound.getSound(SoundEffect.QUIZ_COMPUTER_2,SoundCategory.PLAYERS,1.0f);
                    break;
                case "computer_4":
                    sound = Sound.getSound(SoundEffect.QUIZ_COMPUTER_4,SoundCategory.PLAYERS,1.0f);
                    break;
                case "mainmenu":
                    sound = Sound.getSound(SoundEffect.MAIN_MENU,SoundCategory.PLAYERS);
                    break;
                case "1":
                    Minecraft.getMinecraft().getSoundHandler().stopSounds();
                    Event.getInstance().changeBgm(1);
                    return;
                case "2":
                    Minecraft.getMinecraft().getSoundHandler().stopSounds();
                    Event.getInstance().changeBgm(2);
                    return;
                case "3":
                    Minecraft.getMinecraft().getSoundHandler().stopSounds();
                    Event.getInstance().changeBgm(3);
                    return;
                case "4":
                    Minecraft.getMinecraft().getSoundHandler().stopSounds();
                    Event.getInstance().changeBgm(4);
                    return;
            }
            mc.getSoundHandler().playSound(sound);
        }
        else if (recognitionCode.equals("cuerset")){
            new ResetConfig();
            return;
        }
        else if (recognitionCode.equals("pwdrset")){
            new ResetPWConfig();
            return;
        }
        else if (recognitionCode.equals("allrset")){
            new ResetPWConfig();
            new ResetConfig();
            return;
        }
        else if (recognitionCode.equals("lockdor")){
            if (message.data.length()>=7) {
                t = Integer.parseInt(message.data.substring(7));
            }
            if ("".equals(String.valueOf(t))) return;
            switch (t) {
                case 0:
                    door0 = new A0Birthd7297();
                    if (!GetOneValue.GetOneValue("DOOR", door0.getPassward())) {
                        mc.displayGuiScreen(door0);
                        return;
                    }
                    if (door0!=null) door0.onReward();
                    return;
                case 1:
                    door1 = new A1Birth3castle();
                    if (!GetOneValue.GetOneValue("DOOR", door1.getPassward())) {
                        mc.displayGuiScreen(door1);
                        return;
                    }
                    if (door1!=null) door1.onReward();
                    return;
                case 2:
                    door2 = new A2BirthDaju();
                    if (!GetOneValue.GetOneValue("DOOR",door2.getPassward())) {
                        mc.displayGuiScreen(door2);
                        return;
                    }
                    if (door2!=null) door2.onReward();
                    return;
                case 3:
                    door3 = new A3BirthHanseol();
                    if (!GetOneValue.GetOneValue("DOOR",door3.getPassward())) {
                        mc.displayGuiScreen(door3);
                        return;
                    }
                    if (door3!=null) door3.onReward();
                    return;
                case 4:
                    door4 = new A4MedicineCleanRoom();
                    if (!GetOneValue.GetOneValue("DOOR",door4.getPassward())) {
                        mc.displayGuiScreen(door4);
                        return;
                    }
                    if (door4!=null) door4.onReward();
                    return;
                case 5:
                    door5 = new A5RestRoomDoorLock();
                    if (!GetOneValue.GetOneValue("DOOR",door5.getPassward())) {
                        mc.displayGuiScreen(door5);
                        return;
                    }
                    if (door5!=null) door5.onReward();
                    return;
                case 6:
                    door6 = new A6ExperimentDoorlock();
                    if (!GetOneValue.GetOneValue("DOOR",door6.getPassward())) {
                        mc.displayGuiScreen(door6);
                        return;
                    }
                    if (door6!=null) door6.onReward();
                    return;

                case 7:
                    door7 = new A7ControlRoom();
                    if (!GetOneValue.GetOneValue("DOOR",door7.getPassward())) {
                        mc.displayGuiScreen(door7);
                        return;
                    }
                    if (door7!=null) door7.onReward();
                    return;
                case 8:
                    door8 = new A8OfficeRoom();
                    if (!GetOneValue.GetOneValue("DOOR", door8.getPassward())) {
                        mc.displayGuiScreen(door8);
                        return;
                    }
                    if (door8!=null) door8.onReward();
                    return;
                case 9:
                    door9 = new A9MedicineExperimentRoomDoorLock();
                    if (!GetOneValue.GetOneValue("DOOR", door9.getPassward())) {
                        mc.displayGuiScreen(door9);
                        return;
                    }
                    if (door9!=null) door9.onReward();
                    return;
                case 10:
                    door10 = new A10CleanRoom();
                    if (!GetOneValue.GetOneValue("DOOR", door10.getPassward())) {
                        mc.displayGuiScreen(door10);
                        return;
                    }
                    if (door10!=null) door10.onReward();
                    return;
                case 11:
                    door11 = new A11RepresentativeRoom();
                    if (!GetOneValue.GetOneValue("DOOR", door11.getPassward())) {
                        mc.displayGuiScreen(door11);
                        return;
                    }
                    if (door11!=null) door11.onReward();
                    return;
                case 12:
                    door12 = new A12RepresentSpecificInfoRoom();
                    if (!GetOneValue.GetOneValue("DOOR", door12.getPassward())) {
                        mc.displayGuiScreen(door12);
                        return;
                    }
                    if (door12!=null) door12.onReward();
                    return;
                case 13:
                    door13 = new A13Experiment3RoomBetweenControl3Room();
                    if (!GetOneValue.GetOneValue("DOOR", door13.getPassward())) {
                        mc.displayGuiScreen(door13);
                        return;
                    }
                    if (door13!=null) door13.onReward();
                    return;
                default:
                    mc.displayGuiScreen(new DoorLock(defaultPw));

            }
            return;
        }
        else if (recognitionCode.equals("lockdir")){
            if (message.data.length()>=7) {
                t = Integer.parseInt(message.data.substring(7));
            }
            if ("".equals(String.valueOf(t))) return;
            switch (t){
                case 0:
                    direction0 = new A0MedicinePoster();
                    if (!GetOneValue.GetOneValue("DIRE",direction0.getPassward())) {
                        mc.displayGuiScreen(direction0);
                        return;
                    }
                    if (direction0 != null) direction0.onReward();

                    return;
                case 1:
                    direction1 = new A1MedicineMakeRoom();
                    if (!GetOneValue.GetOneValue("DIRE",direction1.getPassward())) {
                        mc.displayGuiScreen(direction1);
                        return;
                    }
                    if (direction1!=null) direction1.onReward();
                    return;
                case 2:
                    direction2 = new A2ThirdExperimentRoom();
                    if (!GetOneValue.GetOneValue("DIRE",direction2.getPassward())) {
                        mc.displayGuiScreen(direction2);
                        return;
                    }
                    if (direction2!=null) direction2.onReward();
                    return;
                case 3:
                    direction3 = new A3EscapeDoor();
                    if (!GetOneValue.GetOneValue("DIRE",direction3.getPassward())) {
                        mc.displayGuiScreen(direction3);
                        return;
                    }
                    if (direction3!=null) direction3.onReward();
                    return;
                default:
                    mc.displayGuiScreen(new DirectionLock(DirectionLock.COORD.EAST.label+DirectionLock.COORD.WEST.label+DirectionLock.COORD.SOUTH.label+DirectionLock.COORD.NORTH.label));
            }
            return;
        }
        else if (recognitionCode.equals("lockspi")){
            if (message.data.length()>=7) {
                t = Integer.parseInt(message.data.substring(7));
            }
            if ("".equals(String.valueOf(t))) return;
            switch (t){
                case 0:
                    spin0 = new A0SecretRoom();
                    if (!GetOneValue.GetOneValue("SPIN",spin0.getPassward())) {
                        mc.displayGuiScreen(spin0);
                        return;
                    }
                    if (spin0!=null) spin0.onReward();
                    return;
                case 1:
                    spin1 = new A1SpinRestRoomKey();
                    if (!GetOneValue.GetOneValue("SPIN",spin1.getPassward())) {
                        mc.displayGuiScreen(spin1);
                        return;
                    }
                    if (spin1!=null) spin1.onReward();
                    return;
                case 2:
                    spin2 = new A2ExperienceRoom2();
                    if (!GetOneValue.GetOneValue("SPIN",spin2.getPassward())) {
                        mc.displayGuiScreen(spin2);
                        return;
                    }
                    if (spin2!=null) spin2.onReward();
                    return;
                case 3:
                    spin3 = new A3ExperienceRoom3();
                    if (!GetOneValue.GetOneValue("SPIN",spin3.getPassward())) {
                        mc.displayGuiScreen(spin3);
                        return;
                    }
                    if (spin3!=null) spin3.onReward();
                    return;
                default:
                    mc.displayGuiScreen(new SpinLock(defaultPw));
            }
            return;
        }
        else if (recognitionCode.equals("unlkdor")){
            SetValue setValue = new SetValue();
            if (message.data.length()>=7) {
                t = Integer.parseInt(message.data.substring(7));
            }
            if ("".equals(String.valueOf(t))) return;
            setValue.SetValue("DOOR_"+getDoorLockPassward(t),true);
        }
        else if (recognitionCode.equals("unlkdir")){
            SetValue setValue = new SetValue();
            if (message.data.length()>=7) {
                t = Integer.parseInt(message.data.substring(7));
            }
            if ("".equals(String.valueOf(t))) return;
            setValue.SetValue("DIRE_"+getDirectionLockPassward(t),true);
        }
        else if (recognitionCode.equals("unlkspi")){
            SetValue setValue = new SetValue();
            if (message.data.length()>=7) {
                t = Integer.parseInt(message.data.substring(7));
            }
            if ("".equals(String.valueOf(t))) return;
            setValue.SetValue("SPIN_"+getSpinLockPassward(t),true);
        }
        else if (recognitionCode.equals("stlkdor")){
            SetValue setValue = new SetValue();
            if (message.data.length()>=7) {
                t = Integer.parseInt(message.data.substring(7));
            }
            if ("".equals(String.valueOf(t))) return;
            setValue.SetValue("DOOR_"+getDoorLockPassward(t),message.data.substring(8).equals("T"));
        }
        else if (recognitionCode.equals("stlkdir")){
            SetValue setValue = new SetValue();
            if (message.data.length()>=7) {
                t = Integer.parseInt(message.data.substring(7));
            }
            if ("".equals(String.valueOf(t))) return;
            setValue.SetValue("DIRE_"+getDirectionLockPassward(t),message.data.substring(8).equals("T"));
        }
        else if (recognitionCode.equals("stlkspi")){
            SetValue setValue = new SetValue();
            if (message.data.length()>=7) {
                t = Integer.parseInt(message.data.substring(7));
            }
            if ("".equals(String.valueOf(t))) return;
            setValue.SetValue("SPIN_"+getSpinLockPassward(t),message.data.substring(8).equals("T"));
        }
        else if (recognitionCode.equals("showqui")){
            String k = message.data.substring(7);
            if (k!=null) {
                if (k.equals("0")) {
                    mc.displayGuiScreen(new QuizBarcode(k));
                } else {
                    mc.displayGuiScreen(new ShowQuiz(k));
                }
            }
        }
        else if (recognitionCode.equals("cleanch")){
            mc.ingameGUI.getChatGUI().clearChatMessages(true);
            mc.ingameGUI.getChatGUI().refreshChat();
        }
        return;
    }
    private String getDirectionLockPassward(int id){
        switch (id) {
            case 0:
                if (direction0==null){
                    direction0 = new A0MedicinePoster();
                }
                return direction0.getPassward();
            case 1:
                if (direction1==null){
                    direction1 = new A1MedicineMakeRoom();
                }
                return direction1.getPassward();
            case 2:
                if (direction2==null){
                    direction2 = new A2ThirdExperimentRoom();
                }
                return direction2.getPassward();
            case 3:
                if (direction3==null){
                    direction3 = new A3EscapeDoor();
                }
                return direction3.getPassward();
        }
        return null;
    }
    private String getSpinLockPassward(int id){
        switch (id) {
            case 0:
                if (spin0==null){
                    spin0 = new A0SecretRoom();
                }
                return spin0.getPassward();
            case 1:
                if (spin1==null){
                    spin1 = new A1SpinRestRoomKey();
                }
                return spin1.getPassward();
            case 2:
                if (spin2==null){
                    spin2 = new A2ExperienceRoom2();
                }
                return spin2.getPassward();
            case 3:
                if (spin3==null){
                    spin3 = new A3ExperienceRoom3();
                }
                return spin3.getPassward();
        }
        return null;
    }

    private String getDoorLockPassward(int id){
        switch (id){
            case 0:
                if (door0==null){
                    door0 = new A0Birthd7297();
                }
                return door0.getPassward();
            case 1:
                if (door1==null){
                    door1 = new A1Birth3castle();
                }
                return door1.getPassward();
            case 2:
                if (door2==null){
                    door2 = new A2BirthDaju();
                }
                return door2.getPassward();
            case 3:
                if (door3==null){
                    door3 = new A3BirthHanseol();
                }
                return door3.getPassward();
            case 4:
                if (door4==null){
                    door4 = new A4MedicineCleanRoom();
                }
                return door4.getPassward();
            case 5:
                if (door5==null){
                    door5 = new A5RestRoomDoorLock();
                }
                return door5.getPassward();
            case 6:
                if (door6==null){
                    door6 = new A6ExperimentDoorlock();
                }
                return door6.getPassward();
            case 7:
                if (door7==null){
                    door7 = new A7ControlRoom();
                }
                return door7.getPassward();
            case 8:
                if (door8==null){
                    door8 = new A8OfficeRoom();
                }
                return door8.getPassward();
            case 9:
                if (door9==null){
                    door9 = new A9MedicineExperimentRoomDoorLock();
                }
                return door9.getPassward();
            case 10:
                if (door10==null){
                    door10 = new A10CleanRoom();
                }
                return door10.getPassward();
            case 11:
                if (door11==null){
                    door11 = new A11RepresentativeRoom();
                }
                return door11.getPassward();
            case 12:
                if (door12==null){
                    door12 = new A12RepresentSpecificInfoRoom();
                }
                return door12.getPassward();
            case 13:
                if (door13==null){
                    door13 = new A13Experiment3RoomBetweenControl3Room();
                }
                return door13.getPassward();
        }
        return null;
    }
    public static void openClue(String id,boolean TOrF,String playerName,boolean imageOpened) throws Exception {
        if (TOrF) {
            if (Integer.parseInt(id) > 7) return;
            if (imageOpened) Minecraft.getMinecraft().displayGuiScreen(new ShowClueImage("clue_" + id, null));
            if (!isClueOpened(Integer.parseInt(id))) {
                MinecraftForge.EVENT_BUS.register(new Alarm(playerName));
                Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getRecord(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 0.3f));
                if (!isClueInOpenedList(id)) {
                    com.geullo.cluesharingdevice.config.SetValue.SetValue("LIST_" + Get.GetValues("LIST").size(), "CLUE_" + id);
                    com.geullo.cluesharingdevice.config.SetValue.SetValue("CLUE_" + id, true);
                    Minecraft.getMinecraft().player.sendChatMessage("/csd set true " + id + " false all");
                    if (new BDeviceMainMenu().checkAllFoundCLUE()) {
                        Minecraft.getMinecraft().ingameGUI.addChatMessage(ChatType.CHAT, new TextComponentString("§f§lEND :: §f잠금되어 있던 기능이 해제되었습니다."));
                    }
                }
                return;
            } else {
                if (!isClueInOpenedList(id)) {
                    com.geullo.cluesharingdevice.config.SetValue.SetValue("LIST_" + Get.GetValues("LIST").size(), "CLUE_" + id);
                    com.geullo.cluesharingdevice.config.SetValue.SetValue("CLUE_" + id, true);
                    if (new BDeviceMainMenu().checkAllFoundCLUE()) {
                        Minecraft.getMinecraft().ingameGUI.addChatMessage(ChatType.CHAT, new TextComponentString("§f§lEND :: §f잠금되어 있던 기능이 해제되었습니다."));
                    }
                }
            }
        }else{
            if (listFilterClean(id)){
                throw new Exception("List Clean Error - 리스트를 정리하던중 오류가 발생했습니다.");
            }
            com.geullo.cluesharingdevice.config.SetValue.SetValue("CLUE_" + id, false);
        }
    }
    private static boolean isClueOpened(int id) throws IOException {
        return com.geullo.cluesharingdevice.config.GetOneValue.GetOneValue("CLUE", id);
    }
    private static boolean listFilterClean(String id){
        try {
            List<String> list = new ArrayList<>();
            Get.GetValues("LIST").values()
                    .stream()
                    .filter(l -> !l.equals("NULL") && !l.equals("CLUE_" + id))
                    .forEach(list::add);
            for (int i = 0; i < 8; i++) {
                com.geullo.cluesharingdevice.config.SetValue.SetValue("LIST_" + i, "NULL");
            }
            list.forEach(k -> {
                try {
                    com.geullo.cluesharingdevice.config.SetValue.SetValue("LIST_" + list.indexOf(k), k);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            return true;
        }catch (Exception e){
            return false;
        }
    }
    private static boolean isClueInOpenedList(String id) throws IOException {
        return Get.GetValues("LIST").containsValue("CLUE_"+id);
    }
}