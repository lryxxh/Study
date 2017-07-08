/**
 * Selector_test.java
 * Created by liurenyong at 2014-1-2
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package net;

import java.io.IOException;
import java.nio.channels.Selector;

/**
 * 
 * @author liurenyong 2014-1-2
 */
public class Selector_test {
    public static void main(String[] args) {
        try {
            Selector selector = Selector.open();
            System.out.println(selector);
            Selector selector2 = Selector.open();
            System.out.println(selector.equals(selector2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
