/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtchat.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;



public class LogPane extends JPanel{
    JTextArea log;
    public LogPane(){
        init();
    }
    
    //initialize GUI components
    private void init(){
        log = new JTextArea();
        log.setEditable(false);
        log.append("test1\ntest2");
        this.setLayout(new BorderLayout());
        
        this.add(log,BorderLayout.CENTER);
    }
    
    private void appendLog(final String newLog){     
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                log.append(newLog);
            }
        });
    }
}
