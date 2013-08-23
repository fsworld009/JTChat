
package jtchat.gui;
import java.util.HashMap;

//singleton
public class SettingTable {
    private static SettingTable ins;
    private HashMap<String,String> settingMap;
    
    public SettingTable(){
        
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
