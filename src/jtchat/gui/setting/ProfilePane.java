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
    private JLabel lLanguage;
    private JLabel lProfileNotice;
    private JLabel lProfile;
    private File[] langFile;
    
    private JComboBox languageList;
    private JButton bApply;
    
    public ProfilePane(SettingWindow setWinRef){
        this.setWinRef = setWinRef;
        init();
        setLanguageList();
        
    }
    
    private void setLanguageList(){
        File langFolder = new File("./language");
        File[] langFile = langFolder.listFiles();
        Scanner sc=null;
        for(int ix=0;ix<langFile.length;ix++){
            try{
                sc = new Scanner(langFile[ix],"UTF-8");
            }catch(FileNotFoundException e){
                System.err.printf("file not found\n");
            }
            String split[] = sc.nextLine().split("=",2);
            languageList.addItem(split[1]);
        }
        
    }
    
    //invoked by SettingWindow, after SettingWindow called Language.loadDefaultLanguage()
    //need to be improved
    public void setLanguageListToDefault(){
        File langFolder = new File("./language");
        File[] langFile = langFolder.listFiles();
        String defaultCode = Language.ins().getCurrentLangCode();
        for(int ix=0;ix<langFile.length;ix++){
            String filename = langFile[ix].getName();
            if(filename.substring(0,filename.indexOf('.')).equals(defaultCode)){
                languageList.setSelectedIndex(ix);
            }
        }
    }

    
    private void init(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        
        lLanguage = new JLabel("AAAAA");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        this.add(lLanguage,gbc);
        
        languageList = new JComboBox();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        this.add(languageList,gbc);
        
        bApply = new JButton();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        this.add(bApply,gbc);
        
        
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        this.add(new JLabel(" \n "),gbc);
        
        
        lProfile = new JLabel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        
        this.add(lProfile,gbc);
        
        bSave = new JButton();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        
        this.add(bSave,gbc);
        
        bLoad = new JButton();

        //loadProfileList();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        this.add(bLoad,gbc);
        
        bSave.addActionListener(setActionListener);
        bLoad.addActionListener(setActionListener);
        bApply.addActionListener(setActionListener);
        
        lProfileNotice = new JLabel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
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
        //SwingUtilities.invokeLater(new Runnable() {
            //public void run() {
                bSave.setText(Language.ins().get("SetButSave"));
                bLoad.setText(Language.ins().get("SetButLoad"));
                lProfileNotice.setText(Language.ins().get("ProfileSetNote"));
                lLanguage.setText(Language.ins().get("ProfileSetLanguage"));
                lProfile.setText(Language.ins().get("ProfileSetProfile"));
                bApply.setText(Language.ins().get("SetButApply"));
                
                
                
            //}
        //});
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
            }else if(e.getSource() == bApply){
                File langFolder = new File("./language");
                File[] langFile = langFolder.listFiles();
                Language.ins().load(langFile[languageList.getSelectedIndex()]);
            }
        }
    }
}
