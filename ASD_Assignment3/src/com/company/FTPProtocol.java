package com.company;

public class FTPProtocol implements Connection {
    @Override
    public void send(String msg) {
        System.out.println("Sending " + msg + " via FTP Protocol");
    }
}
