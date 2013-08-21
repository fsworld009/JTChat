
package jtchat.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;


public class SettingWindow extends JFrame{
    JTabbedPane tabPane;
    LogPane logPane;
    IRCSetPane ircSetPane;
    MainWindow chatroomWindow;
    public SettingWindow(){
        init();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300,500);
        this.setResizable(false);
    }
    
    private void init(){
        
        
        tabPane = new JTabbedPane();
        this.add(tabPane);
        logPane = new LogPane();
        ircSetPane = new IRCSetPane();
        tabPane.addTab("IRC Set", ircSetPane);
        //tabPane.addTab("Chat Set", logPane);
        //tabPane.addTab("Chat", logPane);
        tabPane.addTab("Log", logPane);
    }
    
    public void showChatroom(){
        chatroomWindow = new MainWindow("",433,"","","");
    }
    

}
