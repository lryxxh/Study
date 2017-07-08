/**
 * Abstraction.java
 * Created by liurenyong at 2013-12-24
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.桥接模式;

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
     * 实现一定的功能，可能需要转调实现部分的具体实现方法。
     */
    public void operation() {
        implementor.operationImpl();
    }
}
