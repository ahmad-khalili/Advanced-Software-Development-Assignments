package com.company;

public class Main {

    public static void main(String[] args) {

        Connection ssh = Protocol.getInstance(ConnectionType.SSH);
        Connection ssh2 = Protocol.getInstance(ConnectionType.SSH);
        Connection telnet = Protocol.getInstance(ConnectionType.TELNET);
        Connection scp = Protocol.getInstance(ConnectionType.SCP);
        Connection ftp = Protocol.getInstance(ConnectionType.FTP);


        if(ssh == ssh2) {
            System.out.println(" ssh is equal to ssh2");
        }
        ssh.send(" testing ssh ");
        telnet.send("Testing telnet ");
        scp.send("Testing scp");


        System.out.println(Protocol.getCurrentConnections());
        Protocol.release(ConnectionType.SSH);
        System.out.println(Protocol.getCurrentConnections());

        ftp = Protocol.getInstance(ConnectionType.FTP);
        System.out.println(Protocol.getCurrentConnections());


        ssh = Protocol.getInstance(ConnectionType.SSH);
        ftp = Protocol.getInstance(ConnectionType.FTP);
        ftp = Protocol.getInstance(ConnectionType.FTP);

        ftp = Protocol.getInstance(ConnectionType.FTP);
        ftp.send("Testing FTP");



    }

}