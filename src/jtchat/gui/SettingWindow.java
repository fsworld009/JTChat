
package jtchat.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;


public class SettingWindow extends JFrame{
    JTabbedPane tabPane;
    LogPane logPane;
    ChatroomSetPane chatSetPane;
    IRCSetPane ircSetPane;
    ProfilePane profilePane;
    //MainWindow chatroomWindow;
    public SettingWindow(MainWindow mainWinRef){
        super("JTChat Setting");
        init(mainWinRef);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300,500);
        this.setResizable(false);
        this.addWindowListener(new closeEventWindowListener());
    }
    
    private void init(MainWindow mainWinRef){
        
        
        tabPane = new JTabbedPane();
        this.add(tabPane);
        logPane = new LogPane();
        ircSetPane = new IRCSetPane();
        chatSetPane = new ChatroomSetPane(mainWinRef);
        profilePane = new ProfilePane();
        tabPane.addTab("IRC Set", ircSetPane);
        tabPane.addTab("Chat Set", chatSetPane);
        tabPane.addTab("Profile", profilePane);
        tabPane.addTab("Log", logPane);
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
    

}
