/**
 * ConcreteCommand.java
 * Created by liurenyong at 2013-12-27
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.����ģʽ;


/**
 * 
 * @author liurenyong 2013-12-27
 */
public class ConcreteCommand implements Command{
    private Receiver receiver = null;
    
    private String state = "";
    
    /**
     * @return the state
     */
    public String getState() {
        return state;
    }
    
    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    /* (non-Javadoc)
     * @see design.����ģʽ.Command#execute()
     */
    @Override
    public void execute() {
        receiver.action();
    }

}
