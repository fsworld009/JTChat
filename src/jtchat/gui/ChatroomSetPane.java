
package jtchat.gui;

import jtchat.gui.util.NwFontChooserS;
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
    private JCheckBox chAlwaysOnTop;
    private JLabel lTextColor;
    private JButton bTextColor;
    private JLabel lTextFont;
    private JButton bTextFont;
    private JLabel lNickColor;
    private JButton bNickColor;
    private JLabel lNickFont;
    private JButton bNickFont;
    private JLabel lSysColor;
    private JButton bSysColor;
    private JLabel lSysFont;
    private JButton bSysFont;
    private JButton bApply;
    
    private Color cBg;
    private Color cText;
    private Color cNick;
    private Color cSys;
    private Font fText;
    private Font fNick;
    private Font fSys;
    
    
    private SetActionListener setActionListener = new SetActionListener();
    
    //need improved
    private MainWindow mainWinRef;



    public ChatroomSetPane(MainWindow mainWinRef){
        this.mainWinRef = mainWinRef;
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
        
        lWindowSize = new JLabel("Chatroom Size: ");
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
        
        lSysColor = new JLabel("System Color: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(lSysColor,gbc);
        
        bSysColor = new JButton();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(bSysColor,gbc);
        
        lSysFont = new JLabel("System Font: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(lSysFont,gbc);
        
        bSysFont = new JButton();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(bSysFont,gbc);
        
        chAlwaysOnTop = new JCheckBox("Always on Top");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(chAlwaysOnTop,gbc);
        
        chUseTwitchColor = new JCheckBox("Use Jtv/Tiwtch Nickname Color");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(chUseTwitchColor,gbc);
        
        bApply = new JButton("Apply");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 2;
        
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(bApply,gbc);
        
        //action listener
        bBgColor.addActionListener(setActionListener);
        bTextColor.addActionListener(setActionListener);
        bTextFont.addActionListener(setActionListener);
        bNickColor.addActionListener(setActionListener);
        bNickFont.addActionListener(setActionListener);
        bSysColor.addActionListener(setActionListener);
        bSysFont.addActionListener(setActionListener);
        bApply.addActionListener(setActionListener);
        
        
    }
    
    public void save(){
        SettingTable.ins().ChatWidth = Integer.parseInt(tWindowWidth.getText());
        SettingTable.ins().ChatHeight = Integer.parseInt(tWindowHeight.getText());
        SettingTable.ins().ChatBgColor = cBg;
        SettingTable.ins().ChatNumOfLines = Integer.parseInt(tNumOfLines.getText());
        SettingTable.ins().ChatTextColor = cText;
        SettingTable.ins().ChatTextFont = fText;
        SettingTable.ins().ChatNickColor = cNick;
        SettingTable.ins().ChatNickFont = fNick;
        SettingTable.ins().ChatSysColor = cSys;
        SettingTable.ins().ChatSysFont = fSys;
        SettingTable.ins().ChatUseTiwtchColor = this.chUseTwitchColor.isSelected();
        SettingTable.ins().ChatAlwaysOnTop = this.chAlwaysOnTop.isSelected();

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
        
        this.cSys = SettingTable.ins().ChatSysColor;
        this.bSysColor.setText(this.colorToHexString(cSys));
        this.fSys = SettingTable.ins().ChatSysFont;
        this.bSysFont.setText(this.fontToString(fSys));
        
        
        this.chUseTwitchColor.setSelected(SettingTable.ins().ChatUseTiwtchColor);
        this.chAlwaysOnTop.setSelected(SettingTable.ins().ChatAlwaysOnTop);
    }
    
    private class SetActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == bBgColor){
                Color choose = JColorChooser.showDialog(ChatroomSetPane.this, "Choose Color", cBg);
                if(choose != null){
                    cBg = choose;
                    bBgColor.setText(colorToHexString(choose));
                }
            }else if(e.getSource() == bTextColor){
                Color choose = JColorChooser.showDialog(ChatroomSetPane.this, "Choose Color", cText);
                if(choose != null){
                    cText = choose;
                    bTextColor.setText(colorToHexString(choose));
                }
            }else if(e.getSource() == bTextFont){
                Font choose = NwFontChooserS.showDialog(null, "Choose Font", fText);
                if(choose != null){
                    fText = choose;
                    bTextFont.setText(fontToString(fText));
                }
            }else if(e.getSource() == bNickColor){
                Color choose = JColorChooser.showDialog(ChatroomSetPane.this, "Choose Color", cNick);
                if(choose != null){
                    cNick = choose;
                    bNickColor.setText(colorToHexString(choose));
                }
            }else if(e.getSource() == bNickFont){
                Font choose = NwFontChooserS.showDialog(null, "Choose Font", fNick);
                if(choose != null){
                    fNick = choose;
                    bNickFont.setText(fontToString(fNick));
                }
            }else if(e.getSource() == bSysColor){
                Color choose = JColorChooser.showDialog(ChatroomSetPane.this, "Choose Color", cSys);
                if(choose != null){
                    cSys = choose;
                    bSysColor.setText(colorToHexString(choose));
                }
            }else if(e.getSource() == bSysFont){
                Font choose = NwFontChooserS.showDialog(null, "Choose Font", fSys);
                if(choose != null){
                    fSys = choose;
                    bSysFont.setText(fontToString(fSys));
                }
            }else if(e.getSource() == bApply){
                save();
                mainWinRef.applyChange();
            }
        }
        
        //ircbot.sendRaw(inputField.getText());
    }
}
