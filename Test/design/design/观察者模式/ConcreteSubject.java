/**
 * ConcreteSubject.java
 * Created by liurenyong at 2013-12-20
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.观察者模式;

/**
 * 
 * @author liurenyong 2013-12-20
 */
public class ConcreteSubject extends Subject {
    private String state = null;
    
    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
        super.notifyObservers();
    }
    
    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

}
