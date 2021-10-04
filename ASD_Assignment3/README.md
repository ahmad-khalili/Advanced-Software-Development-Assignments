# Assignment 3
###  Part 1:
- Instead of creating a class to house all connection protocols, I created an interface with only the "send" method. Then I created a class for each of the protocols that implements the connection interface.
```ruby
public interface Connection {
    void send(String msg);
```
```ruby
public class TELNETProtocol implements Connection {
    @Override
    public void send(String msg) {
        System.out.println("Sending " + msg + " via TELNET Protocol");
```
- Then I used the factory design pattern in a class called "Protocol" that handles:
1- Creating instances of each protocol, while maintaining the 3 concurrent connections limit.
2- Returning all ongoing connections in an ArrayList.
3- Releasing requested protocols.
```ruby
public class Protocol {
    Connection instance;
    ArrayList<String> connections = new ArrayList<>(10);
    public Connection getInstance(String protocol){
        if(protocol == null || connections.size() >= 3){
            System.out.println("You can't have more than 3 connections!");
            return null;
        }
        if(protocol.equals("FTP")){
            connections.add("FTP");
            return instance = new FTPProtocol();
        }
        if(protocol.equals("HTTP")){
            connections.add("HTTP");
            return instance = new HTTPProtocol();
        }
        if(protocol.equals("SSH")){
            connections.add("SSH");
            return instance = new SSHProtocol();
        }
        if(protocol.equals("SCP")){
            connections.add("SCP");
            return instance = new SCPProtocol();
        }
        if(protocol.equals("TELNET")){
            connections.add("TELNET");
            return instance = new TELNETProtocol();
        }
        return null;
    }
    public ArrayList getCurrentConnections(){
        return connections;
    }

    public boolean release(String protocol){
        if(connections.contains(protocol)){
            instance = null;
            connections.remove(protocol);
            return true;
        }
        return false;
```
### Part 2:
- Using the Adapter Design Pattern, I created a class called "TFTPAdapter" that inherits some elements from the "Protoctol" class (for the usage of the ArrayList), and implements the "send" method of the "Connection" interface. I also used the "getTFTPInstance" method from the legacy code to construct the "TFTPAdapter" object.
```ruby
public class TFTPAdapter extends Protocol implements Connection {
    TFTPProtocol tftp = TFTPProtocol.getTFTPInstance();
    public TFTPAdapter(){
        TFTPProtocol.getTFTPInstance();
```
- I then created the "getInstance" method that follows the same pattern found in "Protocol" class, which checks for the concurrent connections and initializes a TFTP connection if the requirements were met.
```ruby
public Connection getInstance(String protocol){
        if(connections.size() >= 3) {
            System.out.println("You can't have more than 3 connections!");
            return null;
        }
            if (protocol.equals("TFTP")) {
                connections.add("TFTP");
                return new TFTPAdapter();
            }
        return null;
```
- I used the "releaseTFTP" method found in the legacy code in the new method found in the adapter class with the adjustment of removing the protocol, if its found, from the active connections. Unfortunately, I couldn't add or release the "TFTP protocol" from the same connections array for the other protocols. So I had to use a new array specifically for this legacy protocol.
```ruby
public boolean release(){
        if(tftp.releaseTFTP() && connections.contains("TFTP")){
            tftp = null;
            connections.remove("TFTP");
            return true;
        }
        return false;
```
- Finally, I also used the old "sendMessage" method in the legacy code in the new adapter class to show messages are being sent using the TFTP protocol.
```ruby
@Override
    public void send(String msg){
        tftp.sendMessage(msg);
```
