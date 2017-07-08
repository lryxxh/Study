/**
 * RefinedAbstraction.java
 * Created by liurenyong at 2013-12-24
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.桥接模式;

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
        //实现一定的功能，可能会使用具体实现部分的实现方法
        //但是本方法更大的可能是使用Abstraction中定义的方法
        //通过组合使用Abstraction中定义的方法来完成更多的功能
    }
}
