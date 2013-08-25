package jtchat.irc;

import java.util.HashMap;
import java.awt.Color;

public class UserColorMapper{
    private static UserColorMapper ins;
    private HashMap<String,Color> map = new HashMap<String,Color>();
    private HashMap<String,String> colorStringToCode;
    public static UserColorMapper ins(){
        if(ins== null){
            ins = new UserColorMapper();
        }
        return ins;
    }
    
    public Color getColor(String user){
        return map.get(user.toLowerCase());
    }
    
    //colorStringToCode: map jtv color string to color code
    private void initColorStringMapper(){
        colorStringToCode = new HashMap<String,String>();
        colorStringToCode.put("red","#FF0000");
        colorStringToCode.put("blue","#0000FF");
        colorStringToCode.put("green","#008000");
        colorStringToCode.put("firebrick","#B22222");
        colorStringToCode.put("coral","#FF7F50");
        colorStringToCode.put("yellowgreen","#9ACD32");
        colorStringToCode.put("orangered","#FF4500");
        colorStringToCode.put("seagreen","#2E8B57");
        colorStringToCode.put("goldenrod","#D2691E");
        colorStringToCode.put("cadetblue","#5F9EA0");
        colorStringToCode.put("dodgerblue","#1E90FF");
        colorStringToCode.put("hotpink","#FF69B4");
        colorStringToCode.put("blueviolet","#8A2BE2");
        colorStringToCode.put("springgreen","#00FF7F");
        colorStringToCode.put("black","#000000");
        colorStringToCode.put("gray","#808080");
        colorStringToCode.put("darkred","#8B0000");
        colorStringToCode.put("midnightblue","#191970");
        colorStringToCode.put("deeppink","#FF1493");
    }
    
    public boolean setColor(String user, String color){
        //System.out.println(color);
        if(color.matches("^#[0-9a-fA-F]{6}$")){
            map.put(user.toLowerCase(),Color.decode(color));
            //System.out.printf("Saved %s %s",user,map.get(user));
            return true;
        }else{
            //handling jtv color string
            if(colorStringToCode==null){
                initColorStringMapper();
            }
            map.put(user.toLowerCase(),Color.decode(colorStringToCode.get(color.toLowerCase())));
            return true;
        }
    }
    
}