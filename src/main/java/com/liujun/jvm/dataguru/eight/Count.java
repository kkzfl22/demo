package com.liujun.jvm.dataguru.eight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Count {

    private List<Long> timeList = new ArrayList<Long>();

    public static void main(String[] args) {

        Count count = new Count();
        count.getList();
    }

    public void getList() {

        FileReader read = null;
        BufferedReader bufRead = null;

        try {
            String path = Count.class.getClassLoader().getResource("com/liujun/jvm/dataguru/eight/").getPath();

            System.out.println(path);

            read = new FileReader(path + "/time.csv");
            bufRead = new BufferedReader(read);

            String line = null;

            while ((line = bufRead.readLine()) != null) {
                line = line.substring(0, line.length() - 1);

                timeList.add(Long.parseLong(line));
            }

            Collections.sort(timeList);

            System.out.println("最小:" + timeList.get(0));
            System.out.println("最大:" + timeList.get(timeList.size() - 1));

            // 进行取得分组信息
            Map<String, List<Long>> MapList = ListToMap(timeList);

            // 得到每秒请求大小信息
            Map<String, Integer> getSizeMap = getListMap(MapList);

            List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(getSizeMap.entrySet());
            Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
                // 升序排序
                public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                    if (o1.getValue() < o2.getValue()) {
                        return 1;
                    } else if (o1.getValue() == o2.getValue()) {
                        return 0;
                    }
                    return -1;
                }

            });

            for (Entry<String, Integer> entry : list) {
                System.out.println(entry);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Map<String, Integer> getListMap(Map<String, List<Long>> map) {
        Map<String, Integer> list = new HashMap<String, Integer>();

        for (Entry<String, List<Long>> item : map.entrySet()) {
            list.put(item.getKey(), item.getValue().size());
        }

        return list;
    }

    private Map<String, List<Long>> ListToMap(List<Long> list) {
        Map<String, List<Long>> map = new HashMap<String, List<Long>>();

        DecimalFormat df = new DecimalFormat("############.###");

        for (Long timevalue : list) {
            Double tmpTime = timevalue / 1000.0d;
            String timeFlow = df.format(tmpTime);

            String key = null;

            if (timeFlow.indexOf(".") == -1) {
                key = timeFlow;
            } else {
                key = timeFlow.substring(0, timeFlow.indexOf("."));
            }

            List<Long> value = map.get(key);

            if (null == value) {
                value = new ArrayList<Long>();
            }

            value.add(timevalue);
            map.put(key, value);
        }

        return map;

    }

}
