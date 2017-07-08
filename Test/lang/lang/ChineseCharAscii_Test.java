/**
 * s.java
 * Created by liurenyong at 2013-8-29
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package lang;

/**
 * 
 * @author liurenyong 2013-8-29
 */
public class ChineseCharAscii_Test {
    public static void main(String[] args) {
        System.out.println('\u4E1F');
        String string = "ÖÐ¹ú";
        char[] array = string.toCharArray();
        int i = 0;
        for(byte temp : string.getBytes()) {
        	i++;
        	if(i%2 == 0) {
        		System.out.println(Integer.toBinaryString(temp).substring(24) + "----------------" +temp);
        	} else {
        		System.out.print(Integer.toBinaryString(temp).substring(24));
        	}
        	
        }
        
        for(char temp : array) {
            System.out.println(Integer.toBinaryString(temp) + "   " +Integer.toHexString(temp));
        }
        
        
        System.err.println((char)0x4E2D);
        System.err.println((char)0x56FD);
    }
}
