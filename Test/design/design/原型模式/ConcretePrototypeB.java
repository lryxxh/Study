/**
 * ConcretePrototypeA.java
 * Created by liurenyong at 2013-12-19
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.原型模式;

/**
 * 
 * @author liurenyong 2013-12-19
 */
public class ConcretePrototypeB implements Prototype {

    public Prototype clone() {
        return new ConcretePrototypeB();
    }
}
