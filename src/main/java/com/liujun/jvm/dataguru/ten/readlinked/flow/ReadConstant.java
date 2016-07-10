package com.liujun.jvm.dataguru.ten.readlinked.flow;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.liujun.jvm.dataguru.ten.bean.ConstantBean;
import com.liujun.jvm.dataguru.ten.readlinked.SeqLinkedList;
import com.liujun.jvm.dataguru.ten.readlinked.ServiceExecInf;
import com.liujun.jvm.dataguru.ten.readlinked.console.JvmClassIntFlag;
import com.liujun.jvm.dataguru.ten.readlinked.console.JvmClassStrFlag;
import com.liujun.jvm.dataguru.ten.util.PrintHex;

/**
 * 读取常量信息
* 源文件名：ReadConstant.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年7月10日
* 修改作者：liujun
* 修改日期：2016年7月10日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public class ReadConstant implements ServiceExecInf {

    @Override
    public boolean invoke(SeqLinkedList seqList) throws Exception {
        FileInputStream input = (FileInputStream) seqList.getValue(JvmClassStrFlag.COMM_INPUT_STREAM.getFlag());

        byte[] constantLengthByte = new byte[2];

        input.read(constantLengthByte);

        int constantLengthint = PrintHex.byteToInt2(constantLengthByte);

        System.out.println("常量池大小:" + constantLengthint);

        // 进行常量池的读取
        Map<Integer, ConstantBean> map = readConsole(input, constantLengthint);

        System.out.println("常量池中存储的大小:" + map.size());

        seqList.putParam(JvmClassStrFlag.READ_CONSTANT_INFO.getFlag(), map);

        return seqList.nextExec();
    }

    @Override
    public boolean rollBackInvoke(SeqLinkedList seqList) throws Exception {
        return seqList.rollExec();
    }

    /**
     * 读取常量池中的信息
    * 方法描述
    * @param input
    * @param maxSize
    * @return
    * @throws IOException
    * @创建日期 2016年7月10日
    */
    private Map<Integer, ConstantBean> readConsole(FileInputStream input, int maxSize) throws IOException {

        Map<Integer, ConstantBean> constantMap = new HashMap<Integer, ConstantBean>();

        int index = 1;
        byte[] constantFloag = new byte[1];

        while (index < maxSize) {
            input.read(constantFloag);

            // 读取JVM的标识
            int constantFlag = PrintHex.byteToInt1(constantFloag);

            // 读取类的信息
            if (JvmClassIntFlag.CONSTANT_Class.getFlag() == constantFlag) {
                // 提取U2长的的name_index u2
                byte[] nameIndex = new byte[2];

                input.read(nameIndex);

                constantMap.put(index, new ConstantBean(nameIndex));

            }
            // 读取常量池中UTF-8 的信息
            else if (JvmClassIntFlag.CONSTANT_Utf8.getFlag() == constantFlag) {

                // 读取utf8字符集的长度length u2
                byte[] utf8LengthByte = new byte[2];

                input.read(utf8LengthByte);
                int utf8Length = PrintHex.byteToInt2(utf8LengthByte);

                byte[] nameValue = new byte[utf8Length];

                // 读取出字符集信息
                input.read(nameValue);

                constantMap.put(index, new ConstantBean(utf8LengthByte, nameValue));
            }
            // 读取int类型的值,long 类型，
            else if (JvmClassIntFlag.CONSTANT_Integer.getFlag() == constantFlag
                    || JvmClassIntFlag.CONSTANT_Float.getFlag() == constantFlag) {
                // byte u4
                byte[] integerByte = new byte[4];
                input.read(integerByte);

                constantMap.put(index, new ConstantBean(integerByte));
            }
            // 当前为long或者double为64位的数字
            else if (JvmClassIntFlag.CONSTANT_Long.getFlag() == constantFlag
                    || JvmClassIntFlag.CONSTANT_Double.getFlag() == constantFlag) {
                // byte u4
                byte[] longByte = new byte[8];
                input.read(longByte);

                constantMap.put(index, new ConstantBean(longByte));
            }
            // 读取出来String类型信息
            else if (JvmClassIntFlag.CONSTANT_String.getFlag() == constantFlag) {
                // string_index u2 (指向utf8的索引)
                byte[] stringByte = new byte[2];
                input.read(stringByte);

                constantMap.put(index, new ConstantBean(stringByte));
            }
            // 12 对一个字段或方法的部分符号引用
            else if (JvmClassIntFlag.CONSTANT_NameAndType.getFlag() == constantFlag) {
                // name_index u2 (名字，指向utf8)
                byte[] nameAndTypeByte = new byte[2];
                input.read(nameAndTypeByte);
                // descriptor_index u2 (描述符类型，指向utf8)
                byte[] discriptByte = new byte[2];
                input.read(discriptByte);

                constantMap.put(index, new ConstantBean(nameAndTypeByte, discriptByte));
            }
            // 对一个字段的符号引用
            // 对一个类中方法的符号引用
            // 对一个接口中方法的符号引用
            else if (JvmClassIntFlag.CONSTANT_Fieldref.getFlag() == constantFlag
                    || JvmClassIntFlag.CONSTANT_Methodref.getFlag() == constantFlag
                    || JvmClassIntFlag.CONSTANT_InterfaceMethodref.getFlag() == constantFlag) {
                // class_index u2 (指向CONSTANT_Class)
                byte[] classIndexByte = new byte[2];
                input.read(classIndexByte);
                // name_and_type_index u2 (指向CONSTANT_NameAndType)
                byte[] nameTypeByte = new byte[2];
                input.read(nameTypeByte);
                constantMap.put(index, new ConstantBean(classIndexByte, nameTypeByte));
            }

            index++;
        }

        System.out.println("index value:" + index);

        return constantMap;

    }

}
