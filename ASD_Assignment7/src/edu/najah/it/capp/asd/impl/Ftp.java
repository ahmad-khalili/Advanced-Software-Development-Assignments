package edu.najah.it.capp.asd.impl;

import edu.najah.it.capp.asd.intf.Protocol;
import edu.najah.it.capp.logger.Logger;

public class Ftp implements Protocol {
	
	private static Protocol instance;
	
	
	private Ftp() {
		System.out.println("Creating a new FTP insatnce");
	}
	
	protected static Protocol getInsatnce() {
		if(instance == null) {
			instance = new Ftp();
		}
		return instance;
	}
	@Override
	public boolean release() {
		instance = null;
		return true;
	}

	@Override
	public void send(String message) {
		Logger.getInstance().logInfo("FTP message sent");
		System.out.println("Sending message from FTP :: " + message);

	}

}
