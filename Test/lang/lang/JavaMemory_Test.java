package lang;

import java.lang.ref.WeakReference;
import java.util.Vector;

public class JavaMemory_Test {
    static byte[] bytes = new byte[1 * 1024 * 1024];
    static WeakReference<byte[]> wrReferences = new WeakReference<byte[]>(bytes);
    public static void main(String[] args) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        System.err.println(".........begin");
//        try {
//            System.setOut(new PrintStream(new File("C:/Users/liurenyong/Desktop/1.log")));
//        } catch (FileNotFoundException e1) {
//            e1.printStackTrace();
//        }
        new Thread() {

            @Override
            public void run() {
                Vector vector = new Vector();
                while (true) {
//                    bytes = new byte[1024 * 1024];
                    byte[] bs = wrReferences.get();
                    vector.add(bs);
                    wrReferences = new WeakReference<byte[]>(new byte[1 * 1024 * 1024]);
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        float totalMemory = Float.parseFloat(""+(Runtime.getRuntime().totalMemory())) / 1024 / 1024;
                        float maxMemeory = Float.parseFloat(""+(Runtime.getRuntime().maxMemory())) / 1024 / 1024;
                        float freeMemory = Float.parseFloat(""+(Runtime.getRuntime().freeMemory())) / 1024 / 1024;
                        float useMemory = (totalMemory - freeMemory);
                        System.out.println(bs.length + "  " + " maxMemory:" + maxMemeory + "  , totalMemory:" + totalMemory + "  , useMemory:" + useMemory
                                + "  , freeMemory:" + freeMemory);
                        if (vector.size() % 50 == 0) {
                            vector.clear();
                        }
                    }
                }
            }
        }.start();

    }
}
