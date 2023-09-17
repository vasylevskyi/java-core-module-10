package ua.goit;

import java.util.Comparator;
import java.util.Map;

class MyComparator implements Comparator {

    Map map;

    public MyComparator(Map map) {
        this.map = map;
    }

    public int compare(Object o1, Object o2) {

        return ((Integer) map.get(o2)).compareTo((Integer) map.get(o1));

    }
}
/*
    In Test Class

        Map<String, Integer> lMap=new HashMap<String, Integer>();
        lMap.put("A", 35);
        lMap.put("B", 25);
        lMap.put("C", 50);

        MyComparator comp=new MyComparator(lMap);

        Map<String,Integer> newMap = new TreeMap(comp);
        newMap.putAll(lMap);*/
