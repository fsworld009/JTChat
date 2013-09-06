
import java.io.File;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import jtchat.irc.IRCBot;
import jtchat.gui.MainWindow;
import jtchat.gui.setting.SettingWindow;
import jtchat.profile.Profile;


public class main {
    public static String password = "";
    public static void main(String[] args){
        //enter password
        /*Scanner sc = new Scanner(System.in);
        System.out.print("Password:");
        main.password = sc.nextLine();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Chatroom chatroom = new Chatroom("irc.twitch.tv", 443, "world9918", "", main.password);
                chatroom.setVisible(true);
            }
        });
        
        System.out.println("END");*/
        File defaultProfile = new File("JTChat.ini");
        if(!defaultProfile.exists()){
            Profile.ins().saveProfile(defaultProfile);
        }else{
            Profile.ins().loadProfile(defaultProfile);
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainWindow mainWindow = new MainWindow();
                mainWindow.setVisible(true);
            }
        });
    }
}
