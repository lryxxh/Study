/**
 * ToStringMethod_Test.java
 * Created by liurenyong at 2013-11-30
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package lang;

/**
 * 
 * @author liurenyong 2013-11-30
 */
public class ToStringMethod_Test {
    
    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        StringBuilder builder = null;
        for (int i = 0; i < 250000;i++) {
            builder  = new StringBuilder();
            for (int j = 0; j < 51;j++) {
                int t = i*j;
                builder.append(t);
            }
            
        }
        System.out.println("   " + (System.currentTimeMillis() - time));
    }

}
