
package jtchat.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import jtchat.irc.IRCBot;


public class Chatroom extends JFrame{
    private IRCBot ircbot;
    public Chatroom(String server, int port, String nickname, String login, String password){
        super();
        
        //ircbot
        ircbot = new IRCBot();
        ircbot.connect(server,port,nickname,login,password);
        ircbot.join("#append");
        
        init();
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(350,500);
        this.setResizable(false);
        
        //stop ircbot on close
        this.addWindowListener(new closeEventWindowListener());
        
        
    }
    
    //initialize UI components
    private void init(){
    

        //BorderLayout layoutMgr = 
        this.setLayout(new BorderLayout());
        
        
        //chat input and send button
        JPanel textBoxPanel = new JPanel();
        textBoxPanel.setLayout(new FlowLayout());
        
        JTextField inputField = new JTextField(14);
        JButton sendButton = new JButton("Chat");
        textBoxPanel.add(inputField);
        textBoxPanel.add(sendButton);
        
        
        this.add(textBoxPanel,BorderLayout.SOUTH);
        
        
        //text area that displays chat
        JEditorPane chatMsgs=null;
        chatMsgs = new JEditorPane();
        
        chatMsgs.setEditable(false);
        chatMsgs.setOpaque(false);
        try {
            //chatMsgs.setText("asdasdadadsadsa");
            chatMsgs.getDocument().insertString(chatMsgs.getDocument().getLength(), "abc\n", null);
            chatMsgs.getDocument().insertString(chatMsgs.getDocument().getLength(), "efg\n", null);
        } catch (BadLocationException ex) {
            Logger.getLogger(Chatroom.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.add(chatMsgs,BorderLayout.CENTER);
    }
    
    private class closeEventWindowListener extends WindowAdapter{
        public void windowClosing(WindowEvent e) {
            Chatroom.this.ircbot.close();
        }
    }
}
