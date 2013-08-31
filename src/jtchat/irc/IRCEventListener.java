package jtchat.irc;


public interface IRCEventListener {
    public void onConnectSuccess();
    public void onLoginSuccess();
    public void onLoginFailed();
}
