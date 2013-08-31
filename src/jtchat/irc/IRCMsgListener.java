package jtchat.irc;


public interface IRCMsgListener {
    public void onChatMsg(String channel, String nickname, boolean isOp, String message);
    public void onChatAction(String channel, String nickname, String action);
    public void onPrivateMsg(String nickname, String message);
    public void onSysMsg(String message);
    //probably makes more sense if this moves to another listener
}
