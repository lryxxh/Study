/**
 * Context.java
 * Created by liurenyong at 2013-12-18
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.����ģʽ;

/**
 * �����ġ�
 * @author liurenyong 2013-12-18
 */
public class Context {

    Strategy strategy;

    /*****************ֱ�Ӵ���Strategy*********************/
    public Context(Strategy strategy) {
        this.strategy = strategy;
    }
    
    public void contextInterface() {
        strategy.algorithmInterface();
    }
    /*****************************************************/
    
    
    /***************��򵥹�����ϣ������������ͣ�Strategy��Context�й���*******************************/
    public Context(String type) {
        if ("A".equals(type)) {
            strategy = new ConcreateStrategyA();
        } else {
            strategy = new ConcreateStrategyB();
        }
    }
    /*****************************************************/
}
