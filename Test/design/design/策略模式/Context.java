/**
 * Context.java
 * Created by liurenyong at 2013-12-18
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.策略模式;

/**
 * 上下文。
 * @author liurenyong 2013-12-18
 */
public class Context {

    Strategy strategy;

    /*****************直接传递Strategy*********************/
    public Context(Strategy strategy) {
        this.strategy = strategy;
    }
    
    public void contextInterface() {
        strategy.algorithmInterface();
    }
    /*****************************************************/
    
    
    /***************与简单工厂结合，参数传递类型，Strategy在Context中构造*******************************/
    public Context(String type) {
        if ("A".equals(type)) {
            strategy = new ConcreateStrategyA();
        } else {
            strategy = new ConcreateStrategyB();
        }
    }
    /*****************************************************/
}
