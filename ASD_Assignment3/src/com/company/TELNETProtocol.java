package com.company;

public class TELNETProtocol implements Connection {
    @Override
    public void send(String msg) {
        System.out.println("Sending " + msg + " via TELNET Protocol");
    }
}
