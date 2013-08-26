
//http://stackoverflow.com/questions/3651494/jfilechooser-with-confirmation-dialog
package jtchat.gui.util;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class JConfirmedFileChooser extends JFileChooser{
    public void approveSelection(){
        File f = getSelectedFile();
        if(f.exists() && getDialogType() == JFileChooser.SAVE_DIALOG){
            int result = JOptionPane.showConfirmDialog(this,"The file already exists, overwrite this file?","Existing file",JOptionPane.YES_NO_CANCEL_OPTION);
            switch(result){
                case JOptionPane.YES_OPTION:
                    super.approveSelection();
                    return;
                case JOptionPane.NO_OPTION:
                    return;
                case JOptionPane.CLOSED_OPTION:
                    return;
                case JOptionPane.CANCEL_OPTION:
                    cancelSelection();
                    return;
            }
        }
        super.approveSelection();
    }
}
