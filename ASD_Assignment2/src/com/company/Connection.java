package com.company;

import java.util.ArrayList;

public class Connection {
    private static final Connection tel_in = new Connection();
    private static final Connection http_in = new Connection();
    private static final Connection ssh_in = new Connection();
    private static final Connection scp_in = new Connection();
    private static final Connection ftp_in = new Connection();
    static ArrayList<String> connections = new ArrayList<>();
    public static final String TELNET = "TELNET";
    public static final String HTTP = "HTTP";
    public static final String SSH = "SSH";
    public static final String FTP = "FTP";
    public static final String SCP = "SCP";
    public int id = 0;
    private Connection(){}
    public static Connection getInstance(String connectionProtocol) {
        if(connections.size() >= 3){
            System.out.println("You can't create more than 3 connections!");
            return null;
        }
        else {
            switch (connectionProtocol) {
                case TELNET:
                    Connection tel_in = new Connection();
                        tel_in.id = 1;
                    connections.add(TELNET);
                    return tel_in;
                case HTTP:
                    Connection http_in = new Connection();
                        http_in.id = 2;
                    connections.add(HTTP);
                    return http_in;
                case SSH:
                    Connection ssh_in = new Connection();
                        ssh_in.id = 3;
                    connections.add(SSH);
                    return ssh_in;
                case FTP:
                    Connection ftp_in = new Connection();
                        ftp_in.id = 4;
                    connections.add(FTP);
                    return ftp_in;
                case SCP:
                    Connection scp_in = new Connection();
                        scp_in.id = 5;
                    connections.add(SCP);
                    return scp_in;
                default:
                    System.out.println("Unidentified Protocol!");
                    return null;
            }
        }
    }
    public static boolean release(String connectionProtocol){
        if (connections.contains(connectionProtocol)){
            connections.remove(connectionProtocol);
            return true;
        }
        else{
            return false;
        }
    }
    public static ArrayList<String> getCurrentConnections(){
        return connections;
    }
    public void send(String msg){
        if (id == 1){
            System.out.println("Sending [" + msg + "] via TELNET protocol");
        }
        else if (id == 2){
            System.out.println("Sending [" + msg + "] via HTTP protocol");
        }
        else if (id == 3){
            System.out.println("Sending [" + msg + "] via SSH protocol");
        }
        else if (id == 4){
            System.out.println("Sending [" + msg + "] via FTP protocol");
        }
        else if (id == 5){
            System.out.println("Sending [" + msg + "] via SCP protocol");
        }
    }
}