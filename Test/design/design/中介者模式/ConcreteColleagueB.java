/**
 * ConcreteColleagueA.java
 * Created by liurenyong at 2013-12-27
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.�н���ģʽ;

/**
 * 
 * @author liurenyong 2013-12-27
 */
public class ConcreteColleagueB extends Colleague{

    /**
     * 
     * @param mediator
     * @author liurenyong 2013-12-27
     */
    public ConcreteColleagueB(Mediator mediator) {
        super(mediator);
    }
    
    public void someOperation() {
        getMediator().change(this);
    }

}
