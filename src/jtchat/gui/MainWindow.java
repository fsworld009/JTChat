
package jtchat.gui;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;


public class MainWindow extends JFrame{
    JTabbedPane tabPane;
    LogPane logPane;
    IRCSetPane ircSetPane;
    public MainWindow(){
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
}
