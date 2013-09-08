package jtchat.gui.setting;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import jtchat.gui.util.JConfirmedFileChooser;
import jtchat.profile.Language;
import jtchat.profile.LanguageChangeListener;
import jtchat.profile.Profile;


public class ProfilePane extends JPanel implements LanguageChangeListener{
    //private JLabel lProfileName;
    //private JComboBox cbProfileList;
    private JButton bSave;
    private JButton bLoad;
    private SetActionListener setActionListener = new SetActionListener();
    private SettingWindow setWinRef;
    private JLabel lProfileNotice;
    private File[] langFiles;
    
    private JComboBox languageList;
    
    public ProfilePane(SettingWindow setWinRef){
        this.setWinRef = setWinRef;
        init();
        getLanguageList();
        
    }
    
    private void getLanguageList(){
        File langFolder = new File("./language");
        langFiles = langFolder.listFiles();
        Scanner sc=null;
        for(int ix=0;ix<langFiles.length;ix++){
            try{
                sc = new Scanner(langFiles[ix]);
            }catch(FileNotFoundException e){
                System.err.printf("file not found\n");
            }
            String split[] = sc.nextLine().split("=",2);
            languageList.addItem(split[1]);
            sc.close();
        }
        
    }
    

    
    private void init(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        
        
        languageList = new JComboBox();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        this.add(languageList,gbc);
        
        
        
        
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        this.add(new JLabel(" "),gbc);
        
        bSave = new JButton();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        
        this.add(bSave,gbc);
        
        bLoad = new JButton();

        //loadProfileList();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        this.add(bLoad,gbc);
        
        bSave.addActionListener(setActionListener);
        bLoad.addActionListener(setActionListener);
        
        lProfileNotice = new JLabel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth=2;
        
        this.add(lProfileNotice,gbc);
        
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
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                bSave.setText(Language.ins().get("SetButSave"));
                bLoad.setText(Language.ins().get("SetButLoad"));
                lProfileNotice.setText(Language.ins().get("ProfileSetNote"));
            }
        });
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
