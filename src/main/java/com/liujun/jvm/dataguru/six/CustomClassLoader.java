package com.liujun.jvm.dataguru.six;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

/**
 * 自定义加载器，进行加载
 * @author liujun
 * @date 2016年6月10日
 * @verion 0.0.1
 */
class CustomClassLoader extends ClassLoader {

    private String basePath = "D:\\java\\work\\self\\demo\\target\\classes\\";

    public CustomClassLoader() {
    }

    public CustomClassLoader(ClassLoader parent) {
        super(parent);
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        // 得到指定路径文件的byte信息
        String path = className2Path(name);

        // 得到字节码信息
        byte[] bytBuff = this.getByte(path);

        Class clazz = null;

        clazz = defineClass(name, bytBuff, 0, bytBuff.length);
        //
        return clazz;
        // return super.findClass(name);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        return super.loadClass(name, false);
    }

    private String className2Path(String name) {
        return basePath + name.replace(".", "/") + ".class";
    }

    /**
     * 将文件转换为byte数组信息
     * @param path
     * @return
     */
    public byte[] getByte(String path) {
        byte[] rsp = null;

        FileInputStream input = null;
        try {
            input = new FileInputStream(path);
            rsp = new byte[input.available()];
            input.read(rsp);

            return rsp;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(input);
        }

        return null;
    }

}
