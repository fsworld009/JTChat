/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtchat.irc;

/**
 *
 * @author leeyc
 */
public interface ChatMsgListener {
    public void onChatMsg(String channel, String sender, String message);
    public void onChatAction(String channel, String sender, String action);
    public void onPrivateMsg(String sender, String message);
}
