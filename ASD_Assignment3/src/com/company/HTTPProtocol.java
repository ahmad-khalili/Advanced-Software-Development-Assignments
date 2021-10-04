package com.company;

public class HTTPProtocol implements Connection {
    @Override
    public void send(String msg) {
        System.out.println("Sending " + msg + " via HTTP Protocol");
    }
}
