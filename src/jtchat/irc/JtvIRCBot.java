
package jtchat.irc;

import java.util.Vector;


public class JtvIRCBot extends IRCBot{
    Vector<ChatLogListener> logListeners;
    Vector<ChatMsgListener> msgListeners;
    
    public JtvIRCBot(){
        super();
        logListeners = new Vector<ChatLogListener>();
        msgListeners = new Vector<ChatMsgListener>();
    }
    
    public void registerLogListener(ChatLogListener listener){
        logListeners.add(listener);
    }
    
    public void removeLogListener(ChatLogListener listener){
        logListeners.remove(listener);
    }
    
    public void registerMsgListener(ChatMsgListener listener){
        msgListeners.add(listener);
    }
    
    public void removeMsgListener(ChatMsgListener listener){
        msgListeners.remove(listener);
    }
    
    public void onLog(String log){
        for(int i=0;i<msgListeners.size();i++){
            logListeners.get(i).onLog(log);
        }
    }
    
    private String getTwitchId(String sender){
        if(sender.contains("!")){
            return sender.substring(0,sender.indexOf('!'));
        }else{
            return sender;
        }
    }
    
    public void onChatMsg(String channel, String sender, String message){
        //parse twitch username from twitch chats
        //ex: user!user@user.tmi.twitch.tv -> user
        
        System.out.printf("get %s %s : %s\n",channel,getTwitchId(sender),message);
        for(int i=0;i<msgListeners.size();i++){
            msgListeners.get(i).onChatMsg(channel, sender, message);
        }
    }
    
    public void onChatAction(String channel, String action){
        for(int i=0;i<msgListeners.size();i++){
            msgListeners.get(i).onChatAction(channel, action);
        }
    }
    
    public void onPrivateMsg(String sender, String message){
        for(int i=0;i<msgListeners.size();i++){
            msgListeners.get(i).onPrivateMsg(sender, message);
        }
    }
}
