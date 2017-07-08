/**
 * ConcreteProductA.java
 * Created by liurenyong at 2013-12-19
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.工厂方法;

/**
 * 
 * @author liurenyong 2013-12-19
 */
public class ConcreteProductA extends Creater {

    public Product factoryMethod() {
        return new ConcreteProduct();
    }

}
