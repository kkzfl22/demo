package com.liujun.io.user.impl;

import com.liujun.io.user.DataBaseTableToEntry;
import com.liujun.io.user.LineProcessInf;

/**
 * 使用命名法来进行转换数据
 * 
 * @author liujun
 * 
 * @date 2016年5月24日
 * @vsersion 0.0.1
 */
public class JavaNameSetPropertyProcess implements LineProcessInf {

    int index = 0;

    @Override
    public String processLine(String linename) throws Exception {

        String tempValue = linename.toLowerCase();

        StringBuilder name = new StringBuilder();
        if (tempValue.indexOf("_") > 0) {

            String[] item = tempValue.split("_");
            if (item.length > 0) {

                for (int i = 0; i < item.length; i++) {
                    // 首字母大写
                    String buffix = item[i].substring(0, 1).toUpperCase() + item[i].substring(1).toLowerCase();

                    // 取得其他字符
                    name.append(buffix);
                }
            }
        } else {
            // 首字母大写
            name.append(tempValue.substring(0, 1).toUpperCase());
            name.append(tempValue.substring(1).toLowerCase());
        }

        // 设置的set方法
        // String result = "bean.set" + name.toString() + "(array[" + index +
        // "]" + ");" + DataBaseTableToEntry.LINE;
        StringBuilder result = new StringBuilder();

        // 用来获取数据的get方法
        // result.append("result.append(\"");
        // result.append(name.toString());
        // result.append("\").append(\":\").append(param.get");
        // result.append(name.toString() + "()).append(\",\");");

        result.append("bean.set").append(name.toString());
        result.append("(\"222\");");
        result.append(DataBaseTableToEntry.LINE);

        index++;

        return result.toString();
    }

}
