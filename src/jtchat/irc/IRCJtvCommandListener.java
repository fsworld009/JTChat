
package jtchat.irc;


public interface IRCJtvCommandListener {
    public void clearChat();
    
    public void clearMsgsFromBannedUser(String username);
}
