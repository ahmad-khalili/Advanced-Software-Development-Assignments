# Assignment 4
###  Part 1:
- The factory class called "Protocol" contains the "getInstance" method to create an object based on the given connection type. It places each places that connection in a HashMap instead of using an array. It also prevents the user from creating an already exsisting connection, or creating more than 3 connections.
```ruby
public static Connection getInstance(String connectionType) {
        if (connections.containsKey(connectionType)) {
            System.out.println("Connection is already created!.");
            return (Connection) connections.get(connectionType);
        } else {
            if (connections.size() >= 3) {
                //do not create connection
                System.out.println("Can't create more than 3 connection!!");
                return null;
            }
            if (connectionType.equals(ConnectionType.FTP)) {
                connections.put(connectionType, Ftp.getInstance());
                return Ftp.getInstance();//Create a new instance
            }
            if (connectionType.equals(ConnectionType.SSH)) {
                connections.put(connectionType, Ssh.getInstance());
                return Ssh.getInstance();
            }
            if (connectionType.equals(ConnectionType.TELNET)) {
                connections.put(connectionType, Telnet.getInstance());
                return Telnet.getInstance();
            }
            if (connectionType.equals(ConnectionType.SCP)) {
                connections.put(connectionType, Scp.getInstance());
                return Scp.getInstance();
            }
        }
        return null;
```
- The "release" method removes the given connection type from the HashMap and uses the "release" method from the protocol classes, which in return sets the instance's value to null. And then returns true if the connection exists and false otherwise.
```ruby
public static boolean release(String connectionType) {
        if (connections.containsKey(connectionType)) {
            connections.remove(connectionType);
            if (connectionType.equals(ConnectionType.FTP)) {
                Ftp.getInstance().release();

            } else if (connectionType.equals(ConnectionType.SSH)) {
                Ssh.getInstance().release();

            } else if (connectionType.equals(ConnectionType.TELNET)) {
                Telnet.getInstance().release();

            } else if (connectionType.equals(ConnectionType.SCP)) {
                Scp.getInstance().release();

            }
            return true;
        }

        return false;
```
- The "getCurrentConnections" method takes all the keys from the HashMap "connections", which are the created connections, and places them in a newly created ArrayList called "myConnection", then returns that ArrayList.
```ruby
public static ArrayList<String> getCurrentConnections() {
        Set<String> keys = connections.keySet();

        ArrayList<String> myConnection = new ArrayList<String>();
        for (String key : keys) {
            myConnection.add(key);
        }
        return myConnection;
```
### Part 2:
![](https://github.com/ahmad-khalili/Advanced-Software-Development-Assignments/blob/main/Images/Connection_UML.jpg?raw=true)
