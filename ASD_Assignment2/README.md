# Assignment 2: Singleton Design Pattern
- I created a class called "Connnection" and assigned an object to each protocol type. I  created an ArrayList element to store the connections to use it later on in the "getCurrentConnections()" method. I also defined an integer type id to use it for connection type identifying in the "send()" method (made it public to help you in testing the code).
```ruby
public class Connection {
    private static final Connection tel_in = new Connection();
    private static final Connection http_in = new Connection();
    private static final Connection ssh_in = new Connection();
    private static final Connection scp_in = new Connection();
    private static final Connection ftp_in = new Connection();
    static ArrayList<String> connections = new ArrayList<>();
    public static final String TELNET = "TELNET";
    public static final String HTTP = "HTTP";
    public static final String SSH = "SSH";
    public static final String FTP = "FTP";
    public static final String SCP = "SCP";
    public int id = 0;
```
- I then created the "getInstance()" method containing:
    - An if statement to check if the current connections are equal to or more than 3.
    - A switch statement to check for the connection protocol. Creating an object for each protocol, assigning the (id) for each protocol (I used a non-static variable in a static method so each object could have its own unique id), to later be used in "send()", and lastly adding the connection protocol to the list, to be used in "getCurrentConnections()".
```ruby
public static Connection getInstance(String connectionProtocol) {
        if(connections.size() >= 3){
            System.out.println("You can't create more than 3 connections!");
            return null;
        }
        else {
            switch (connectionProtocol) {
                case TELNET:
                    Connection tel_in = new Connection();
                        tel_in.id = 1;
                    connections.add(TELNET);
                    return tel_in;
                case HTTP:
                    Connection http_in = new Connection();
                        http_in.id = 2;
                    connections.add(HTTP);
                    return http_in;
                case SSH:
                    Connection ssh_in = new Connection();
                        ssh_in.id = 3;
                    connections.add(SSH);
                    return ssh_in;
                case FTP:
                    Connection ftp_in = new Connection();
                        ftp_in.id = 4;
                    connections.add(FTP);
                    return ftp_in;
                case SCP:
                    Connection scp_in = new Connection();
                        scp_in.id = 5;
                    connections.add(SCP);
                    return scp_in;
                default:
                    System.out.println("Unidentified Protocol!");
                    return null;
```
- The "release()" method is a bolean type method to return true if the the specified connection protocol is in the list while removing it, and false if it's not.
```ruby
public static boolean release(String connectionProtocol){
        if (connections.contains(connectionProtocol)){
            connections.remove(connectionProtocol);
            return true;
        }
        else{
            return false;
```
- The "getCurrentConnections()" methods returns an ArrayList<String> since I used an ArrayList of type string to store the current connections.
```ruby
public static ArrayList<String> getCurrentConnections(){
        return connections;
    }
```
- The "send()" method takes a string parameter, which is the message, and then prints out that message with the connection protocol (using the id we set in the "getInstance()" method) its been called from.
```ruby
public void send(String msg){
        if (id == 1){
            System.out.println("Sending [" + msg + "] via TELNET protocol");
        }
        else if (id == 2){
            System.out.println("Sending [" + msg + "] via HTTP protocol");
        }
        else if (id == 3){
            System.out.println("Sending [" + msg + "] via SSH protocol");
        }
        else if (id == 4){
            System.out.println("Sending [" + msg + "] via FTP protocol");
        }
        else if (id == 5){
            System.out.println("Sending [" + msg + "] via SCP protocol");
```
P.S: I tried handling the exception for the null connections but it didn't work out, and I wouldn't have made it in time for the deadline.
