package com.company;

public class Ftp implements Connection {
	
	private static Connection instance;
	
	
	public Ftp() {
		System.out.println("Creating a new FTP instance");
	}
	
	public static Connection getInstance() {
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
		System.out.println("Sending message from FTP :: " + message);

	}

}
