/**
 * ConcreteClass.java
 * Created by liurenyong at 2013-12-19
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.模板方法;


/**
 * 
 * @author liurenyong 2013-12-19
 */
public class ConcreteClass extends TemplateMethod {

    /* (non-Javadoc)
     * @see design.模板方法.TemplateMethod#doPrimitiveOperation2()
     */
    @Override
    public void doPrimitiveOperation2() {
        System.out.println("算法骨架调用方法实现");
    }

    /* (non-Javadoc)
     * @see design.模板方法.TemplateMethod#doPrimitiveOperation1()
     */
    @Override
    public void doPrimitiveOperation1() {
        System.out.println("算法骨架调用方法实现");
    }

}
