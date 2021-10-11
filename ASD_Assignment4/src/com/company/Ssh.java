package com.company;

public class Ssh implements Connection {

private static Connection instance;
	
	
	private Ssh() {
		System.out.println("Creating a new SSH instance");
	}
	
	public static Connection getInstance() {
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
		System.out.println("Sending message from SSH :: " + message);

	}

}
