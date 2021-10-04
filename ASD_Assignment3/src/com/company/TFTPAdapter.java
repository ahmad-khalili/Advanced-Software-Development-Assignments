package com.company;

import java.util.ArrayList;

public class TFTPAdapter extends Protocol implements Connection {
    TFTPProtocol tftp = TFTPProtocol.getTFTPInstance();
    public TFTPAdapter(){
        TFTPProtocol.getTFTPInstance();
    }
    @Override
    public Connection getInstance(String protocol){
        if(connections.size() >= 3) {
            System.out.println("You can't have more than 3 connections!");
            return null;
        }
            if (protocol.equals("TFTP")) {
                connections.add("TFTP");
                return new TFTPAdapter();
            }
        return null;
    }
    public boolean release(){
        if(tftp.releaseTFTP() && connections.contains("TFTP")){
            tftp = null;
            connections.remove("TFTP");
            return true;
        }
        return false;
    }
    @Override
    public void send(String msg){
        tftp.sendMessage(msg);
    }
}
