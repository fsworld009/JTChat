/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtchat.gui;

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

/**
 *
 * @author leeyc
 */
public class IRCSetPane extends JPanel{
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
    private JButton bSave;
    private SetActionListener setActionListener = new SetActionListener();
    public IRCSetPane(){
        init();
        load();
    }
    
    private void init(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        
        //Server
        lIrcServer = new JLabel("Server: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(lIrcServer,gbc);

        tIrcServer = new JTextField(SettingTable.ins().IRCserver);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(tIrcServer,gbc);

        //Port
        lIrcPort = new JLabel("Port: ");
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
        
        //Server Password
        lIrcServerPass = new JLabel("Pass: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(lIrcServerPass,gbc);

        tIrcServerPass = new JPasswordField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(tIrcServerPass,gbc);
        
        //Nick
        lIrcNick = new JLabel("Nickname: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(lIrcNick,gbc);

        tIrcNick = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(tIrcNick,gbc);
        
        //Channel
        lIrcChannel = new JLabel("Channel: ");
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
        
        bSave = new JButton("Save");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(bSave,gbc);
        bSave.addActionListener(setActionListener);

    }
    
    public void save(){
        SettingTable.ins().IRCserver = tIrcServer.getText();
        SettingTable.ins().IRCport = Integer.parseInt(tIrcPort.getText());
        SettingTable.ins().IRCnickname = tIrcNick.getText();
        SettingTable.ins().IRCservpass = new String(tIrcServerPass.getPassword());
        SettingTable.ins().IRCchannel = tIrcChannel.getText();
        
    }
    
    public void load(){
        //load settings from SettingTable
        tIrcServer.setText(SettingTable.ins().IRCserver);
        tIrcPort.setText(String.format("%d",SettingTable.ins().IRCport));
        tIrcNick.setText(SettingTable.ins().IRCnickname);
        //profiles do not save password, so just clear it
        tIrcServerPass.setText("");
        tIrcChannel.setText(SettingTable.ins().IRCchannel);
    }
    
    private class SetActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == bSave){
                save();
            }
        }
        
        //ircbot.sendRaw(inputField.getText());
    }
}
