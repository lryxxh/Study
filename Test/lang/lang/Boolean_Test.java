/**
 * Boolean_Test.java
 * Created by liurenyong at 2013-11-27
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package lang;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * 
 * @author liurenyong 2013-11-27
 */
public class Boolean_Test<E> extends HashSet<E>{
//    private int addCount = 0;
//    @Override
//    public boolean add(E e){
//        addCount++;
//        return super.add(e);
//    }
// 
//    @Override
//    public boolean addAll(Collection<? extends E> c){
//        addCount += c.size();
//        return super.addAll(c);
//    }
// 
//    public static void main(String[] args) {
//        Boolean_Test<String> s = new Boolean_Test<String>();
//        s.addAll(Arrays.asList("Accordion","Banjo","Kazoo"));
//        System.out.println(s.addCount);
//    }
    
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        for (int i = 0; i < 150000;i++){
            if(i%3 == 0) {
                list.add(Math.rint(Math.random() * 1000));
            } else if(i%3==1){
                list.add(""+(Math.rint(Math.random() * 1000)));
            } else{
                list.add((Math.rint(Math.random() * 1000)));
            }
        }
        list.add(1111111);
        
        long time = System.currentTimeMillis();
        System.out.println(list.get(111111));
        System.out.println(System.currentTimeMillis() - time);
        
        LinkedList list2 = new LinkedList();
        for (int i = 0; i < 150000;i++){
            if(i%3 == 0) {
                list2.add(Math.rint(Math.random() * 1000));
            } else if(i%3==1){
                list2.add(""+(Math.rint(Math.random() * 1000)));
            } else {
                list2.add((Math.rint(Math.random() * 1000)));
            }
        }
        list2.add(1111111);
        
        long time2 = System.currentTimeMillis();
        System.out.println(list2.get(111111));
        System.out.println(System.currentTimeMillis() - time2);
        
        
//        String[] strings = { "0", "1", "2", "3", "4", "5"};
// 
//        List<Integer> integers = new ArrayList<Integer>();
//        for(String s : strings){
//            integers.add(Integer.valueOf(s));
//        }
// 
//        System.out.println(Collections.binarySearch(integers, 1,cmp));
    }
 
    static Comparator<Integer> cmp = new Comparator<Integer>(){
        public int compare(Integer i,Integer j){
            return i<j?-1:(i==j?0:1);
        }
    };
}
