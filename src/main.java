
import java.util.Scanner;
import javax.swing.SwingUtilities;
import jtchat.irc.IRCBot;
import jtchat.gui.Chatroom;


public class main {
    public static String password = "";
    public static void main(String[] args){
        //enter password
        Scanner sc = new Scanner(System.in);
        System.out.print("Password:");
        main.password = sc.nextLine();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Chatroom chatroom = new Chatroom("irc.twitch.tv", 443, "megasonimon", "", main.password);
                chatroom.setVisible(true);
            }
        });
        
    }
}
