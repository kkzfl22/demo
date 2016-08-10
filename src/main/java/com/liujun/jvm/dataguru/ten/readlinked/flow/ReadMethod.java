package com.liujun.jvm.dataguru.ten.readlinked.flow;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import com.liujun.jvm.dataguru.ten.bean.ClassBeanInfo;
import com.liujun.jvm.dataguru.ten.bean.ConstantBean;
import com.liujun.jvm.dataguru.ten.readlinked.SeqLinkedList;
import com.liujun.jvm.dataguru.ten.readlinked.ServiceExecInf;
import com.liujun.jvm.dataguru.ten.readlinked.console.JvmClassStrFlag;
import com.liujun.jvm.dataguru.ten.util.PrintHex;

/**
 *进行方法的读取
* 源文件名：ReadConstant.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年7月10日
* 修改作者：liujun
* 修改日期：2016年7月10日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public class ReadMethod implements ServiceExecInf {

    @SuppressWarnings("unchecked")
    @Override
    public boolean invoke(SeqLinkedList seqList) throws Exception {
        FileInputStream input = (FileInputStream) seqList.getValue(JvmClassStrFlag.COMM_INPUT_STREAM.getFlag());

        // 得到结果信息
        Map<Integer, ConstantBean> contStant = (Map<Integer, ConstantBean>) seqList
                .getValue(JvmClassStrFlag.READ_CONSTANT_INFO.getFlag());

        ClassBeanInfo rspBean = (ClassBeanInfo) seqList.getValue(JvmClassStrFlag.COMM_RSP_MSG.getFlag());

        if (null == rspBean) {
            rspBean = new ClassBeanInfo();
        }

        // 读取方法的数量
        byte[] methodNumByte = new byte[2];
        input.read(methodNumByte);

        int methodNum = PrintHex.byteToInt2(methodNumByte);

        // 针对多个方法进行遍历
        for (int i = 0; i < methodNum; i++) {
            getMethod(input, contStant, rspBean);
        }

        seqList.putParam(JvmClassStrFlag.COMM_RSP_MSG.getFlag(), rspBean);

        return seqList.nextExec();
    }

    /**
     * 得到方法的信息,method_info
    * 方法描述
    * @param input
    * @param contStant
    * @param rspBean
    * @throws IOException
    * @创建日期 2016年7月10日
    */
    private void getMethod(FileInputStream input, Map<Integer, ConstantBean> contStant, ClassBeanInfo rspBean)
            throws IOException {
        // 首先读取access_flags 访问符
        byte[] accessFlagByte = new byte[2];
        input.read(accessFlagByte);
        int accessFlag = PrintHex.byteToInt2(accessFlagByte);
        System.out.println("当前的访问修饰符为:" + Integer.toHexString(accessFlag));

        // 读取name_index u2
        byte[] nameIndexByte = new byte[2];
        input.read(nameIndexByte);
        int nameIndex = PrintHex.byteToInt2(nameIndexByte);

        if (null != contStant.get(nameIndex)) {
            rspBean.addMethodList(new String(contStant.get(nameIndex).getValue()));
            System.out.println("当前方法名为 ：" + new String(contStant.get(nameIndex).getValue()));
        }

        // 读取descriptor_index u2 描述符，用于表达方法的参数和返回值
        byte[] descriptIndex = new byte[2];
        input.read(descriptIndex);
        int methodDiscriptNum = PrintHex.byteToInt2(descriptIndex);
        ConstantBean methodDiscriptbean = contStant.get(methodDiscriptNum);
        System.out.println("当前方法的类型信息为:" + new String(methodDiscriptbean.getValue()));

        // 读取attributes_count扩展的数量
        byte[] attributeCount = new byte[2];
        input.read(attributeCount);
        int attributedCount = PrintHex.byteToInt2(attributeCount);
        System.out.println("当前方法的扩展数量为:" + attributedCount);

        if (0 != attributedCount) {
            for (int i = 0; i < attributedCount; i++) {
                // 进行attibute信息的读取操作
                byte[] attributeNameIndexByte = new byte[2];
                input.read(attributeNameIndexByte);
                int attNameIndex = PrintHex.byteToInt2(attributeNameIndexByte);
                ConstantBean attriName = contStant.get(attNameIndex);
                String attName = new String(attriName.getValue());
                System.out.println("当前方法扩展的名字:" + attName);

                // 读取attribute_length u4
                byte[] attributeLength = new byte[4];
                input.read(attributeLength);
                int attLengthIndex = PrintHex.byteToInt4(attributeLength);
                System.out.println("当前方法的attribute_length为 ：" + attLengthIndex);

                // max_stack
                byte[] maxStack = new byte[2];
                input.read(maxStack);
                int maxStackNum = PrintHex.byteToInt2(maxStack);
                System.out.println("得到code属性的maxStack信息:" + maxStackNum);

                // max_locals
                byte[] maxLocals = new byte[2];
                input.read(maxLocals);
                int maxLocalsNum = PrintHex.byteToInt2(maxLocals);
                System.out.println("得到code属性的max_locals:" + maxLocalsNum);

                // code_length字节码长度
                byte[] codeLength = new byte[4];
                input.read(codeLength);
                int codeLengthVal = PrintHex.byteToInt4(codeLength);
                System.out.println("得到code属性的code_length:" + codeLengthVal);

                // 按照字节码的长度读取字节码
                if (codeLengthVal > 0) {
                    // 读取指令集长度
                    for (int j = 0; j < codeLengthVal; j++) {
                        byte[] codeByte = new byte[1];
                        input.read(codeByte);
                        int codeValue = PrintHex.byteToInt1(codeByte);
                        System.out.println("得到code属性的codeValue:" + codeValue);
                    }

                }

                // 进行异常信息的读取
                byte[] exceptionTableLengthByte = new byte[2];
                input.read(exceptionTableLengthByte);
                int exceptionTableLength = PrintHex.byteToInt2(exceptionTableLengthByte);
                System.out.println("得到code属性的异常表的长度:" + exceptionTableLength);

                if (exceptionTableLength > 0) {
                    // 进行异常的读取
                    System.out.println("进行异常读取以");

                }

                // 进行读取出linetable信息
                // 进行异常信息的读取
                byte[] attributescountByte = new byte[2];
                input.read(attributescountByte);
                int attributescount = PrintHex.byteToInt2(attributescountByte);
                System.out.println("得到code属性的数量为:" + attributescount);

                if (attributescount > 0) {

                    if ("Code".equals(attName)) {
                        // 进行遍历读取
                        this.codeRead(input, contStant, attributescount);
                    }
                }

            }
        }
    }

    /**
     * 进行code的读取
    * 方法描述
    * @param input
    * @param contStant
     * @throws IOException 
    * @创建日期 2016年7月10日
    */
    private void codeRead(FileInputStream input, Map<Integer, ConstantBean> contStant, int attributescount)
            throws IOException {

        for (int i = 0; i < attributescount; i++) {
            // 首先读取出attribute_name_index
            byte[] attributeNameIndexBytes = new byte[2];
            input.read(attributeNameIndexBytes);
            int attributeNameIndexNum = PrintHex.byteToInt2(attributeNameIndexBytes);
            ConstantBean constanBean = contStant.get(attributeNameIndexNum);

            String constantNeme = null;
            if (null != constanBean) {
                constantNeme = new String(constanBean.getValue());
                System.out.println("LineNumberTable 的文件属性值为:" + constantNeme);
            }

            // code_length字节码长度
            byte[] attributeLengthByte = new byte[4];
            input.read(attributeLengthByte);
            int attributeLength = PrintHex.byteToInt2(attributeLengthByte);
            System.out.println("得到code属性的attributeLength:" + attributeLength);

            // 表项
            byte[] lineNumberTableLengthByte = new byte[2];
            input.read(lineNumberTableLengthByte);
            int lineNumberTableLength = PrintHex.byteToInt2(lineNumberTableLengthByte);
            System.out.println("得到code属性表项信息的:" + lineNumberTableLength);

            // 针对LineNumberTable作解析
            if ("LineNumberTable".equals(constantNeme)) {
                if (lineNumberTableLength > 0) {
                    for (int j = 0; j < lineNumberTableLength; j++) {
                        // 字节码偏移量
                        byte[] startPcByte = new byte[2];
                        input.read(startPcByte);
                        int startPcNum = PrintHex.byteToInt2(startPcByte);
                        System.out.println("得到code属性表项信息的:" + startPcNum);

                        // 字节码偏移量
                        byte[] lineNumberByte = new byte[2];
                        input.read(lineNumberByte);
                        int lineNumber = PrintHex.byteToInt2(lineNumberByte);
                        System.out.println("得到code属性表项信息的:" + lineNumber);
                    }
                }
            }
            // 针对LocalVariableTable作出解析
            else if ("LocalVariableTable".equals(constantNeme)) {
                if (lineNumberTableLength > 0) {
                    for (int j = 0; j < lineNumberTableLength; j++) {
                        // 字节码偏移量
                        byte[] startPcByte = new byte[2];
                        input.read(startPcByte);
                        int startPcNum = PrintHex.byteToInt2(startPcByte);
                        System.out.println("得到code属性表项信息的:" + startPcNum);

                        // length
                        byte[] lengthBye = new byte[2];
                        input.read(lengthBye);
                        int length = PrintHex.byteToInt2(lengthBye);
                        System.out.println("得到code属length信息的:" + length);

                        // nameindex
                        byte[] nameIndex = new byte[2];
                        input.read(nameIndex);
                        int nameIndexValue = PrintHex.byteToInt2(nameIndex);
                        ConstantBean nameConstant = contStant.get(nameIndexValue);
                        if (null != nameConstant) {
                            String constantNameshow = new String(nameConstant.getValue());
                            System.out.println("LocalVariableTable 的名称值为:" + constantNameshow);
                        }

                        // descriptor_index
                        byte[] descriptorIndexByte = new byte[2];
                        input.read(descriptorIndexByte);
                        int descriptorIndex = PrintHex.byteToInt2(descriptorIndexByte);
                        System.out.println("得到code属descriptorIndex信息的:" + descriptorIndex);

                        // descriptor_index
                        byte[] indexBytes = new byte[2];
                        input.read(indexBytes);
                        int index = PrintHex.byteToInt2(indexBytes);
                        System.out.println("得到code属descriptorIndex信息的:" + index);

                    }
                }
            }

        }

    }

    @Override
    public boolean rollBackInvoke(SeqLinkedList seqList) throws Exception {
        return seqList.rollExec();
    }

}
