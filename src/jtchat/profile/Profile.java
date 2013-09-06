
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
    public int ChatPosX = 10;
    public int ChatPosY = 10;
    public int ChatBorderThickness = 5;
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
    
    public static String colorToHexString(Color color){
        return String.format("#%06x",color.getRGB() & 0x00FFFFFF).toUpperCase();
    }
    
    public static String fontToString(Font font){
        return font.getFontName() + "|" + String.format("%d",font.getSize());
    } 

    public boolean loadProfile(File profile){
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
                this.IRCserver = split[1];
            }else if(split[0].equals("IRCport")){
                this.IRCport = Integer.parseInt(split[1]);
            }else if(split[0].equals("IRCnickname")){
                this.IRCnickname = split[1];
            }else if(split[0].equals("IRCchannel")){
                this.IRCchannel = split[1];
            }else if(split[0].equals("ChatWidth")){
                this.ChatWidth = Integer.parseInt(split[1]);
            }else if(split[0].equals("ChatHeight")){
                this.ChatHeight = Integer.parseInt(split[1]);
            }else if(split[0].equals("ChatPosX")){
                this.ChatPosX = Integer.parseInt(split[1]);
            }else if(split[0].equals("ChatPosY")){
                this.ChatPosY = Integer.parseInt(split[1]);
            }else if(split[0].equals("ChatBorderThickness")){
                this.ChatBorderThickness = Integer.parseInt(split[1]);
            }else if(split[0].equals("ChatNumOfLines")){
                this.ChatNumOfLines = Integer.parseInt(split[1]);
            }else if(split[0].equals("ChatBgColor")){
                this.ChatBgColor = Color.decode(split[1]);
            }else if(split[0].equals("ChatTextColor")){
                this.ChatTextColor = Color.decode(split[1]);
            }else if(split[0].equals("ChatTextFont")){
                String[] fontInfo = split[1].split("\\|",2);
                this.ChatTextFont = Font.decode(fontInfo[0]+" PLAIN "+fontInfo[1]);
            }else if(split[0].equals("ChatNickColor")){
                this.ChatNickColor = Color.decode(split[1]);
            }else if(split[0].equals("ChatNickFont")){
                String[] fontInfo = split[1].split("\\|",2);
                this.ChatNickFont = Font.decode(fontInfo[0]+" PLAIN "+fontInfo[1]);
            }else if(split[0].equals("ChatSysColor")){
                this.ChatSysColor = Color.decode(split[1]);
            }else if(split[0].equals("ChatSysFont")){
                String[] fontInfo = split[1].split("\\|",2);
                this.ChatSysFont = Font.decode(fontInfo[0]+" PLAIN "+fontInfo[1]);
            }else if(split[0].equals("ChatAlwaysOnTop")){
                this.ChatAlwaysOnTop = Boolean.parseBoolean(split[1]);
            }else if(split[0].equals("ChatUseTiwtchColor")){
                this.ChatUseTiwtchColor = Boolean.parseBoolean(split[1]);
            }
        }
        sc.close();
        return true;
    }
    

    
    public boolean saveProfile(File profile){
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
            fout.write("IRCserver="+this.IRCserver+"\r\n");
            fout.write("IRCport="+this.IRCport+"\r\n");
            fout.write("IRCnickname="+this.IRCnickname+"\r\n");
            fout.write("IRCchannel="+this.IRCchannel+"\r\n");
            fout.write("ChatWidth="+this.ChatWidth+"\r\n");
            fout.write("ChatHeight="+this.ChatHeight+"\r\n");
            fout.write("ChatPosX="+this.ChatPosX+"\r\n");
            fout.write("ChatPosY="+this.ChatPosY+"\r\n");
            fout.write("ChatBorderThickness="+this.ChatBorderThickness+"\r\n");
            fout.write("ChatBgColor="+colorToHexString(this.ChatBgColor)+"\r\n");
            fout.write("ChatNumOfLines="+this.ChatNumOfLines+"\r\n");
            fout.write("ChatTextColor="+colorToHexString(this.ChatTextColor)+"\r\n");
            fout.write("ChatTextFont="+fontToString(this.ChatTextFont)+"\r\n");
            fout.write("ChatNickColor="+colorToHexString(this.ChatNickColor)+"\r\n");
            fout.write("ChatNickFont="+fontToString(this.ChatNickFont)+"\r\n");
            fout.write("ChatSysColor="+colorToHexString(this.ChatSysColor)+"\r\n");
            fout.write("ChatSysFont="+fontToString(this.ChatSysFont)+"\r\n");
            fout.write("ChatAlwaysOnTop="+this.ChatAlwaysOnTop+"\r\n");
            fout.write("ChatUseTiwtchColor="+this.ChatUseTiwtchColor+"\r\n");
            fout.close();
        } catch (IOException ex) {
            System.err.println("Error: IO error");
            return false;
        }
        return true;
    }

    
}
