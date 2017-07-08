/**
 * ThreadLocal_Test.java
 * Created by liurenyong at 2013-12-18
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package lang;


/**
 * 
 * @author liurenyong 2013-12-18
 */
public class ThreadLocal_Test {
    
    static class InnerBean {
        ThreadLocal local = new ThreadLocal();
        private int a = 0;
        
        /**
         * @param a the a to set
         */
        public void setA(int a) {
            this.a = a;
            local.set(a);
        }
        
        /**
         * @return the a
         */
        public int getA() {
            return (Integer) local.get();
        }
    }
    
    public static void main(String[] args) {
        Thread thred;
        final InnerBean bean = new InnerBean();
        bean.setA(123);
        new Thread() {
            @Override
            public void run() {
              bean.setA(345);
              System.out.println(bean.getA());
            }
        }.start();
        
        new Thread() {
            @Override
            public void run() {
              bean.setA(6666);
              System.out.println(bean.getA());
            }
        }.start();
        
        System.out.println(bean.getA());
    }

}
