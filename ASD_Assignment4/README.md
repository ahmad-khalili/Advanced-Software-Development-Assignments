# Assignment 4
###  Part 1:
- The factory class called "FactoryProtocol" contains the "getInstance" method to create an object based on the given connection type. It places each places that connection in a HashMap instead of using an array. It also prevents the user from creating an already exsisting connection, or creating more than 3 connections.
```ruby
public class FactoryProtocol {
    public static Map connections = new HashMap<String, Protocol>();
    public static Protocol getInstance(String connectionType) {
        if(connections.containsKey(connectionType)) {
            System.out.println("Connection is already created!.");
            return (Protocol) connections.get(connectionType);
        } else {
            if (connections.size() >= 3) {
                //do not create connection
                System.out.println("Can't create more than 3 connection!!");
                return null;
            }
            if (connectionType.equals(ConnectionType.FTP)) {
                connections.put(connectionType, Ftp.getInsatnce());
                return Ftp.getInsatnce();//Create a new instance
            }
            if (connectionType.equals(ConnectionType.SSH)) {
                connections.put(connectionType, Ssh.getInsatnce());
                return Ssh.getInsatnce();
            }
            if (connectionType.equals(ConnectionType.TELNET)) {
                connections.put(connectionType, Telnet.getInsatnce());
                return Telnet.getInsatnce();
            }
            if (connectionType.equals(ConnectionType.SCP)) {
                connections.put(connectionType, Scp.getInsatnce());
                return Scp.getInsatnce();
            }
        }
        return null;
    }
}
```
- The "Connection" class has the method getInstance that calls the factory class's get instance, for the sake of not affecting the client-side. It also uses the "FactoryProtocol" connections HashMap to release and get current connections.
```ruby
public class Connection {
	//getInstance, createConnection , getConnection
	//release (String type)
	//getCurrentConnections
	public static Protocol getInstance(String connectionType) {
		return FactoryProtocol.getInstance(connectionType);
	}
```
```ruby
public static boolean release(String connectionType) {
		if(FactoryProtocol.connections.containsKey(connectionType)) {
			FactoryProtocol.connections.remove(connectionType);
			if(connectionType.equals(ConnectionType.FTP)) {
				Ftp.getInsatnce().release();
				
			} else if(connectionType.equals(ConnectionType.SSH)) {
				Ssh.getInsatnce().release();
				
			} else if(connectionType.equals(ConnectionType.TELNET)) {
				Telnet.getInsatnce().release();
				 
			} else if(connectionType.equals(ConnectionType.SCP) ) {
				Scp.getInsatnce().release();
				 
			}
			return true;
		}
		return false;
	}
	public static ArrayList<String> getCurrentConnections() {
		Set<String> keys = FactoryProtocol.connections.keySet();
		
		ArrayList<String> myConnection =  new ArrayList<String>();
		for (String key : keys) {
			myConnection.add(key);
		}
		return myConnection;
```
### Part 2:
![](https://github.com/ahmad-khalili/Advanced-Software-Development-Assignments/blob/main/Images/Connection_UML.png?raw=true)
