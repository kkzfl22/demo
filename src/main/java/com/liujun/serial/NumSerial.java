package com.liujun.serial;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.io.IOUtils;

public class NumSerial {

    public static void main(String[] args) {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ByteArrayOutputStream bosInt = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        ByteArrayInputStream inputIs = null;
        ObjectInputStream ois = null;

        int value = 100;
        try {
            // 写入当前对象的二进制流
            oos = new ObjectOutputStream(bos);
            oos.writeObject(new Integer(value));
            System.out.println(bos.toByteArray().length);

            oos = new ObjectOutputStream(bosInt);
            oos.writeInt(value);
            System.out.println(bosInt.toByteArray().length);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(ois);
            IOUtils.closeQuietly(inputIs);
            IOUtils.closeQuietly(oos);
            IOUtils.closeQuietly(bos);
        }

    }

}
