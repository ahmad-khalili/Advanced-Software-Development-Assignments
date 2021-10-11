package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Protocol {

    public static Map connections = new HashMap<String, Protocol>();

    //getInstance, createConnection , getConnection
    //release (String type)
    //getCurrentConnections
    public static Connection getInstance(String connectionType) {
        if (connections.containsKey(connectionType)) {
            System.out.println("Connection is already created!.");
            return (Connection) connections.get(connectionType);
        } else {
            if (connections.size() >= 3) {
                //do not create connection
                System.out.println("Can't create more than 3 connection!!");
                return null;
            }
            if (connectionType.equals(ConnectionType.FTP)) {
                connections.put(connectionType, Ftp.getInstance());
                return Ftp.getInstance();//Create a new instance
            }
            if (connectionType.equals(ConnectionType.SSH)) {
                connections.put(connectionType, Ssh.getInstance());
                return Ssh.getInstance();
            }
            if (connectionType.equals(ConnectionType.TELNET)) {
                connections.put(connectionType, Telnet.getInstance());
                return Telnet.getInstance();
            }
            if (connectionType.equals(ConnectionType.SCP)) {
                connections.put(connectionType, Scp.getInstance());
                return Scp.getInstance();
            }
        }
        return null;

    }
    public static boolean release(String connectionType) {
        if (connections.containsKey(connectionType)) {
            connections.remove(connectionType);
            if (connectionType.equals(ConnectionType.FTP)) {
                Ftp.getInstance().release();

            } else if (connectionType.equals(ConnectionType.SSH)) {
                Ssh.getInstance().release();

            } else if (connectionType.equals(ConnectionType.TELNET)) {
                Telnet.getInstance().release();

            } else if (connectionType.equals(ConnectionType.SCP)) {
                Scp.getInstance().release();

            }
            return true;
        }

        return false;

    }

    public static ArrayList<String> getCurrentConnections() {
        Set<String> keys = connections.keySet();

        ArrayList<String> myConnection = new ArrayList<String>();
        for (String key : keys) {
            myConnection.add(key);
        }
        return myConnection;


    }
}