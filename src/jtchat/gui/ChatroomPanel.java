
package jtchat.gui;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BoxView;
import javax.swing.text.ComponentView;
import javax.swing.text.Element;
import javax.swing.text.IconView;
import javax.swing.text.LabelView;
import javax.swing.text.ParagraphView;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;
import jtchat.profile.Profile;


public class ChatroomPanel extends JPanel{
    JTextPane chatMsgsPane;
    private ChatMessage chatMsgs;
    
    public ChatroomPanel(){
        chatMsgs = new ChatMessage();
        init();
    }
    
    private void init(){
        this.setLayout(new BorderLayout());
        this.setOpaque(false);
        
        chatMsgsPane = new JTextPane();     
        chatMsgsPane.setEditable(false);
        JScrollPane chatScrollPane = new JScrollPane(chatMsgsPane,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        chatMsgsPane.setEditorKit(new ChatroomPanel.WrapEditorKit());
        chatMsgsPane.setOpaque(false);

        chatScrollPane.getViewport().setOpaque(false);
        chatScrollPane.setOpaque(false);

        chatMsgsPane.setBorder(BorderFactory.createLineBorder(Profile.ins().ChatBgColor,Profile.ins().ChatBorderThickness));
        chatScrollPane.setBorder(null);
        
        this.add(chatScrollPane,BorderLayout.CENTER);
    }
    
    /*public IRCJtvCommandListener getCommandListener(){
        return chatMsgs;
    }*/

    public void clearChat() {
        if(Profile.ins().ChatClear){
            chatMsgs.clearChat();
        }
        addMessage("[SYS] Chat has been cleared",ChatMessage.MsgType.Sys);
    }

    
    public void clearMsgsFromBannedUser(String username) {
        if(Profile.ins().ChatClearBannedMsg){
            chatMsgs.clearMsgsFromBannedUser(username);
        }
        addMessage("[SYS] "+username+" has been banned / timeoutted",ChatMessage.MsgType.Sys);
    }
    
    
    public void addMessage(String msg, ChatMessage.MsgType type){
        //do
        chatMsgs.addMessage(msg,type);
        chatMsgs.setText(chatMsgsPane);
    }
    
    public void applyChange(){
        chatMsgs.setText(chatMsgsPane);
        chatMsgsPane.setBorder(BorderFactory.createLineBorder(Profile.ins().ChatBgColor,Profile.ins().ChatBorderThickness));
    }
    
    
    //http://java-sl.com/tip_letter_wrap_java7.html
    class WrapEditorKit extends StyledEditorKit {
        ViewFactory defaultFactory=new ChatroomPanel.WrapColumnFactory();
        public ViewFactory getViewFactory() {
            return defaultFactory;
        }
 
    }
 
    class WrapColumnFactory implements ViewFactory {
        public View create(Element elem) {
            String kind = elem.getName();
            if (kind != null) {
                if (kind.equals(AbstractDocument.ContentElementName)) {
                    return new ChatroomPanel.WrapLabelView(elem);
                } else if (kind.equals(AbstractDocument.ParagraphElementName)) {
                    return new ParagraphView(elem);
                } else if (kind.equals(AbstractDocument.SectionElementName)) {
                    return new BoxView(elem, View.Y_AXIS);
                } else if (kind.equals(StyleConstants.ComponentElementName)) {
                    return new ComponentView(elem);
                } else if (kind.equals(StyleConstants.IconElementName)) {
                    return new IconView(elem);
                }
            }
 
            // default to text display
            return new LabelView(elem);
        }
    }
 
    class WrapLabelView extends LabelView {
        public WrapLabelView(Element elem) {
            super(elem);
        }
 
        public float getMinimumSpan(int axis) {
            switch (axis) {
                case View.X_AXIS:
                    return 0;
                case View.Y_AXIS:
                    return super.getMinimumSpan(axis);
                default:
                    throw new IllegalArgumentException("Invalid axis: " + axis);
            }
        }
 
    }
    
}
