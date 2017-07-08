/**
 * Colleague.java
 * Created by liurenyong at 2013-12-27
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.中介者模式;

/**
 * 
 * @author liurenyong 2013-12-27
 */
public abstract class Colleague {
    
    private Mediator mediator;
    
    /**
     * 
     * @author liurenyong 2013-12-27
     */
    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }
    
    /**
     * @return the mediator
     */
    public Mediator getMediator() {
        return mediator;
    }

}
