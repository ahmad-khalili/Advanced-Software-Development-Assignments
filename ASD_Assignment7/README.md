# Assignment 7
### Part 1
##### 1- Release Method
- I let the system check for the connections before the try and catch statements since it
doesn't/shouldn't throw any exceptions if its true. Then, in the inside the try statement:
    - I checked if the connection type doesn't exist and threw a protocol exception (I assumed that a connection was already released if it didn't exist in the hashmap).
    - I threw an "Unknown Error" exception at anything that didn't specify the above criteria
- However, I didn't create an exception for the (connection in use) since I wouldn't know when a connection is actually in use and I couldn't assume it was the last created connection in the hashmap because hashmaps aren't ordered. To assume it was the last one I could've created a treemap or specified a key for each entry in the already created hashmap.
- Finally, I caught the exceptions thrown in the try statement and printed out the expcetion along with the exceptions' messages and returned false as in the connection release was unsuccesful to end the program. I also included a finally statement to make sure that the connections are always released since that was specified in the assignment's requirements.
```ruby
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
```
##### 2- GetInstance Method
- In this method, I kept the "already existing connection if statement" outside of the try statement, if the condition was not met then we check for the 3 required exceptions:
    - Check for the hashmap's size, if it's larger than 3 (which is the maximum ammount of connections), throw a "No connection available" exception.
    - Check for the value of the timeout (I assumed we had a timeout for the connections in this case) and if it's equal to that or larger than it, then throw a "Timeout Exceeded" exception.
    - Lastly, check if the system is busy or not, using the constant value of "BUSY_STATE" that you created in a seperate project, and if the status was matching the busy state, then throw a "System is busy" exception.
- If none of the if statements condition were met, then create the connection and add it to the hashmap. And in the catch statement, I printed out the exception's message and returned null.
```ruby
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
```
### Part 2
- In the "Logger" class, I used an ArrayList to add the messages of the logging methods in. Then I created the "PrintLogs" method that prints the contents of that ArrayList at the end of the program since that would be more orgranized than to print out the log in between the program's execution (I printed the "INFO"/"DEBUG" as normal strings and "WARNING"/"ERROR" as errors just like you declared). I also created another method that saves the logs to a text file called "SaveLogs". I used logging the connection class (You can see where the logging methods where called in the above code snippets) and in the protocol classes in the send methods (Can be viewed here).

- Send Methods:
```ruby
public void send(String message) {
		Logger.getInstance().logInfo("SSH message sent");
		System.out.println("Sending message from SSH :: " + message);
```

- Logger Class: 
```ruby
public void logInfo(String message) { logsArray.add(java.time.LocalDateTime.now()  + " [INFO] " + message); }

	public void logDebug(String message) {
		logsArray.add(java.time.LocalDateTime.now()  + " [Debug] " + message);
	}

	public void logWarning(String message) {
		logsArray.add(java.time.LocalDateTime.now()  + " [Warn] " + message);
	}

	public void logError(String message) {
		logsArray.add(java.time.LocalDateTime.now()  + " [Error] " + message);
	}

	public static void PrintLogs(){
		System.out.println("\nLogs: ");
		for(int i = 0;i<logsArray.size();i++){
			if(logsArray.get(i).contains("[Error]") || logsArray.get(i).contains("[Warn]"))
			System.err.println(logsArray.get(i) + "\n");

			else System.out.println(logsArray.get(i) + "\n");
		}
	}

	public static void SaveLogs(){
		try{
			FileWriter out = new FileWriter("./logs.txt",true);
			for(int i =0;i<logsArray.size();i++)
				out.write(logsArray.get(i) + "\n");

			out.close();
		}
		catch(IOException ex){
			System.out.println("IOException: " + ex.getMessage());
			return;
```
### Why loggers use the Singleton Design Pattern?
- Because in logging, you're usually printing logs while seperate parts of the system are executing and these logs need to be printed in the same file (like "logs.txt" in my solution) and without a single object, these logs would be a mess. So instead of using a normal class for a logger and calling its constructor, we define a static attribute of that class and instantiate that attribute as a new object in a method called "getInstance()" and we call it instead. This may not hugely affect an application when its a single logger, but if they're more than one logger, they would conflict with each other when printing in the same log file.
