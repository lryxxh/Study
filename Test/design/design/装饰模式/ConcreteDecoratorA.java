/**
 * ConcreteDecoratorA.java
 * Created by liurenyong at 2013-12-18
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.װ��ģʽ;

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
    
    /** ���ӵ�״̬ */
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
        //��Ҫ��ӵ�ְ��ʵ��
    }
    
    /* (non-Javadoc)
     * @see design.װ��ģʽ.Decorator#operate()
     */
    @Override
    public void operate() {
        //���Դ���һЩ�����Ĳ���
        super.operate();
    }

}
