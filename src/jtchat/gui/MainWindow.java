
package jtchat.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


public class MainWindow extends JFrame{
    private IRCBot ircbot;
    private JTextField inputField;
    public MainWindow(){
        super();
        

        
        init();
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(350,500);
        this.setResizable(false);
        
        //stop ircbot on close
        this.addWindowListener(new closeEventWindowListener());
        
        
    }
    
    public void connect(){
        //ircbot
        ircbot = new IRCBot();
        ircbot.connect("irc.twitch.tv",443,"world9918","JTChat","");
        ircbot.join("#world9918");
        ircbot.sendRaw("JTVCLIENT");
    }
    
    //initialize UI components
    private void init(){
    

        //BorderLayout layoutMgr = 
        this.setLayout(new BorderLayout());
        
        
        //chat input and send button
        JPanel textBoxPanel = new JPanel();
        textBoxPanel.setLayout(new FlowLayout());
        
        inputField = new JTextField(14);
        
        inputField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ircbot.sendRaw(inputField.getText());
            }
        });
        
        
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
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.add(chatMsgs,BorderLayout.CENTER);
    }
    
    private class closeEventWindowListener extends WindowAdapter{
        public void windowClosing(WindowEvent e) {
            /*Thread closeIrcBotThread = new Thread(new Runnable(){
                public void run(){
                    System.out.println("close start");
                    Chatroom.this.ircbot.close();
                    System.out.println("close end");
                }
            });
            closeIrcBotThread.start();*/
            MainWindow.this.ircbot.close();
        }
    }
}
