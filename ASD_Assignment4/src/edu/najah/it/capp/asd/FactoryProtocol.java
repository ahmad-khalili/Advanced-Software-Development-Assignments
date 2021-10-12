package edu.najah.it.capp.asd;

import edu.najah.it.capp.asd.constants.ConnectionType;
import edu.najah.it.capp.asd.impl.Ftp;
import edu.najah.it.capp.asd.impl.Scp;
import edu.najah.it.capp.asd.impl.Ssh;
import edu.najah.it.capp.asd.impl.Telnet;
import edu.najah.it.capp.asd.intf.Protocol;

import java.util.HashMap;
import java.util.Map;

public class FactoryProtocol {
    public static Map connections = new HashMap<String, Protocol>();
    public static Protocol getInstance(String connectionType) {
        if(connections.containsKey(connectionType)) {
            System.out.println("Connection is already created!.");
            return (Protocol) connections.get(connectionType);
        } else {
            if (connections.size() >= 3) {
                //do not create connection
                System.out.println("Can't create more than 3 connection!!");
                return null;
            }
            if (connectionType.equals(ConnectionType.FTP)) {
                connections.put(connectionType, Ftp.getInsatnce());
                return Ftp.getInsatnce();//Create a new instance
            }
            if (connectionType.equals(ConnectionType.SSH)) {
                connections.put(connectionType, Ssh.getInsatnce());
                return Ssh.getInsatnce();
            }
            if (connectionType.equals(ConnectionType.TELNET)) {
                connections.put(connectionType, Telnet.getInsatnce());
                return Telnet.getInsatnce();
            }
            if (connectionType.equals(ConnectionType.SCP)) {
                connections.put(connectionType, Scp.getInsatnce());
                return Scp.getInsatnce();
            }
        }
        return null;
    }
}
