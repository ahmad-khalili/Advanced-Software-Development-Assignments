package com.company;

public interface Connection {
    public boolean release();
    public void send(String msg);
}
