package jtchat.gui.setting;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import jtchat.gui.util.JConfirmedFileChooser;
import jtchat.profile.LanguageChangeListener;
import jtchat.profile.Profile;


public class ProfilePane extends JPanel implements LanguageChangeListener{
    //private JLabel lProfileName;
    //private JComboBox cbProfileList;
    private JButton bSave;
    private JButton bLoad;
    private SetActionListener setActionListener = new SetActionListener();
    private SettingWindow setWinRef;
    
    public ProfilePane(SettingWindow setWinRef){
        this.setWinRef = setWinRef;
        init();
    }
    

    
    private void init(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        
        bSave = new JButton("Save");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        this.add(bSave,gbc);
        
        bLoad = new JButton("Load");

        //loadProfileList();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        this.add(bLoad,gbc);
        
        bSave.addActionListener(setActionListener);
        bLoad.addActionListener(setActionListener);
        
        
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth=2;
        
        this.add(new JLabel("Password won't be saved or loaded"),gbc);
        
        /*
        lProfileName = new JLabel("Profile: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(lProfileName,gbc);
        
        cbProfileList = new JComboBox();

        loadProfileList();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        this.add(cbProfileList,gbc);*/
        
    }


    public void languageChange() {
        
    }
    
    private class SetActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == bSave){
                    JFileChooser fChooser = new JConfirmedFileChooser();
                    fChooser.setCurrentDirectory(new File("."));
                    int returnVal = fChooser.showSaveDialog(ProfilePane.this);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                       Profile.ins().saveProfile(fChooser.getSelectedFile());
                       
                    }
            }else if(e.getSource() == bLoad){
                    JFileChooser fChooser = new JConfirmedFileChooser();
                    fChooser.setCurrentDirectory(new File("."));
                    int returnVal = fChooser.showOpenDialog(ProfilePane.this);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                       Profile.ins().loadProfile(fChooser.getSelectedFile());
                       setWinRef.load();
                       setWinRef.applyChangeToChatroom();
                    }
            }
        }
    }
}
