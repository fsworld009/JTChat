
package jtchat.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
    private JLabel lUseTwitchColor;
    private JCheckBox cUseTwitchColor;
    private JLabel lTextColor;
    private JButton bTextColor;
    private JLabel lTextFont;
    private JButton bTextFont;
    private JLabel lNickColor;
    private JButton bNickColor;
    private JLabel lNickFont;
    private JButton bNickFont;
    private JButton bSave;



    public ChatroomSetPane(){
        init();
    }
    
    private String colorToHexString(Color color){
        return String.format("#%06x",color.getRGB() & 0x00FFFFFF).toUpperCase();
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
        
        tWindowWidth = new JTextField(String.format("%d",SettingTable.ins().ChatWidth),4);
        tWindowHeight = new JTextField(String.format("%d",SettingTable.ins().ChatHeight),4);
        
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
        
        bBgColor = new JButton(colorToHexString(SettingTable.ins().ChatBgColor));
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
        
        tNumOfLines = new JTextField("20");
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
        
        bTextColor = new JButton(colorToHexString(SettingTable.ins().ChatTextColor));
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
        
        bTextFont = new JButton("Font");
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
        
        bNickColor = new JButton(colorToHexString(SettingTable.ins().ChatNickColor));
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
        
        bNickFont = new JButton("Font");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(bNickFont,gbc);
        
        cUseTwitchColor = new JCheckBox("Use Jtv/Tiwtch Nickname Color");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        this.add(cUseTwitchColor,gbc);
        
        bSave = new JButton("Save");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(bSave,gbc);
        
    }
}
