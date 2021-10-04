package com.company;

public class SSHProtocol implements Connection {
    @Override
    public void send(String msg) {
        System.out.println("Sending " + msg + " via SSH Protocol");
    }
}
