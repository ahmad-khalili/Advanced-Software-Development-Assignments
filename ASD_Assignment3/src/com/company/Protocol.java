package com.company;

import java.util.ArrayList;

public class Protocol {
    Connection instance;
    ArrayList<String> connections = new ArrayList<>(10);
    public Connection getInstance(String protocol){
        if(protocol == null || connections.size() >= 3){
            System.out.println("You can't have more than 3 connections!");
            return null;
        }
        if(protocol.equals("FTP")){
            connections.add("FTP");
            return instance = new FTPProtocol();
        }
        if(protocol.equals("HTTP")){
            connections.add("HTTP");
            return instance = new HTTPProtocol();
        }
        if(protocol.equals("SSH")){
            connections.add("SSH");
            return instance = new SSHProtocol();
        }
        if(protocol.equals("SCP")){
            connections.add("SCP");
            return instance = new SCPProtocol();
        }
        if(protocol.equals("TELNET")){
            connections.add("TELNET");
            return instance = new TELNETProtocol();
        }
        return null;
    }
    public ArrayList getCurrentConnections(){
        return connections;
    }

    public boolean release(String protocol){
        if(connections.contains(protocol)){
            instance = null;
            connections.remove(protocol);
            return true;
        }
        return false;
    }
}
