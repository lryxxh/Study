/**
 * RefinedAbstraction.java
 * Created by liurenyong at 2013-12-24
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.�Ž�ģʽ;

/**
 * 
 * @author liurenyong 2013-12-24
 */
public class RefinedAbstraction extends Abstraction{

    /**
     * 
     * @param implementor
     * @author liurenyong 2013-12-24
     */
    public RefinedAbstraction(Implementor implementor) {
        super(implementor);
    }

    public void otherOperation() {
        //ʵ��һ���Ĺ��ܣ����ܻ�ʹ�þ���ʵ�ֲ��ֵ�ʵ�ַ���
        //���Ǳ���������Ŀ�����ʹ��Abstraction�ж���ķ���
        //ͨ�����ʹ��Abstraction�ж���ķ�������ɸ���Ĺ���
    }
}
