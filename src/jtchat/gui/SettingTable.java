
package jtchat.gui;
import java.util.HashMap;

//singleton
public class SettingTable {
    private static SettingTable ins = null;
    private HashMap<String,String> settingMap;
    
    public SettingTable(){
        settingMap.put("IRCserver","irc.twitch.tv");
        settingMap.put("IRCport","443");
        settingMap.put("IRCnickname","world9918");
        settingMap.put("IRCchannel","#world9918");
    }
    
    public static SettingTable ins(){
        if(ins == null){
            ins = new SettingTable();
        }
        return ins;
    }
    
    public String read(String option){
        return settingMap.get(option);
    }
    
    public void put(String option, String setting){
        settingMap.put(option, setting);
    }
    
}
