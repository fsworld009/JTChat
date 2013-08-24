
package jtchat.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import jtchat.irc.IRCBot;


public class MainWindow extends JFrame{
    private IRCBot ircbot;
    private JTextField inputField;
    private JButton setButton;
    private JButton sendButton;
    private JButton connectButton;
    private SettingWindow settingWindow = new SettingWindow();;
    private ChatActionListener chatActionListener = new ChatActionListener();
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
        ircbot.connect(SettingTable.ins().IRCserver,SettingTable.ins().IRCport,SettingTable.ins().IRCnickname,"JTChat",SettingTable.ins().IRCservpass);
        ircbot.join(SettingTable.ins().IRCchannel);
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
        sendButton = new JButton("Chat");
        sendButton.setMargin(new Insets(0,0,0,0));
        setButton = new JButton("Setting");
        setButton.setMargin(new Insets(0,0,0,0));
        connectButton = new JButton("Connect");
        connectButton.setMargin(new Insets(0,0,0,0));
        
        //inputField.addActionListener();
        
        
        
        textBoxPanel.add(inputField);
        textBoxPanel.add(sendButton);
        textBoxPanel.add(setButton);
        textBoxPanel.add(connectButton);        

        
        sendButton.addActionListener(chatActionListener);
        setButton.addActionListener(chatActionListener);
        connectButton.addActionListener(chatActionListener);
        
        
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
            if(MainWindow.this.ircbot != null){
                MainWindow.this.ircbot.close();
            }
        }
    }
    
    private class ChatActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == MainWindow.this.setButton){
                if(!settingWindow.isVisible()){
                    SwingUtilities.invokeLater(new Runnable() {
                               public void run() {
                                   settingWindow.setVisible(true);
                               }
                           });
                }
            }else if(e.getSource() == MainWindow.this.connectButton){
                connectButton.setText("Disconnect");
                MainWindow.this.connect();
            }
        }
        
        //ircbot.sendRaw(inputField.getText());
    }
}
