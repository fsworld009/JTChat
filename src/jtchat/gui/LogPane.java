package jtchat.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import jtchat.irc.ChatLogListener;



public class LogPane extends JPanel implements ChatLogListener{
    JTextArea log;
    public LogPane(){
        init();
    }
    
    //initialize GUI components
    private void init(){
        log = new JTextArea();
        log.setEditable(false);
        //log.append("test1\ntest2");
        this.setLayout(new BorderLayout());
        
        this.add(new JScrollPane(log),BorderLayout.CENTER);
    }
    
    private void appendLog(final String newLog){     
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                log.append(newLog);
            }
        });
    }
    
    public void onLog(String log){
        appendLog(log+"\r\n");
    }
}
