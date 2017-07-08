/**
 * ConcreteIterator.java
 * Created by liurenyong at 2013-12-24
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.迭代器模式;

/**
 * 
 * @author liurenyong 2013-12-24
 */
public class ConcreteIterator implements Iterator{
    
    /** 持有被迭代的具体的聚合对象 */
    private ConcreteAggregate aggregate;
    
    /** 内部索引，记录当前迭代到的索引位置，-1表示刚开始的时候，迭代器指向聚合对象第一个对象之前 */
    private int index = -1; 
    
    /**
     * 
     * @author liurenyong 2013-12-24
     */
    public ConcreteIterator(ConcreteAggregate aggregate) {
        this.aggregate = aggregate;
    }

    /* (non-Javadoc)
     * @see design.迭代器模式.Iterator#first()
     */
    @Override
    public void first() {
        index = 0;
    }

    /* (non-Javadoc)
     * @see design.迭代器模式.Iterator#next()
     */
    @Override
    public void next() {
        if (index < this.aggregate.size()) {
            index ++;
        }
    }

    /* (non-Javadoc)
     * @see design.迭代器模式.Iterator#isDone()
     */
    @Override
    public boolean isDone() {
        if (index == this.aggregate.size()) {
            return true;
        }
        return false;
    }

    /* (non-Javadoc)
     * @see design.迭代器模式.Iterator#currentObject()
     */
    @Override
    public Object currentObject() {
        return this.aggregate.get(index);
    }

}
