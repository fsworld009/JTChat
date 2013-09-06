
package jtchat.gui;

import jtchat.gui.setting.SettingWindow;
import jtchat.profile.Profile;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import jtchat.irc.IRCEventListener;
import jtchat.irc.IRCMsgListener;
import jtchat.irc.JtvIRCBot;



public class MainWindow extends JFrame implements IRCMsgListener, IRCEventListener{
    private JtvIRCBot ircbot;
    private JTextField inputField;
    private JButton setButton;
    private JButton sendButton;
    private JButton connectButton;
    private ChatroomPanel chatPane;
    
    //private JPanel textBoxPanel;
    //private JScrollPane chatScrollPane;
    private SettingWindow settingWindow = new SettingWindow(this); //need improved
    private ChatActionListener chatActionListener = new ChatActionListener();
    
    //for chat
    private String channel = "";
    private String nickname = "";
    
    private boolean hitConnectButton = false;
    
    public MainWindow(){
        super("JTChat");
        

        init();
        applyChange();
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setSize(350,500);
        this.setResizable(true);
        
        //stop ircbot on close
        this.addWindowListener(new closeEventWindowListener());
        this.addComponentListener(new ChatComponentAdapter());
        
        
    }
    
    private void connect(){
        
        if(ircbot==null){
            //initialize ircbot
            ircbot = new JtvIRCBot();
            ircbot.registerLogListener(settingWindow.logPaneRef());
            ircbot.registerMsgListener(this);
            ircbot.registerEventListener(this);
        
        }
        ircbot.connect(Profile.ins().IRCserver,Profile.ins().IRCport,Profile.ins().IRCnickname,"JTChat",Profile.ins().IRCservpass);
        this.channel = Profile.ins().IRCchannel;
        this.nickname = Profile.ins().IRCnickname;

        
    }
    
    private void disconnect(){
        ircbot.close();
    }
    
   
    //initialize UI components
    private void init(){
    

        //BorderLayout layoutMgr = 
        this.setLayout(new BorderLayout());
        
        
        //chat input and send button
        
        
        

        
        inputField = new JTextField();
        inputField.setFont(new Font("Arial Unicode MS",Font.PLAIN,12));
        sendButton = new JButton("Chat");
        sendButton.setMargin(new Insets(0,0,0,0));
        setButton = new JButton("Setting");
        setButton.setMargin(new Insets(0,0,0,0));
        connectButton = new JButton("Connect");
        connectButton.setMargin(new Insets(0,0,0,0));
        
        //
        JPanel textBoxPanel = new JPanel();
        textBoxPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc;
        
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        textBoxPanel.add(inputField,gbc);
        
        /*gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.0;
        textBoxPanel.add(sendButton,gbc);*/
        
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        //gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        textBoxPanel.add(setButton,gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        //gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        textBoxPanel.add(connectButton,gbc);        
        

        
        sendButton.addActionListener(chatActionListener);
        setButton.addActionListener(chatActionListener);
        connectButton.addActionListener(chatActionListener);
        inputField.addActionListener(chatActionListener);

        
        this.add(textBoxPanel,BorderLayout.SOUTH);

        
        
        //text area that displays chat
        chatPane = new ChatroomPanel();
        this.add(chatPane,BorderLayout.CENTER);
        
        

        
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
            Profile.ins().saveProfile(new File("JTChat.ini"));
        }
    }
    
    public void onChatMsg(String channel, String nickname, boolean isOp, String message){
        //SwingUtilities.invokeLater(new Runnable() {
        //    public void run() {

            //}
        //}
        //appendChatMsg(sender,message);
        
        //force capitalize nickname
        String capNickname = nickname.substring(0,1).toUpperCase() + nickname.substring(1);
        
        if(channel.equals(this.channel)){
            chatPane.addMessage(String.format("%s: %s",capNickname, message));
        }
        
    }
    public void onChatAction(String channel, String nickname, String action){
        //force capitalize nickname
        String capNickname = nickname.substring(0,1).toUpperCase() + nickname.substring(1);
        
        if(channel.equals(this.channel)){
            chatPane.addMessage(String.format("%s %s",capNickname, action));
        }
    }
    public void onPrivateMsg(String nickname, String message){
        
    }
    
    public void onSysMsg(String message){
        chatPane.addMessage(String.format("[SYS] %s", message));

    }
    
    public void onConnectSuccess(){

    }
    
    public void onLoginFailed(){
        connectButton.setText("Connect");
    }
    
    public void onLoginSuccess(){
        ircbot.join(Profile.ins().IRCchannel);
        ircbot.sendRaw("JTVCLIENT");
    }
    
    
    /*private void appendChatMsg(String nickname, String message){
        //append(String.format("%s: ",nickname),SettingTable.ins().ChatNickColor,SettingTable.ins().ChatNickFont);
        //append(String.format("%s\r\n",message),SettingTable.ins().ChatTextColor,SettingTable.ins().ChatTextFont);
    }
    
    private void appendSysMsg(String message){
        //append(String.format("[SYS] %s\r\n",message),SettingTable.ins().ChatTextColor,SettingTable.ins().ChatTextFont);
    }
    
    
    private void append(final String message,final Color color,final Font font){

        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                try{
                    chatAttr.addAttribute(StyleConstants.CharacterConstants.Foreground, color);
                    chatAttr.addAttribute(StyleConstants.FontConstants.FontFamily, font.getFamily());
                    chatAttr.addAttribute(StyleConstants.FontConstants.FontSize, font.getSize());
                    chatMsgsPane.getDocument().insertString(chatMsgsPane.getDocument().getLength(), message, chatAttr);
                } catch (BadLocationException ex) {
                    //need improved
                    System.err.println("BadLocationException");
                }
            }
        });
    }*/
    
    //called by ChatroomSetPane and MainWindow constructor
    public void applyChange(){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //background
                //chatMsgs.setBackground(SettingTable.ins().ChatBgColor);
                //textBoxPanel.setBackground(SettingTable.ins().ChatBgColor);
                
                //chatScrollPane.getVerticalScrollBar().setBackground(SettingTable.ins().ChatBgColor);
                


                
                MainWindow.this.getContentPane().setBackground(Profile.ins().ChatBgColor);
                
                //input field
                inputField.setBackground(Profile.ins().ChatBgColor);
                inputField.setForeground(Profile.ins().ChatTextColor);
                
                //buttons
                sendButton.setForeground(Profile.ins().ChatTextColor);
                setButton.setForeground(Profile.ins().ChatTextColor);
                connectButton.setForeground(Profile.ins().ChatTextColor);
                
                sendButton.setBackground(Profile.ins().ChatBgColor);
                setButton.setBackground(Profile.ins().ChatBgColor);
                connectButton.setBackground(Profile.ins().ChatBgColor);
                
                //set inner window size
                MainWindow.this.getContentPane().setPreferredSize(new Dimension(Profile.ins().ChatWidth+(2*Profile.ins().ChatBorderThickness)+17,Profile.ins().ChatHeight+(2*Profile.ins().ChatBorderThickness)+45));
                MainWindow.this.pack();
                
                MainWindow.this.setLocation(Profile.ins().ChatPosX, Profile.ins().ChatPosY);
                
                //font
                inputField.setFont(new Font(Profile.ins().ChatTextFont.getFontName(),Font.PLAIN,12));
                
                //alwaysontop
                MainWindow.this.setAlwaysOnTop(Profile.ins().ChatAlwaysOnTop);
                
                chatPane.applyChange();
                
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
                if(!hitConnectButton){
                    //connect
                    MainWindow.this.connect();
                    connectButton.setText("Disconnect");
                }else{
                    MainWindow.this.disconnect();
                    connectButton.setText("Connect");
                }
                hitConnectButton = !hitConnectButton;
                
            }else if(e.getSource() == sendButton || e.getSource() == inputField){
                String message = MainWindow.this.inputField.getText();
                if(!message.equals("")){
                    if(ircbot != null && ircbot.isConnected()){
                        ircbot.chat(MainWindow.this.channel, message);
                        if(message.matches("^/me.*")){
                            onChatAction(MainWindow.this.channel,MainWindow.this.nickname,message.replaceFirst("/me", ""));
                        }else{
                            onChatMsg(MainWindow.this.channel,MainWindow.this.nickname,false,message);
                        }

                    }
                    inputField.setText("");
                }
                
            }
            
        }
        
    }
    
    private class ChatComponentAdapter extends ComponentAdapter{
        public void componentResized(ComponentEvent e)
        {
                Dimension dx = MainWindow.this.getContentPane().getSize();
                //System.out.printf("%d %d\n",d.width,d.height);
                Profile.ins().ChatWidth = dx.width-17-(2*Profile.ins().ChatBorderThickness);
                Profile.ins().ChatHeight = dx.height-45-(2*Profile.ins().ChatBorderThickness);
                MainWindow.this.settingWindow.onMainWindowResize();
        }
    }
    
}
