
package jtchat.gui;

//singleton data structure
public class SettingTable {
    private static SettingTable ins = null;
    //private HashMap<String,String> settingMap;
    public String IRCserver = "";
    public int IRCport = 443;
    public String IRCnickname = "";
    public String IRCservpass="";
    public String IRCchannel="";
       
    public static SettingTable ins(){
        if(ins == null){
            ins = new SettingTable();
        }
        return ins;
    }


    
}
