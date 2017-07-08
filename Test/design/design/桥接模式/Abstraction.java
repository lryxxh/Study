/**
 * Abstraction.java
 * Created by liurenyong at 2013-12-24
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.�Ž�ģʽ;

/**
 * 
 * @author liurenyong 2013-12-24
 */
public abstract class Abstraction {

    protected Implementor implementor;
    
    /**
     * 
     * @author liurenyong 2013-12-24
     */
    public Abstraction(Implementor implementor) {
        this.implementor = implementor;
    }
    
    /** 
     * ʵ��һ���Ĺ��ܣ�������Ҫת��ʵ�ֲ��ֵľ���ʵ�ַ�����
     */
    public void operation() {
        implementor.operationImpl();
    }
}
