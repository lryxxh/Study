/**
 * Float_Instance_Test.java
 * Created by liurenyong at 2013-11-26
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package lang;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * 
 * @author liurenyong 2013-11-26
 */
public class Float_Instance_Test {
    
    public static void main(String[] args) throws InterruptedException {
        int a = 23;
        int b = 32;
        int c = a ^ b;
        System.out.println( 0xffff+ " "+ Long.toBinaryString(0xffff));
        System.out.println("0"+Integer.toBinaryString(a) );
        System.out.println(Integer.toBinaryString(b));
        System.out.println(Integer.toBinaryString(c));
        System.out.println(a ^ c);
        System.out.println(b ^ c);
        ReferenceQueue<Integer> referenceQueue = new ReferenceQueue<Integer>();
        PhantomReference<Integer> wReference = new PhantomReference<Integer>(new Integer(2), referenceQueue); 
        wReference.clear();
        System.gc();
        
        Thread.sleep(2000);
        System.out.println(wReference.get() + "   " + referenceQueue.poll());
//        List<List> arrayList = new ArrayList<List>();
//        WeakReference<Integer> integer;
//        for(int i = 0; i< 51;i++) {
//            List tt = new ArrayList();
//            for (int j = 0; j < 250000; j++) {
//                integer = new WeakReference<Integer>(i*j);
//                tt.add(integer.get().toString());
//                integer = null;
//            }
//            arrayList.add(tt);
//        }
//        printMemory();
//        int[][] temp = new int[250000][51];
//        System.err.println("........................");
//        int a = -16;
//        System.out.println(Integer.toBinaryString(-16));
//        System.out.println(a >>> 2);
//        System.out.println(a >> 2);
//        System.out.println(Integer.toBinaryString(-4));
//        System.out.println(Integer.toBinaryString(1073741820));
//       float value = 124.0f;
////       Object dObject = value;
//       String string = String.valueOf(value);
//       System.out.println(string);
       
    }

    /** 
     * 
     */
    private static void printMemory() {
        double total = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        double free = Runtime.getRuntime().freeMemory() / 1024 / 1024;
        double use = (total - free);
        System.out.println("total " + total + "m  free" + free + "m use" + use + "m");
    }

}
