/**
 * Aggregate.java
 * Created by liurenyong at 2013-12-24
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.������ģʽ;

/**
 * �ۺ϶���ӿڣ����崴����Ӧ�������Ķ���Ľӿڡ�
 * @author liurenyong 2013-12-24
 */
public abstract class Aggregate {

    /** 
     * ����������������Ӧ����������Ľӿڡ�
     * @return
     */
    public abstract Iterator createIterator();
}
