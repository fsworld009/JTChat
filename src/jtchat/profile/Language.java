
package jtchat.profile;

import java.util.HashMap;
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
    
    public void load(String langCode){
        //apply change
        for(int ix=0;ix<languageChangeListener.size();ix++){
            languageChangeListener.get(ix).languageChange();
        }
    }
    
    public void registerLogListener(LanguageChangeListener listener){
        languageChangeListener.add(listener);
    }
    
    public void removeLogListener(LanguageChangeListener listener){
        languageChangeListener.remove(listener);
    }
}
