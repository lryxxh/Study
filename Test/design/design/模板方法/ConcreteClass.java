/**
 * ConcreteClass.java
 * Created by liurenyong at 2013-12-19
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.ģ�巽��;


/**
 * 
 * @author liurenyong 2013-12-19
 */
public class ConcreteClass extends TemplateMethod {

    /* (non-Javadoc)
     * @see design.ģ�巽��.TemplateMethod#doPrimitiveOperation2()
     */
    @Override
    public void doPrimitiveOperation2() {
        System.out.println("�㷨�Ǽܵ��÷���ʵ��");
    }

    /* (non-Javadoc)
     * @see design.ģ�巽��.TemplateMethod#doPrimitiveOperation1()
     */
    @Override
    public void doPrimitiveOperation1() {
        System.out.println("�㷨�Ǽܵ��÷���ʵ��");
    }

}
