package lang;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class JavapTip {
    
    static int a = 111;
    static {
        a = 10;
    }
    
    
    public static void main(String[] args) {
        System.out.println(new SimpleDateFormat("yyyy Äê £¨M/4£© ¼¾¶È").format(new Date()));
//        Integer a = 1;
//        Integer b = 2;
//        Integer c = 3;
//        Integer d = 3;
//        Integer e = 321;
//        Integer f = 321;
//        Long g = 3L;
//        System.out.println(c == d);
//        System.out.println(e == f);
//        System.out.println(c == (a + b));
//        System.out.println(c.equals(a + b));
//        System.out.println(g == (a + b));
//        System.out.println(g.equals(a + b));
//        int a_count = new JavapTip().a;
//        System.out.println(a_count);
//        int a = Sub.A;
//        System.out.println(a);
        // DecimalFormat format = new DecimalFormat("#.###");
        // System.out.println(format.format(234.567));
        // withStrings(80);
    }

    public static String withStrings(int count) {
        String s = "";
        for (int i = 0; i < count; i++) {
            s += i;
        }

        return s;
    }

    private static String withStringBuffer(int count) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < count; i++) {
            sb.append(i);
        }

        return sb.toString();
    }

    interface Interface0 {
        int A = 4;
    }

    interface Interface1 extends Interface0 {
        int A = 4;
    }

    interface Interface2 {
        int A = 4;
    }

    static class Parent implements Interface1 {
        public static int A = 6;
        public int tt(List<String> list){
            return 1;
        }
        
        public String tt(List<Integer> list){
            return "";
        }
        public static final void test() {}
    }

    static class Sub extends Parent implements Interface2 {
        public static int A = 4;
        
//        public static void test(){}
        public static void main(String[] args) {
            Sub.test();
        }
    }

}