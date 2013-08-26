package jtchat.irc;


public interface ChatMsgListener {
    public void onChatMsg(String channel, String nickname, boolean isOp, String message);
    public void onChatAction(String channel, String nickname, String action);
    public void onPrivateMsg(String nickname, String message);
    public void onSysMsg(String message);
    //probably makes more sense if this moves to another listener
    public void onConnectSuccess();
    public void onLoginFailed();
}
