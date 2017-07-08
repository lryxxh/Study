/**
 * ConcreteIterator.java
 * Created by liurenyong at 2013-12-24
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.������ģʽ;

/**
 * 
 * @author liurenyong 2013-12-24
 */
public class ConcreteIterator implements Iterator{
    
    /** ���б������ľ���ľۺ϶��� */
    private ConcreteAggregate aggregate;
    
    /** �ڲ���������¼��ǰ������������λ�ã�-1��ʾ�տ�ʼ��ʱ�򣬵�����ָ��ۺ϶����һ������֮ǰ */
    private int index = -1; 
    
    /**
     * 
     * @author liurenyong 2013-12-24
     */
    public ConcreteIterator(ConcreteAggregate aggregate) {
        this.aggregate = aggregate;
    }

    /* (non-Javadoc)
     * @see design.������ģʽ.Iterator#first()
     */
    @Override
    public void first() {
        index = 0;
    }

    /* (non-Javadoc)
     * @see design.������ģʽ.Iterator#next()
     */
    @Override
    public void next() {
        if (index < this.aggregate.size()) {
            index ++;
        }
    }

    /* (non-Javadoc)
     * @see design.������ģʽ.Iterator#isDone()
     */
    @Override
    public boolean isDone() {
        if (index == this.aggregate.size()) {
            return true;
        }
        return false;
    }

    /* (non-Javadoc)
     * @see design.������ģʽ.Iterator#currentObject()
     */
    @Override
    public Object currentObject() {
        return this.aggregate.get(index);
    }

}
