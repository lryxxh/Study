/**
 * Invoker.java
 * Created by liurenyong at 2013-12-27
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.����ģʽ;

/**
 * 
 * @author liurenyong 2013-12-27
 */
public class Invoker {

    /** �������� */
    private Command command = null;
    
    /**
     * @param command the command to set
     */
    public void setCommand(Command command) {
        this.command = command;
    }
    
    /** 
     * Ҫ������ִ������
     */
    public void execute() {
        this.command.execute();
    }
}
