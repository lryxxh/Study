/**
 * ArrayList_Test.java
 * Created by liurenyong at 2013-11-15
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package lang;

import java.util.Collections;
import java.util.List;

/**
 * 
 * @author liurenyong 2013-11-15
 */
public class ArrayList_Test {

    /** 
     * @param args
     */
    public static void main(String[] args) {
//        ArrayList_Test test1 = new ArrayList_Test();
//        ArrayList_Test test2= new ArrayList_Test();
//        ArrayList_Test test3 = new ArrayList_Test();
//        ArrayList lista = new ArrayList();
//        lista.add("123");
//        lista.add("345");
//        lista.add("456");
//        
//        ArrayList listb = new ArrayList();
//        listb.add("567");
//        
//        System.out.println("before clear " + lista.size() + "  " + listb.size());
//        System.out.println("before clear " + lista.hashCode() + "  " + listb.hashCode());
//        listb.clear();
//        System.out.println("after clear " + lista.size() + "  " + listb.size());
//        System.out.println("after clear " + lista.hashCode() + "  " + listb.hashCode());
//        listb.addAll(lista);
//        System.out.println("after add all " + lista.size() + "  " + listb.size());
//        System.out.println("after add all " + lista.hashCode() + "  " + listb.hashCode());
//        
//        listb.clear();
//        System.out.println("after clear " + lista.size() + "  " + listb.size());
//        System.out.println("after clear " + lista.hashCode() + "  " + listb.hashCode());
        
        List list = Collections.EMPTY_LIST;
        for (int i = 0; i< 100000;i++) {
            list.add(String.valueOf(i));
        }
        
        list = null;
        System.out.println(list.size());

    }

}
