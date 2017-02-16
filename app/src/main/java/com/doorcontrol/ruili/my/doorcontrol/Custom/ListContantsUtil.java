package com.doorcontrol.ruili.my.doorcontrol.Custom;

/**
 * @
 * @类名称: ${type_name}
 * @类描述: ${todo}
 * @创建人：
 * @创建时间：${date} ${time}
 * @备注：
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListContantsUtil {

    static final List<Integer> indexPositionList = new ArrayList<Integer>();
    static final List<String> AbcList = new ArrayList<String>();
    static final HashMap<String, Integer> indexPositionMap = new HashMap<String, Integer>();

    static void putNameIndexToMap(List<String> list, HashMap<String, String> nameAndPinyin) {
        int lenght = list.size();
        for (int i = 0; i < lenght; ++i) {
            String name = nameAndPinyin.get(list.get(i)).substring(0, 1);
            // 判断该字符是属于字母还是数字或其他的
            int ascii = name.toCharArray()[0];
            if (ascii >= 65 && ascii <= 90) {
                if (!indexPositionMap.containsKey(name)) {
                    indexPositionMap.put(name, i);
                    AbcList.add(name);
                    indexPositionList.add(i);
                }
            } else {
                if (!indexPositionMap.containsKey("#")) {
                    indexPositionMap.put("#", i);
                    AbcList.add("#");
                    indexPositionList.add(i);
                }
            }

        }

    }
}

