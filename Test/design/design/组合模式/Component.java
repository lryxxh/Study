/**
 * Component.java
 * Created by liurenyong at 2013-12-24
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.���ģʽ;

/**
 * ������������Ϊ����еĶ����ṩ�����ӿڣ�ʵ�ֽӿڵ�ȱʡ��Ϊ��
 * @author liurenyong 2013-12-24
 */
public abstract class Component {

    /** 
     * ʾ�ⷽ�����������������еĹ��ܷ�����
     */
    public abstract void someOperation();
    
    /** 
     * ����϶����м����������
     * @param child
     */
    public void addChild(Component child) {
        throw new UnsupportedOperationException();
    }
    
    /** 
     * ����϶������Ƴ��������
     * @param child
     */
    public void removeChild(Component child) {
        throw new UnsupportedOperationException();
    }
    
    /** 
     * ����ĳ��������Ӧ���������
     * @param index
     * @return
     */
    public Component getChildren(int index) {
        throw new UnsupportedOperationException();
    }
}
