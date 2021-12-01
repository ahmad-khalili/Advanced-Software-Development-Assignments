package edu.najah.it.capp.asd.impl;

import edu.najah.it.capp.asd.intf.Protocol;
import edu.najah.it.capp.logger.Logger;

public class Ssh implements Protocol {

private static Protocol instance;
	
	
	private Ssh() {
		System.out.println("Creating a new SSH insatnce");
	}
	
	protected static Protocol getInsatnce() {
		if(instance == null) {
			instance = new Ssh();
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
		Logger.getInstance().logInfo("SSH message sent");
		System.out.println("Sending message from SSH :: " + message);

	}

}
