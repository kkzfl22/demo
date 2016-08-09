package com.liujun.io.user;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.liujun.io.user.impl.TableLineToBeanProcess;

public class DataBaseTableToEntry {

    /**
     * 换行符
     */
    public static final String LINE = "\r\n";

    /**
     * 读取文件将将列中的首字母大写
     * 
     * @param path
     */
    public void readToBig(String path, LineProcessInf lineProcess) {
        FileReader read = null;
        BufferedReader bufRead = null;

        try {
            read = new FileReader(path);
            bufRead = new BufferedReader(read);
            String line = null;

            String out = null;

            while ((line = bufRead.readLine()) != null) {
                // 进行行数据处理
                out = lineProcess.processLine(line);

                System.out.print(out);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(bufRead);
            IOUtils.closeQuietly(read);
        }

    }

    public static void main(String[] args) {
        DataBaseTableToEntry load = new DataBaseTableToEntry();

        String path = DataBaseTableToEntry.class.getClassLoader().getResource("com/liujun/io/user/").getPath()
                + "table.txt";
        // 设置输出为表信息到javabean转化
        LineProcessInf lineProcess = new TableLineToBeanProcess();

        load.readToBig(path, lineProcess);

        // String pathCamel =
        // DataBaseTableToEntry.class.getClassLoader().getResource("com/liujun/io/user/").getPath()
        // + "CamelName.txt";
        //
        // // 设置输出为表信息到javabean转化
        // LineProcessInf lineCamelProcess = new CamelNameLineProcess();
        //
        // load.readToBig(pathCamel, lineCamelProcess);
        // System.out.println();
    }

}
