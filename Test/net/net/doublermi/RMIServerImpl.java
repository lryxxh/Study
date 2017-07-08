/**
 * RMIServerImpl.java
 * Created by liurenyong at 2013-10-14
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package net.doublermi;

import java.beans.PropertyChangeEvent;
import java.rmi.RemoteException;

/**
 * 
 * @author liurenyong 2013-10-14
 */
public class RMIServerImpl implements RMIServerInterface{
    
    boolean flag = false;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        boolean flag = (Boolean)evt.getNewValue();
        setFlag(flag);
    }

    @Override
    public void invoke(String a, String b) throws RemoteException {
        
    }
    
    /**
     * @param flag the flag to set
     */
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    
    /**
     * @return the flag
     */
    public boolean isFlag() {
        return flag;
    }

}
