/**
 * Originator.java
 * Created by liurenyong at 2013-12-20
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.备忘录模式;

/**
 * 原发器对象，
 * @author liurenyong 2013-12-20
 */
public class Originator {

    /** 原发器状态 */
    private String state = "";
    
    /** 
     * 创建保存原发器对象的状态的备忘录对象。
     * @return
     */
    public Memento createMemento() {
        return new MementoImpl(state);
    }
    
    /** 
     * 重新设置原发器对象的状态，让其回到备忘录对象记录的状态。
     * @param memento
     */
    public void setMemento(Memento memento) {
        MementoImpl mementoImpl = (MementoImpl)memento;
        this.state = mementoImpl.getState();
    }
    
    /**
     * 真正的备忘录对象，实现备忘录窄接口
     * 实现成私有的内部类，不让外部访问
     * @author liurenyong 2013-12-20
     */
    private static class MementoImpl implements Memento {
        private String state = "";
        
        public MementoImpl(String state) {
            this.state = state;
        }
        
        /**
         * @return the state
         */
        public String getState() {
            return state;
        }
    }
}
