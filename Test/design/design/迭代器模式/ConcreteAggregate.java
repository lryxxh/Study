/**
 * ConcreteAggregate.java
 * Created by liurenyong at 2013-12-24
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.迭代器模式;


/**
 * 
 * @author liurenyong 2013-12-24
 */
public class ConcreteAggregate extends Aggregate{
    
    private String[] ss = null;
    
    /**
     * 
     * @author liurenyong 2013-12-24
     */
    public ConcreteAggregate(String[] ss) {
        this.ss = ss;
    }

    /* (non-Javadoc)
     * @see design.迭代器模式.Aggregate#createIterator()
     */
    @Override
    public Iterator createIterator() {
        return new ConcreteIterator(this);
    }
    
    public Object get(int index) {
        Object retObject = null;
        if (index < ss.length) {
            retObject = ss[index];
        }
        return retObject;
    }
    
    public int size() {
        return this.ss.length;
    }

}
