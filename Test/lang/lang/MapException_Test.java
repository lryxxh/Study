/**
 * MapException_Test.java
 * Created by liurenyong at 2013-9-3
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package lang;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author liurenyong 2013-9-3
 */
public class MapException_Test {

    /**
     * 
     * @param args
     * @author liurenyong 2013-9-3
     */
    public static void main(String[] args) {
        Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();

        Map<String, List<Integer>> removeMap = new HashMap<String, List<Integer>>();
        
        List<Integer> pdrList = new ArrayList<Integer>();
        Collections.synchronizedCollection(pdrList);
        pdrList.add(1);
        pdrList.add(2);
        pdrList.add(3);
        map.put("pdr", pdrList);

        List<Integer> realtimeList = new ArrayList<Integer>();
        Collections.synchronizedCollection(realtimeList);
        realtimeList.add(11);
        realtimeList.add(232);
        realtimeList.add(433);
        map.put("realtime", realtimeList);

        List<Integer> studyList = new ArrayList<Integer>();
        Collections.synchronizedCollection(studyList);
        studyList.add(1222);
        studyList.add(2222);
        studyList.add(3222);
        map.put("study", pdrList);
        
        removeMap.put("pdr", new ArrayList<Integer>());
        removeMap.put("realtime", new ArrayList<Integer>());
        removeMap.put("study", new ArrayList<Integer>());

        System.out.println(map);
        for (String key : map.keySet()) {
            for (Integer noInteger : map.get(key)) {
                removeMap.get(key).add(noInteger);
            }
            map.get(key).removeAll(removeMap.get(key));
        }
        System.out.println(map);
    }

}
