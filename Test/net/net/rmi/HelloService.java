/**
 * HelloService.java
 * Created by liurenyong at 2013-9-24
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package net.rmi;

import java.io.IOException;
import java.rmi.Remote;

/**
 * 
 * @author liurenyong 2013-9-24
 */
public interface HelloService extends Remote{
    
    public String say(String word) throws IOException;
    public void set(String word) throws IOException;
    public void cancelTask() throws IOException;
}
