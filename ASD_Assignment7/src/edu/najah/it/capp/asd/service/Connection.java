package edu.najah.it.capp.asd.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import edu.najah.it.capp.asd.impl.TFTPAdpter;
import edu.najah.it.capp.asd.constants.ConnectionType;
import edu.najah.it.capp.asd.impl.Ftp;
import edu.najah.it.capp.asd.impl.ProtocolFactory;
import edu.najah.it.capp.asd.impl.Scp;
import edu.najah.it.capp.asd.impl.Ssh;
import edu.najah.it.capp.asd.impl.Telnet;
import edu.najah.it.capp.asd.intf.Protocol;
import edu.najah.it.capp.exception.ProtocolException;
import edu.najah.it.capp.logger.Logger;

public class Connection {
	
	
	
	public static Map connections = new HashMap<String, Protocol>();

	//getInstance, createConnection , getConnection
	public static Protocol getInstance(String connectionType) throws ProtocolException {
		int timeoutCount = 0;
		final int READY_STATUS = 100;
		final int BUSY_STATUS = 999;
		int status = READY_STATUS;
		if (connections.containsKey(connectionType)) {
			System.out.println("Connection is already created!.");
			Logger.getInstance().logInfo("Revoked " + connectionType + " connection (Connection Already Created)");
			return (Protocol) connections.get(connectionType);
		} else {
			try {
				if (connections.size() >= 3) {
					Logger.getInstance().logInfo("Revoked " + connectionType + " connection (Reached the maximum number of connections)");
					throw new ProtocolException("No connections available");
				}
				if (timeoutCount >= 30) {
					Logger.getInstance().logWarning("Failed to create " + connectionType + " connection (System timeout)");
					throw new ProtocolException("Timeout Error");
				}

				if (status == BUSY_STATUS) {
					Logger.getInstance().logWarning("Failed to create " + connectionType + " connection (System Busy)");
					throw new ProtocolException("System is busy");
				}

				Protocol instance = ProtocolFactory.createProcol(connectionType);
				connections.put(connectionType, instance);
				Logger.getInstance().logInfo("Created " + connectionType + " connection");
				return instance;
			}
			catch (ProtocolException ex){
				System.out.println(ex.getMessage());
				return null;
			}
		}
	}
	
	public static boolean release(String connectionType) throws ProtocolException {
		if (connections.containsKey(connectionType)) {
			connections.remove(connectionType);
			ProtocolFactory.createProcol(connectionType).release();
			Logger.getInstance().logInfo("Released " + connectionType + " connection");
			return true;
		}
		try {
			if (!connections.containsKey(connectionType)) {
				Logger.getInstance().logError("Failed to release " + connectionType + " connection (Connection Already Released)");
				throw new ProtocolException("Connection was already released");
			}

			Logger.getInstance().logError("Failed to release " + connectionType + " connection (Unknown Error)");
			throw new ProtocolException("Unknown Error");
		}
		catch(ProtocolException ex){
			System.out.println(ex.getMessage());
			return false;
		}
		finally{
				connections.remove(connectionType);

				ProtocolFactory.createProcol(connectionType).release();
		}
	}
	public static ArrayList<String> getCurrentConnections() {
		Set<String> keys = connections.keySet();

		ArrayList<String> myConnection =  new ArrayList<String>();
		for (String key : keys) {
			myConnection.add(key);
		}
		return myConnection;
		
		
	}
}
