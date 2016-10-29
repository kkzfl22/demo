package com.liujun.hadoop.hadoopClient;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HadoopClient {

    public HadoopClient() {
    }

    public static void main(String args[]) throws IOException {
        // 测试 创建新文件
        byte[] contents = "hello world 世界你好\n--created by eclipse\n".getBytes();
        createFile("/test/first23.txt", contents); // 或
        // createFile("hdfs://os1:8020/eclipse/first.txt", contents);

        // 测试 上传本地文件
        // uploadFile("D:\\c.txt", "/eclipse/");

        // 测试重命名
        // rename("/eclipse/c.txt", "/eclipse/cc.txt");

        // 测试删除文件
        // delete("/eclipse/cc.txt"); //使用相对路径
        // delete("/eclipse2"); //删除目录

        // 测试新建目录
        // mkdir("/eclipse2/");

        // 测试读取文件
        // readFile("/eclipse/first.txt");

        // 测试文件是否存在
        // fileIsExists("/eclipse/first.txt");

    }

    // 1、创建新文件（直接生成指定路径下的first.txt，即：/eclipse/first.txt）
    public static void createFile(String dst, byte[] contents) throws IOException {
        Configuration conf = new Configuration();
        System.out.println("-----------:" + conf);
        conf.set("fs.defaultFS", "hdfs://os1:8020"); // master
        FileSystem fs = FileSystem.get(conf);
        Path dstPath = new Path(dst); // 目标路径
        // 打开一个输出流
        FSDataOutputStream outputStream = fs.create(dstPath);
        outputStream.write(contents);
        outputStream.close();
        fs.close();
        System.out.println("文件创建成功！");
    }

    // 2、上传本地文件
    public static void uploadFile(String src, String dst) throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://os1:8020"); // master
        FileSystem fs = FileSystem.get(conf);
        Path srcPath = new Path(src); // 源路径
        Path dstPath = new Path(dst); // 目标路径
        // 调用文件系统的文件复制函数,前面参数是指是否删除原文件，true为删除，默认为false
        fs.copyFromLocalFile(false, srcPath, dstPath);

        // 打印文件路径
        System.out.println("Upload to " + conf.get("fs.default.name"));
        // 列出指定路径下的所有文件
        System.out.println("------------list files------------" + "\n");
        FileStatus[] fileStatus = fs.listStatus(dstPath);
        for (FileStatus file : fileStatus) {
            System.out.println(
                    file.getPath() + "--" + file.getGroup() + "--" + file.getBlockSize() + "--" + file.getLen() + "--");
        }
        fs.close();
    }

}
