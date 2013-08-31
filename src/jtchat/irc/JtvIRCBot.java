
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
        for(int i=0;i<logListeners.size();i++){
            logListeners.get(i).onLog(log);
        }
    }
    
    /*private String getTwitchId(String sender){
        //parse twitch username from twitch chats
        //ex: user!user@user.tmi.twitch.tv -> user
        if(sender.contains("!")){
            return sender.substring(0,sender.indexOf('!'));
        }else{
            return sender;
        }
    }*/
    
    public void onChatMsg(String channel, String nickname, boolean isOp, String message){
        //sender = getTwitchId(sender);
        for(int i=0;i<msgListeners.size();i++){
            msgListeners.get(i).onChatMsg(channel, nickname, false, message);
        }
    }
    
    public void onChatAction(String channel, String nickname, String action){
        for(int i=0;i<msgListeners.size();i++){
            msgListeners.get(i).onChatAction(channel, nickname, action);
        }
    }
    
    public void onPrivateMsg(String nickname, String message){
        //sender = getTwitchId(sender);
        if(nickname.equals("jtv")){
            if(message.matches("USERCOLOR .*")){
                String[] parse = message.split(" ",3);
                UserColorMapper.ins().setColor(parse[1], parse[2]);
            }else if(message.matches("CLEARCHAT .*")){
                String[] parse = message.split(" ",2);
                this.log(String.format("%s has been banned / timeoutted",parse[1]),IRCBot.LogType.SYS);
            }
        }
        
        for(int i=0;i<msgListeners.size();i++){
            msgListeners.get(i).onPrivateMsg(nickname, message);
        }
    }
    
    public void onAccidentDisconnection(){
        //auto reconnect
        reconnect();
    }
    
    public void onSysMsg(String message){
        for(int i=0;i<msgListeners.size();i++){
            msgListeners.get(i).onSysMsg(message);
        }
    }
    
    public void onConnectSuccess(){
        for(int i=0;i<msgListeners.size();i++){
            msgListeners.get(i).onConnectSuccess();
        }
    }
    
    public void onLoginSuccess(){
        for(int i=0;i<msgListeners.size();i++){
            msgListeners.get(i).onLoginSuccess();
        }
    }
    
    public void onLoginFailed(){
        for(int i=0;i<msgListeners.size();i++){
            msgListeners.get(i).onLoginFailed();
        }
    }
}
