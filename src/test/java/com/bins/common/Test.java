package com.bins.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by songrongbin on 2016/5/11.
 */
public class Test {
    public static void main(String args[]) {
        Map<String, Integer> map = new HashMap();
        map.put("key", 1);

        //Map<String, ?> mapnew = map;
        Integer mapvalue = map.get("key");
        System.out.println(mapvalue.equals(1));
    }
}
