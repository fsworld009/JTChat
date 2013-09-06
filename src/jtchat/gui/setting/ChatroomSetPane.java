
package jtchat.gui.setting;

import jtchat.profile.Profile;
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
import jtchat.gui.MainWindow;





public class ChatroomSetPane extends JPanel{
    private JLabel lWindowSize;
    private JTextField tWindowWidth;
    private JTextField tWindowHeight;
    private JLabel lWindowPos;
    private JTextField tWindowPosX;
    private JTextField tWindowPosY;
    
    private JLabel lBgColor;
    private JButton bBgColor;
    private JLabel lBorder;
    private JTextField tBorder;

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
    private SettingWindow setWinRef;



    public ChatroomSetPane(SettingWindow setWinRef){
        this.setWinRef = setWinRef;
        init();
        load();
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
        
        lWindowPos = new JLabel("Chatroom Position: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(lWindowPos,gbc);
        
        tWindowPosX = new JTextField(4);
        tWindowPosY = new JTextField(4);
        
        JPanel windowPosPanel = new JPanel();
        windowPosPanel.setLayout(new FlowLayout());
        windowPosPanel.add(new JLabel("X"));
        windowPosPanel.add(tWindowPosX);
        windowPosPanel.add(new JLabel(" Y"));
        windowPosPanel.add(tWindowPosY);
        
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(windowPosPanel,gbc);
        
        lBorder = new JLabel("Border thickness: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(lBorder,gbc);
        
        tBorder = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(tBorder,gbc);
        
        
        lBgColor = new JLabel("Bg Color: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(lBgColor,gbc);
        
        bBgColor = new JButton();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(bBgColor,gbc);
        
        lNumOfLines = new JLabel("Max number of lines: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(lNumOfLines,gbc);
        
        tNumOfLines = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(tNumOfLines,gbc);
        
       
        lTextColor = new JLabel("Text Color: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(lTextColor,gbc);
        
        bTextColor = new JButton();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(bTextColor,gbc);
        
        lTextFont = new JLabel("Text Font: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(lTextFont,gbc);
        
        bTextFont = new JButton();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(bTextFont,gbc);
        
        lNickColor = new JLabel("Nickname Color: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(lNickColor,gbc);
        
        bNickColor = new JButton();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(bNickColor,gbc);
        
        lNickFont = new JLabel("Nickname Font: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(lNickFont,gbc);
        
        bNickFont = new JButton();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(bNickFont,gbc);
        
        lSysColor = new JLabel("System Color: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(lSysColor,gbc);
        
        bSysColor = new JButton();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(bSysColor,gbc);
        
        lSysFont = new JLabel("System Font: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(lSysFont,gbc);
        
        bSysFont = new JButton();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 10;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(bSysFont,gbc);
        
        chAlwaysOnTop = new JCheckBox("Always on Top");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(chAlwaysOnTop,gbc);
        
        chUseTwitchColor = new JCheckBox("Use Jtv/Tiwtch Nickname Color");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(chUseTwitchColor,gbc);
        
        bApply = new JButton("Apply");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 13;
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
        Profile.ins().ChatWidth = Integer.parseInt(tWindowWidth.getText());
        Profile.ins().ChatHeight = Integer.parseInt(tWindowHeight.getText());
        Profile.ins().ChatPosX = Integer.parseInt(tWindowPosX.getText());
        Profile.ins().ChatPosY = Integer.parseInt(tWindowPosY.getText());
        Profile.ins().ChatBorderThickness = Integer.parseInt(tBorder.getText());
        Profile.ins().ChatBgColor = cBg;
        Profile.ins().ChatNumOfLines = Integer.parseInt(tNumOfLines.getText());
        Profile.ins().ChatTextColor = cText;
        Profile.ins().ChatTextFont = fText;
        Profile.ins().ChatNickColor = cNick;
        Profile.ins().ChatNickFont = fNick;
        Profile.ins().ChatSysColor = cSys;
        Profile.ins().ChatSysFont = fSys;
        Profile.ins().ChatUseTiwtchColor = this.chUseTwitchColor.isSelected();
        Profile.ins().ChatAlwaysOnTop = this.chAlwaysOnTop.isSelected();

    }
    
    public void load(){
        //window
        this.tWindowWidth.setText(String.format("%d",Profile.ins().ChatWidth));
        this.tWindowHeight.setText(String.format("%d",Profile.ins().ChatHeight));
        
        this.tWindowPosX.setText(String.format("%d",Profile.ins().ChatPosX));
        this.tWindowPosY.setText(String.format("%d",Profile.ins().ChatPosY));
        
        this.tNumOfLines.setText(String.format("%d",Profile.ins().ChatNumOfLines));
        
        this.tBorder.setText(String.format("%d",Profile.ins().ChatBorderThickness));
        
        this.cBg = Profile.ins().ChatBgColor;
        this.bBgColor.setText(Profile.colorToHexString(cBg));
        
        this.cText = Profile.ins().ChatTextColor;
        this.bTextColor.setText(Profile.colorToHexString(cText));
        this.fText = Profile.ins().ChatTextFont;
        this.bTextFont.setText(Profile.fontToString(fText));
        
        this.cNick = Profile.ins().ChatNickColor;
        this.bNickColor.setText(Profile.colorToHexString(cNick));
        this.fNick = Profile.ins().ChatNickFont;
        this.bNickFont.setText(Profile.fontToString(fNick));
        
        this.cSys = Profile.ins().ChatSysColor;
        this.bSysColor.setText(Profile.colorToHexString(cSys));
        this.fSys = Profile.ins().ChatSysFont;
        this.bSysFont.setText(Profile.fontToString(fSys));
        
        
        this.chUseTwitchColor.setSelected(Profile.ins().ChatUseTiwtchColor);
        this.chAlwaysOnTop.setSelected(Profile.ins().ChatAlwaysOnTop);
    }
    
    private class SetActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == bBgColor){
                Color choose = JColorChooser.showDialog(ChatroomSetPane.this, "Choose Color", cBg);
                if(choose != null){
                    cBg = choose;
                    bBgColor.setText(Profile.colorToHexString(choose));
                }
            }else if(e.getSource() == bTextColor){
                Color choose = JColorChooser.showDialog(ChatroomSetPane.this, "Choose Color", cText);
                if(choose != null){
                    cText = choose;
                    bTextColor.setText(Profile.colorToHexString(choose));
                }
            }else if(e.getSource() == bTextFont){
                Font choose = NwFontChooserS.showDialog(null, "Choose Font", fText);
                if(choose != null){
                    fText = choose;
                    bTextFont.setText(Profile.fontToString(fText));
                }
            }else if(e.getSource() == bNickColor){
                Color choose = JColorChooser.showDialog(ChatroomSetPane.this, "Choose Color", cNick);
                if(choose != null){
                    cNick = choose;
                    bNickColor.setText(Profile.colorToHexString(choose));
                }
            }else if(e.getSource() == bNickFont){
                Font choose = NwFontChooserS.showDialog(null, "Choose Font", fNick);
                if(choose != null){
                    fNick = choose;
                    bNickFont.setText(Profile.fontToString(fNick));
                }
            }else if(e.getSource() == bSysColor){
                Color choose = JColorChooser.showDialog(ChatroomSetPane.this, "Choose Color", cSys);
                if(choose != null){
                    cSys = choose;
                    bSysColor.setText(Profile.colorToHexString(choose));
                }
            }else if(e.getSource() == bSysFont){
                Font choose = NwFontChooserS.showDialog(null, "Choose Font", fSys);
                if(choose != null){
                    fSys = choose;
                    bSysFont.setText(Profile.fontToString(fSys));
                }
            }else if(e.getSource() == bApply){
                save();
                setWinRef.applyChangeToChatroom();
            }
        }
        
        //ircbot.sendRaw(inputField.getText());
    }
    
    public void onMainWindowResize(){
        this.tWindowWidth.setText(String.format("%d",Profile.ins().ChatWidth));
        this.tWindowHeight.setText(String.format("%d",Profile.ins().ChatHeight));
        this.tWindowPosX.setText(String.format("%d",Profile.ins().ChatPosX));
        this.tWindowPosY.setText(String.format("%d",Profile.ins().ChatPosY));
    }
    
}
