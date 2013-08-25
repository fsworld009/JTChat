
package jtchat.gui;

//singleton data structure

import java.awt.Color;
import java.awt.Font;

public class SettingTable {
    private static SettingTable ins = null;
    //private HashMap<String,String> settingMap;
    
    //IRC
    public String IRCserver = "irc.twitch.tv";
    public int IRCport = 443;
    public String IRCnickname = "world9918";
    public String IRCservpass="";
    public String IRCchannel="#world9918";
    
    //Chatroom
    public int ChatWidth = 350;
    public int ChatHeight = 550;
    public Color ChatBgColor = Color.decode("#000000");
    public int ChatNumOfLines = 20;
    public Color ChatTextColor = Color.decode("#FFFFFF");
    public Font ChatTextFont = new Font("Arial Unicode MS",Font.PLAIN,14);
    public Color ChatNickColor = Color.decode("#FFFF00");
    public Font ChatNickFont = new Font("MS Gothic",Font.PLAIN,14);
    public boolean ChatAlwaysOnTop = false;
    public boolean ChatUseTiwtchColor = true;
    public Color ChatSysColor = Color.decode("#FFCCCC");
    public Font ChatSysFont = new Font("Arial",Font.PLAIN,14);
    

       
    public static SettingTable ins(){
        if(ins == null){
            ins = new SettingTable();
        }
        return ins;
    }


    
}
