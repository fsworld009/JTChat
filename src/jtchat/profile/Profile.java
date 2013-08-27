
package jtchat.profile;

//singleton data structure

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Profile {
    private static Profile ins = null;
    //private HashMap<String,String> settingMap;
    
    //IRC
    public String IRCserver = "irc.twitch.tv";
    public int IRCport = 443;
    public String IRCnickname = "world9918";
    public String IRCservpass="";
    public String IRCchannel="#world9918";
    
    //Chatroom
    public int ChatWidth = 160;
    public int ChatHeight = 330;
    public Color ChatBgColor = Color.decode("#000000");
    public int ChatNumOfLines = 20;
    public Color ChatTextColor = Color.decode("#FFFFFF");
    public Font ChatTextFont = new Font("Arial Unicode MS",Font.PLAIN,12);
    public Color ChatNickColor = Color.decode("#FFFF00");
    public Font ChatNickFont = new Font("Arial",Font.PLAIN,12);
    public boolean ChatAlwaysOnTop = false;
    public boolean ChatUseTiwtchColor = true;
    public Color ChatSysColor = Color.decode("#FF9999");
    public Font ChatSysFont = new Font("Arial",Font.PLAIN,12);
    

       
    public static Profile ins(){
        if(ins == null){
            ins = new Profile();
        }
        return ins;
    }
    
    

    public static boolean loadProfile(File profile){
        return true;
    }
    
    public static String colorToHexString(Color color){
        return String.format("#%06x",color.getRGB() & 0x00FFFFFF).toUpperCase();
    }
    
    public static String fontToString(Font font){
        return font.getFontName() + "|" + String.format("%d",font.getSize());
    }
    
    public static void saveProfile(File profile){
        FileWriter fstream = null;
        try{
            fstream = new FileWriter(profile);
        }catch(java.io.FileNotFoundException e){
            //cannot create file or don't have permission to write
            System.err.println("Error: cannot create output file or don't have permission to write");
        } catch (IOException ex) {
            System.err.println("Error: IO error");
        }
        
        BufferedWriter fout = new BufferedWriter(fstream);
        try {
            fout.write("IRCServer="+Profile.ins().IRCserver+"\r\n");
            fout.write("IRCport="+Profile.ins().IRCport+"\r\n");
            fout.write("IRCnickname="+Profile.ins().IRCnickname+"\r\n");
            fout.write("IRCchannel="+Profile.ins().IRCchannel+"\r\n");
            fout.write("ChatWidth="+Profile.ins().ChatWidth+"\r\n");
            fout.write("ChatHeight="+Profile.ins().ChatHeight+"\r\n");
            fout.write("ChatBgColor="+colorToHexString(Profile.ins().ChatBgColor)+"\r\n");
            fout.write("ChatNumOfLines="+Profile.ins().ChatNumOfLines+"\r\n");
            fout.write("ChatTextColor="+colorToHexString(Profile.ins().ChatTextColor)+"\r\n");
            fout.write("ChatTextFont="+fontToString(Profile.ins().ChatTextFont)+"\r\n");
            fout.write("ChatNickColor="+colorToHexString(Profile.ins().ChatNickColor)+"\r\n");
            fout.write("ChatNickFont="+fontToString(Profile.ins().ChatNickFont)+"\r\n");
            fout.write("ChatSysColor="+colorToHexString(Profile.ins().ChatSysColor)+"\r\n");
            fout.write("ChatSysFont="+fontToString(Profile.ins().ChatSysFont)+"\r\n");
            fout.close();
        } catch (IOException ex) {
            System.err.println("Error: IO error");
        }
    }

    
}
