/**
 * RMIServerInterface.java
 * Created by liurenyong at 2013-10-14
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package net.doublermi;

import java.beans.PropertyChangeListener;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 
 * @author liurenyong 2013-10-14
 */
public interface RMIServerInterface extends Remote, PropertyChangeListener{

    public void invoke(String a, String b) throws RemoteException;
}
