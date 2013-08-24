
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
import jtchat.irc.ChatMsgListener;
import jtchat.irc.JtvIRCBot;



public class MainWindow extends JFrame implements ChatMsgListener{
    private JtvIRCBot ircbot;
    private JTextField inputField;
    private JButton setButton;
    private JButton sendButton;
    private JButton connectButton;
    private JEditorPane chatMsgs;
    private SettingWindow settingWindow = new SettingWindow();;
    private ChatActionListener chatActionListener = new ChatActionListener();
    
    //for chat
    private String channel = "";
    private String nickname = "";
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
        
        if(ircbot==null){
            //initialize ircbot
            ircbot = new JtvIRCBot();
            ircbot.registerLogListener(settingWindow.logPaneRef());
            ircbot.registerMsgListener(this);
        
        }
        if(ircbot.connect(SettingTable.ins().IRCserver,SettingTable.ins().IRCport,SettingTable.ins().IRCnickname,"JTChat",SettingTable.ins().IRCservpass)){
            ircbot.join(SettingTable.ins().IRCchannel);
            ircbot.sendRaw("JTVCLIENT");
            this.channel = SettingTable.ins().IRCchannel;
            this.nickname = SettingTable.ins().IRCnickname;
        }
        
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
        
        //
        
        
        
        textBoxPanel.add(inputField);
        textBoxPanel.add(sendButton);
        textBoxPanel.add(setButton);
        textBoxPanel.add(connectButton);        

        
        sendButton.addActionListener(chatActionListener);
        setButton.addActionListener(chatActionListener);
        connectButton.addActionListener(chatActionListener);
        inputField.addActionListener(chatActionListener);
        
        this.add(textBoxPanel,BorderLayout.SOUTH);
        
        
        //text area that displays chat
        chatMsgs = new JEditorPane();
        
        chatMsgs.setEditable(false);
        chatMsgs.setOpaque(false);
        //try {
            //chatMsgs.setText("asdasdadadsadsa");
            //chatMsgs.getDocument().insertString(chatMsgs.getDocument().getLength(), "abc\n", null);
            //chatMsgs.getDocument().insertString(chatMsgs.getDocument().getLength(), "efg\n", null);
        //} 
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
    
    public void onChatMsg(String channel, String sender, String message){
        //SwingUtilities.invokeLater(new Runnable() {
        //    public void run() {

            //}
        //}
        append(String.format("%s: %s\r\n",sender,message));
    }
    public void onChatAction(String channel, String sender, String action){
        
    }
    public void onPrivateMsg(String sender, String message){
        
    }
    
    public void onSysMsg(String message){
            append(String.format("[SYS] %s\r\n",message));

    }
    
    private void append(final String message){
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                try{
                    chatMsgs.getDocument().insertString(chatMsgs.getDocument().getLength(), message, null);
                } catch (BadLocationException ex) {
                    //need improved
                    System.err.println("BadLocationException");
                }
            }
        });
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
            }else if(e.getSource() == sendButton || e.getSource() == inputField){
                String message = MainWindow.this.inputField.getText();
                ircbot.chat(MainWindow.this.channel, message);
                inputField.setText("");
                append(String.format("%s: %s\r\n",MainWindow.this.nickname,message));
            }
            
        }
        
        //ircbot.sendRaw(inputField.getText());
    }
    
    
}
