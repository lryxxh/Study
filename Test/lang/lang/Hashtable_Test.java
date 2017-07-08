/**
 * Hashtable_Test.java
 * Created by liurenyong at 2013-12-25
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package lang;

import java.util.Arrays;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

/**
 * 
 * @author liurenyong 2013-12-25
 */
public class Hashtable_Test {
    public static void main(String[] args) {
        Hashtable table = new Hashtable();
        table.put(new String("123"), 123l);
        table.put(new String("123"), 456l);
        System.out.println(table.size());
        String[] strings = {"123", "345", "456"};
        System.out.println(Arrays.binarySearch(strings, "2222"));
    }

}
