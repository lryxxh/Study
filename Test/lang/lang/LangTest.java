package lang;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import org.jdesktop.swingx.autocomplete.ListAdaptor;

/**
 * Test.java
 * Created by liurenyong at 2013-11-19
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */

/**
 * 
 * @author liurenyong 2013-11-19
 */
public class LangTest {

    /**
     * 
     * @author liurenyong 2013-11-25
     */
    public LangTest() {
        int a = 9;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        WeakHashMap<String, String> map = new WeakHashMap<String, String>();
        map.put(new String("1"), "1");
        map.put("2", "2");
        String s = new String("3");
        map.put(s, "3");
        List<String> list = new ArrayList<String>();
        list.add(map.get("1"));
        list.add(map.get("2"));
        list.add(map.get("3"));
        System.err.println(list);
        s = null;
        System.out.println(list + " map " + map);
        
        while (map.size() > 0) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored) {
            }
            System.out.println("Map Size:" + map.size());
            System.out.println(map.get("1"));
            System.out.println(map.get("2"));
            System.out.println(map.get("3"));
            System.gc();
        }
        // System.out.println(ByteOrder.nativeOrder());
        // String a = new String("123");
        // try {
        // Thread.sleep(100000000);
        // } catch (InterruptedException e) {
        // e.printStackTrace();
        // }

        // int a = Character.digit("12354".charAt(2), 8);
        // System.out.println(a);
        // T t = null;
        // System.out.println(T_IMpl.b);
        // Document document = new JTextField().getDocument();
        // document = new JTextPane().getDocument();
        // document = new JEditorPane().getDocument();
        // System.out.println(document);

        // int[][][][] a = { { { { 1, 2, 3 ,9}, { 4, 5, 6 }, { 4, 5, 6 } }, { {
        // 10, 20, 30 }, { 40, 50, 60 }, { 4, 5, 6 } } },
        // { { { 1, 2, 3 }, { 4, 5, 6 }, { 4, 5, 6 } }, { { 10, 20, 30 }, { 40,
        // 50, 60 }, { 4, 5, 6 } } } };
        // for (int i = 0; i < a.length; i++) {
        // for (int j = 0; j < a[i].length; j++) {
        // for (int k = 0; k < a[i][j].length; k++) {
        // for(int l = 0; l< a[i][j][k].length;l++) {
        // System.out.print(a[i][j][k][l] + " ");
        // }
        // System.out.println();
        // }
        // System.out.println();
        // }
        // System.out.println();
        // }
    }

}

interface T {
    static int a = 0;
    static int b = (int) (Math.random() * 100);
}

class T_IMpl implements T {
    static int a = 10;
    // static int b = (int) (Math.random() * 10);
}