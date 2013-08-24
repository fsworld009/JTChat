package jtchat.gui;

import java.awt.BorderLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import jtchat.irc.ChatLogListener;



public class LogPane extends JPanel implements ChatLogListener{
    private JTextPane log;
    public LogPane(){
        init();
    }
    
    //initialize GUI components
    private void init(){
        log = new JTextPane();
        log.setEditable(false);
        //log.append("test1\ntest2");
        this.setLayout(new BorderLayout());
        
        this.add(new JScrollPane(log),BorderLayout.CENTER);
    }
    
    private void appendLog(final String newLog){     
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    log.getDocument().insertString(log.getDocument().getLength(), newLog, null);
                } catch (BadLocationException ex) {
                    System.err.println("BadLocationException");
                }
            }
        });
    }
    
    public void onLog(String log){
        appendLog(log+"\r\n");
    }
}
