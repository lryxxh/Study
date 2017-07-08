/**
 * Caretaker.java
 * Created by liurenyong at 2013-12-20
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.����¼ģʽ;

/**
 * ���𱣴汸��¼�Ķ���
 * @author liurenyong 2013-12-20
 */
public class Caretaker {
    
    /** ��¼������ı���¼���� */
    private Memento memento = null;
    
    /** 
     * ���汸��¼����
     * @param memento
     */
    public void saveMemento(Memento memento) {
        this.memento = memento;
    }
    
    /** 
     * ��ȡ������ı���¼����
     * @return
     */
    public Memento retriveMemento() {
        return this.memento;
    }

}
