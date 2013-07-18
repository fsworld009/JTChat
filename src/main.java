
import jtchat.IRCBot;




/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author leeyc
 */
public class main {
    public static void main(String[] args){
        IRCBot ircbot = new IRCBot();
        ircbot.connect("irc.twitch.tv", 443, "megasonimon", "", "");
        ircbot.join("#append");
        ircbot.loop();
    }
}
