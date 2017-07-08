/**
 * ConcreteDecoratorA.java
 * Created by liurenyong at 2013-12-18
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.装饰模式;

/**
 * 
 * @author liurenyong 2013-12-18
 */
public class ConcreteDecoratorA extends Decorator{

    /**
     * 
     * @param component
     * @author liurenyong 2013-12-18
     */
    public ConcreteDecoratorA(Component component) {
        super(component);
    }
    
    /** 增加的状态 */
    private String addedState;
    
    /**
     * @return the addedState
     */
    public String getAddedState() {
        return addedState;
    }
    
    /**
     * @param addedState the addedState to set
     */
    public void setAddedState(String addedState) {
        this.addedState = addedState;
    }
    
    @SuppressWarnings("unused")
    private void addedBehavior() {
        //需要添加的职责实现
    }
    
    /* (non-Javadoc)
     * @see design.装饰模式.Decorator#operate()
     */
    @Override
    public void operate() {
        //可以处理一些其他的操作
        super.operate();
    }

}
