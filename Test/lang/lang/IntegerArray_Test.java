/**
 * IntegerArray_Test.java
 * Created by liurenyong at 2013-11-29
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package lang;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author liurenyong 2013-11-29
 */
public class IntegerArray_Test {

    public static int[] array = new int[250000];

    static {
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long time = System.currentTimeMillis();
        List<String> list = new ArrayList<String>();
        List<List> arrayList = new ArrayList<List>();
        for(int i = 0; i< 51;i++) {
            List tt = new ArrayList();
            for (int j = 0; j < 250000; j++) {
                if(i% 5 == 0) {
                    tt.add(String.valueOf(1000 * (i + j)));
                } else if (i % 5 == 1) {
                    tt.add(Integer.valueOf(50 + (i*j)));
                } else if (i % 5 == 2) {
                    tt.add(Long.valueOf(1l*(i+j)));
                } else if (i % 5 == 3) {
                    tt.add(String.valueOf("" +(i*j)));
                } else if (i % 5 == 4) {
                    tt.add(new Date());
                }
            }
            arrayList.add(tt);
        }
        printMemory();
        System.err.println("before=====" + (System.currentTimeMillis() - time) );
        Object[] obj = arrayList.toArray();
        String[][] convert = new String[250000][51];
        Object[] row = null;
        for(int i =0; i< obj.length;i++) {
            row = (Object[]) ((List)obj[i]).toArray();
            for (int j =0;j<row.length;j++) {
                convert[j][i] = (String) row[j];
            }
        }
//        arrayList.clear();
//        arrayList = null;
        printMemory();
        arrayList.clear();
        arrayList = null;
        System.gc();
        printMemory();
        System.out.println(convert.length);
        System.err.println("after=====" + (System.currentTimeMillis() - time) );
//        time = System.currentTimeMillis();
//        Vector<Vector<String>> vector = new Vector<Vector<String>>();
//        for(int i = 0; i< arrayList.size();i++) {
//            Vector<String> ss = new Vector<String>();
//            for (int j = 0; j <arrayList.get(i).size();j++) {
//                ss.add(arrayList.get(i).get(j).toString());
//            }
//            vector.add(ss);
//        }
//        System.err.println("----------------------" + ( System.currentTimeMillis() - time));
//        printMemory();
//        WeakReference<List> wrList = new WeakReference<List>(new ArrayList()); 
//        List getList = wrList.get();

//        WeakReference<Object> sReference = new WeakReference<Object>((int) (250000 * Math.random()) * (int) (51 * Math.random()));
//        Integer integer = null;//(Integer) sReference.get();

//        list.add(String.valueOf(sReference.get()));
//        System.err.println("value " + integer + "¡¡" + sReference.get());
//        for (int i = 0; i < 1; i++) {
//            System.gc();
//        }
//        System.err.println(integer + "  " + sReference.get() + " ==== " + list);

        // WeakReference<List> wrstrings = new WeakReference<List>(new
        // ArrayList());
        // long time = System.currentTimeMillis();
        // WeakReference<Integer> wr = null;
        // WeakReference<Object> stringwr = null;
        // for (int i= 0; i < 51; i++) {
        // for (int j = 0; j < 250000; j++) {
        // wrstrings.get().add(i * j);
        // }
        // printMemory();
        // }
        // printMemory();
        // System.err.println("----------------------------------------------------");
        // List<Object> ss = new ArrayList<Object>(new
        // ArrayList(wrstrings.get()));
        // System.gc();
        // WeakReference<List> wrlist = new WeakReference<List>(new
        // ArrayList(wrstrings.get()));
        // printMemory();
        // System.out.println(ss.size());
        // printMemory();
        // System.out.println("........." + (System.currentTimeMillis() -
        // time));
    }

    /** 
     * 
     */
    private static void printMemory() {
        double total = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        double free = Runtime.getRuntime().freeMemory() / 1024 / 1024;
        double use = (total - free);
        System.err.println("total " + total + "m  free" + free + "m use" + use + "m");
    }

}
