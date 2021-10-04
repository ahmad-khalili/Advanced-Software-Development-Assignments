package com.company;

public class TFTPProtocol{
    private static TFTPProtocol instance;
    private static boolean connected = false;
    public boolean isConnected() {
        return connected;
    }
    /**
     *
     */
    private TFTPProtocol() {
        connected = true;
    }

    /**
     *
     * @return
     */
    public static TFTPProtocol getTFTPInstance() {
        if (instance == null) {
            instance = new TFTPProtocol();
        }
        return instance;
    }

    public boolean releaseTFTP() {
        if(instance != null) {
            instance = null;
            return true;
        }
        return false;
    }

    public void sendMessage(String message) {
        System.out.println("I am sending a " + message + " via TFTP Protocol");

    }

}