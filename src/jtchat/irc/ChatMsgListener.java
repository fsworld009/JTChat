package jtchat.irc;


public interface ChatMsgListener {
    public void onChatMsg(String channel, String sender, String message);
    public void onChatAction(String channel, String sender, String action);
    public void onPrivateMsg(String sender, String message);
    public void onSysMsg(String message);
    //probably makes more sense if this moves to another listener
    public void onReconnect();
}
