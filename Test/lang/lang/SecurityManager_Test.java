/**
 * SecurityManager_Test.java
 * Created by liurenyong at 2013-10-29
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package lang;


/**
 * 
 * @author liurenyong 2013-10-29
 */
public class SecurityManager_Test {
    
    public static void main(String[] args) {
        new SecurityManager().checkConnect("192.168.60.22", 11012);
//        try {
//            Socket socket = new Socket("192.168.60.22", 11012);
//            System.out.println(socket.isConnected());
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
