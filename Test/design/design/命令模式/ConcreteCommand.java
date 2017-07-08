/**
 * ConcreteCommand.java
 * Created by liurenyong at 2013-12-27
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.命令模式;


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
     * @see design.命令模式.Command#execute()
     */
    @Override
    public void execute() {
        receiver.action();
    }

}
