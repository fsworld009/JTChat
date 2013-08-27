
package jtchat.gui.setting;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class AboutPane extends JPanel{
    public AboutPane(){
        JTextArea taAbout = new JTextArea();
        taAbout.setText("JTChat\n\n"
                + "a IRC client for Jtv/Twitch broadcasters to\n"
                + "display their chatroom on their stream\n"
                + "\n\n\n\nAuthor: Fuunkao Sekai\n"
                + "http://fuunkao-sekai.blogspot.com");
        this.setLayout(new BorderLayout());
        this.add(taAbout,BorderLayout.CENTER);
    }
    
}
