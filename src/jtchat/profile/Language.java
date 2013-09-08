
package jtchat.profile;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

/*   String Table:
 * MainButConnect
 * MainButDisconnect
 * MainButSetting
 * SetWinTitle
 * SetButApply
 * SetButSave
 * SetButLoad
 * IRCSetTabName
 * IRCSetServer
 * IRCSetPort
 * IRCSetPass
 * IRCSetNickname
 * IRCSetChannel
 * ChatSetTabName
 * ChatSetSize
 * ChatSetPos
 * ChatSetBorderThick
 * ChatSetBgColor
 * ChatSetMaxLine
 * ChatSetTextColor
 * ChatSetTextFont
 * ChatSetNickColor
 * ChatSetNickFont
 * ChatSetSysColor
 * ChatSetSysFont
 * ChatSetAlwaysOnTop
 * ChatSetUseJtvColor
 * ChatSetChatClear
 * ChatSetChatClearBannedUser
 * ProfileSetTabName
 * ProfileSetLanguage
 * ProfileSetNote
 * LogSetTabName
 * LogSetButClear
 * AboutTabName
 *   
 */


public class Language {
    private static Language ins=null;
    private HashMap<String,String> translate;
    private Vector<LanguageChangeListener> languageChangeListener;
    
    public Language(){
        translate = new HashMap<String,String>();
        languageChangeListener = new Vector<LanguageChangeListener>();
    }
    
    public static Language ins(){
        if(ins==null){
            ins = new Language();
        }
        return ins;
    }
    
    public String get(String entryName){
        String value = translate.get(entryName);
        return value==null?"":value;
    }
    
    public boolean load(String langCode){
        //apply change
        Scanner sc=null;
        try{
            sc = new Scanner(new File("./language/"+langCode+".ini"));
        }catch(FileNotFoundException e){
            System.err.printf("file not found\n");
            return false;
        }
        String line;

        while(sc.hasNext()){
            line = sc.nextLine();
            String[] split = line.split("=",2);
            translate.put(split[0], split[1]);
        }
        sc.close();
        
        for(int ix=0;ix<languageChangeListener.size();ix++){
            languageChangeListener.get(ix).languageChange();
        }
        return true;
    }
    
    public void registerLogListener(LanguageChangeListener listener){
        languageChangeListener.add(listener);
    }
    
    public void removeLogListener(LanguageChangeListener listener){
        languageChangeListener.remove(listener);
    }
}
