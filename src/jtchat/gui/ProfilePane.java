package jtchat.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ProfilePane extends JPanel{
    //private JLabel lProfileName;
    //private JComboBox cbProfileList;
    private JButton bSave;
    private JButton bLoad;
    
    public ProfilePane(){
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
}
