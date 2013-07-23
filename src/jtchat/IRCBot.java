
package jtchat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.PriorityQueue;


public class IRCBot {
    private Socket socket;
    private BufferedWriter writer;
    private BufferedReader reader;
    private PriorityQueue<String> sendMsgQueue;
    private SendThread sendThread;
    private ReceiveThread receiveThread;
    public void connect(String server, int port, String nickname, String login, String password){
        // Connect to the IRC server.

        try{
            socket = new Socket();
            socket.connect(new InetSocketAddress(server, port),10000);
            if(socket.isConnected()){
                System.out.printf("%s %s\r\n",socket.getInetAddress(),socket.getPort());
                writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                
                
                // Log on to the server.
                writer.write("PASS " + password + "\r\n");
                writer.write("NICK " + nickname + "\r\n");
                
                //writer.write("USER " + login + " 8 * : Java IRC Hacks Bot\r\n");
                writer.flush();
            }
            
            //setup input and output thread
            receiveThread = new ReceiveThread();
            receiveThread.start();

        }catch(Exception e){
            if(e instanceof UnknownHostException){
                //no such host
                System.out.printf("cannot connect to host\r\n");
            }else if(e instanceof IOException){
                //threw by socket.getOutputStream( ) and socket.getInputStream( ) and writer, reader
                System.out.printf("error when trying to establish I/O\r\n");
            }
        }
    }
    
    public void join(String channel){
        try{
            String send = "JOIN " + channel + "\r\n";
            writer.write(send);
            writer.flush( );
            System.out.printf(">>> %s",send);
        }catch(Exception e){
            if(e instanceof IOException){
                //threw by writer
               
            }
        }
    }
       
    public void close(){
        try{
            writer.close();
            reader.close();
            socket.close();
        }catch(Exception e){
            if(e instanceof IOException){
                //threw by writer
               
            }
        }
    }
    
    private class SendThread extends Thread{
        public void run() {
            
        }
    }
    
    private class ReceiveThread extends Thread{
        public void run() {
            String line;
            try{
                //writer.write("USER " + login + " 8 * : Java IRC Hacks Bot\r\n");
                //String send = "NAMES #append\r\n";
                //writer.write(send);
                //System.out.printf(">>> %s",send);
                //writer.flush();
                while (true) {
                    //print line
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

                        System.out.printf("<<< %s\r\n",line);
                        if (line.toUpperCase().startsWith("PING ")) {
                            // respond to PINGs
                            String response = "PONG " + line.substring(5) + "\r\n";
                            writer.write(response);
                            writer.flush();
                            System.out.printf(">>> %s",response);
                        }
                    }else{
                        System.out.printf("null\r\n");
                    }
                }
            }catch(Exception e){
                System.out.printf("%s",e.toString());
                if(e instanceof IOException){
                    //threw by writer

                }
            }
        }
    }
}

