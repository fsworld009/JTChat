package jtchat.gui.setting;

import jtchat.gui.util.JConfirmedFileChooser;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import jtchat.irc.IRCLogListener;
import jtchat.profile.Language;
import jtchat.profile.LanguageChangeListener;



public class LogPane extends JPanel implements IRCLogListener, LanguageChangeListener{
    private JTextPane log;
    private JButton bSave;
    private JButton bClear;
    private SetActionListener setActionListener = new SetActionListener();
    public LogPane(){
        init();
    }
    
    //initialize GUI components
    private void init(){
        
        log = new JTextPane();
        log.setEditable(false);
        //log.append("test1\ntest2");
        this.setLayout(new BorderLayout());
        
        this.add(new JScrollPane(log),BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new GridLayout(0,2));
        bSave = new JButton();
        bClear = new JButton();
        buttonPanel.add(bSave);
        buttonPanel.add(bClear);
        this.add(buttonPanel,BorderLayout.SOUTH);
        bSave.addActionListener(setActionListener);
        bClear.addActionListener(setActionListener);
        
    }
    
    private void appendLog(final String newLog){     
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    log.getDocument().insertString(log.getDocument().getLength(), newLog, null);
                } catch (BadLocationException ex) {
                    System.err.println("BadLocationException");
                }
            }
        });
    }
    
    public void onLog(String log){
        appendLog(log+"\r\n");
    }
    
    private void writeLog(File destination){
        FileWriter fstream = null;
        try{
            fstream = new FileWriter(destination);
        }catch(java.io.FileNotFoundException e){
            //cannot create file or don't have permission to write
            System.err.println("Error: cannot create output file or don't have permission to write");
            //System.exit(-1);
        } catch (IOException ex) {
            System.err.println("Error: IO error");
            //System.exit(-1);
        }
        
        BufferedWriter fout = new BufferedWriter(fstream);
        try {
            fout.write(log.getText());
            fout.close();
        } catch (IOException ex) {
            System.err.println("Error: IO error");
            //System.exit(-1);
        }
    }


    public void languageChange() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                bSave.setText(Language.ins().get("SetButSave"));
                bClear.setText(Language.ins().get("LogSetButClear"));
            }
        });
    }
    
    private class SetActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == bClear){
                log.setText("");
            }else if(e.getSource() == bSave){
                    JFileChooser fChooser = new JConfirmedFileChooser();
                    fChooser.setCurrentDirectory(new File("."));
                    int returnVal = fChooser.showSaveDialog(LogPane.this);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                       writeLog(fChooser.getSelectedFile());
                       
                    }
            }
        }
    }
    

}
