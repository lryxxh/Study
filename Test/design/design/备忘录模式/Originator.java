/**
 * Originator.java
 * Created by liurenyong at 2013-12-20
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.����¼ģʽ;

/**
 * ԭ��������
 * @author liurenyong 2013-12-20
 */
public class Originator {

    /** ԭ����״̬ */
    private String state = "";
    
    /** 
     * ��������ԭ���������״̬�ı���¼����
     * @return
     */
    public Memento createMemento() {
        return new MementoImpl(state);
    }
    
    /** 
     * ��������ԭ���������״̬������ص�����¼�����¼��״̬��
     * @param memento
     */
    public void setMemento(Memento memento) {
        MementoImpl mementoImpl = (MementoImpl)memento;
        this.state = mementoImpl.getState();
    }
    
    /**
     * �����ı���¼����ʵ�ֱ���¼խ�ӿ�
     * ʵ�ֳ�˽�е��ڲ��࣬�����ⲿ����
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
