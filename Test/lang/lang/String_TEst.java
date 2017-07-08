/**
 * String_TEst.java
 * Created by liurenyong at 2013-11-13
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package lang;


/**
 * 
 * @author liurenyong 2013-11-13
 */
public class String_TEst {
    public static void main(String[] args) {
        test();
      
    }
    
    public static void test() {
        String str = "°¡";
        for(byte temp : str.getBytes()) {
            System.out.println(temp + " " + Integer.toHexString((short)temp));
        }
        for(char ch : str.toCharArray()) {
            System.out.println(Integer.toHexString(ch));
        }
    }
    
    public void tt() {
        // String string = new String("ÖÐ¹ú");
        // System.out.println(string.getBytes().length + " string length ");
        // try {
        // string = new String(string.getBytes("utf-8"));
        // System.out.println(string.getBytes().length + "  " + new
        // String(string.getBytes(), "utf-8"));
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
    }
    public void tst() {
        String str = "Öaa";
        for (byte t : str.getBytes()) {
            System.out.println(t);
        }
        System.out.println(new String(new byte[]{-28,-32,-12,-12,-12,64}));
    }
}
