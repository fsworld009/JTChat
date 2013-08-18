/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtchat.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
    private JTextField tIrcServerPass;
    private JLabel lIrcNick;
    private JTextField tIrcNick;
    private JLabel lIrcChannel;
    private JTextField tIrcChannel;
    private JButton bConnect;
    public IRCSetPane(){
        init();
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

        tIrcServer = new JTextField();
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

        tIrcServerPass = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(tIrcServerPass,gbc);
        
        //Nick
        lIrcNick = new JLabel("Nick: ");
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

        tIrcChannel = new JTextField("#");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(tIrcChannel,gbc);
        
        //Connect
        bConnect = new JButton("Connect");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(bConnect,gbc);
 

    }
}
