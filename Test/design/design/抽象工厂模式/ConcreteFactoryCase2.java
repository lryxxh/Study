/**
 * ConcreteFactoryCase1.java
 * Created by liurenyong at 2013-12-20
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.抽象工厂模式;

/**
 * 
 * @author liurenyong 2013-12-20
 */
public class ConcreteFactoryCase2 extends AbstractFactory{

    /* (non-Javadoc)
     * @see design.抽象工厂模式.AbstractFactory#createProductA()
     */
    @Override
    public ProductA createProductA() {
        return new ProductACase2();
    }

    /* (non-Javadoc)
     * @see design.抽象工厂模式.AbstractFactory#createProductB()
     */
    @Override
    public ProductB createProductB() {
        return new ProductBCase2();
    }

    /* (non-Javadoc)
     * @see design.抽象工厂模式.AbstractFactory#createProductC()
     */
    @Override
    public ProductC createProductC() {
        return new ProductCCase2();
    }

}
