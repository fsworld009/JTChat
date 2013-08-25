
package jtchat.gui;

//singleton data structure

import java.awt.Color;
import java.awt.Font;

public class SettingTable {
    private static SettingTable ins = null;
    //private HashMap<String,String> settingMap;
    
    //IRC
    public String IRCserver = "";
    public int IRCport = 443;
    public String IRCnickname = "";
    public String IRCservpass="";
    public String IRCchannel="";
    
    //Chatroom
    public int ChatWidth = 300;
    public int ChatHeight = 550;
    public Color ChatBgColor = Color.decode("#000000");
    public int ChatNumOfLines = 20;
    public Color ChatTextColor = Color.decode("#FFFFFF");
    public Font ChatTextFont = new Font("Arial Unicode MS",Font.PLAIN,9);
    public Color ChatNickColor = Color.decode("#FFFF00");
    public Font ChatNickFont = new Font("MS Gothic",Font.PLAIN,9);
    public boolean ChatUseTiwtchColor = true;

       
    public static SettingTable ins(){
        if(ins == null){
            ins = new SettingTable();
        }
        return ins;
    }


    
}
