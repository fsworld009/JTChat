
package jtchat.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Chatroom extends JFrame{
    public Chatroom(){
        super();
        init();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(350,500);
        this.setResizable(false);
    }
    
    //initialize UI components
    private void init(){
        //BorderLayout layoutMgr = 
        
        JPanel textBoxPanel = new JPanel();
        textBoxPanel.setLayout(new FlowLayout());
        
        JTextField inputField = new JTextField(14);
        JButton sendButton = new JButton("Chat");
        textBoxPanel.add(inputField);
        textBoxPanel.add(sendButton);
        
        this.setLayout(new BorderLayout());
        this.add(textBoxPanel,BorderLayout.SOUTH);
    }
}
