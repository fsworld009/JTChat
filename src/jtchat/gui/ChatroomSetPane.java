
package jtchat.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ChatroomSetPane extends JPanel{
    private JLabel lWindowSize;
    private JTextField tWindowWidth;
    private JLabel lBgColor;
    private JButton bBgColor;
    private JTextField tWindowHeight;
    private JLabel lNumOfLines;
    private JTextField tNumOfLines;
    private JCheckBox chUseTwitchColor;
    private JLabel lTextColor;
    private JButton bTextColor;
    private JLabel lTextFont;
    private JButton bTextFont;
    private JLabel lNickColor;
    private JButton bNickColor;
    private JLabel lNickFont;
    private JButton bNickFont;
    private JButton bSave;
    
    private Color cBg;
    private Color cText;
    private Color cNick;
    private Font fText;
    private Font fNick;
    
    private SetActionListener setActionListener = new SetActionListener();



    public ChatroomSetPane(){
        init();
        load();
    }
    
    private String colorToHexString(Color color){
        return String.format("#%06x",color.getRGB() & 0x00FFFFFF).toUpperCase();
    }
    
    private String fontToString(Font font){
        return font.getFontName() + " " + String.format("%d",font.getSize());
    }
    
    private void init(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        
        lWindowSize = new JLabel("Window Size: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(lWindowSize,gbc);
        
        tWindowWidth = new JTextField(4);
        tWindowHeight = new JTextField(4);
        
        JPanel windowSizePanel = new JPanel();
        windowSizePanel.setLayout(new FlowLayout());
        windowSizePanel.add(tWindowWidth);
        windowSizePanel.add(new JLabel("X"));
        windowSizePanel.add(tWindowHeight);
        
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(windowSizePanel,gbc);
        
        
        lBgColor = new JLabel("Bg Color: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(lBgColor,gbc);
        
        bBgColor = new JButton();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(bBgColor,gbc);
        
        lNumOfLines = new JLabel("Max number of lines: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(lNumOfLines,gbc);
        
        tNumOfLines = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(tNumOfLines,gbc);
        
       
        lTextColor = new JLabel("Text Color: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(lTextColor,gbc);
        
        bTextColor = new JButton();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(bTextColor,gbc);
        
        lTextFont = new JLabel("Text Font: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(lTextFont,gbc);
        
        bTextFont = new JButton();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(bTextFont,gbc);
        
        lNickColor = new JLabel("Nickname Color: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(lNickColor,gbc);
        
        bNickColor = new JButton();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(bNickColor,gbc);
        
        lNickFont = new JLabel("Nickname Font: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(lNickFont,gbc);
        
        bNickFont = new JButton();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(bNickFont,gbc);
        
        chUseTwitchColor = new JCheckBox("Use Jtv/Tiwtch Nickname Color");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        this.add(chUseTwitchColor,gbc);
        
        bSave = new JButton("Save");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(bSave,gbc);
        
        //action listener
        bBgColor.addActionListener(setActionListener);
        
    }
    
    public void save(){
        SettingTable.ins().ChatWidth = Integer.parseInt(tWindowWidth.getText());
        SettingTable.ins().ChatHeight = Integer.parseInt(tWindowHeight.getText());
        SettingTable.ins().ChatBgColor = Color.decode(bBgColor.getText());
        SettingTable.ins().ChatNumOfLines = Integer.parseInt(tNumOfLines.getText());
        //SettingTable.ins().ChatTextColor = Color.decode(bTextColor.getText());
        //SettingTable.ins().ChatTextFont = Font.decode(bTextFont.getText());

    }
    
    public void load(){
        //window
        this.tWindowWidth.setText(String.format("%d",SettingTable.ins().ChatWidth));
        this.tWindowHeight.setText(String.format("%d",SettingTable.ins().ChatHeight));
        
        this.tNumOfLines.setText(String.format("%d",SettingTable.ins().ChatNumOfLines));
        
        this.cBg = SettingTable.ins().ChatBgColor;
        this.bBgColor.setText(this.colorToHexString(cBg));
        
        this.cText = SettingTable.ins().ChatTextColor;
        this.bTextColor.setText(this.colorToHexString(cText));
        this.fText = SettingTable.ins().ChatTextFont;
        this.bTextFont.setText(this.fontToString(fText));
        
        this.cNick = SettingTable.ins().ChatNickColor;
        this.bNickColor.setText(this.colorToHexString(cNick));
        this.fNick = SettingTable.ins().ChatNickFont;
        this.bNickFont.setText(this.fontToString(fNick));
        
        this.chUseTwitchColor.setSelected(SettingTable.ins().ChatUseTiwtchColor);
    }
    
    private class SetActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == bBgColor){
                Color choose = JColorChooser.showDialog(ChatroomSetPane.this, "Choose Color", Color.yellow);
                if(choose != null){
                    cBg = choose;
                }
            }
        }
        
        //ircbot.sendRaw(inputField.getText());
    }
}