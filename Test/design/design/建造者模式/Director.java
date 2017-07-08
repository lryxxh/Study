/**
 * Director.java
 * Created by liurenyong at 2013-12-19
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.建造者模式;

/**
 * 
 * @author liurenyong 2013-12-19
 */
public class Director {
    
    public void buildProduct(Builder builder) {
        builder.buildPart1();
        builder.buildPart2();
        builder.buildPart3();
    }

}
