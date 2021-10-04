package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Protocol factory = new Protocol();
        TFTPAdapter adapter = new TFTPAdapter();
        Connection telnetConnection = factory.getInstance("TELNET");

        Connection telnetConnection2 = factory.getInstance("TELNET");

        Connection sshConnection = factory.getInstance("SSH");

        ArrayList<String> currentConnections = factory.getCurrentConnections();

        System.out.println(currentConnections);

        Connection httpConnection = factory.getInstance("HTTP");

        Connection scpConnection = factory.getInstance("SCP");

        boolean isReleased = factory.release("SSH");

        Connection ftpConnection = factory.getInstance("FTP");

        currentConnections = factory.getCurrentConnections();

        System.out.println(currentConnections);

        ftpConnection.send("My message");

        sshConnection.send("My message");

        Connection tftpConnection = adapter.getInstance("TFTP");

        currentConnections = adapter.getCurrentConnections();

        System.out.println(currentConnections);

        tftpConnection.send("Hi");

        boolean isReleased1 = adapter.release();

        currentConnections = adapter.getCurrentConnections();

        System.out.println(currentConnections);
    }
}
