/**
 * Aggregate.java
 * Created by liurenyong at 2013-12-24
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.迭代器模式;

/**
 * 聚合对象接口，定义创建相应迭代器的对象的接口。
 * @author liurenyong 2013-12-24
 */
public abstract class Aggregate {

    /** 
     * 工厂方法，创建相应迭代器对象的接口。
     * @return
     */
    public abstract Iterator createIterator();
}
