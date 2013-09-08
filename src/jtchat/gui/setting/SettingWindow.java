
package jtchat.gui.setting;

import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import jtchat.gui.MainWindow;
import jtchat.profile.Language;
import jtchat.profile.LanguageChangeListener;


public class SettingWindow extends JFrame implements LanguageChangeListener{
    private JTabbedPane tabPane;
    private LogPane logPane;
    private ChatroomSetPane chatSetPane;
    private IRCSetPane ircSetPane;
    private ProfilePane profilePane;
    private AboutPane aboutPane;
    private MainWindow mainWinRef;
    //MainWindow chatroomWindow;
    public SettingWindow(MainWindow mainWinRef){
        super();
        this.mainWinRef = mainWinRef;
        init(mainWinRef);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(320,500);
        this.setResizable(false);
        this.addWindowListener(new closeEventWindowListener());
        
        loadLanguage();
    }
    
    private void loadLanguage(){
        Language.ins().registerLogListener(mainWinRef);
        Language.ins().registerLogListener(this);
        Language.ins().registerLogListener(logPane);
        Language.ins().registerLogListener(ircSetPane);
        Language.ins().registerLogListener(chatSetPane);
        Language.ins().registerLogListener(profilePane);
        Language.ins().registerLogListener(aboutPane);
        Language.ins().load("en-US");
    }
    
    
    private void init(MainWindow mainWinRef){
        
        
        tabPane = new JTabbedPane();
        this.add(tabPane);
        logPane = new LogPane();
        ircSetPane = new IRCSetPane();
        chatSetPane = new ChatroomSetPane(this);
        profilePane = new ProfilePane(this);
        aboutPane = new AboutPane();
        tabPane.addTab("", ircSetPane);
        tabPane.addTab("", chatSetPane);
        tabPane.addTab("", profilePane);
        tabPane.addTab("", logPane);
        tabPane.addTab("", aboutPane);
    }

    
    public void languageChange() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SettingWindow.this.setTitle(Language.ins().get("SetWinTitle"));
                tabPane.setTitleAt(0, Language.ins().get("IRCSetTabName"));
                tabPane.setTitleAt(1, Language.ins().get("ChatSetTabName"));
                tabPane.setTitleAt(2, Language.ins().get("ProfileSetTabName"));
                tabPane.setTitleAt(3, Language.ins().get("LogSetTabName"));
                tabPane.setTitleAt(4, Language.ins().get("AboutTabName"));
            }
        });
    }
    

    private class closeEventWindowListener extends WindowAdapter{
        public void windowClosing(WindowEvent e) {
            //apply setting
            //ircSetPane.save();
        }
    }
    

    //temp method
    public LogPane logPaneRef(){
        return logPane;
    }
    
    public void load(){
        ircSetPane.load();
        chatSetPane.load();
        
    }
    
    public void applyChangeToChatroom(){
        mainWinRef.applyChange();
    }
    
    public void onMainWindowResize(){
        chatSetPane.onMainWindowResize();
    }
    

}
