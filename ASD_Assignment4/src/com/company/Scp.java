package com.company;

public class Scp implements Connection {

private static Connection instance;
	
	
	private Scp() {
		System.out.println("Creating a new SCP instance");
	}
	
	public static Connection getInstance() {
		if(instance == null) {
			instance = new Scp();
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
		System.out.println("Sending message from SCP :: " + message);

	}

}
