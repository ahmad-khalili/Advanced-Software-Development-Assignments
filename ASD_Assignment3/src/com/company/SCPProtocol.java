package com.company;

public class SCPProtocol  implements Connection {
    @Override
    public void send(String msg) {
        System.out.println("Sending " + msg + " via SCP Protocol");
    }
}
