package com.company;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Connection telnetConnection = Connection.getInstance(Connection.TELNET);

        Connection telnetConnection2 = Connection.getInstance(Connection.TELNET);

        Connection sshConnection = Connection.getInstance(Connection.SSH);

        Connection httpConnection = Connection.getInstance(Connection.HTTP);

        Connection scpConnection = Connection.getInstance(Connection.SCP);

        boolean isReleased = Connection.release(Connection.SSH);

        Connection ftpConnection = Connection.getInstance(Connection.FTP);

        ArrayList<String> currentConnections = Connection.getCurrentConnections();

        ftpConnection.send("My message");

        sshConnection.send("My message");

    }

}