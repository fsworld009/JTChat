
package jtchat.irc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Timer;


public class IRCBot {
    private Socket socket;
    private BufferedWriter writer;
    private BufferedReader reader;
    private PriorityQueue<String> sendMsgQueue;
    
    boolean threadRunning = false;
    private SendThread sendThread;
    private ReceiveThread receiveThread;
    
    //latest connect info
    private String lastIRCServer = "";
    private int lastIRCPort = 0;
    private String lastIRCServerPass = "";
    private String lastIRCNickname = "";
    private String lastIRCLogin = "";
    

    Timer aliveCheckTask;
    Timer pingTask;
    
    private enum LogType{
        SEND, RECEIVE, SYS
    }
    
    private void log(String log, IRCBot.LogType type ){
        String result="";
        if(type == IRCBot.LogType.SEND){
            result+=">>> ";
        }else if(type == IRCBot.LogType.RECEIVE){
            result+="<<< ";
        }else{
            //SYS
            result+="[SYS] ";
        }
        String logWithoutReturn = log.replaceAll("\r\n", "");
        result+=logWithoutReturn;
        System.out.println(result);
        onLog(result);
        
        if(type == IRCBot.LogType.SYS){
            onSysMsg(logWithoutReturn);
        }
    }
    
    public void sendRaw(String message){
        synchronized(sendMsgQueue){
            sendMsgQueue.add(message+"\r\n");
        }
    }
    
    public boolean connect(String server, int port, String nickname, String login, String password){
        // Connect to the IRC server.

        try{
            socket = new Socket();
            socket.connect(new InetSocketAddress(server, port),10000);
            if(socket.isConnected()){
                log(String.format("Connectted to %s:%s",socket.getInetAddress(),socket.getPort()),IRCBot.LogType.SYS);
                //save connection info
                lastIRCServer = server;
                lastIRCPort = port;
                lastIRCServerPass = password;
                lastIRCNickname = nickname;
                lastIRCLogin = login;
                
                writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                
                //setup message queqe
                sendMsgQueue = new PriorityQueue<String>(10,new MsgComparator());
                
                // Log on to the server, must sent before creating thread
                writer.write("PASS " + password + "\r\n");
                log("PASS " + password,IRCBot.LogType.SEND);
                writer.write("NICK " + nickname + "\r\n");
                log("NICK " + nickname,IRCBot.LogType.SEND);
                writer.write("USER " + login + "\r\n");
                log("USER " + "JTChat",IRCBot.LogType.SEND);
                writer.flush();
                
                //setup input and output thread
                threadRunning = true;

                receiveThread = new ReceiveThread();
                receiveThread.start();

                sendThread = new SendThread();
                sendThread.start();
                
                
                //setup disconnection checking timer
                aliveCheckTask = new Timer(); 
                aliveCheckTask.scheduleAtFixedRate(new AliveCheckTask(), 10000,10000);
                
                pingTask = new Timer(); 
                pingTask.scheduleAtFixedRate(new PingTask(), 30000,30000);
 
                
                return true;
                
            }
        }catch(Exception e){
            if(e instanceof UnknownHostException){
                //no such host
                log(String.format("cannot connect to host\r\n"),IRCBot.LogType.SYS);
            }else if(e instanceof IOException){
                //threw by socket.getOutputStream( ) and socket.getInputStream( ) and writer, reader
                log(String.format("error when trying to establish I/O\r\n"),IRCBot.LogType.SYS);
            }
            
        }
        return false;
    }
    
    public void join(String channel){
        try{
            String send = "JOIN " + channel;
            sendRaw(send);
        }catch(Exception e){
            if(e instanceof IOException){
                //threw by writer
               
            }
        }
    }
       
    public void close(){
        try{
            //close connection only once
            if(threadRunning){
                //cancel alive checking task
                aliveCheckTask.cancel();
                System.out.println("Timer closed");

                //close input and output thread
                threadRunning = false;
                /*try {
                    //while(sendThread.isAlive() || receiveThread.isAlive()){

                        Thread.sleep(5000);
                    //}
                } catch(InterruptedException e){
                    //threw by Thread.sleep()
                }*/
                writer.close();
                reader.close();
                socket.close();
                //sendThread.interrupt();
                //receiveThread.interrupt();
                log("Disconnect from server",IRCBot.LogType.SYS);
            }else{
                //socket has already closed
                log("Connection has already closed, duplicate IRCBot.close() call",IRCBot.LogType.SYS);
            }
        }catch(Exception e){
            if(e instanceof IOException){
                //threw by writer
               
            }
        }
    }
    
    
    private String getUsername(String sender){
        //parse twitch username from twitch chats
        //ex: user!user@user.tmi.twitch.tv -> user
        if(sender.contains("!")){
            return sender.substring(0,sender.indexOf('!'));
        }else{
            return sender;
        }
    }
    
    private void parseMessage(String message){
        String firstParse[] = message.split(" ",3);
        //action at firstParse[1]
        if(firstParse.length<2){
            return;
        }
        if(firstParse[1].equals("PRIVMSG")){
            String parse[] = message.split(" ",4);
            /*
             * parse[0].subString(1): sender
             * parse[2]: target channel/user
             * parse[3].subString(1): message
             */
            if(parse[2].charAt(0)=='#'){
                onChatMsg(parse[2].substring(1),getUsername(parse[0].substring(1)),parse[3].substring(1));
            }else{
                onPrivateMsg(getUsername(parse[0].substring(1)), parse[3].substring(1));
            }
            
        }else if(firstParse[1].equals("JOIN")){

            String parse[] = message.split(" ",3);
            //user succesffully joins a channel
            if(getUsername(parse[0].substring(1)).equals(lastIRCNickname)){
                log(String.format("Joined %s",parse[2]),IRCBot.LogType.SYS);
            }
            
            //other users join the channel
        }
    }
    
    //override these functions on subclass
    public void onLog(String log){
        
    }
    public void onChatMsg(String channel, String sender, String message){
        
    }
    public void onChatAction(String channel, String sender, String action){
        
    }
    public void onPrivateMsg(String sender, String message){
        
    }
    
    public void onSysMsg(String message){
        
    }
    
    
    //the connection is closed by accident
    public void onAccidentDisconnection(){
        
    }
    
    private class SendThread extends Thread{
        
        public void run() {
            while (threadRunning) {
                synchronized(sendMsgQueue){
                    if(!sendMsgQueue.isEmpty()){
                        synchronized(writer){
                            try{
                                String message;
                                while(!sendMsgQueue.isEmpty()){                             
                                    message = sendMsgQueue.poll();
                                    writer.write(message);
                                    log(message,IRCBot.LogType.SEND);
                                }
                                writer.flush();
                            }catch(IOException e){
                                log("Send thread I/O error",IRCBot.LogType.SYS);
                                break;
                            }
                        }
                    }
                }
            }
            System.out.println("SendThread closed");
        }
    }

    
    private class ReceiveThread extends Thread{
        public void run() {
            String line;
            
            while (threadRunning) {
                //print line
                synchronized(reader){
                    try{
                        line = reader.readLine();
                        if(line != null){
                            int colon_pos = line.indexOf(':');

                            if (line.charAt(0) != ':' && colon_pos > 0){
                                //if an irc message begins with " ", then trim the leading spaces
                                line=line.substring(colon_pos);
                            }else if(colon_pos == -1 && line.contains("PONG")){
                                //trim leading spaces for PONG message
                                line=line.substring(line.indexOf('P'));
                            }

                            log(line,IRCBot.LogType.RECEIVE);
                            if (line.toUpperCase().startsWith("PING ")) {
                                // respond to PINGs
                                String response = "PONG " + line.substring(5) + "\r\n";
                                sendRaw(response);
                                log(response,IRCBot.LogType.SEND);
                            }else{
                                parseMessage(line);
                            }

                        }else{
                            System.out.printf("null\r\n");
                        }
                    }catch(IOException e){
                        //threw by reader
                        log("Receive thread I/O error",IRCBot.LogType.SYS);
                        break;
                    }
                 }
            }
            System.out.println("ReceiveThread closed");
            
        }
    }
    
    private class MsgComparator implements Comparator<String>{
        public int compare(String s1, String s2){
            return 0;
        }
    }
    
    private class AliveCheckTask extends TimerTask{
        public void run(){
            if(!sendThread.isAlive() || !receiveThread.isAlive()){
                IRCBot.this.close();
                IRCBot.this.onAccidentDisconnection();
            }else{
                //log("Connection alive",IRCBot.LogType.SYS);
            }
        }
    }
    
    private class PingTask extends TimerTask{
        public void run(){
            IRCBot.this.sendRaw("PING");
        }
    }
}

