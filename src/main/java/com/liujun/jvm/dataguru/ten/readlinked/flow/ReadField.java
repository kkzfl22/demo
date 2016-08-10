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
 *进行属性的读取
* 源文件名：ReadConstant.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年7月10日
* 修改作者：liujun
* 修改日期：2016年7月10日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public class ReadField implements ServiceExecInf {

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

        // 得到的数量
        byte[] fieldCountByte = new byte[2];
        input.read(fieldCountByte);

        int fieldNum = PrintHex.byteToInt2(fieldCountByte);

        for (int i = 0; i < fieldNum; i++) {
            this.getFieldName(input, contStant, rspBean);
        }

        seqList.putParam(JvmClassStrFlag.COMM_RSP_MSG.getFlag(), rspBean);

        return seqList.nextExec();
    }

    /**
     * 得到属性的名称信息
     * 
     * 表示字段的类型
    B byte
    
    C char
    
    D double
    
    F float
    
    I int
    
    J long
    
    S short
    
    Z boolean
    
    V void
    
    L 对象
    
    Ljava/lang/Object;
    
    [
    
    数组 [[Ljava/lang/String; = String[][]
    * 方法描述
    * @param input
    * @return
     * @throws IOException 
    * @创建日期 2016年7月10日
    */
    private void getFieldName(FileInputStream input, Map<Integer, ConstantBean> contStant, ClassBeanInfo rspBean)
            throws IOException {

        // 得到的数量
        byte[] accessFlag = new byte[2];
        input.read(accessFlag);

        int fieldPrint = PrintHex.byteToInt2(accessFlag);

        System.out.println("当前属性的访问修改为:" + Integer.toHexString(fieldPrint));

        // 得到属性的名称
        byte[] fieldNameIndexByte = new byte[2];
        input.read(fieldNameIndexByte);

        int fieldIndexNum = PrintHex.byteToInt2(fieldNameIndexByte);

        if (fieldIndexNum != 0) {

            ConstantBean bean = contStant.get(fieldIndexNum);
            rspBean.addFieldlist(new String(bean.getValue()));
        }

        // 得到字段的类型信息
        byte[] discriptIndexByte = new byte[2];
        input.read(discriptIndexByte);

        int fieldDiscriptNum = PrintHex.byteToInt2(discriptIndexByte);
        ConstantBean fieldDiscriptbean = contStant.get(fieldDiscriptNum);

        System.out.println("当前属性的类型信息为:" + new String(fieldDiscriptbean.getValue()));

        // 进行读取attributes_count u2
        byte[] attributesCountIndex = new byte[2];
        input.read(attributesCountIndex);

        int fieldNum = PrintHex.byteToInt2(attributesCountIndex);

        System.out.println("当前的属性索引表的数量:" + fieldNum);

        // 如果属性的数据不为空，则进行属性的读取操作
        if (0 != fieldNum) {

            for (int i = 0; i < fieldNum; i++) {

                // 进行attibute信息的读取操作
                byte[] attributeNameIndexByte = new byte[2];
                input.read(attributeNameIndexByte);
                int attNameIndex = PrintHex.byteToInt2(attributeNameIndexByte);
                ConstantBean attriName = contStant.get(attNameIndex);
                System.out.println("当前属性扩展的名字:" + new String(attriName.getValue()));

                // 读取attribute_length u4
                byte[] attributeLength = new byte[4];
                input.read(attributeLength);
                int attLengthIndex = PrintHex.byteToInt2(attributeNameIndexByte);
                System.out.println("当前属性的长度信息为 ：" + attLengthIndex);

                // info[attribute_length] u1
                byte[] infoAttributInfo = new byte[2];
                input.read(infoAttributInfo);
                System.out.println("内容信息:" + new String(infoAttributInfo));
            }
        }

    }

    @Override
    public boolean rollBackInvoke(SeqLinkedList seqList) throws Exception {
        return seqList.rollExec();
    }

}
