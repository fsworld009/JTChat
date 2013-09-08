
import java.io.File;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import jtchat.irc.IRCBot;
import jtchat.gui.MainWindow;
import jtchat.gui.setting.SettingWindow;
import jtchat.profile.Language;
import jtchat.profile.Profile;


public class main {
    public static void main(String[] args){
        File defaultLanguage = new File("language.ini");
        if(!defaultLanguage.exists()){
            //create default language ini
            Language.createDefaultLanguageIni();
        }
        
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
