/**
 * Invoker.java
 * Created by liurenyong at 2013-12-27
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.命令模式;

/**
 * 
 * @author liurenyong 2013-12-27
 */
public class Invoker {

    /** 具体命令 */
    private Command command = null;
    
    /**
     * @param command the command to set
     */
    public void setCommand(Command command) {
        this.command = command;
    }
    
    /** 
     * 要求命令执行请求
     */
    public void execute() {
        this.command.execute();
    }
}
