/**
 * Salutation.java
 * Created by liurenyong at 2013-11-28
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package lang;

/**
 * 
 * @author liurenyong 2013-11-28
 */
public class Salutation {
    
    private static final String hello = "Hello, world!";
    private static final String GREETING_STRING = "Greetings, planet!";
    private static final String salutation = "Salutations, orb!";
    private static int choice = (int)(Math.random() * 2.99);
    
    public static void main(String[] args) {
        String s = hello;
        System.out.println(choice);
        if (choice == 1) {
            s = GREETING_STRING;
        } else if (choice == 2) {
            s = salutation;
        }
        System.out.println(s);
    }

}
