
package jtchat.profile;

//singleton data structure

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Profile {
    private static Profile ins = null;
    //private HashMap<String,String> settingMap;
    
    //IRC
    public String IRCserver = "";
    public int IRCport = 443;
    public String IRCnickname = "";
    public String IRCservpass="";
    public String IRCchannel="#";
    
    //Chatroom
    public int ChatWidth = 240;
    public int ChatHeight = 360;
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
        Scanner sc=null;
        try{
            sc = new Scanner(profile);
        }catch(FileNotFoundException e){
            System.err.printf("file not found\n");
            return false;
        }
        String line;

        while(sc.hasNext()){
            line = sc.nextLine();
            String[] split = line.split("=",2);
            if(split[0].equals("IRCserver")){
                Profile.ins().IRCserver = split[1];
            }else if(split[0].equals("IRCport")){
                Profile.ins().IRCport = Integer.parseInt(split[1]);
            }else if(split[0].equals("IRCnickname")){
                Profile.ins().IRCnickname = split[1];
            }else if(split[0].equals("IRCchannel")){
                Profile.ins().IRCchannel = split[1];
            }else if(split[0].equals("ChatWidth")){
                Profile.ins().ChatWidth = Integer.parseInt(split[1]);
            }else if(split[0].equals("ChatHeight")){
                Profile.ins().ChatHeight = Integer.parseInt(split[1]);
            }else if(split[0].equals("ChatNumOfLines")){
                Profile.ins().ChatNumOfLines = Integer.parseInt(split[1]);
            }else if(split[0].equals("ChatBgColor")){
                Profile.ins().ChatBgColor = Color.decode(split[1]);
            }else if(split[0].equals("ChatTextColor")){
                Profile.ins().ChatTextColor = Color.decode(split[1]);
            }else if(split[0].equals("ChatTextFont")){
                String[] fontInfo = split[1].split("\\|",2);
                Profile.ins().ChatTextFont = Font.decode(fontInfo[0]+" PLAIN "+fontInfo[1]);
            }else if(split[0].equals("ChatNickColor")){
                Profile.ins().ChatNickColor = Color.decode(split[1]);
            }else if(split[0].equals("ChatNickFont")){
                String[] fontInfo = split[1].split("\\|",2);
                Profile.ins().ChatNickFont = Font.decode(fontInfo[0]+" PLAIN "+fontInfo[1]);
            }else if(split[0].equals("ChatSysColor")){
                Profile.ins().ChatSysColor = Color.decode(split[1]);
            }else if(split[0].equals("ChatSysFont")){
                String[] fontInfo = split[1].split("\\|",2);
                Profile.ins().ChatSysFont = Font.decode(fontInfo[0]+" PLAIN "+fontInfo[1]);
            }else if(split[0].equals("ChatAlwaysOnTop")){
                Profile.ins().ChatAlwaysOnTop = Boolean.parseBoolean(split[1]);
            }else if(split[0].equals("ChatUseTiwtchColor")){
                Profile.ins().ChatUseTiwtchColor = Boolean.parseBoolean(split[1]);
            }
        }
        sc.close();
        return true;
    }
    
    public static String colorToHexString(Color color){
        return String.format("#%06x",color.getRGB() & 0x00FFFFFF).toUpperCase();
    }
    
    public static String fontToString(Font font){
        return font.getFontName() + "|" + String.format("%d",font.getSize());
    }
    
    public static boolean saveProfile(File profile){
        FileWriter fstream = null;
        try{
            fstream = new FileWriter(profile);
        }catch(java.io.FileNotFoundException e){
            //cannot create file or don't have permission to write
            System.err.println("Error: cannot create output file or don't have permission to write");
            return false;
        } catch (IOException ex) {
            System.err.println("Error: IO error");
            return false;
        }
        
        BufferedWriter fout = new BufferedWriter(fstream);
        try {
            fout.write("IRCserver="+Profile.ins().IRCserver+"\r\n");
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
            fout.write("ChatAlwaysOnTop="+Profile.ins().ChatAlwaysOnTop+"\r\n");
            fout.write("ChatUseTiwtchColor="+Profile.ins().ChatUseTiwtchColor+"\r\n");
            fout.close();
        } catch (IOException ex) {
            System.err.println("Error: IO error");
            return false;
        }
        return true;
    }

    
}
