/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtchat.gui.setting;

import jtchat.profile.Profile;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import jtchat.profile.Language;
import jtchat.profile.LanguageChangeListener;

/**
 *
 * @author leeyc
 */
public class IRCSetPane extends JPanel implements LanguageChangeListener{
    private JLabel lIrcServer;
    private JTextField tIrcServer;
    private JLabel lIrcPort;
    private JTextField tIrcPort;
    private JLabel lIrcServerPass;
    private JPasswordField tIrcServerPass;
    private JLabel lIrcNick;
    private JTextField tIrcNick;
    private JLabel lIrcChannel;
    private JTextField tIrcChannel;
    private JButton bApply;
    private SetActionListener setActionListener = new SetActionListener();
    public IRCSetPane(){
        init();
        load();
    }
    
    private void init(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        
        //Server
        lIrcServer = new JLabel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(lIrcServer,gbc);

        tIrcServer = new JTextField(Profile.ins().IRCserver);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(tIrcServer,gbc);

        //Port
        lIrcPort = new JLabel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(lIrcPort,gbc);
        
        tIrcPort = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(tIrcPort,gbc);
        
        //Nick
        lIrcNick = new JLabel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(lIrcNick,gbc);

        tIrcNick = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(tIrcNick,gbc);
        
        //Server Password
        lIrcServerPass = new JLabel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(lIrcServerPass,gbc);

        tIrcServerPass = new JPasswordField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(tIrcServerPass,gbc);
        

        
        //Channel
        lIrcChannel = new JLabel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(lIrcChannel,gbc);

        tIrcChannel = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(tIrcChannel,gbc);
        
        //Connect
        
        bApply = new JButton();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(bApply,gbc);
        bApply.addActionListener(setActionListener);

    }
    
    public void save(){
        Profile.ins().IRCserver = tIrcServer.getText();
        Profile.ins().IRCport = Integer.parseInt(tIrcPort.getText());
        Profile.ins().IRCnickname = tIrcNick.getText();
        Profile.ins().IRCservpass = new String(tIrcServerPass.getPassword());
        Profile.ins().IRCchannel = tIrcChannel.getText();
        
    }
    
    public void load(){
        //load settings from SettingTable
        tIrcServer.setText(Profile.ins().IRCserver);
        tIrcPort.setText(String.format("%d",Profile.ins().IRCport));
        tIrcNick.setText(Profile.ins().IRCnickname);
        //profiles do not save password, so just clear it
        tIrcServerPass.setText("");
        tIrcChannel.setText(Profile.ins().IRCchannel);
    }

    public void languageChange() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                lIrcServer.setText(Language.ins().get("IRCSetServer"));
                lIrcPort.setText(Language.ins().get("IRCSetPort"));
                lIrcNick.setText(Language.ins().get("IRCSetNickname"));
                lIrcServerPass.setText(Language.ins().get("IRCSetServerPass"));
                lIrcChannel.setText(Language.ins().get("IRCSetChannel"));
                bApply.setText(Language.ins().get("SetButApply"));
            }
        });
    }
    
    private class SetActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == bApply){
                save();
            }
        }
        
        //ircbot.sendRaw(inputField.getText());
    }
}
