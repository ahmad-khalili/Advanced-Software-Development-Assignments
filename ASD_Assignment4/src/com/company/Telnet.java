package com.company;

public class Telnet implements Connection {

private static Connection instance;
	
	
	private Telnet() {
		System.out.println("Creating a new Telnet instance");
	}
	
	public static Connection getInstance() {
		if(instance == null) {
			instance = new Telnet();
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
		System.out.println("Sending message from TELNET :: " + message);

	}

}
