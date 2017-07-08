/**
 * ConcreteColleagueA.java
 * Created by liurenyong at 2013-12-27
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.中介者模式;

/**
 * 
 * @author liurenyong 2013-12-27
 */
public class ConcreteColleagueA extends Colleague{

    /**
     * 
     * @param mediator
     * @author liurenyong 2013-12-27
     */
    public ConcreteColleagueA(Mediator mediator) {
        super(mediator);
    }
    
    public void someOperation() {
        getMediator().change(this);
    }

}
