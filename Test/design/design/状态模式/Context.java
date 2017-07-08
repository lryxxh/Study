/**
 * Context.java
 * Created by liurenyong at 2013-12-20
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.状态模式;

/**
 * 状态上下文，通过用来定义客户感兴趣的接口，同时维护一个具体的处理当前状态的实例对象。
 * @author liurenyong 2013-12-20
 */
public class Context {

    /** 状态 */
    private State state = null;
    
    /**
     * @param state the state to set
     */
    public void setState(State state) {
        this.state = state;
    }
    
    public void request(String type) {
        state.handle(type);
    }

}
