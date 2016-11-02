package com.liujun.io.user;

import com.liujun.io.user.impl.JavaNameSetPropertyProcess;

public class MainRun {

    public static void main(String[] args) {
        DataBaseTableToEntry load = new DataBaseTableToEntry();

        String path = DataBaseTableToEntry.class.getClassLoader().getResource("com/liujun/io/user/").getPath()
                + "javaName.txt";
        // 设置输出为表信息到javabean转化
        LineProcessInf lineProcess = new JavaNameSetPropertyProcess();

        System.out.println("StringBuilder result = new StringBuilder();");

        load.readToBig(path, lineProcess);

        System.out.println("return result.toString();");

        StringBuilder msg = new StringBuilder();

        msg.append("000066171220064");
        msg.reverse();

        System.out.println(msg.toString());
    }

}
