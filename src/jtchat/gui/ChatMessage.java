package jtchat.gui;

import jtchat.profile.Profile;
import java.awt.Color;
import java.awt.Font;
import java.util.Vector;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import jtchat.irc.UserColorMapper;

public class ChatMessage{
    private SimpleAttributeSet chatAttr;


    

    public enum MsgType{
        Text, Action, Sys
    }
    
    private Vector<String> messages;
    private Vector<MsgType> messagesType;
    
    public ChatMessage(){
        messages = new Vector<String>();
        messagesType = new Vector<MsgType>();
        chatAttr = new SimpleAttributeSet();

    }

    private void raw(){
        for(int ix=0;ix<messages.size();ix++){
            System.out.println("RAW: "+messages.get(ix));
        }
    }
    
    
    public void addMessage(String newMsg, MsgType type){
        messages.add(newMsg);
        messagesType.add(type);
    }
    
    public void clearChat(){
        messages.removeAllElements();
        messagesType.removeAllElements();
    }
    
    public void clearMsgsFromBannedUser(String username) {
        String split[];
        for(int ix=0;ix<messages.size();ix++){
            switch(messagesType.get(ix)){
                case Text:
                    split = messages.get(ix).split(":",2);
                    if(split[0].toLowerCase().equals(username)){
                        messages.remove(ix);
                        messages.add(ix, split[0]+": <deleted>");
                    }
                case Action:
                    split = messages.get(ix).split(" ",2);
                    if(split[0].toLowerCase().equals(username)){
                        messages.remove(ix);
                        messages.add(ix, split[0]+"  <deleted>");
                    }
                default:
                    break;
            }
        }
    }
    
    public void removeOldLines(){
        //remove old msgs
        
        while(messages.size()>Profile.ins().ChatNumOfLines){
            messages.remove(0);
            messagesType.remove(0);
        }
    }
    
    private void setTextAttribute(Font font, Color color){
        chatAttr.addAttribute(StyleConstants.CharacterConstants.Foreground, color);
        chatAttr.addAttribute(StyleConstants.FontConstants.FontFamily, font.getFamily());
        chatAttr.addAttribute(StyleConstants.FontConstants.FontSize, font.getSize());
    }
    
    public void setText(final JTextPane chatPane){
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                removeOldLines();
                //raw();
                Document doc = chatPane.getDocument();
                Color color=null;
                Font font=null;
                try {
                    doc.remove(0, doc.getLength());
                    String split[];
                    for(int ix=0;ix<messages.size();ix++){
                        switch(messagesType.get(ix)){
                            case Text:
                                split = messages.get(ix).split(":",2);

                                //draw nick
                                if(Profile.ins().ChatUseTiwtchColor){
                                    color = UserColorMapper.ins().getColor(split[0]);
                                    if(color==null){
                                        color = Profile.ins().ChatNickColor;
                                    }
                                }else{
                                    color = Profile.ins().ChatNickColor;
                                }
                                font = Profile.ins().ChatNickFont;
                                setTextAttribute(font,color);
                                doc.insertString(doc.getLength(), split[0], chatAttr);
                                //draw text
                                color = Profile.ins().ChatTextColor;
                                font = Profile.ins().ChatTextFont;
                                setTextAttribute(font,color);
                                doc.insertString(doc.getLength(), ":"+split[1], chatAttr);
                                break;
                            case Action:
                                split = messages.get(ix).split(" ",2);
                                if(Profile.ins().ChatUseTiwtchColor){
                                    color = UserColorMapper.ins().getColor(split[0]);
                                    if(color==null){
                                        color = Profile.ins().ChatNickColor;
                                    }
                                }else{
                                    color = Profile.ins().ChatNickColor;
                                }
                                font = Profile.ins().ChatNickFont;
                                setTextAttribute(font,color);
                                doc.insertString(doc.getLength(), messages.get(ix), chatAttr);
                                break;
                            case Sys:
                                color = Profile.ins().ChatSysColor;
                                font = Profile.ins().ChatSysFont;
                                setTextAttribute(font,color);
                                doc.insertString(doc.getLength(), messages.get(ix), chatAttr);
                                break;
                            default:
                                break;
                        }
                        if(ix < messages.size()-1){
                            doc.insertString(doc.getLength(), "\n", chatAttr);
                        }
                    }
                } catch (BadLocationException ex) {
                    System.err.printf("BadLocationException %d\n",ex.offsetRequested());
                }
            }
        });
         
    }
    
    
}
