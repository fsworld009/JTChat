
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BoxView;
import javax.swing.text.ComponentView;
import javax.swing.text.Element;
import javax.swing.text.IconView;
import javax.swing.text.LabelView;
import javax.swing.text.ParagraphView;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;
import jtchat.irc.ChatMsgListener;
import jtchat.irc.JtvIRCBot;



public class MainWindow extends JFrame implements ChatMsgListener{
    private JtvIRCBot ircbot;
    private JTextField inputField;
    private JButton setButton;
    private JButton sendButton;
    private JButton connectButton;
    private JTextPane chatMsgsPane;
    
    //private JPanel textBoxPanel;
    //private JScrollPane chatScrollPane;
    private SettingWindow settingWindow = new SettingWindow(this); //need improved
    private ChatActionListener chatActionListener = new ChatActionListener();
    
    //for chat
    private String channel = "";
    private String nickname = "";
    private SimpleAttributeSet chatAttr;
    
    private ChatMessage chatMsgs;
    public MainWindow(){
        super("JTChat");
        chatAttr = new SimpleAttributeSet();
        chatMsgs = new ChatMessage();

        
        init();
        applyChange();
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setSize(350,500);
        this.setResizable(false);
        
        //stop ircbot on close
        this.addWindowListener(new closeEventWindowListener());
        
        
    }
    
    private void connect(){
        
        if(ircbot==null){
            //initialize ircbot
            ircbot = new JtvIRCBot();
            ircbot.registerLogListener(settingWindow.logPaneRef());
            ircbot.registerMsgListener(this);
        
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
        chatMsgsPane = new JTextPane();

        
        chatMsgsPane.setEditable(false);
        chatMsgsPane.setFont(new Font("Serif",Font.PLAIN,14));

        //try {
            //chatMsgs.setText("asdasdadadsadsa");
            //chatMsgs.getDocument().insertString(chatMsgs.getDocument().getLength(), "abc\n", null);
            //chatMsgs.getDocument().insertString(chatMsgs.getDocument().getLength(), "efg\n", null);
        //} 
        JScrollPane chatScrollPane = new JScrollPane(chatMsgsPane,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        
        //make components transparent
        //textBoxP1.setOpaque(false);
        //textBoxP2.setOpaque(false);
        textBoxPanel.setOpaque(false);
        chatMsgsPane.setOpaque(false);
        chatMsgsPane.setEditorKit(new WrapEditorKit());

        chatScrollPane.getViewport().setOpaque(false);
        chatScrollPane.setOpaque(false);

        chatMsgsPane.setBorder(null);
        chatScrollPane.setBorder(null);
        
        
        this.add(chatScrollPane,BorderLayout.CENTER);
        

        
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
    
    public void onChatMsg(String channel, String nickname, boolean isOp, String message){
        //SwingUtilities.invokeLater(new Runnable() {
        //    public void run() {

            //}
        //}
        //appendChatMsg(sender,message);
        if(channel.equals(this.channel)){
            chatMsgs.add(String.format("%s: %s",nickname, message));
            chatMsgs.setText(chatMsgsPane);
        }
        
    }
    public void onChatAction(String channel, String nickname, String action){
        if(channel.equals(this.channel)){
            chatMsgs.add(String.format("%s %s",nickname, action));
            chatMsgs.setText(chatMsgsPane);
        }
    }
    public void onPrivateMsg(String nickname, String message){
        
    }
    
    public void onSysMsg(String message){
        chatMsgs.add(String.format("[SYS] %s", message));
        chatMsgs.setText(chatMsgsPane);

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
                MainWindow.this.getContentPane().setPreferredSize(new Dimension(Profile.ins().ChatWidth+17,Profile.ins().ChatHeight+43));
                MainWindow.this.pack();
                
                //font
                inputField.setFont(new Font(Profile.ins().ChatTextFont.getFontName(),Font.PLAIN,12));
                
                //alwaysontop
                MainWindow.this.setAlwaysOnTop(Profile.ins().ChatAlwaysOnTop);
                
                chatMsgs.setText(chatMsgsPane);
                
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
                if(ircbot == null || !ircbot.isConnected()){
                    //connect
                    MainWindow.this.connect();
                    connectButton.setText("Disconnect");
                }else if(ircbot != null && ircbot.isConnected()){
                    MainWindow.this.disconnect();
                    connectButton.setText("Connect");
                }
                
            }else if(e.getSource() == sendButton || e.getSource() == inputField){
                String message = MainWindow.this.inputField.getText();
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
    
    //http://java-sl.com/tip_letter_wrap_java7.html
    class WrapEditorKit extends StyledEditorKit {
        ViewFactory defaultFactory=new WrapColumnFactory();
        public ViewFactory getViewFactory() {
            return defaultFactory;
        }
 
    }
 
    class WrapColumnFactory implements ViewFactory {
        public View create(Element elem) {
            String kind = elem.getName();
            if (kind != null) {
                if (kind.equals(AbstractDocument.ContentElementName)) {
                    return new WrapLabelView(elem);
                } else if (kind.equals(AbstractDocument.ParagraphElementName)) {
                    return new ParagraphView(elem);
                } else if (kind.equals(AbstractDocument.SectionElementName)) {
                    return new BoxView(elem, View.Y_AXIS);
                } else if (kind.equals(StyleConstants.ComponentElementName)) {
                    return new ComponentView(elem);
                } else if (kind.equals(StyleConstants.IconElementName)) {
                    return new IconView(elem);
                }
            }
 
            // default to text display
            return new LabelView(elem);
        }
    }
 
    class WrapLabelView extends LabelView {
        public WrapLabelView(Element elem) {
            super(elem);
        }
 
        public float getMinimumSpan(int axis) {
            switch (axis) {
                case View.X_AXIS:
                    return 0;
                case View.Y_AXIS:
                    return super.getMinimumSpan(axis);
                default:
                    throw new IllegalArgumentException("Invalid axis: " + axis);
            }
        }
 
    }
    
    
}
